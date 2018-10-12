var app = getApp();
//引入js
var requireList = require("../../require.js");
var starscore = requireList.starscore;
var getGoods = requireList.getGoods;
var canvasClock = requireList.canvasClock;
var util = requireList.util;
var api = requireList.api;
Page({
  data:{
    onLoadStatus: true,
    indicatorDots: true,
    loadingStatus: false, // loading
    loadingFinish: false,
    shopLogo:"",
    shopPrompt: [],
    shopDelivery: [],
    swiperCurrent: 0,
    selectCurrent: 0,
    categories: [],
    activeCategoryId: null,
    goods: [],
    goodsList: [],
    goodsListCurrent: [],
    scrollTop: 0,
    page: 1,
    pageSize: 5000,
    classifyViewed: null,
    width: 0,
    height: 0,
    movable: {
      text: ''
    },
  },

  onLoad: function (options) {
    var that = this
    //设置标题
    wx.setNavigationBarTitle({
      title: wx.getStorageSync('mallName')
    })
    that.getRefreshData()
    //获取系统信息  
    wx.getSystemInfo({
      //获取系统信息成功，将系统窗口的宽高赋给页面的宽高  
      success: function (res) {
        that.width = res.windowWidth / 2.9  //2.6
        that.height = res.windowWidth / 2.9  //2.6
      }
    })
  },

  getRefreshData:function(){
    var that = this
    //设置初始参数
    that.setData({
      categories: app.globalData.categories,
      goods: app.globalData.goods,
      goodsList: app.globalData.goodsList,
      onLoadStatus: app.globalData.onLoadStatus,
      activeCategoryId: app.globalData.activeCategoryId,
      mallName: wx.getStorageSync('mallName')
    })
    //被选中的类别
    for (var i = 0; i < that.data.categories.length; i++) {
      if (that.data.activeCategoryId === that.data.categories[i].id) {
        that.setData({
          classifyViewed: that.data.categories[i].id,
          scrolltop: 0,
          goodsListCurrent: that.data.goodsList[i],
        })
      }
    }
    //获取商家logo
    //that.getShopLogo();
    //获取温馨提示
    //that.getPrompt();
    //获取关于我们
    //that.getDelivery();
    if (!that.data.onLoadStatus) {
      that.showDialog('.onLoad-err')
    }
  },

  onReady: function () {
    var that = this
    //调用canvasClock函数  
    canvasClock.canvasClock(that)
    //对canvasClock函数循环调用  
    this.interval = setInterval(function () { canvasClock.canvasClock(that)}, 1000)
  },

  onPullDownRefresh: function () {
    var that = this
    wx.showNavigationBarLoading()
    that.reLoad()
    wx.hideNavigationBarLoading() //完成停止加载
    wx.stopPullDownRefresh() //停止下拉刷新
  },

  onShareAppMessage: function () {
    return {
      title: wx.getStorageSync('mallName') + app.globalData.shareProfile,
      path: '/pages/classification/index',
      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },

  //获取被选中的类别的商品
  tapClassify: function (e) {
    var that = this;
    var id = e.target.dataset.id;
    if (id === that.data.classifyViewed) {
      that.setData({
        scrolltop: 0,
      })
      return;
    } else {
      that.setData({
        classifyViewed: id,
      });
      console.log('id:', that.data.classifyViewed)
      for (var i = 0; i < that.data.categories.length; i++) {
        if (id === that.data.categories[i].id) {
          that.setData({
            page: 1,
            scrolltop: 0,
            goodsListCurrent: that.data.goodsList[i]
          })
        }
      }
    }
  },

  //事件处理函数
  toDetailsTap: function (e) {
    console.log(e)
    wx.navigateTo({
      url: "/pages/goods-details/index?id=" + e.currentTarget.dataset.id
    })
  },

  //下拉刷新获取商品
  reLoad: function () {
    var that = this
    getGoods.getCategories()
    that.getRefreshData()
  },

  getShopLogo:function(){
    // 设置商家logo
    this.setData({
      shopLogo: app.globalData.merchant.shopLogo,
    })
  },

  getPrompt: function () {
    // 温馨提示 滑动标题
    this.setData({
      shopPrompt: app.globalData.merchant.shopPrompt,            
    })
  },

  getDelivery: function () {
    var that = this
    //获取关于我们Title
    this.setData({
      shopDelivery: app.globalData.merchant.shopDelivery,
    })
  },

  //页面卸载，清除画布绘制计时器  
  onUnload: function () {
    clearInterval(this.interval)
  },
  showDialog: function (dialogName) {
    let dialogComponent = this.selectComponent(dialogName)
    dialogComponent && dialogComponent.show();
  },
  hideDialog: function (dialogName) {
    let dialogComponent = this.selectComponent(dialogName)
    dialogComponent && dialogComponent.hide();
  },
  onConfirm: function () {
    this.hideDialog('.onLoad-err')
    this.reLoad()
  },
  onCancel: function () {
    this.hideDialog('.onLoad-err')
  }
})