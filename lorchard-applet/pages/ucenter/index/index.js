const app = getApp()

Page({
  data: {
    aboutUsTitle: '',
    aboutUsContent: '',
    servicePhoneNumber: '',
    balance: 0,
    freeze: 0,
    score: 0,
    score_sign_continuous: 0,
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
    // this.getUserApiInfo();
    // this.getUserAmount();
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
      phoneNumber: "800-100-6000",
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