var shop_index_ops = {
	// 初始化参数
	init : function() {
		this.inintComponent();
		this.eventBind();
	},
	// 初始化组件
	inintComponent : function() {
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
				websocket = new WebSocket("wss://106.14.177.78:9000/");
			} else if ('MozWebSocket' in window) {
				websocket = new MozWebSocket("wss://106.14.177.78:9000"
					+ "/websocket");
			} else {
				websocket = new SockJS("https://" + "106.14.177.78:9000"
					+ "/socketjs");
			}

			// 打开时
			websocket.onopen = function(evnt) {
				// 发送上线消息
				var onLineMessage = {};
				onLineMessage.fromMerchantId = 1;
				onLineMessage.messageConent = "商家客户端id1,上线";
				onLineMessage.messageType = "ON_LINE";
				var jsonOnLineMessage= JSON.stringify(onLineMessage);
				console.log("json message --->"+jsonOnLineMessage);
				websocket.send(jsonOnLineMessage);
			};
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
		})
		
		// 初始化流量统计图
		$.ajax({
			url : WEB_ROOT + '/shop/dailyShopFlow',
			type : 'GET',
			dataType : 'json'
		}).then(
				function(res) {
					console.log(res.data);
					if (res.code == 0) {
						echarts_ops.four_line('流量趋势', [ 'pageView', 'visitorNumber', 'goodsView', 'goodsAceessNumber' ], [
								'浏览量', '访客数', '商品浏览量', '商品访问数' ], res.data,
								'board-diagram');
					}
				}).fail(function(res) {

		});
		
		// 初始化dashboard
		$.ajax({
			url : WEB_ROOT + '/shop/dashboard',
			type : 'GET',
			dataType : 'json'
		}).then(
				function(res) {
					if (res.code == 0) {
						if(res.data!=null){
							$(".header-name").html(res.data.merchantName);
							$(".yesterday-pageView").html(res.data.pageView);
							$(".yesterday-visitorNumber").html(res.data.visitorNumber);
							$(".yesterday-goodsView").html(res.data.goodsView);
							$(".yesterday-goodsAceessNumber").html(res.data.goodsAceessNumber);						
							$(".goodsCount").html(res.data.goodsCount);						
						}else{
							$(".yesterday-pageView").html(0);
							$(".yesterday-visitorNumber").html(0);
							$(".yesterday-goodsView").html(0);
							$(".yesterday-goodsAceessNumber").html(0);						
							$(".goodsCount").html(0);
						}
					};	
				}).fail(function(res) {
                      console.log(res.msg);
		             });
		

	},
	eventBind : function() {
		// 发布新产品
		$(".shop-content .realse-goods").click(function(e) {
			window.location.href = WEB_ROOT+'/goods/set?title=goodsIndex&ops=add';
		});

		

	}
};
$(function() {
	shop_index_ops.init();
});


