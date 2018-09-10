//index.js
//获取应用实例
var app = getApp()
var util = require('../../utils/util.js');
var api = require('../../config/api.js');
Page({
  data: {
    addressList:[]
  },

  selectTap: function (e) {
    var id = e.currentTarget.dataset.id;
    util.requestGet({
      url: api.AddressUpdateState,
      data:{
        id: id,
        memberId: app.globalData.memberId
      },
      success: (res) => {
        wx.navigateBack({})
      }
    })
  },

  addAddess : function () {
    wx.navigateTo({
      url:"/pages/address-add/index?memberId="+app.globalData.memberId
    })
  },
  
  editAddess: function (e) {
    wx.navigateTo({
      url: "/pages/address-add/index?id=" + e.currentTarget.dataset.id + "&memberId=" + app.globalData.memberId
    })
  },
  
  onLoad: function () {
    console.log('onLoad')
  },

  onShow : function () {
    this.initShippingAddress();
  },

  initShippingAddress: function () {
    var that = this;
    util.requestGet({
      url: api.AddressList,
      data: {
        memberId: app.globalData.memberId
      },
      success: (res) => {
        if (res.code === 0) {
          that.setData({
            addressList: res.data
          });
        } else {
          that.setData({
            addressList: null
          });
        }
      }
    })
  }
})
