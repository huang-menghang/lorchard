//login.js
//获取应用实例
var app = getApp();
//引入starscore.js
var starscore = require("../../templates/starscore/starscore.js");
Page({
  data: {
    remind: '加载中',
    angle: 0
  },
  
  //页面初始化函数
  onLoad: function (options) {
    app.globalData.merchantId = options.merchantId
    var that = this
    //获取商城名称
    wx.request({
      //请求的url
      url: 'https://www.shuguomall.club/lorchard-api/merchant/'+app.globalData.merchantId,
      //成功回调
      success: function (res) {
        //设置全局商家
        app.globalData.merchant = res.data.data
        //设置本地缓存mallName的值(data数据)
        if (res.data.code == 0) {
          wx.setStorageSync('mallName', res.data.data.name);
          that.setData({
            mallName: res.data.data.name
          })
        }
      },
      fail: function (res) {
        console.log(res)
      }
    })
    //获取所有顶层分类
    wx.request({
      //请求的url
      url: 'https://www.shuguomall.club/lorchard-api/goodsCategory/parent',
      data:{
        id: app.globalData.merchantId
      },
      //成功回调
      success: function (res) {
        //定义分类数组
        var categories = [];
        //成功则在数组中放入获取的种类
        if (res.data.code == 0) {
          for (var i = 0; i < res.data.data.length; i++) {
            categories.push(res.data.data[i]);
          }
        }
        //给变量赋值
        app.globalData.categories = categories
        //调用获取某一类下商品的函数
        that.getGoods(app.globalData.merchantId);//0代表获取全品类商品
      },
      //失败回调
      fail: function () {
        //设置变量 代表加载失败
        app.globalData.onLoadStatus = false
        //隐藏loading提示框
        wx.hideLoading()
        console.log('11')
      }
    })
    //获取星级相关
    wx.request({
      //请求的url
      url: 'https://api.it120.cc/godream/score/send/rule',
      //发送到controller的数据
      data: {
        code: '1'
      },
      //成功回调
      success: function (res) {
        //设置变量的值(变量为globalData中定义的,在下面可以看到)
        if (res.data.code == 0) {
          app.globalData.order_reputation_score = res.data.data[0].score;
        }
      }
    })
    //最低充值金额
    wx.request({
      //请求的url
      url: 'https://api.it120.cc/godream/config/get-value',
      //发送到controller的数据
      data: {
        key: 'recharge_amount_min'
      },
      //成功回调
      success: function (res) {
        //设置变量值
        if (res.data.code == 0) {
          app.globalData.recharge_amount_min = res.data.data.value;
        }
      }
    })
    // 获取砍价设置
    wx.request({
      //请求的url
      url: 'https://api.it120.cc/godream/shop/goods/kanjia/list',
      //发送到controller的数据
      data: {},
      //成功回调
      success: function (res) {
        //设置变量值
        if (res.data.code == 0) {
          app.globalData.kanjiaList = res.data.data.result;
        }
      }
    })
  },

  //获取分类下的商品
  getGoods: function (categoryId) {
    //id==0设置为空 这一步根据数据库调整 可删除
    if (categoryId == 0) {
      categoryId = "";
    }
    console.log(categoryId + "666")
    //获取商品
    wx.request({
      //请求的url
      url: 'https://www.shuguomall.club/lorchard-api/goods/pagination',
      //发送到controller的数据
      data: {
        page: app.globalData.page,
        limit: app.globalData.pageSize,
        merchantId: 1
      },
      //成功回调
      success: function (res) {
        //全局属性赋值为空数组
        app.globalData.goods = []
        //声明一个空数组
        var goods = [];
        //成功但数据长度为0 返回
        if (res.data.code != 0 || res.data.data.length == 0) {
          /*that.setData({
            prePageBtn: false,
            nextPageBtn: true,
            toBottom: true
          });*/
          return;
        }
        //在当前的goods数组中加入值
        for (var i = 0; i < res.data.data.length; i++) {
          goods.push(res.data.data[i]);
        }
        console.log('goods----------------------')
        console.log(goods)
        //获取全部商品名称，做为智能联想输入库
        var goodsName = [];
        //遍历获取所有商品名
        for (var i = 0; i < goods.length; i++) {
          goodsName.push(goods[i].name);
        }
        //变量赋值
        app.globalData.goodsName = goodsName
        //声明一个page指向全局page
        var page = app.globalData.page;
        //声明一个pageSize指向全局pageSize
        var pageSize = app.globalData.pageSize;
        //遍历商品
        for (let i = 0; i < goods.length; i++) {
          //星级评分的处理过程
          goods[i].starscore = (goods[i].numberGoodReputation / goods[i].sales) * 5
          goods[i].starscore = Math.ceil(goods[i].starscore / 0.5) * 0.5
          //星级评分的图片路径
          goods[i].starpic = starscore.picStr(goods[i].starscore)
        }
        //全局属性goods赋值
        app.globalData.goods = goods
        console.log('getGoodsReputation----------------------')
        console.log(app.globalData.goods)

        //再次获取商品的集合
        wx.request({
          //请求的url
          url: 'https://www.shuguomall.club/lorchard-api/goodsCategory/pagination',
          //发送到controller的数据
          data: {
            page: app.globalData.page,
            pageSize: app.globalData.pageSize,
          },
          //成功回调
          success: function (res) {
            //声明categories指向全局categories
            var categories = app.globalData.categories
            //声明各变量
            var goodsList = [],
              id,
              key,
              name,
              typeStr,
              goodsTemp = []
            //遍历categories
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
              //
              if ((app.globalData.activeCategoryId === null) & (goodsTemp.length > 0)) {
                app.globalData.activeCategoryId = categories[i].id
              }
              //商品数据添加
              goodsList.push({ 'id': id, 'key': key, 'name': name, 'type': typeStr, 'goods': goodsTemp })
              console.log("你好," + categories[i].name)
            }
            //全局goodsList赋值
            app.globalData.goodsList = goodsList
            //加载成功
            app.globalData.onLoadStatus = true
            console.log('categories:', categories)
            //that.globalData.activeCategoryId = categories[0].id   改为第一个不为null的类
            console.log('getGoodsList----------------------')
            console.log(app.globalData.goodsList)
          },
          //失败回调
          fail: function () {
            //加载失败
            app.globalData.onLoadStatus = false
            console.log('33')
          }
        })
      }
    })
  },

  //首页点击进入的页面
  goToIndex: function () {
    wx.switchTab({
      url: '/pages/choiceness/index',
    });
  },

  onShow: function () {

  },
  
  onReady: function () {
    var that = this;
    setTimeout(function () {
      that.setData({
        remind: ''
      });
    }, 1000);
    wx.onAccelerometerChange(function (res) {
      var angle = -(res.x * 30).toFixed(1);
      if (angle > 14) { angle = 14; }
      else if (angle < -14) { angle = -14; }
      if (that.data.angle !== angle) {
        that.setData({
          angle: angle
        });
      }
    });
  },
});