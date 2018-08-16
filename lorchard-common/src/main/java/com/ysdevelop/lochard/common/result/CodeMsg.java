package com.ysdevelop.lochard.common.result;
/**
 * 
 * @author OldHuang
 * 
 * @Package com.ysdevelop.result
 * 
 * @Description: CodeMsg,所用的业务问题都会集中调用在这里统计处理
 * 
 * @date 2018年4月2日
 * 
 * @version
 * 
 */
public class CodeMsg {
	private int code;
	private String msg;

	private CodeMsg() {
	}

	// 通用的错误码
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
	public static CodeMsg UPLOAD_FAILED = new CodeMsg(500101, "上传失败");

	// 分类 11 开始
	public static CodeMsg CATEGORY_ADD_FAILED = new CodeMsg(500110, "分类创建失败");

	// 用户
	public static CodeMsg MOBILE_EMPTY = new CodeMsg(500001,"手机号码为空");
	public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500002,"密码为空");
	public static CodeMsg PASSWORD_WRONG =  new CodeMsg(500003,"密码错误");
	public static CodeMsg MOBILE_EXIST =  new CodeMsg(500004,"手机号码已经注册");
	public static CodeMsg PASSWORD_CONFIRM =  new CodeMsg(500005,"两次密码输入不一致");
	public static CodeMsg ERROR_NAEM_PASSWORD =  new CodeMsg(500006,"两次密码输入不一致");
	public static CodeMsg ERROR_OLD_PASSWORD =  new CodeMsg(500050,"旧密码输入错误");
	public static CodeMsg USERNAME_EXIST = new CodeMsg(500051, "注册的登录名已存在");
	public static CodeMsg PASSWORD_LENGTH_SMALL = new CodeMsg(500052, "请输入6到23位密码");
	public static CodeMsg ADMIN_EXIST = new CodeMsg(500053, "超级管理员已存在，请勿再注册超级管理员");
	public static CodeMsg CASH_NOT_ENOUGH = new CodeMsg(500054, "用户余额不足，无法完成提现");
	
	//分类
	public static CodeMsg CLASSNAME_EMPTY = new CodeMsg(500007,"分类名不能为空");
	public static CodeMsg CLASSNAME_EXIST = new CodeMsg(500008,"分类名已存在");
	public static CodeMsg CLASS_SON_EXIST = new CodeMsg(500009,"删除失败，该分类下还存在分类，请先删除该分类下的所有分类");
	public static CodeMsg CLASSNAME_COMMODITY_EXIST = new CodeMsg(500701,"分类下存在商品");
	
	//商品
	public static CodeMsg SPECATION_EXIST = new CodeMsg(500010,"该商品规格已存在");
	public static CodeMsg COMMODITYNAME_EXIST = new CodeMsg(500022,"该商品名已存在");
	
	//服务
	public static CodeMsg MINISTRY_EXIST = new CodeMsg(500024,"该商品名已存在");

	//订单
	public static CodeMsg ORDERID_EMPTY = new CodeMsg(500011,"订单id不能为空");
	public static CodeMsg ORDERID_EXPRESSNO = new CodeMsg(500013,"快递号不能为空");
	public static CodeMsg ORDERID_EXCL = new CodeMsg(500014,"EXCL无数据");
	public static CodeMsg IS_DELIVERY = new CodeMsg(500030,"订单已派送或无法派送");
	
	//统计
	public static CodeMsg NEWMEMBER_EXIST = new CodeMsg(500012,"新增会员数量为空");
	
	//轮播
	public static CodeMsg SCROLL_EXIST = new CodeMsg(500015,"轮播id不能为空");
	public static CodeMsg SCROLL_COMMODITY = new CodeMsg(500016,"轮播图跳转商品不能为空");
	public static CodeMsg SCROLL_INDEX = new CodeMsg(500017,"轮播顺序不能为空");
	public static CodeMsg SCROLL_NO = new CodeMsg(500018,"轮播id或顺序或跳转商品id不能为数字");
	
	//店铺
	public static CodeMsg SHOP_EXIST = new CodeMsg(500019,"店铺id不能为空");
	public static CodeMsg SHOP_LEASTPRICE = new CodeMsg(500020,"店铺最低价不能为空");
	public static CodeMsg SHOP_NO = new CodeMsg(500021,"店铺最低价只能为数字或小数位数过多");
	
	//用户
	public static CodeMsg PARENT_ID_EQUAL = new CodeMsg(500023,"上级ID不能与自己ID相同");
	
	// 小程序授权
	public static CodeMsg SESSION_EXPRISE = new CodeMsg(500500,"小程序会话失效");
	public static CodeMsg MEMBER_NOREGISTER = new CodeMsg(500501,"会员未注册");
	public static CodeMsg MEMBER_REGISTER_FAILDED = new CodeMsg(500502,"会员注册失败");
	// 小程序首页
	public static CodeMsg SCROOLIMAGE_EMPTY = new CodeMsg(500510,"轮播图为空");
	public static CodeMsg NEWS_EMPTY = new CodeMsg(500511,"新闻为空");
	// 小程序订单
	public static CodeMsg ORDER_PAYED = new CodeMsg(500520,"订单已经支付了");
	public static CodeMsg ORDER_CREATE_FAILED = new CodeMsg(500521,"订单支付失败");
	public static CodeMsg ORDER_PEAS_EMPTY = new CodeMsg(500522,"您的豆豆不够了");
	// 小程序签到
	public static CodeMsg SIGN_ALREADY = new CodeMsg(500600,"你已经签到");
    // 小程序会员注册
	public static CodeMsg MESSAGE_SEND_ERROR = new CodeMsg(500600,"短信发送失败");
	public static CodeMsg VERIFY_CODE_INVALID = new CodeMsg(500601,"短信验证码已经失效");
	public static CodeMsg VERIFY_CODE_WRONG = new CodeMsg(500602,"短信验证码错误");
	public static CodeMsg MESSAGE_CODE_SEND = new CodeMsg(500603,"短信验证码已经发送");
	public static CodeMsg MEMBER_INVITER_EXIST = new CodeMsg(500604,"会员已经关联");
    
	// 小程序代理申请
	public static CodeMsg AGENT_NOT_EXISTED = new CodeMsg(500604,"您还没有申请代理的资格或者已经申请");

	// 权限
	public static CodeMsg PERMISSION_URL_EXIST = new CodeMsg(500301, "权限URL已存在");

	// 登录
	public static CodeMsg USER_PASSWORD_ERROR = new CodeMsg(500401, "账号或密码错误");
	public static CodeMsg CODE_VALIDATE = new CodeMsg(500402, "验证码错误，请重新填写");
	
	// 提现失败
	public static CodeMsg CASH_FAILURE = new CodeMsg(500601, "提现失败!");
    
	//上传的图片类型有误
	public static CodeMsg TYPE_WRONG = new CodeMsg(500901, "上传的图片类型有误!");
	//上传的图片找不到
	public static CodeMsg PICTURE_NOT_FIND = new CodeMsg(500902, "上传的图片找不到!");
	//上传失败
	public static CodeMsg UPLOAD_FAILURE = new CodeMsg(500903, "上传失败!");
	//上传成功
	public static CodeMsg UPLOAD_SUCCESS = new CodeMsg(500904, "上传成功!");
	//上传的文件为空
	public static CodeMsg FILE_IS_EMPTY = new CodeMsg(500905, "上传的文件为空!");
	//获取配置文件失败
	public static CodeMsg CONFIGURATION_FAILURE = new CodeMsg(500906, "获取配置文件失败!");
	
	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CodeMsg fillArgs(Object... args) {
		Integer code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code, message);
	}

}
