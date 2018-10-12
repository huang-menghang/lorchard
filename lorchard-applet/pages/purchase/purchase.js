const app = getApp();
var requireList = require("../../require.js");
var util = requireList.util;
var api = requireList.api;
Page({
  data: {


  },

  onLoad: function(options) {
    var that =this;
    var merchantId = app.globalData.merchantId
    util.requestGet({
      url: api.GroupGoodsUrl,
      data: {
        merchantId: merchantId
      },
      success:function(res){
        console.log(res)
        that.setData({
          activitylist: res.data
        })
      }
    })
  },

  createOrder: function (e) {
    var id = e.currentTarget.dataset.id
    console.log("id",id);
    wx.navigateTo({
      url: "/pages/purchase/immediately/immediately?id=" +id 
    })
  }
})
