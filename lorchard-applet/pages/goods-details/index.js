//index.js
//获取应用实例
var app = getApp();
//引入wxParse.js
var WxParse = require('../../templates/wxParse/wxParse.js');
var util = require('../../utils/util.js');
var api = require('../../config/api.js');
Page({
  data: {
    autoplay: true,
    interval: 3000,
    duration: 1000,
    goodsDetail: {},
    swiperCurrent: 0,
    hasMoreSelect: false,
    selectSize: "选择：",
    selectSizePrice: 0,
    shopNum: 0,
    hideShopPopup: true,
    buyNumber: 0,
    buyNumMin: 1,
    buyNumMax: 0,

    shopDeliveryPrice: 0,
    propertyChildIds: "",
    propertyChildNames: "",
    canSubmit: false, //  选中规格尺寸时候是否允许加入购物车
    shopCarInfo: {},
    shopType: "addShopCar", //购物类型，加入购物车或立即购买，默认为加入购物车
  },

  //轮播图切换触发函数
  swiperchange: function(e) {
    this.setData({
      swiperCurrent: e.detail.current
    })
  },

  //监控页面加载函数
  onLoad: function(e) {
    var that = this;
    wx.showShareMenu({
      // 要求小程序返回分享目标信息
      withShareTicket: true
    }); 
    //获取购物车数据
    wx.getStorage({
      key: 'shopCarInfo' + app.globalData.merchantId,
      success: function(res) {
        that.setData({
          shopCarInfo: res.data,
          shopNum: res.data.shopNum
        });
      }
    })
    util.requestGet({
      url: api.GoodsDetailsUrl,
      data: { goodsId: e.id, merchantId: app.globalData.merchantId, memberId: app.globalData.memberId},
      success: function (res) {
        var selectSizeTemp = "规格";
        //是否拥有可选属性(尺寸,型号,颜色等)
        that.setData({
          hasMoreSelect: true,
          selectSize: that.data.selectSize + selectSizeTemp,
          selectSizePrice: res.data.minPrice,
        });
        //设置商品详细信息
        that.data.goodsDetail = res.data;
        //是否含有视频
        if (res.data.videoId) {
          that.getVideoSrc(res.data.data.videoId);
        }
        //设置属性
        that.setData({
          goodsDetail: res.data,
          selectSizePrice: res.data.minPrice,
          buyNumMax: res.data.stock,
          buyNumber: (res.data.stock > 0) ? 1 : 0,
          propertyName: res.data.specificationsDescription
        });
        //设置详细描述
        WxParse.wxParse('article', 'html', res.data.description, that, 5);
      }
    })
    //调用获取评价函数
    this.reputation(e.id);
    //调用获取起送价函数
    this.getDeliveryPrice();
  },

  //前往购物车函数
  goShopCar: function() {
    //打开新页面 关闭所有页面
    wx.reLaunch({
      url: "/pages/shop-cart/index"
    });
  },

  //加入购物车函数
  toAddShopCar: function() {
    this.setData({
      shopType: "addShopCar"
    })
    this.bindGuiGeTap();
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
    var that = this
    //console.log(e.currentTarget.dataset.propertyid)
    this.data.canSubmit = true;
    //设置当前规格价格
    if (this.data.canSubmit) {
      that.setData({
        selectSizePrice: that.data.goodsDetail.minPrice,
        buyNumMax: that.data.goodsDetail.stock,
        buyNumber: (that.data.goodsDetail.stock > 0) ? 1 : 0
      });
    }
    this.setData({
      goodsDetail: that.data.goodsDetail,
      canSubmit: this.data.canSubmit
    })
  },

  //加入购物车
  addShopCar: function() {
    //提示选择商品规格
    if (!this.data.canSubmit) {
      wx.showModal({
        title: '提示',
        content: '请选择商品规格！',
        showCancel: false
      })
      this.bindGuiGeTap();
      return;
    }
    //提示购买不能为0
    if (this.data.buyNumber < 1) {
      wx.showModal({
        title: '提示',
        content: '购买数量不能为0！',
        showCancel: false
      })
      return;
    }
    //组建购物车
    var shopCarInfo = this.bulidShopCarInfo();

    this.setData({
      shopCarInfo: shopCarInfo,
      shopNum: shopCarInfo.shopNum
    });

    // 写入本地存储
    wx.setStorage({
      key: "shopCarInfo"+app.globalData.merchantId,
      data: shopCarInfo
    })
    this.closePopupTap();
    wx.showToast({
      title: '加入购物车成功',
      icon: 'success',
      duration: 2000
    })
    //console.log(shopCarInfo);

    //shopCarInfo = {shopNum:12,shopList:[]}
  },

  //立即购买
  buyNow: function() {
    //提示选择商品规格
    if (!this.data.canSubmit) {
      wx.showModal({
        title: '提示',
        content: '请选择商品规格！',
        showCancel: false
      })
      this.bindGuiGeTap();
      return;
    }
    //提示购买数量不能为0
    if (this.data.buyNumber < 1) {
      wx.showModal({
        title: '提示',
        content: '购买数量不能为0！',
        showCancel: false
      })
      return;
    }
    //提示商品未到起送价
    if (this.data.selectSizePrice * this.data.buyNumber < this.data.shopDeliveryPrice) {
      wx.showModal({
        title: '未达到起送价,起送价:' + this.data.shopDeliveryPrice+"元",
        content: '请您再选一些吧！',
        showCancel: false,
      })
    } else {
      //组建立即购买信息
      var buyNowInfo = this.buliduBuyNowInfo();
      // 写入本地存储
      wx.setStorage({
        key: "buyNowInfo" + app.globalData.merchantId,
        data: buyNowInfo
      })
      this.closePopupTap();
      //前往支付页面
      wx.navigateTo({
        url: "/pages/to-pay-order/index?orderType=buyNow"
      })
    }
  },

  //组建购物车信息
  bulidShopCarInfo: function() {
    // 加入购物车
    var shopCarMap = this.getShopCarMap();
    //购物车信息
    var shopCarInfo = this.data.shopCarInfo;
    //判断购物车物品数量
    if (!shopCarInfo.shopNum) {
      shopCarInfo.shopNum = 0;
    }
    //判断购物车物品集合
    if (!shopCarInfo.shopList) {
      shopCarInfo.shopList = [];
    }
    //含有相同物品标识
    var hasSameGoodsIndex = -1;
    //相同物品数量相加
    for (var i = 0; i < shopCarInfo.shopList.length; i++) {
      var tmpShopCarMap = shopCarInfo.shopList[i];
      if (tmpShopCarMap.goodsId == shopCarMap.goodsId) {
        hasSameGoodsIndex = i;
        shopCarMap.number = shopCarMap.number + tmpShopCarMap.number;
        break;
      }
    }
    //购物车物品数量
    shopCarInfo.shopNum = shopCarInfo.shopNum + this.data.buyNumber;
    //如果含有相同商品 删除原来数组替换为新数组
    if (hasSameGoodsIndex > -1) {
      shopCarInfo.shopList.splice(hasSameGoodsIndex, 1, shopCarMap);
      //没有则添加
    } else {
      shopCarInfo.shopList.push(shopCarMap);
    }
    return shopCarInfo;
  },

  //组建立即购买信息
  buliduBuyNowInfo: function() {
    var shopCarMap = this.getShopCarMap();

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

  getShopCarMap: function() {
    var shopCarMap = {};
    shopCarMap.goodsId = this.data.goodsDetail.id;
    shopCarMap.previewImages = this.data.goodsDetail.previewImages;
    shopCarMap.name = this.data.goodsDetail.name;
    shopCarMap.price = this.data.selectSizePrice;
    shopCarMap.left = "";
    shopCarMap.active = true;
    shopCarMap.label = this.data.goodsDetail.specificationsDescription;
    shopCarMap.number = this.data.buyNumber;
    shopCarMap.test = "111";
    shopCarMap.test1 = undefined;
    return shopCarMap;
  },

  //分享转发
  onShareAppMessage: function() {
    return {
      title: this.data.goodsDetail.name,
      path: '/pages/goods-details/index?id=' + this.data.goodsDetail.id,
      success:function(res) {
        // 转发成功
        console.log("转发成功",res)
      },
      fail: function (res) {
        // 转发失败
        console.log("转发失败", res)
      }
    }
  },

  //评价详情
  reputation: function(goodsId) {
    var that = this;
    wx.request({
      url: 'https://api.it120.cc/' + app.globalData.subDomain + '/shop/goods/reputation',
      data: {
        goodsId: goodsId
      },
      success: function(res) {
        if (res.data.code == 0) {
          //console.log(res.data.data);
          that.setData({
            reputation: res.data.data
          });
        }
      }
    })
  },

  //视频地址
  getVideoSrc: function(videoId) {
    var that = this;
    wx.request({
      url: 'https://api.it120.cc/' + app.globalData.subDomain + '/media/video/detail',
      data: {
        videoId: videoId
      },
      success: function(res) {
        if (res.data.code == 0) {
          that.setData({
            videoMp4Src: res.data.data.fdMp4
          });
        }
      }
    })
  },

  //最低配送
  getDeliveryPrice: function() {
    var that = this
    that.setData({
      shopDeliveryPrice: 50
    })
  }
})