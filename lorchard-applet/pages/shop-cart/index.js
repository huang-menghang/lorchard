//index.js
var app = getApp()
var requireList = require("../../require.js");
var util = requireList.util;
var api = requireList.api;
Page({
  data: {
    goodsList: {
      saveHidden: true,
      totalPrice: 0,
      allSelect: true,
      noSelect: false,
      list: []
    },
    shopDeliveryPrice: [],
    delBtnWidth: 120, //删除按钮宽度单位（rpx）
  },

  //获取元素自适应后的实际宽度
  getEleWidth: function(w) {
    var real = 0;
    try {
      var res = wx.getSystemInfoSync().windowWidth;
      var scale = (750 / 2) / (w / 2); //以宽度750px设计稿做宽度的自适应
      // console.log(scale);
      real = Math.floor(res / scale);
      return real;
    } catch (e) {
      return false;
      // Do something when catch error
    }
  },
  //初始化元素宽度
  initEleWidth: function() {
    var delBtnWidth = this.getEleWidth(this.data.delBtnWidth);
    this.setData({
      delBtnWidth: delBtnWidth
    });
  },
  //初始化函数
  onLoad: function() {
    this.initEleWidth();
    //this.onShow();
    this.getDeliveryPrice()
  },
  //渲染函数
  onShow: function() {
    var shopList = [];
    // 获取购物车数据
    var shopCarInfoMem = wx.getStorageSync("shopCarInfo" + app.globalData.merchantId);
    if (shopCarInfoMem && shopCarInfoMem.shopList) {
      shopList = shopCarInfoMem.shopList
    }
    this.data.goodsList.list = shopList;
    this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), shopList);
    console.log("购物车页面:", this.data.goodsList)
  },

  toIndexPage: function() {
    console.log("跳转页面")
    wx.reLaunch({
      url: "/pages/classification/index"
    });
  },

  touchS: function(e) {
    if (e.touches.length == 1) {
      this.setData({
        startX: e.touches[0].clientX
      });
    }
  },

  touchM: function(e) {
    var index = e.currentTarget.dataset.index;
    if (e.touches.length == 1) {
      var moveX = e.touches[0].clientX;
      var disX = this.data.startX - moveX;
      var delBtnWidth = this.data.delBtnWidth;
      var left = "";
      if (disX == 0 || disX < 10) { //如果移动距离小于等于0，container位置不变
        left = "margin-left:0px";
      } else if (disX > 10) { //移动距离大于0，container left值等于手指移动距离
        left = "margin-left:-" + disX + "px";
        if (disX >= delBtnWidth) {
          left = "left:-" + delBtnWidth + "px";
        }
      }
      var list = this.data.goodsList.list;
      if (index != "" && index != null) {
        list[parseInt(index)].left = left;
        this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list);
      }
    }
  },

  touchE: function(e) {
    var index = e.currentTarget.dataset.index;
    if (e.changedTouches.length == 1) {
      var endX = e.changedTouches[0].clientX;
      var disX = this.data.startX - endX;
      var delBtnWidth = this.data.delBtnWidth;
      //如果距离小于删除按钮的1/2，不显示删除按钮
      var left = disX > delBtnWidth ? "margin-left:-" + delBtnWidth + "px" : "margin-left:0px";
      var list = this.data.goodsList.list;
      if (index !== "" && index != null) {
        list[parseInt(index)].left = left;
        this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list);

      }
    }
  },

  delItem: function(e) {
    var index = e.currentTarget.dataset.index;
    var list = this.data.goodsList.list;
    list.splice(index, 1);
    this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list);
  },

  selectTap: function(e) {
    var index = e.currentTarget.dataset.index;
    var list = this.data.goodsList.list;
    if (index !== "" && index != null) {
      list[parseInt(index)].active = !list[parseInt(index)].active;
      this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list);
    }
  },

  totalPrice: function() {
    var list = this.data.goodsList.list;
    var total = 0;
    for (var i = 0; i < list.length; i++) {
      var curItem = list[i];
      if (curItem.active) {
        total += parseFloat(curItem.goodsPrice) * curItem.itemNum;
      }
    }
    total = parseFloat(total.toFixed(2)); //js浮点计算bug，取两位小数精度
    return total;
  },

  allSelect: function() {
    var list = this.data.goodsList.list;
    var allSelect = false;
    for (var i = 0; i < list.length; i++) {
      var curItem = list[i];
      if (curItem.active) {
        allSelect = true;
      } else {
        allSelect = false;
        break;
      }
    }
    return allSelect;
  },

  noSelect: function() {
    var list = this.data.goodsList.list;
    var noSelect = 0;
    for (var i = 0; i < list.length; i++) {
      var curItem = list[i];
      if (!curItem.active) {
        noSelect++;
      }
    }
    if (noSelect == list.length) {
      return true;
    } else {
      return false;
    }
  },

  setGoodsList: function(saveHidden, total, allSelect, noSelect, list) {
    this.setData({
      goodsList: {
        saveHidden: saveHidden,
        totalPrice: total,
        allSelect: allSelect,
        noSelect: noSelect,
        list: list
      }
    });
    var shopCarInfo = {};
    var tempNumber = 0;
    shopCarInfo.shopList = list;
    for (var i = 0; i < list.length; i++) {
      tempNumber = tempNumber + list[i].itemNum
    }
    shopCarInfo.shopNum = tempNumber;
    wx.setStorage({
      key: "shopCarInfo" + app.globalData.merchantId,
      data: shopCarInfo
    })
  },

  bindAllSelect: function() {
    var currentAllSelect = this.data.goodsList.allSelect;
    var list = this.data.goodsList.list;
    if (currentAllSelect) {
      for (var i = 0; i < list.length; i++) {
        var curItem = list[i];
        curItem.active = false;
      }
    } else {
      for (var i = 0; i < list.length; i++) {
        var curItem = list[i];
        curItem.active = true;
      }
    }
    this.setGoodsList(this.getSaveHide(), this.totalPrice(), !currentAllSelect, this.noSelect(), list);
  },

  jiaBtnTap: function(e) {
    var index = e.currentTarget.dataset.index;
    var list = this.data.goodsList.list;
    if (index !== "" && index != null) {
      if (list[parseInt(index)].itemNum < 10) {
        list[parseInt(index)].itemNum++;
        this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list);
      }
    }
  },

  jianBtnTap: function(e) {
    var index = e.currentTarget.dataset.index;
    var list = this.data.goodsList.list;
    if (index !== "" && index != null) {
      if (list[parseInt(index)].itemNum > 1) {
        list[parseInt(index)].itemNum--;
        this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list);
      }
    }
  },

  editTap: function() {
    var list = this.data.goodsList.list;
    for (var i = 0; i < list.length; i++) {
      var curItem = list[i];
      curItem.active = false;
    }
    this.setGoodsList(!this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list);
  },

  saveTap: function() {
    var list = this.data.goodsList.list;
    for (var i = 0; i < list.length; i++) {
      var curItem = list[i];
      curItem.active = true;
    }
    this.setGoodsList(!this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list);
  },

  getSaveHide: function() {
    var saveHidden = this.data.goodsList.saveHidden;
    return saveHidden;
  },

  deleteSelected: function() {
    var list = this.data.goodsList.list;
    list = list.filter(function(curGoods) {
      return !curGoods.active;
    });
    this.setGoodsList(this.getSaveHide(), this.totalPrice(), this.allSelect(), this.noSelect(), list);
  },

  /**从购物车去商品支付页面*/
  toPayOrder: function() {
    if (this.data.goodsList.totalPrice < this.data.shopDeliveryPrice) {
      this.goodsValidate('未达到起送价', '请您再选一些吧！')
    } else {
      wx.showLoading();
      var that = this;
      if (this.data.goodsList.noSelect) {
        wx.hideLoading();
        return;
      }
      // 重新计算价格，判断库存
      var shopList = [];
      var shopCarInfoMem = wx.getStorageSync('shopCarInfo' + app.globalData.merchantId);
      if (shopCarInfoMem && shopCarInfoMem.shopList) {
        shopList = shopCarInfoMem.shopList.filter(entity => {
          return entity.active;
        });
      }
      console.log("购物车shopList:", shopList)
      if (shopList.length == 0) {
        wx.hideLoading();
        return;
      }
      var isFail = false;
      var doneNumber = 0;
      var needDoneNUmber = shopList.length;
      for (let i = 0; i < shopList.length; i++) {
        if (isFail) {
          wx.hideLoading();
          return;
        }
        let carShopBean = shopList[i];

        // 获取价格和库存
        util.requestGet({
          url: api.GoodsValidateUrl,
          data: {
            goodsId: carShopBean.goodsId
          },
          success: function(res) {
            doneNumber++;
            if (!carShopBean.label || carShopBean.label == "") {
              if (res.data.specificationsDescription) {
                that.goodsValidate(res.data.name , ' 商品已失效，请重新购买');
                isFail = true;
                return;
              }
            }
            if (res.data.stock < carShopBean.itemNum) {
              that.goodsValidate(res.data.name , ' 库存不足，请重新购买');
              isFail = true;
              return;
            }
            if (res.data.minPrice != carShopBean.goodsPrice) {
              that.goodsValidate(res.data.name , ' 价格有调整，请重新购买');
              isFail = true;
              return;
            }
            if (needDoneNUmber == doneNumber) {
              that.navigateToPayOrder();
            }
          }
        })

      }
    }
  },

  goodsValidate: function(title,content) {
    wx.showModal({
      title: title,
      content: content,
      showCancel: false
    })
    wx.hideLoading();
  },

  navigateToPayOrder: function() {
    wx.hideLoading();
    wx.navigateTo({
      url: "/pages/to-pay-order/index?orderType=shopCart"
    })
  },

  getDeliveryPrice: function() {
    var that = this
    that.setData({
      shopDeliveryPrice: 0
    })
  }
})