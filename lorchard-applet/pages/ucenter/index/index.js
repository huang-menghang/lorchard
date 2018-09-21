const app = getApp()
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
Page({
  data: {
    aboutUsTitle: '',
    aboutUsContent: '',
    servicePhoneNumber: '',
    availableScore: 0,
    todayScore: 1,
    totalDay: 0,
    status: 0,
    iconSize: 45,
    iconColor: '#999999'
  },

  onPullDownRefresh: function() {
    var that = this
    wx.showNavigationBarLoading()
    that.onShow()
    wx.hideNavigationBarLoading() //完成停止加载
    wx.stopPullDownRefresh() //停止下拉刷新
  },

  onLoad() {
    let that = this;
    let userInfo = wx.getStorageSync('userInfo' + app.globalData.merchantId)
    that.setData({
      userInfo: userInfo,
      version: app.globalData.version
    })
  },

  onShow() {
    this.getUserAmount();
    // this.getUserApiInfo();
    // this.checkScoreSign();
    // this.getAboutUs();
    // this.getservicePhoneNumber();
  },

  aboutUs: function() {
    var that = this
    wx.showModal({
      title: "小果源商城",
      content: "欢迎各位的到来",
      showCancel: false
    })
  },

  makePhoneCall: function() {
    var that = this;
    wx.makePhoneCall({
      phoneNumber: "10086",
      success: function(res) {},
      fail: function(res) {
        wx.showModal({
          title: '呼叫失败',
          content: '请稍后再试',
          showCancel: false,
        })
      },
      complete: function(res) {},
    })
  },

  getUserAmount: function () {
    var that = this;
    util.requestGet({
      url: api.MemberPointUrl,
      data: {
        merchantId: app.globalData.merchantId,
        memberId: app.globalData.memberId,
        availableScore: that.data.availableScore,
        todayScore: that.data.todayScore,
        totalDay: that.data.totalDay,
        status: that.data.status
      },
      success: function (res) {
       
        if (res.code == 0) {
          that.setData({
            availableScore: res.data.availableScore,
            todayScore: res.data.todayScore,
            totalDay: res.data.totalDay,
            status: res.data.status
          });
        }
      } 
    })
  },

  scoresign: function () {
    var that = this;
    console.log("status",that.data.status)
    util.requestGet({
      url: api.PointUpdateUrl,
      data: {
        merchantId: app.globalData.merchantId,
        memberId: app.globalData.memberId,
        availableScore: that.data.availableScore,
        todayScore: that.data.todayScore,
        totalDay: that.data.totalDay,
        status: that.data.status
      },
      success: function (res) {
        if (res.code == 0 && (typeof res.data) != "string") {
          that.setData({
            availableScore: res.data.availableScore,
            todayScore: res.data.todayScore,
            totalDay: res.data.totalDay,
            status: res.data.status
          })
        } else {
          wx.showModal({
            title: '已签到',
            content: res.data,
            showCancel: false
          })
        }
      }
    })
  },

  recharge: function() {
    wx.navigateTo({
      url: "/pages/recharge/index"
    })
  },

  withdraw: function() {
    wx.navigateTo({
      url: "/pages/withdraw/index"
    })
  }
})