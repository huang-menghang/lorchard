$(function() {
	// 加载弹出层
	layui.use([ 'form', 'element' ], function() {
		layer = layui.layer;
		element = layui.element;
	});
});
var common_ops = {
	alert : function(msg, cb) {
		layer.alert(msg, {
			yes : function(index) {
				if (typeof cb == "function") {
					cb();
				}
				layer.close(index);
			}
		});
	},
	confirm : function(msg, callback) {
		callback = (callback != undefined) ? callback : {
			'ok' : null,
			'cancel' : null
		};
		layer.confirm(msg, {
			btn : [ '确定', '取消' ]
		// 按钮
		}, function(index) {
			// 确定事件
			if (typeof callback.ok == "function") {
				callback.ok();
			}
			layer.close(index);
		}, function(index) {
			// 取消事件
			if (typeof callback.cancel == "function") {
				callback.cancel();
			}
			layer.close(index);
		});
	},
	tip : function(msg, target) {
		layer.tips(msg, target, {
			tips : [ 3, '#e5004f' ]
		});
		$('html, body').animate({
			scrollTop : target.offset().top - 10
		}, 100);
	},
	g_getQueryString : function(name) {
		//console.log(name);
		if(name == "rurl"){
			var r = window.location.search.split("rurl=")[1];
			return r;
		}
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r != null) return unescape(r[2]);
		return null;
	}

}

/**
 * RF工具类
 */
RfUtil = function() {
};
RfUtil.prototype = {
	// 判断是否为空,如果为空返回true，否则返回false
	isEmpty : function(text) {
		if (text == undefined || text == null || text == '' || text == 'null'
				|| text == 'undefined') {
			return true;
		} else {
			text = text.replace(/(\s*$)/g, '');
			if (text == '') {
				return true;
			}
		}
		return false;
	},
	// 数字正则表达式，验证通过返回 true；
	numValid : function(text) {
		var patten = new RegExp(/^[0-9]+$/);
		return patten.test(text);
	},
	// 价格的正则表达式
	priceValid : function(text) {
		var patten = new RegExp(/^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/);
		return patten.test(text);
	},
	// 英文、数字正则表达式
	enNumValid : function(text) {
		var patten = new RegExp(/^[a-zA-Z0-9]+$/);
		return patten.test(text);
	},
	// 英文、数字、-、_验证
	cValid : function(text) {
		var patten = new RegExp(/^[a-zA-Z][\w-_]{5,19}$/);
		return patten.test(text);
	},
	// 中文、英文、数字、-、_验证
	zcValid : function(text) {
		var patten = RegExp(/^[\u4E00-\u9FA5A-Za-z0-9_-]+$/);
		return patten.test(text);
	},
	// email
	emailValid : function(text) {
		var patten = RegExp(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/);
		return patten.test(text);
	},
	mobileValid : function(text) {
		var patten = RegExp(/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/);
		return patten.test(text);
	},
	idCardValid : function(text) {
		var patten = RegExp(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/);
		return patten.test(text);
	},
	
};
rf = new RfUtil();
Date.prototype.format = function (format) {  
    var args = {  
        "M+": this.getMonth() + 1,  
        "d+": this.getDate(),  
        "h+": this.getHours(),  
        "m+": this.getMinutes(),  
        "s+": this.getSeconds(),  
    };  
    if (/(y+)/.test(format))  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    for (var i in args) {  
        var n = args[i];  
        if (new RegExp("(" + i + ")").test(format))  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));  
    }  
    return format;  
};  

