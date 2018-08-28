//login.js
//获取应用实例
var app = getApp();
//引入starscore.js
var starscore = require("../../templates/starscore/starscore.js");
var getGoods = require("../../templates/getGoods/getGoods.js");
Page({
  data: {
    remind: '加载中',
    angle: 0,
    goodList:{
      good:null,
      goodnum:null
    }
  },
  
  //页面初始化函数
  onLoad: function (options) {
    app.globalData.merchantId = options.merchantId
    var that = this
    //获取商城名称
    that.getMallName()   
    //获取所有顶层分类
    getGoods.getCategories()
    //获取商品分数
    that.getScore()
    //最低充值金额
    that.getRechargeMin()
    // 获取砍价设置
    that.getKanjia()
  },

  //获取商城名称
  getMallName:function(){
	  var that = this;
	  wx.request({
      url: 'https://www.shuguomall.club/lorchard-api/merchant/'+app.globalData.merchantId,
      success: function (res) {
        app.globalData.merchant = res.data.data
        //设置本地缓存mallName的值(data数据)
        if (res.data.code == 0) {
          wx.setStorageSync('mallName', res.data.data.name);
          that.setData({
            mallName: res.data.data.name
          })
        }
      },
    })
  },

  
  //获取商品分数
  getScore:function(){
	  wx.request({
      url: 'https://api.it120.cc/godream/score/send/rule',
      data: {
        code: '1'
      },
      success: function (res) {
        if (res.data.code == 0) {
          app.globalData.order_reputation_score = res.data.data[0].score;
        }
      }
    })
  },
  
  //获取最低充值金额
  getRechargeMin:function(){
	  wx.request({
      url: 'https://api.it120.cc/godream/config/get-value',
      data: {
        key: 'recharge_amount_min'
      },
      success: function (res) {
        if (res.data.code == 0) {
          app.globalData.recharge_amount_min = res.data.data.value;
        }
      }
    })
  },
  
  //获取砍价设置
  getKanjia:function(){
	  wx.request({
      url: 'https://api.it120.cc/godream/shop/goods/kanjia/list',
      data: {},
      success: function (res) {
        if (res.data.code == 0) {
          app.globalData.kanjiaList = res.data.data.result;
        }
      }
    })
  },
  
  
            
  //进入授权登录的首页
  goToIndex: function () {
    wx.navigateTo({
      url: '/pages/authorize/index',
    });
  },

  onShow: function () {

  },
  
  onReady: function () {
    var that = this;
    setTimeout(function () {
      that.setData({
        remind: ''
      });
    }, 1000);
    wx.onAccelerometerChange(function (res) {
      var angle = -(res.x * 30).toFixed(1);
      if (angle > 14) { angle = 14; }
      else if (angle < -14) { angle = -14; }
      if (that.data.angle !== angle) {
        that.setData({
          angle: angle
        });
      }
    });
  },
});