//index.js
//获取应用实例
var starscore = require("../../templates/starscore/starscore.js");
var WxSearch = require('../../templates/wxSearch/wxSearch.js');
var app = getApp()
Page({
  data: {
    page:1,
    pageSize:10000,
    keyword:'',
    loadingHidden: false, // loading
    userInfo: {},
    categories: [],
    goods: [],
    scrollTop: 0,
    loadingMoreHidden: false,
    hasNoCoupons: true,
    couponsTitlePicStr:'',
    coupons: [],
    networkStatus: true, //正常联网
    couponsStatus: 0,
    getCoupStatus: -1
  },

  //初始化
  onLoad: function () {
    var that = this
    //初始化的时候渲染wxSearchdata 第二个为你的search高度
    WxSearch.init(that, 43, app.globalData.hotGoods);
    //获取全部商品名称，做为智能联想输入库
    WxSearch.initMindKeys(app.globalData.goodsName);  
    that.getCouponsTitlePicStr();
    that.getCoupons();
    console.log("hasNoCoupons", that.data.hasNoCoupons)
    if (that.data.hasNoCoupons){
      setTimeout(()=>{
        that.setData({
          display: "display:none"
        })
      },500)
    }
  },

  //下拉刷新
  onPullDownRefresh: function () {
    var that = this
    wx.showNavigationBarLoading()
    that.onLoad()
    wx.hideNavigationBarLoading() //完成停止加载
    wx.stopPullDownRefresh() //停止下拉刷新
  },

  //分享
  onShareAppMessage: function () {
    return {
      title: wx.getStorageSync('mallName') + '——' + app.globalData.shareProfile,
      path: '/pages/finder/index',
      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },

  //显示
  onShow: function () {
    var that = this;
    
  },
  
  //获取优惠券标题图片
  getCouponsTitlePicStr: function () {
    var that = this;
    //  获取商城名称
    wx.request({
      url: 'https://api.it120.cc/' + app.globalData.subDomain + '/config/get-value',
      data: {
        key: 'couponsTitlePicStr'
      },
      success: function (res) {
        if (res.data.code == 0) {
          that.setData({
            couponsTitlePicStr: res.data.data.value
          })
          console.log('couponsTitlePicStr:', res.data.data.value)
        }
      },
    })
  },

  //前往商品详情
  toDetailsTap: function (e) {
    wx.navigateTo({
      url: "/pages/goods-details/index?id=" + e.currentTarget.dataset.id
    })
  },

  //前往搜索
  toSearch: function (e) {
    console.log(e)
    wx.navigateTo({
      url: '/pages/search/index?keyword=' + this.data.keyword,
    })
    console.log(e);
  },

  //获取商品集合
  getGoodsList: function () {
    var that = this;
    var goods = app.globalData.goods
    that.setData({
      goods: app.globalData.goods,
      loadingMoreHidden: true
    });
    if (goods.length == 0) {
      that.setData({
        loadingMoreHidden: false,
        prePageBtn: false,
         nextPageBtn: true,
        toBottom: true
      });
      return;
    }
  },

  //获取优惠券
  getCoupons: function () {
    var that = this;
    wx.request({
      url: 'https://api.it120.cc/' + app.globalData.subDomain + '/discounts/coupons',
      data: {
        type: ''
      },
      success: function (res) {
        if (res.data.code == 0) {
          that.setData({
            hasNoCoupons: false,
            coupons: res.data.data,
            couponsStatus: 1
          });
          setTimeout(() => {
            that.setData({
              couponsStatus: -1
            })
          }, 1500)
        } else if (res.data.code == 700) {
          that.setData({
            hasNoCoupons: true,
            coupons: res.data.data,
            couponsStatus: 2
          });
          setTimeout(() => {
            that.setData({
              couponsStatus: -1
            })
          }, 1500)
        }
      },
      fail: function(res) {
        that.setData({
          networkStatus: false
        })
        setTimeout(() => {
          that.setData({
            networkStatus: true
          })
        }, 1500)
      }
    })
  },

  //点击优惠券事件
  gitCoupon: function (e) {
    var that = this;
    wx.request({
      url: 'https://api.it120.cc/' + app.globalData.subDomain + '/discounts/fetch',
      data: {
        id: e.currentTarget.dataset.id,
        token: wx.getStorageSync('token')
      },
      success: function (res) {
        if (res.data.code == 20001 || res.data.code == 20002) {
          that.setData({
            getCoupStatus: 0
          })
          setTimeout(() => {
            that.setData({
              getCoupStatus: -1
            })
          }, 1500)
          return;
        }
        if (res.data.code == 20003) {
          that.setData({
            getCoupStatus: 2
          })
          setTimeout(() => {
            that.setData({
              getCoupStatus: -1
            })
          }, 1500)
          return;
        }
        if (res.data.code == 30001) {
          that.setData({
            getCoupStatus: 3
          })
          setTimeout(() => {
            that.setData({
              getCoupStatus: -1
            })
          }, 1500)
          return;
        }
        if (res.data.code == 20004) {
          that.setData({
            getCoupStatus: 4
          })
          setTimeout(() => {
            that.setData({
              getCoupStatus: -1
            })
          }, 1500)
          return;
        }
        if (res.data.code == 0) {
          that.setData({
            getCoupStatus: 1
          })
          setTimeout(() => {
            that.setData({
              getCoupStatus: -1
            })
          }, 1500)
        } else {
          wx.showModal({
            title: '错误',
            content: res.data.msg,
            showCancel: false
          })
        }
      }
    })
  },

  couponDisplay:function(){
    var that = this
    that.setData({
      display:"display:none"
    })
  },

  //////////////////////////////////////
  wxSearchFn: function (e) {
    var that = this
    console.log("wxSearchFn")
    that.toSearch();
    WxSearch.wxSearchAddHisKey(that);
  },

  wxSearchInput: function (e) {
    var that = this
    WxSearch.wxSearchInput(e, that);
    console.log("wxSearchInput")
    that.setData({
      keyword: that.data.wxSearchData.value,
    })
  },

  wxSerchFocus: function (e) {
    var that = this
    console.log("wxSerchFocus")
    WxSearch.wxSearchFocus(e, that);
  },
  wxSearchBlur: function (e) {
    var that = this
    console.log("wxSearchBlur")
    WxSearch.wxSearchBlur(e, that);
  },

  wxSearchKeyTap: function (e) {
    var that = this
    WxSearch.wxSearchKeyTap(e, that);
    console.log("wxSearchKeyTap")
    that.setData({
      keyword: that.data.wxSearchData.value,
    })
  },
  wxSearchDeleteKey: function (e) {
    var that = this
    WxSearch.wxSearchDeleteKey(e, that);
  },
  wxSearchDeleteAll: function (e) {
    var that = this;
    WxSearch.wxSearchDeleteAll(that);
  },
  wxSearchTap: function (e) {
    var that = this
    WxSearch.wxSearchHiddenPancel(that);
  }
})
