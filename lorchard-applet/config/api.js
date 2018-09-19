// 后台数据接口统一放在api.js中
var NewApiRootUrl = 'https://www.ysdevelop.cn/lorchard-api/';

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
  MerchantNoticeUrl: NewApiRootUrl + 'merchant/merchantNotice',
  //获取商家轮播图
  MerchantBannerUrl: NewApiRootUrl +'merchant/merchantBanner',
  //商家反馈意见
  MerchantFeedbackUrl: NewApiRootUrl + 'merchant/addFeedback',
  //商家用户积分获取
  MemberPointUrl: NewApiRootUrl + 'memberPoint',
  //商家用户积分更新
  PointUpdateUrl: NewApiRootUrl + 'memberPoint/update',

  //收货地址
  AddressDetail: NewApiRootUrl+ 'address',
  //收货地址集合
  AddressList: NewApiRootUrl + 'address/list',
  //设置默认收货地址
  AddressUpdateState: NewApiRootUrl + 'address/updateState',
  //获取默认地址
  AddressState: NewApiRootUrl + 'address/state',

  //创建订单物品
  CreateOrder: NewApiRootUrl + 'order/createOrder',
  //获取订单列表
  OrderList: NewApiRootUrl + 'order/list',
  //取消订单
  OrderClose: NewApiRootUrl + 'order/close',
  //获取单个订单
  OrderByNo: NewApiRootUrl + 'order/getOrder',
  //订单支付
  OrderPrePay: NewApiRootUrl + 'order/prePay',
  //确定收货
  ConfirmOrder: NewApiRootUrl + 'order/confirmOrder',
  //支付成功日志
  SuccessPay: NewApiRootUrl + 'order/successPay',
  //创建成功日志
  SuccessCreate: NewApiRootUrl + 'order/successCreate',
}