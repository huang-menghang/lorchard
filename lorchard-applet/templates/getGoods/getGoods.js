var app = getApp();
var starscore = require("../../templates/starscore/starscore.js");
var util = require("../../utils/util.js");
var api = require("../../config/api.js");

//获取当前商家商品种类
function getCategories() {
  util.requestGet({
    url: api.ParentCategoryUrl,
    data: {
      merchantId: app.globalData.merchantId
    },
    success: function(res) {
      console.log("获取的商品种类:", res.data)
      var categories = [];
      if (res.code == 0) {
        for (var i = 0; i < res.data.length; i++) {
          categories.push(res.data[i]);
        }
      }
      app.globalData.categories = categories
      getGoods(app.globalData.merchantId);
    },
    fail: function() {
      //设置变量 代表加载失败
      app.globalData.onLoadStatus = false
      //隐藏loading提示框
      wx.hideLoading()
      console.log('获取商品种类失败')
    }
  })
}

//获取商家的所有商品信息
function getGoods(merchantId) {
  util.requestGet({
    url: api.GoodPaginationUrl,
    data: {
      page: app.globalData.page,
      limit: app.globalData.pageSize,
      merchantId: merchantId
    },
    success: function(res) {
      console.log("获取的商品", res.data);
      var goods = [];
      var goodsName = [];
      if (res.code != 0 || res.data.length == 0) {
        return;
      }
      for (var i = 0; i < res.data.length; i++) {
        goods.push(res.data[i]);
      }
      for (var i = 0; i < goods.length; i++) {
        goodsName.push(goods[i].name);
      }
      app.globalData.goods = goods
      app.globalData.goodsName = goodsName
      //商品归类
      sortGoods(goods)
    }
  })
}

//商品归类
function sortGoods(goods) {
  var categories = app.globalData.categories
  //声明各变量
  var goodsList = [],
    id, name, goodsTemp = []

  for (let i = 0; i < categories.length; i++) {
    id = categories[i].id;
    name = categories[i].name;
    goodsTemp = [];

    //遍历商品,商品属于此分类则加入goodsTemp数组中
    for (let j = 0; j < goods.length; j++) {
      if (goods[j].parentId === id) {
        goodsTemp.push(goods[j])
      }
    }
    //设置初始被选中的类别
    if ((app.globalData.activeCategoryId === null) & (goodsTemp.length > 0)) {
      app.globalData.activeCategoryId = categories[i].id
    }
    //分类下的商品集合
    goodsList.push({
      'id': id,
      'name': name,
      'goods': goodsTemp
    })
  }
  app.globalData.goodsList = goodsList
  //加载成功
  app.globalData.onLoadStatus = true
  console.log('getGoodsList', app.globalData.goodsList)
}

module.exports = {
  getCategories: getCategories
}