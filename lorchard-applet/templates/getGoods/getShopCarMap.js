var appData = getApp().globalData; 

function getShopCarMap(that){ 
  var thatData = that.data;
  var shopCarMap = {};
  shopCarMap.merchantId = appData.merchantId;
  shopCarMap.memberId = appData.memberId;
  shopCarMap.goodsId = thatData.goodsDetail.id;
  shopCarMap.goodsName = thatData.goodsDetail.name;
  shopCarMap.goodsPrice = thatData.selectSizePrice;
  shopCarMap.previewImages = thatData.goodsDetail.previewImages;
  shopCarMap.parentId = thatData.goodsDetail.parentId;
  shopCarMap.parentCategoryName = thatData.goodsDetail.parentCategoryName;
  shopCarMap.itemNum = thatData.buyNumber;
  shopCarMap.left = "";
  shopCarMap.active = true;
  shopCarMap.label = thatData.goodsDetail.specificationsDescription;
  shopCarMap.goodsType = shopCarMap.label;
  return shopCarMap;
}

module.exports = {
  getShopCarMap: getShopCarMap
}