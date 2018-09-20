//login.js
//获取应用实例
var app = getApp();
//引入starscore.js
var starscore = require("../../templates/starscore/starscore.js");
var getGoods = require("../../templates/getGoods/getGoods.js");
var util = require('../../utils/util.js');
var api = require('../../config/api.js');
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
    app.globalData.merchantId = options.scene
    console.log("app.globalData.merchantId", options.scene)
    var that = this
    //获取商城名称
    that.getMallName()
    //获取所有顶层分类
    getGoods.getCategories()


  //   //建立连接
  //   wx.connectSocket({
  //     url: "ws://localhost:9000",
  //   })

  //   //连接成功
  //   wx.onSocketOpen(function () {
  //     console.log("连接成功")
  //     //发送消息
  //     wx.sendSocketMessage({
  //       data: '{"messageConent":"stack","messageType":"ON_LINE","fromMerchantId":"1"}',
  //     })
  //   })

  //   //接受消息
  //   wx.onSocketMessage(function (data) {
  //     console.log(data);
  //   })
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
         // wx.setStorageSync('mallName', res.data.name);
          that.setData({
            //mallName: res.data.name
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
      this.login();
    }
  },

  //登录验证
  login: function() {
    let that = this;
    let merchantId = app.globalData.merchantId
    //获取用户token信息
    let token = wx.getStorageSync('token' + merchantId);
    //如果存在token 去验证token是否过期
    if (token) {
      app.globalData.memberId = wx.getStorageSync('memberId' + merchantId)
      util.requestGet({
        url: api.CheckTokenUrl,
        data: {
          token: token
        },
        success: function(res) {
          console.log(res)
          if (res.code != 0) {
            //token过期 移除缓存token
            wx.removeStorageSync('token' + merchantId)
            //递归调用登录
            that.login();
          } else {
            //前往店铺首页
            wx.switchTab({
              url: '/pages/choiceness/index',
            })
          }
        }
      })
      return;
    }
    //调用微信的登录验证
    wx.login({
      success: function(res) {
        util.requestGet({
          url: api.MemberLoginUrl,
          data: {
            code: res.code
          },
          success: function(res) {
            if (res.data.id == null) {
              // 去注册
              app.globalData.openid = res.data.openid
              console.log("openid", app.globalData.openid)
              that.registerUser();
              return;
            }
            if (res.code != 0) {
              // 登录错误
              wx.hideLoading();
              wx.showModal({
                title: '提示',
                content: '无法登录，请重试',
                showCancel: false
              })
              return;
            }
            app.globalData.memberId = res.data.id
            wx.setStorageSync('memberId' + merchantId, res.data.id)
            wx.setStorageSync('token' + merchantId, res.data.token)
            //前往店铺首页
            wx.switchTab({
              url: '/pages/choiceness/index',
            })
          }
        })
      }
    })
  },
  //注册
  registerUser: function() {
    var that = this;
    wx.login({
      success: function(res) {
        var code = res.code; // 微信登录接口返回的 code 参数，下面注册接口需要用到
        wx.getUserInfo({
          success: function(res) {
            console.log("registerRes", res)
            console.log("openidReg", app.globalData.openid)
            var userinfo = res.userInfo
            //下面开始调用注册接口
            util.requestPost({
              url: api.MemberRegisterUrl,
              data: {
                avatar: userinfo.avatarUrl,
                gender: userinfo.gender,
                nicknameStr: userinfo.nickName,
                openid: app.globalData.openid,
                language: userinfo.language,
                country: userinfo.country,
                province: userinfo.province,
                city: userinfo.city
              },
              success: (res) => {
                app.globalData.memberId = res.data.id
                wx.hideLoading();
                that.login();
              }
            })
          }
        })
      }
    })
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