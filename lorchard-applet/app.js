App({
  globalData: {
    //商家id
    merchantId: 0,
    //openid
    openid:null,
    //用户id
    memberId:0,
    //商家
    merchant: null,
    page: 1,
    pageSize: 10000,
    //商品种类数组 
    categories: [],
    //商品数组
    goods: [],
    //自定义热门搜索商品
    hotGoods: ['桔', '火龙果', '香蕉', '酸奶', '甘蔗'],
    //商品名称数组
    goodsName: [],
    //商品集合数组
    goodsList: [],
    //加载成功标识
    onLoadStatus: true,
    activeCategoryId: null,
    notices: [],
    userInfo: null,
    version: "2.0.5",
    shareProfile: '一流的服务，做超新鲜的水果',
  },

  //监听小程序初始化 初始化完成后调用
  onLaunch: function (options) {
    
  },
  
  //微信模版消息 暂不知用途
  sendTempleMsg: function (orderId, trigger, template_id, form_id, page, postJsonString, emphasis_keyword){
    var that = this;
    wx.request({
      url: 'https://api.it120.cc/godream/template-msg/put',
      method:'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        token: wx.getStorageSync('token'), //登录接口返回的登录凭证
        type: 0, //0 小程序 1 服务号
        module: 'order', //所属模块：immediately 立即发送模板消息；order 所属订单模块
        business_id: orderId, //登录接口返回的登录凭证
        trigger: trigger, //module不为immediately时必填，代表对应的【订单】触发的状态
        template_id: template_id, //模板消息ID
        form_id: form_id, //type=0时必填，表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
        url: page, //小程序：点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）；服务号：跳转的网页地址
        postJsonString: postJsonString, //模板消息内容
        emphasis_keyword: emphasis_keyword //小程序："keyword1.DATA" 模板需要放大的关键词，不填则默认无放大
      },
      success: (res) => {
        //console.log(res.data);
      }
    })
  }
})
