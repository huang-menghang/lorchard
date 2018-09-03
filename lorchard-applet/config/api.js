// 后台数据接口统一放在api.js中
var NewApiRootUrl = 'http://localhost/lorchard-api/';

module.exports = {
  //检查用户token是否失效
  CheckTokenUrl: NewApiRootUrl + 'member/checkToken',
  //用户登录
  MemberLoginUrl: NewApiRootUrl + 'member/login',
  //用户注册
  MemberRegisterUrl: NewApiRootUrl + 'member/register',

  //获取顶级分类
  ParentCategoryUrl: NewApiRootUrl +'goodsCategory/parent',
  //获取所有商品信息
  GoodPaginationUrl: NewApiRootUrl +'goods/pagination',
  //获取具体商品详细信息
  GoodsDetailsUrl: NewApiRootUrl +'goods/details',
  //验证具体商品详细信息
  GoodsValidateUrl: NewApiRootUrl + 'goods/validate',

  //获取商家信息
  MerchantUrl: NewApiRootUrl +'merchant',
  //首次访问商家首页
  FirstVisitUrl: NewApiRootUrl + 'merchant/firstVisit',
  //获取商家公告
  MerchantNoticeUrl: NewApiRootUrl + 'merchantNotice',
  //获取商家轮播图
  MerchantBannerUrl: NewApiRootUrl +'merchantBanner'
}