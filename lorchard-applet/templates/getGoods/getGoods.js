  var app = getApp();
  var starscore = require("../../templates/starscore/starscore.js");

  //获取当前商家商品种类
  function getCategories() {
   wx.request({   
    url: 'https://www.shuguomall.club/lorchard-api/goodsCategory/parent',
    data: {
      id: app.globalData.merchantId
    },
    success: function (res) {
      var categories = [];
      if (res.data.code == 0) {
        for (var i = 0; i < res.data.data.length; i++) {
          categories.push(res.data.data[i]);
        }
      }
      app.globalData.categories = categories
      getGoods(app.globalData.merchantId);
    },
    fail: function () {
      //设置变量 代表加载失败
      app.globalData.onLoadStatus = false
      //隐藏loading提示框
      wx.hideLoading()
      console.log('获取商品种类失败')
    }
  })
}

//获取商家的所有商品信息
function getGoods (merchantId) {
  wx.request({
    url: 'https://www.shuguomall.club/lorchard-api/goods/pagination',
    data: {
      page: app.globalData.page,
      limit: app.globalData.pageSize,
      merchantId: merchantId
  },
  success: function (res) {
      var goods = [];
      var goodsName = [];
      if (res.data.code != 0 || res.data.data.length == 0) {
        return;
      }
      for (var i = 0; i < res.data.data.length; i++) {
        goods.push(res.data.data[i]);
      }
      console.log('goods:', goods)
      for (var i = 0; i < goods.length; i++) {
        goodsName.push(goods[i].name);
      }
      for (let i = 0; i < goods.length; i++) {
        //评分处理(好评数/销量)
        goods[i].starscore = (goods[i].numberGoodReputation / goods[i].sales) * 5
        goods[i].starscore = Math.ceil(goods[i].starscore / 0.5) * 0.5
        //星级评分的图片路径
        goods[i].starpic = starscore.picStr(goods[i].starscore)
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
  var goodsList = [], id, key, name, typeStr, goodsTemp = []

  for (let i = 0; i < categories.length; i++) {
    id = categories[i].id;
    key = categories[i].key;
    name = categories[i].name;
    typeStr = categories[i].type;
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
    goodsList.push({ 'id': id, 'key': key, 'name': name, 'type': typeStr, 'goods': goodsTemp })
    console.log("你好," + categories[i].name)
  }
  app.globalData.goodsList = goodsList
  //加载成功
  app.globalData.onLoadStatus = true
  console.log('categories:', categories)
  console.log('getGoodsList', app.globalData.goodsList)
}

module.exports = {
  getCategories: getCategories
}