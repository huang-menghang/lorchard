// index.js
var starscore = require("../../templates/starscore/starscore.js");
var WxSearch = require('../../templates/wxSearch/wxSearch.js');
var app = getApp()
Page({
  data: {
      scrollTop: 0,
      searchInput: '',
      keyword:"",
      pageNum:1,
      pageSize:1000,
      load_statue:true,
      goods: [] ,
      goodsNum: 0,
      loadingHidden: false,
  },

  onLoad: function (options) {
    var that = this;
    console.log(options)
    //初始化的时候渲染wxSearchdata 第二个为你的search高度
    WxSearch.init(that, 43, app.globalData.hotGoods);
    WxSearch.initMindKeys(app.globalData.goodsName);
    this.setData({
      keyword: options.keyword,
    })
    this.refreshGoodsList();
  },

  listenerSearchInput: function (e) {
    this.setData({
      searchInput: e.detail.value
    })
  },

  loadSearchContent: function (keyword){
      wx.showLoading({
        title: '加载中',
      })
      var that=this;
      var goods = [];
      for(var i = 0;i<app.globalData.goods.length;i++){
        if (app.globalData.goods[i].name.indexOf(keyword) != -1){
          goods.push(app.globalData.goods[i])
        }
      }
      console.log("goods:", goods)
      that.setData({
        load_statue: true,
        goods: goods,
        goodsNum: goods.length
      })
    if (goods == null || goods.length < 10) {
      console.log("数据为空")
      that.setData({
        loadingHidden: true
      })
    } else {
      that.setData({
        loadingHidden: false
      })
    }
    wx.showToast({
      title: '加载成功',
    })
  },

  refreshGoodsList:function(){
    this.setData({
      pageNum: 1,
      goods: []
    })
    this.loadSearchContent(this.data.keyword, this.data.pageNum, this.data.pageSize);
  },

  loadMoreGoodsList:function(){
    var page = this.data.pageNum+ 1;
    this.setData({
      pageNum: page
    })
    this.loadSearchContent(this.data.keyword, this.data.pageNum, this.data.pageSize);
  },

  toSearch:function(e){
    var that = this
    console.log(e)
    
    this.refreshGoodsList();
  },

  toDetailsTap: function (e) {
    console.log(e)
    wx.navigateTo({
      url: "/pages/goods-details/index?id=" + e.currentTarget.dataset.id
    })
  },

  //////////////////////////////////////
  wxSearchFn: function (e) {
    var that = this
    that.toSearch();
    WxSearch.wxSearchAddHisKey(that);

  },
  wxSearchInput: function (e) {
    var that = this
    WxSearch.wxSearchInput(e, that);

    that.setData({
      keyword: that.data.wxSearchData.value,
    })
  },
  wxSerchFocus: function (e) {
    var that = this
    WxSearch.wxSearchFocus(e, that);
  },
  wxSearchBlur: function (e) {
    var that = this
    WxSearch.wxSearchBlur(e, that);
  },
  wxSearchKeyTap: function (e) {
    var that = this
    WxSearch.wxSearchKeyTap(e, that);

    that.setData({
      keyword: that.data.wxSearchData.value,
    })
  },
  wxSearchDeleteKey: function (e) {
    var that = this
    WxSearch.wxSearchDeleteKey(e, that);
  },
  wxSearchDeleteAll: function (e) {
    var that = this;
    WxSearch.wxSearchDeleteAll(that);
  },
  wxSearchTap: function (e) {
    var that = this
    WxSearch.wxSearchHiddenPancel(that);
  }
})