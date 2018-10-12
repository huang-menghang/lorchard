module.exports = {
  //config
  api: require("/config/api.js"),
  
  //util
  util: require("/utils/util.js"),
  city: require("/utils/city.js"),
  pay: require("/utils/pay.js"),

  //templates
  canvasClock: require("/templates/canvasClock/canvasClock.js"),
  getGoods: require('/templates/getGoods/getGoods.js'),
  getGoodsDetails: require('/templates/getGoods/getGoodsDetails.js'),
  getShopCarMap: require('/templates/getGoods/getShopCarMap.js'),
  starscore: require("/templates/starscore/starscore.js"),
  wxParse: require('/templates/wxParse/wxParse.js'),
  wxSearch: require('/templates/wxSearch/wxSearch.js'),
}