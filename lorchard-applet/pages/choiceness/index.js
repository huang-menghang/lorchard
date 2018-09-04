//index.js
//引入starscore.js
var starscore = require("../../templates/starscore/starscore.js");
var util = require('../../utils/util.js');
var api = require('../../config/api.js');
//获取应用实例
var app = getApp();
//注册当前页面
Page({
  //页面初始数据
  data: {
    indicatorDots: true,
    autoplay: true,
    interval: 3500,
    duration: 1500,
    // loading中
    loadingMore: false,
    //到底啦
    isEnd: false,
    userInfo: {},
    swiperCurrent: 0,
    recommendTitlePicStr: '../../images/index/recommend.png',
    categories: [],
    activeCategoryId: 0,
    //按类别的商品
    goodsList: [],
    //推荐商品
    recommendGoods: [],
    //显示的推荐商品，为了缓解网络加载压力设置每次加载15个推荐商品
    recommendGoodsShow: [],
    banners: [],
    showNoBanners: false,
    loadingMoreHidden: true,
    //加载商品时的页数默认为1开始,在app页面加载
    page: [],
    //每页商品数设置为5000确保能全部加载商品，在app页面加载
    pageSize: [],
    stv: {
      windowWidth: 0,
      windowHeight: 0,
    },
    height: []
  },

  //页面加载函数
  onLoad: function() {
    //获取page
    var that = this
    util.requestGet({
      url: api.FirstVisitUrl,
      data: {
        merchantId: app.globalData.merchantId,
        memberId: app.globalData.memberId
      },
      success: function (res) {
        
      }
    })
    that.refershInfo();
  },

  refershInfo: function() {
    //获取page
    var that = this
    //设置data数据
    that.setData({
      goodsList: app.globalData.goodsList,
      pageSize: app.globalData.pageSize,
      page: app.globalData.page,
      recommendGoodsShow: []
    })
    //设置页面标题
    wx.setNavigationBarTitle({
      title: '欢迎来到' + wx.getStorageSync('mallName'),
    })
    //调用获取轮播图函数
    that.getBanners();
    //调用获取公告函数
    that.getNotice();
    //调用获取推荐商品函数
    that.getAppRecommendGoodsList()
    //加载显示商品函数
    that.getRGshow()
    //try...catch
    try {
      //获取用户手机配置信息
      var res = wx.getSystemInfoSync()
      console.log('system information', res)
      //如果存在公告
      if (that.data.noticeList) {
        //设置stv,height属性
        that.setData({
          stv: {
            windowsWidth: res.windowWidth,
            windowsHeight: res.windowHeight
          },
          height: (750 / res.windowWidth) * res.windowHeight - 100
        })
        //否则另行设置
      } else {
        that.setData({
          stv: {
            windowsWidth: res.windowWidth,
            windowsHeight: res.windowHeight
          },
          height: (750 / res.windowWidth) * res.windowHeight
        })
      }

    } catch (e) {

    }
    //打印设置完的属性
    console.log('stv', that.data.stv, that.data.height)
  },

  //页面显示函数
  onShow: function() {
    var that = this
  },

  //下拉刷新函数
  onPullDownRefresh: function() {
    //获取page对象
    var that = this
    //设置属性
    that.setData({
      loadingMore: true,
      isEnd: false
    })
    //显示导航条加载动画
    wx.showNavigationBarLoading()
    //调用页面加载函数
    that.refershInfo()
    wx.hideNavigationBarLoading() //完成停止加载
    wx.stopPullDownRefresh() //停止下拉刷新
  },

  //转发按钮触发
  onShareAppMessage: function() {
    //返回一个对象
    return {
      //转发标题
      title: wx.getStorageSync('mallName') + '——' + app.globalData.shareProfile,
      //转发路径
      path: '/pages/finder/index',
      success: function(res) {
        // 转发成功
      },
      fail: function(res) {
        // 转发失败
      }
    }
  },

  //用户上拉触底
  onReachBottom: function() {
    //获取page对象
    var that = this
    that.setData({
      autoplay: false
    })
    //调用商品显示函数
    that.getRGshow()
  },

  //事件处理函数
  //轮播图函数
  swiperchange: function(e) {
    //console.log(e.detail.current)
    //设置数据index
    this.setData({
      swiperCurrent: e.detail.current
    })
  },

  //点击商品进入详情页
  toDetailsTap: function(e) {
    //保留原页面并跳转
    wx.navigateTo({
      url: "/pages/goods-details/index?id=" + e.currentTarget.dataset.id
    })
  },

  //点击轮播图进入详情页
  tapBanner: function(e) {
    //跳转的id不为0
    if (e.currentTarget.dataset.id != 0) {
      //保留原页面并跳转
      wx.navigateTo({
        url: "/pages/goods-details/index?id=" + e.currentTarget.dataset.id
      })
    }
  },

  // 从app页面的goodsList中获取推荐商品
  getAppRecommendGoodsList: function() {
    //获取page对象
    var that = this
    //获取app中定义的商品数组
    var goods = app.globalData.goods
    //定义推荐商品数组
    var recommendGoods = []
    //遍历商品数组
    for (let i = 0; i < goods.length; i++) {
      //如果商品为推荐商品则添加到推荐商品数组
      if (goods[i].recommend === 1) {
        recommendGoods.push(goods[i])
      }
    }
    //设置属性
    that.setData({
      recommendGoods: recommendGoods
    })
    console.log('recommendGoods:', recommendGoods)
  },

  //获取公告信息
  getNotice: function() {
    var that = this;
    util.requestGet({
      url: api.MerchantNoticeUrl,
      data: {
        merchantId: app.globalData.merchantId
      },
      success: function(res) {
        that.setData({
          noticeList: res.data
        });
        app.globalData.notices = res.data
      }
    })
  },

  //获取轮播图
  getBanners: function() {
    var that = this
    //获取轮播图
    util.requestGet({
      url: api.MerchantBannerUrl,
      data: {
        merchantId: app.globalData.merchantId
      },
      success: function(res) {
        if (res.code === 0) {
          that.setData({
            banners: res.data
          });
        } else {
          that.setData({
            showNoBanners: true
          })
          that.showPopup('.banners_warn_Popup')
        }
      }
    })
  },

  //显示推荐商品
  getRGshow: function() {
    //获取page对象
    var that = this;
    //isEnd则返回
    if (that.data.isEnd) {
      console.log('isEnd, return...')
      that.setData({
        autoplay: true
      })
      return;
    }
    console.log('notEnd, continue')
    //设置还有更多
    that.setData({
      loadingMore: true
    })
    //一次加载数量
    var pageSize = 10; //一次加载完所有的推荐商品，避免minui显示问题that.data.recommendGoods.length
    //推荐商品显示集合
    var recommendGoodsShow = that.data.recommendGoodsShow;
    //集合的长度
    var rgShowLen = recommendGoodsShow.length;
    console.log('rgShowLen', rgShowLen)
    //显示集合+加载数量<=推荐商品数量
    if (rgShowLen + pageSize <= that.data.recommendGoods.length) {
      //向推荐商品显示集合加入推荐商品
      for (var i = rgShowLen; i < rgShowLen + pageSize; i++) {
        recommendGoodsShow.push(that.data.recommendGoods[i])
      }
      //设置推荐商品显示集合属性
      that.setData({
        recommendGoodsShow: recommendGoodsShow
      })
      //若没加载完设置还有更多
      if (recommendGoodsShow.length < that.data.recommendGoods.length) {
        that.setData({
          loadingMore: true,
        })
        //否则设置结束
      } else {
        that.setData({
          loadingMore: false,
          isEnd: true
        })
      }
      console.log(recommendGoodsShow.length, '-=-=-=-=-=-=-=')
      //若推荐商品显示集合长度<推荐商品长度
    } else if (rgShowLen < that.data.recommendGoods.length) {
      //向集合中添加商品
      for (var i = rgShowLen; i < that.data.recommendGoods.length; i++) {
        recommendGoodsShow.push(that.data.recommendGoods[i])
      }
      //虚拟加载特效
      //设置属性 结束加载
      that.setData({
        recommendGoodsShow: recommendGoodsShow,
        loadingMore: false,
        isEnd: true
      })
      console.log(recommendGoodsShow.length, '-=-=-=-=-=-=-=')
    }
  },

  //调用组件
  showPopup(PopupClassname) {
    //调用组件 选择当前页面的符合条件组件
    let popupComponent = this.selectComponent(PopupClassname);
    popupComponent && popupComponent.show();
  }
})