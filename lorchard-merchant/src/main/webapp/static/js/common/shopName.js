var shop_name_ops = {
	init:function(){
		this.inintComponent();
	},
	inintComponent:function(){
		//取得运营人信息
		$.ajax({
			url:WEB_ROOT+'/shop/operatorName',
			type:'GET',
			dataType:'json'
			}).then(
					function(res) {
						if (res.code == 0) {
							$(".shopName").html(res.data.operatorName);
							$(".header-name").html(res.data.operatorName);
						};
						
						}).fail(function(res) {
                console.log(res.msg);
            });
		
		var websocket_ops = {
				init:function(){
					this.initMessageWebscoket();
					this.eventBind();
				},
				initMessageWebscoket : function() {
					var websocket;
					console.log("初始化websocket");
			// 首先判断是否 支持 WebSocket
			if ('WebSocket' in window) {
				websocket = new WebSocket("wss://www.ysdevelop.cn/websocket");
			} else if ('MozWebSocket' in window) {
				websocket = new MozWebSocket("wss://www.ysdevelop.cn/websocket"
					+ "/websocket");
			} else {
				websocket = new SockJS("https://" + "www.ysdevelop.cn/websocket"
					+ "/socketjs");
			}
			var merchantId;
			$.ajax({
	  			url:WEB_ROOT+'/goods/merchantId',
	  			type:'GET',
	  			dataType:'json'
	  		}).done(function(res){
	  			console.log("res.data"+res.data);
	  			if(res.code==0){
	  				merchantId=res.data;
	  				// 打开时
	  				websocket.onopen = function(evnt) {
	  					// 发送上线消息
	  					var onLineMessage = {};
	  					onLineMessage.fromMerchantId = merchantId;
	  					onLineMessage.messageConent = "商家客户端id:"+"merchantId"+",上线";
	  					onLineMessage.messageType = "ON_LINE";
	  					var jsonOnLineMessage= JSON.stringify(onLineMessage);
	  					console.log("json message --->"+jsonOnLineMessage);
	  					websocket.send(jsonOnLineMessage);
	  				};
	  			}
	  		});
			// 处理消息时
			websocket.onmessage = function(evnt) {
				var message = JSON.parse(event.data);
				console.log(message);
				var messageType = message.messageType;
				switch(messageType){
					case "MERCHANT":
					$.each(message.messageConent,function(i,v){
						layer.alert(v.conent);
					});
					break;
					default:
					break;
				}

			};
			window.onbeforeunload = function() {
				websocket.close();
			}
			function closeWebSocket() {
				websocket.close();
			}

			// 发送消息
			function send() {
				var message = "@heart";
				heartCheck.start();
			}
			var heartCheck = {
				timeout : 5000,
				timeoutObj : null,
				reset : function() {
					clearTimeout(this.timeoutObj);
					this.start();
				},
				start : function() {
					this.timeoutObj = setTimeout(function() {
						websocket.send("@heart");
					}, this.timeout)
				}
			}

		},
		eventBind:function(){

		}
		}	
		$(function(){
		websocket_ops.init();
		});
		
	}
}
$(function(){
	shop_name_ops.init();
});
			