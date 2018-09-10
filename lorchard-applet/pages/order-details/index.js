var app = getApp();
var util = require('../../utils/util.js');
var api = require('../../config/api.js');
Page({
  data: {
    orderNo: 0,
    statusSteps: [],
  },

  onLoad: function(e) {
    var orderNo = e.orderNo;
    this.data.orderNo = orderNo;
    this.setData({
      orderNo: orderNo
    });
  },

  onShow: function() {
    var that = this;
    util.requestGet({
      url: api.OrderByNo,
      data: {
        orderNo: that.data.orderNo
      },
      success: function(res) {
        wx.hideLoading();
        if (res.code != 0) {
          wx.showModal({
            title: '错误',
            content: '',
            showCancel: false
          })
          return;
        }
        that.setData({
          orderDetail: res.data
        });
        console.log("orderDetail:", that.data.orderDetail)
        that.updateStatusSteps(res.data)
      }
    })
  },

  wuliuDetailsTap: function(e) {
    var orderId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: "/pages/wuliu/index?id=" + orderId
    })
  },

  confirmBtnTap: function(e) {
    var that = this;
    var orderNo = e.currentTarget.dataset.orderno;
    wx.showModal({
      title: '确认您已收到商品？',
      content: '',
      success: function(res) {
        if (res.confirm) {
          wx.showLoading();
          util.requestGet({
            url: api.ConfirmOrder,
            data: {
              orderNo: orderNo
            },
            success: function (res) {
              if (res.code == 0) {
                that.onShow();
              }
            }
          })
        }
      }
    })
  },

  updateStatusSteps: function(orderDetail) {
    var that = this;
    var currents = [false, false, false, false];
    currents[orderDetail.orderStatus] = true
    var dones = [];
    var texts = ['待支付', '待发货', '待收货','已完成'];
    var descs=[];
    switch (orderDetail.orderStatus) {
      case 0:
        dones = [true, false, false,false]
        descs = ['等待中...','','','']
        that.setData({
          statusSteps: that.setStatusSteps(currents, dones, texts, descs)
        })
        break;
      case 1:
        dones = [true, true, false,false]
        descs = ['成功', '等待中...','','']
        that.setData({
          statusSteps: that.setStatusSteps(currents, dones, texts, descs)
        })
        break;
      case 2:
        dones = [true, true, true, false]
        descs = ['成功', '成功', '等待中...','']
        that.setData({
          statusSteps: that.setStatusSteps(currents, dones, texts, descs)
        })
        break;
      case 3:
        that.setData({
          statusSteps:[that.setStatusStep(true, true, '退款中', "正在退款")],
        })
        break;
      case 4:
        that.setData({
          statusSteps: [
            that.setStatusStep(true, true, '已取消', "成功"),
          ]
        })
        break;
      case 5:
        dones = [true, true, true, true, true]
        descs = ['成功', '成功', '成功', orderDetail.addDate]
        that.setData({
          statusSteps: that.setStatusSteps(currents, dones, texts, descs)
        })
        break;
    }
  },

  setStatusSteps: function (currents, dones, texts, descs){
    var that = this;
    var statusSteps = [];
    for(var i=0;i<4;i++){
      statusSteps.push(that.setStatusStep(currents[i], dones[i], texts[i], descs[i]))
    }
    return statusSteps;
  },

  setStatusStep: function (current, done, text, desc) {
    var statusStep = {
      current: current,
      done: done,
      text: text,
      desc: desc
    }
    return statusStep;
  }

})