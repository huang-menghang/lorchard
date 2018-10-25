//index.js
//获取应用实例
var app = getApp()
var requireList = require("../../../require.js");
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
    orderPendingBalance: 0,
    orderInfo: {},
    orderType: "",
    hasNoCoupons: true,
    coupons: [],
    couponPrice: 0,
    curCoupon: null,
    inviteId: null,
    orderResult: true
  },

  onLoad: function(e) {
    var that = this;
    that.setData({
      isNeedLogistics: 1,
      orderType: e.orderType,
      sendMethods: this.data.sendArray,
      inviteId: e.inviteId
    });
  },

  onShow: function() {
    var that = this;
    var shopList = [];

    //立即购买下单
    var buyNowInfoMem = wx.getStorageSync('groupBuyInfo' + app.globalData.merchantId);
    if (buyNowInfoMem && buyNowInfoMem.shopList) {
      shopList = buyNowInfoMem.shopList
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
    if (event.detail.value == 0) {
      freightPrice = 0;
    } else {
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
    for (var i = 0; i < orderItems.length; i++) {
      orderItems[i].itemPrice = util.multiply(orderItems[i].goodsPrice, orderItems[i].itemNum);
      orderTotalPrice += orderItems[i].itemPrice;
    }
    this.data.orderItems = orderItems;
    that.setData({
      orderTotalPrice: orderTotalPrice,
      orderPendingBalance: (orderTotalPrice + that.data.freightPrice).toFixed(2)
    })
  },

  //创建订单
  createOrder: function(e) {
    var that = this;
    var orderInfo = that.data.orderInfo;
    wx.showLoading();
    //设置信息 
    var setResult = that.setInfo(that, orderInfo, e);
    if (!setResult) {
      return;
    }
    that.setOrder(orderInfo);
  },

  setInfo: function(that, orderInfo, e) {
    orderInfo.remark = e.detail.value.remark;
    orderInfo.orderTotalPrice = that.data.orderTotalPrice;
    orderInfo.orderPendingBalance = that.data.orderPendingBalance;
    orderInfo.orderItems = that.data.orderItems;
    //判断是否选择地址
    if (that.data.isNeedLogistics > 0) {
      if (!that.data.curAddressData) {
        wx.hideLoading();
        wx.showModal({
          title: '错误',
          content: '请先设置您的收货地址！',
          showCancel: false
        })
        return false;
      }
      //设置配送地址信息
      orderInfo.province = that.data.curAddressData.province;
      orderInfo.city = that.data.curAddressData.city;
      orderInfo.diatrict = that.data.curAddressData.diatrict;
      orderInfo.address = that.data.curAddressData.address;
      orderInfo.orderMemberName = that.data.curAddressData.consignee;
      orderInfo.mobile = that.data.curAddressData.mobile;
    }
    return true;
  },

  setOrder: function(orderInfo) {
    var that = this;
    util.requestJson({
      url: api.CreateOrder,
      data: JSON.stringify(orderInfo),
      success: function(res) {
        wx.hideLoading();
        if (res.code != 0) {
          wx.showModal({
            title: '订单有误,请重新下单',
            content: '订单有误,请重新下单',
            showCancel: false
          })
          return;
        } else if (res.code == 0){
            that.data.orderNo=res.data;
          app.globalData.orderNo = res.data;
        }
        that.successCreate(res);
        that.groupOrder(that);
      }
    })
  },

  successCreate: function(res) {
    util.requestGet({
      url: api.SuccessCreate,
      data: {
        orderNo: res.data
      },
      success: function() {
        console.log("创建订单成功")
      }
    })
  },

  groupOrder: function(that) {
    var inviteId = that.data.inviteId;
    var merchantId = app.globalData.merchantId;
    var memberId = app.globalData.memberId;
    var orderNo=that.data.orderNo;
    console.log("orderNo"+orderNo)
    console.log("inviteId" + inviteId)
    if (inviteId == "null") {
      util.requestGet({
        url: api.groupCreateUrl,
        data: {
          merchantId: merchantId,
          memberId: memberId,
          orderNo: orderNo
        },
        success: function() {
          console.log("创建团购订单成功");
          wx.navigateTo({
            url: "/pages/purchase/group-list/index"
          })
        }
      })
    } else {
      util.requestGet({
        url: api.groupUpdateUrl,
        data: {
          inviteId: inviteId,
          merchantId: merchantId,
          memberId: memberId,
          orderNo: orderNo
        },
        success: function() {
          console.log("更新团购订单成功");
        }
      })
    }
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