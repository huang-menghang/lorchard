var app = getApp();
var util = require("../../utils/util.js");
var api = require("../../config/api.js");
var WxParse = require("../wxParse/wxParse.js");

function getGoodsDetails(e, that) {
  util.requestGet({
    url: api.GoodsDetailsUrl,
    data: {
      goodsId: e.id,
      merchantId: app.globalData.merchantId,
      memberId: app.globalData.memberId
    },
    success: function(res) {
      var selectSizeTemp = "规格";
      //是否拥有可选属性(尺寸,型号,颜色等)
      that.setData({
        hasMoreSelect: true,
        selectSize: that.data.selectSize + selectSizeTemp,
        selectSizePrice: res.data.originalPrice,
        goodsDetail: res.data,
        buyNumMax: res.data.stock,
        buyNumber: (res.data.stock > 0) ? 1 : 0,
        propertyName: res.data.specificationsDescription
      });
      //设置商品详细信息
      that.data.goodsDetail = res.data;
      //是否含有视频
      if (res.data.videoId) {
        that.getVideoSrc(res.data.data.videoId);
      }
      //设置详细描述
      WxParse.wxParse('article', 'html', res.data.description, that, 5);
    }
  })
}


module.exports = {
  getGoodsDetails: getGoodsDetails
}