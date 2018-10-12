var app = getApp();
//引入wxParse.js
var requireList = require("../../../require.js");
var WxParse = requireList.wxParse;
var util = requireList.util;
var api = requireList.api;
var getShopCarMap = requireList.getShopCarMap;
var getGoodsDetails = requireList.getGoodsDetails;

Page({
  data: {
    inviteId:null,
    autoplay: true,
    interval: 3000,
    duration: 1000,
    goodsDetail: {},
    swiperCurrent: 0,
    hasMoreSelect: false,
    selectSizePrice: 0,
    shopNum: 0,
    hideShopPopup: true,
    buyNumber: 0,
    buyNumMin: 1,
    buyNumMax: 0,
    canSubmit: false, //  选中规格尺寸时候是否允许加入购物车
  },

  //监控页面加载函数
  onLoad: function(e) {
    var that = this;
    if (e.inviteId != undefined){
      this.setData({
        inviteId: e.inviteId
      })
    }
    
    var inviteId = this.data.inviteId;
    console.log("团购订单初始化",e);
    getGoodsDetails.getGoodsDetails(e, that);
  },

  //轮播图切换触发函数
  swiperchange: function(e) {
    this.setData({
      swiperCurrent: e.detail.current
    })
  },

  //立即购买函数
  tobuy: function() {
    this.setData({
      shopType: "tobuy"
    });
    this.bindGuiGeTap();
  },

  //规格选择弹出框
  bindGuiGeTap: function() {
    this.setData({
      hideShopPopup: false
    })
  },

  //规格选择弹出框隐藏
  closePopupTap: function() {
    this.setData({
      hideShopPopup: true
    })
  },

  numJianTap: function() {
    if (this.data.buyNumber > this.data.buyNumMin) {
      var currentNum = this.data.buyNumber;
      currentNum--;
      this.setData({
        buyNumber: currentNum
      })
    }
  },

  numJiaTap: function() {
    if (this.data.buyNumber < this.data.buyNumMax) {
      var currentNum = this.data.buyNumber;
      currentNum++;
      this.setData({
        buyNumber: currentNum
      })
    }
  },

  //选择商品规格
  labelItemTap: function(e) {
    var that = this;
    var goodsDetail = that.data.goodsDetail;
    that.setData({
      selectSizePrice: goodsDetail.spellingGroupPrice,
      buyNumMax: goodsDetail.stock,
      buyNumber: (goodsDetail.stock > 0) ? 1 : 0,
      goodsDetail: goodsDetail,
      canSubmit: true
    });
  },

  //立即购买
  buyNow: function() {
    var canSubmit = this.data.canSubmit;
    if (!canSubmit) {
      this.showModalInfo('提示', '请选择商品规格！', false)
      this.bindGuiGeTap();
      return;
    }

    var buyNumber = this.data.buyNumber;
    if (buyNumber < 1) {
      this.showModalInfo('提示', '购买数量不能为0！', false)
      return;
    }

    var selectSizePrice = this.data.selectSizePrice;
    var shopDeliveryPrice = this.data.shopDeliveryPrice;
    if (selectSizePrice * buyNumber < shopDeliveryPrice) {
      var title = '未达到起送价,起送价:' + shopDeliveryPrice + "元";
      this.showModalInfo(title, '请您再选一些吧！', false)
    } else {
      var buyNowInfo = this.buliduBuyNowInfo();
      var merchantId = app.globalData.merchantId;
      wx.setStorage({
        key: "groupBuyInfo" + merchantId,
        data: buyNowInfo
      })
      this.closePopupTap();
      var inviteId = this.data.inviteId;
      wx.navigateTo({
        url: "/pages/purchase/group-order/index?orderType=groupBuy&inviteId="+inviteId
      })
    }
  },

  //组建立即购买信息
  buliduBuyNowInfo: function() {
    var shopCarMap = getShopCarMap.getShopCarMap(this);
    var buyNowInfo = {};
    if (!buyNowInfo.shopNum) {
      buyNowInfo.shopNum = 0;
    }
    if (!buyNowInfo.shopList) {
      buyNowInfo.shopList = [];
    }
    buyNowInfo.shopList.push(shopCarMap);
    return buyNowInfo;
  },

  showModalInfo: function(title, content, showCancel) {
    wx.showModal({
      title: title,
      content: content,
      showCancel: showCancel,
    })
  },

  //分享转发
  onShareAppMessage: function() {

  },

})