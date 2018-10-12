//login.js
//获取应用实例
var app = getApp();
//引入starscore.js
var requireList = require("../../require.js");
var starscore = requireList.starscore;
var getGoods = require("../../templates/getGoods/getGoods.js");
var util = requireList.util;
var api = requireList.api;
Page({
  data: {
    remind: '加载中',
    angle: 0,
    goodList: {
      good: null,
      goodnum: null
    }
  },

  //页面初始化函数
  onLoad: function(options) {
    console.log("app.globalData.merchantId", options.scene)
    if (options.scene != undefined){
      app.globalData.merchantId = options.scene
    }
    var that = this
    //获取商城名称
    that.getMallName()
    //获取所有顶层分类
    getGoods.getCategories()
   },

  //获取商城名称
  getMallName: function() {
    var that = this;
    util.requestGet({
      url: api.MerchantUrl,
      data: {
        merchantId: app.globalData.merchantId
      },
      success: function(res) {
        app.globalData.merchant = res.data
        //设置本地缓存mallName的值(data数据)
        if (res.code == 0) {
         wx.setStorageSync('mallName', res.data.name);
          that.setData({
            mallName: res.data.name
          })
        }
      }
    })
  },

  //判断是都可以使用授权登录
  canILogin: function(e) {
    console.log("wx----->" + wx.canIUse('button.open-type.getUserInfo'));
    wx.removeStorageSync('appletMember');
    if (!wx.canIUse('button.open-type.getUserInfo')) { // 对应的功能就是通过按钮获取用
      wx.showModal({
        title: '提示',
        content: '请升级微信版本',
        confirmColor: "#29aceb",
        showCancel: false
      });
    }
  },

  //是否同意授权登录
  bindGetUserInfo: function(e) {
    var that = this;
    console.log("111111");
    if (!e.detail.userInfo) {
      console.log("用户拒绝授权")
      wx.showModal({
        title: '警告',
        confirmText: '确定',
        confirmColor: '#37C31A',
        content: '若不授权微信登录，则无法使用小果源；请重新点击进入店铺进行授权',
        success: function(res) {
          return;
        }
      })
    } else {
      wx.setStorageSync('userInfo' + app.globalData.merchantId, e.detail.userInfo)
      util.toLogin(that);
    }
  },

  onShow: function() {

  },

  onReady: function() {
    var that = this;
    setTimeout(function() {
      that.setData({
        remind: ''
      });
    }, 1000);
    wx.onAccelerometerChange(function(res) {
      var angle = -(res.x * 30).toFixed(1);
      if (angle > 14) {
        angle = 14;
      } else if (angle < -14) {
        angle = -14;
      }
      if (that.data.angle !== angle) {
        that.setData({
          angle: angle
        });
      }
    });
  },
});