//index.js
//获取应用实例
var app = getApp()
var requireList = require("../../require.js");
var util = requireList.util;
var api = requireList.api;
Page({
  data: {
    orderItems: [],
    sendArray: ["自提", "商家配送"],
    sendMethod: 1,
    isNeedLogistics: 0,
    freightPrice: 0.01,
    orderTotalPrice: 0,
    orderPendingBalance:0,
    orderInfo: {},
    orderType: "",
    hasNoCoupons: true,
    coupons: [],
    couponPrice: 0,
    curCoupon: null
  },

  onLoad: function(e) {
    var that = this;
    that.setData({
      isNeedLogistics: 1,
      orderType: e.orderType,
      sendMethods: this.data.sendArray
    });
  },

  onShow: function() {
    var that = this;
    var shopList = [];
    //立即购买下单
    if ("buyNow" === that.data.orderType) {
      var buyNowInfoMem = wx.getStorageSync('buyNowInfo' + app.globalData.merchantId);
      that.data.kjId = buyNowInfoMem.kjId;
      if (buyNowInfoMem && buyNowInfoMem.shopList) {
        shopList = buyNowInfoMem.shopList
      }
    } else {
      //购物车下单
      var shopCarInfoMem = wx.getStorageSync('shopCarInfo' + app.globalData.merchantId);
      if (shopCarInfoMem && shopCarInfoMem.shopList) {
        shopList = shopCarInfoMem.shopList.filter(entity => {
          return entity.active;
        });
      }
    }
    that.setData({
      orderItems: shopList,
    });
    that.initShippingAddress();
    that.createOrderItem();
  },

  //收货地址
  initShippingAddress: function() {
    var that = this;
    util.requestGet({
      url: api.AddressState,
      data: {
        memberId: app.globalData.memberId
      },
      success: (res) => {
        if (res.code === 0) {
          that.setData({
            curAddressData: res.data
          });
        } else {
          that.setData({
            curAddressData: null
          });
        }
      }
    })
    
  },

  bindPickersendMethodChange: function(event) {
    var freightPrice = 0.01;
    if (event.detail.value == 0){
      freightPrice = 0;
    }else{
      freightPrice = 0.01;
    }
    this.setData({
      sendMethod: event.detail.value,
      isNeedLogistics: event.detail.value,
      freightPrice: freightPrice
    })
    this.createOrderItem();
  },

  //创建订单商品
  createOrderItem: function() {
    var that = this;
    that.data.orderInfo.sendMethod = that.data.sendMethod;
    that.data.orderInfo.freightPrice = that.data.freightPrice;
    var orderItems = this.data.orderItems;
    var orderTotalPrice = 0;
    for (var i=0;i<orderItems.length;i++){
      orderItems[i].itemPrice = util.multiply(orderItems[i].goodsPrice, orderItems[i].itemNum);
      orderTotalPrice += orderItems[i].itemPrice;
    }
    this.data.orderItems = orderItems;
    that.setData({
      orderTotalPrice: orderTotalPrice,
      orderPendingBalance: (orderTotalPrice + that.data.freightPrice).toFixed(2)
    })

    // that.getMyCoupons();
  },

  //创建订单
  createOrder: function(e) {
    var that = this;
    var orderInfo = that.data.orderInfo;
    wx.showLoading();
    //设置备注信息
    orderInfo.remark = e.detail.value.remark;
    orderInfo.orderTotalPrice = that.data.orderTotalPrice;
    orderInfo.orderPendingBalance = that.data.orderPendingBalance;
    //判断是否选择地址
    if (that.data.isNeedLogistics > 0) {
      if (!that.data.curAddressData) {
        wx.hideLoading();
        wx.showModal({
          title: '错误',
          content: '请先设置您的收货地址！',
          showCancel: false
        })
        return;
      }
      //设置配送地址信息
      orderInfo.province = that.data.curAddressData.province;
      orderInfo.city = that.data.curAddressData.city;
      orderInfo.diatrict = that.data.curAddressData.diatrict;
      orderInfo.address = that.data.curAddressData.address;
      orderInfo.orderMemberName = that.data.curAddressData.consignee;
      orderInfo.mobile = that.data.curAddressData.mobile;
    }
    orderInfo.orderItems = that.data.orderItems;
    util.requestJson({
      url: api.CreateOrder,
      data: JSON.stringify(orderInfo),
      success: function (res) {
        wx.hideLoading();
        if (res.code != 0) {
          wx.showModal({
            title: '订单有误,请重新下单',
            content: '订单有误,请重新下单',
            showCancel: false
          })
          return;
        }
        if ("buyNow" != that.data.orderType) {
          // 清空购物车数据
          wx.removeStorageSync('shopCarInfo' + app.globalData.merchantId);
        }
        util.requestGet({
          url: api.SuccessCreate,
          data: {
            orderNo: res.data
          },
          success: function () {
            console.log("创建订单成功")
          }
        })
        //发送模板消息
        //sendTempleMsg(res);
        // 下单成功，跳转到订单管理界面
        wx.redirectTo({
          url: "/pages/ucenter/order-list/index"
        })
      }
    })
    
  },

  sendTempleMsg:function(res){
    // 配置模板消息推送
    var postJsonString = {};
    //订单关闭
    postJsonString.keyword1 = {
      value: res.data.orderNumber,
      color: '#173177'
    }
    postJsonString.keyword2 = {
      value: res.data.dateAdd,
      color: '#173177'
    }
    postJsonString.keyword3 = {
      value: res.data.amountReal + '元',
      color: '#173177'
    }
    postJsonString.keyword4 = {
      value: '已关闭',
      color: '#173177'
    }
    postJsonString.keyword5 = {
      value: '您可以重新下单，请在30分钟内完成支付',
      color: '#173177'
    }
    app.sendTempleMsg(res.data.data.id, -1,
      'gVeVx5mthDBpIuTsSKaaotlFtl5sC4I7TZmx2PtEYn8', e.detail.formId,
      'pages/classification/index', JSON.stringify(postJsonString), 'keyword4.DATA');
    //订单已发货待确认通知
    postJsonString = {};
    postJsonString.keyword1 = {
      value: res.data.orderNumber,
      color: '#173177'
    }
    postJsonString.keyword2 = {
      value: res.data.dateAdd,
      color: '#173177'
    }
    postJsonString.keyword3 = {
      value: '已发货'
    }
    postJsonString.keyword4 = {
      value: '您的订单已发货，请保持手机通常，如有任何问题请联系客服13722396885',
      color: '#173177'
    }
    app.sendTempleMsg(res.data.data.id, 2,
      'ul45AoQgIIZwGviaWzIngBqohqK2qrCqS3JPcHKzljU', e.detail.formId,
      'pages/ucenter/order-details/index?id=' + res.data.data.id, JSON.stringify(postJsonString), 'keyword3.DATA');
  },

  addAddress: function() {
    wx.navigateTo({
      url: "/pages/address-add/index?memberId=" + app.globalData.memberId
    })
  },

  selectAddress: function() {
    wx.navigateTo({
      url: "/pages/select-address/index"
    })
  }
})