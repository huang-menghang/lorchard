var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var app = getApp();
Page({
  data: {
    array: ['请选择反馈类型', '商品相关', '物流状况', '客户服务', '优惠活动', '功能异常', '产品建议', '其他'],
    index: 0,
    mobile: '',
  },
  bindPickerChange: function(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index: e.detail.value
    })
  },
  clearMobileNumber: function() {
    this.setData({
      //更新页面input框显示
      mobile: ''
    })
  },
  formSubmit: function(e) {
    var that=this
    console.log(that.data.array[parseInt(e.detail.value.index)])
    util.requestJson({
      url: api.MerchantFeedbackUrl,
      data: {
        merchantId: app.globalData.merchantId,
        memberId: app.globalData.memberId,
        feedbackType:that.data.array[parseInt(e.detail.value.index)],
        message: e.detail.value.message,
        mobile: e.detail.value.mobile
      },
      success: function(res) {
        if (res.data == 0) {
          wx.showToast({
            title: '提交失败！！！',
            icon: 'loading',
            duration: 1500
          })
        } else {      
          wx.showToast({
            title: '提交成功！！！',
            icon: 'success',
            duration: 1000
          })
        }
      }
    })
  },

  onLoad: function(options) {},

  onReady: function() {

  },
  onShow: function() {

  },

  onHide: function() {
    // 页面隐藏

  },

  onUnload: function() {
    // 页面关闭
  }

})