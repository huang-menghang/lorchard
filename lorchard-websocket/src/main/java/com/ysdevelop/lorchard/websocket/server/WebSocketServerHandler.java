package com.ysdevelop.lorchard.websocket.server;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.websocket.bo.WebSocketMessage;
import com.ysdevelop.lorchard.websocket.manager.ChannelHandlerContextManager;
import com.ysdevelop.lorchard.websocket.service.WebSocketService;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.websocket.server
 * 
 * @Description websocketHandler 具体实现类,实现握手
 * 
 * @Date 2018年9月5日
 * 
 * @Version
 * 
 */
@Component
@Sharable
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
	private WebSocketServerHandshaker handshaker;

	private ChannelHandlerContext ctx;

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private WebSocketService websocketService;

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
		// websocket握手为 HttpRequest,握手成功的时候为Websocket
		if (msg instanceof FullHttpRequest) {
			handleHttpRequest(ctx, (FullHttpRequest) msg);
		} else if (msg instanceof WebSocketFrame) {
			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		}

	}

	/**
	 * 处理http 请求,完成Websocket握手<br/>
	 * 
	 * @param ctx
	 * 
	 * @param msg
	 * 
	 */
	private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
		if (!request.getDecoderResult().isSuccess() || (!Constant.WEB_SOCKET.equals(request.headers().get("Upgrade")))) {
			sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}
		logger.info("websocket begin shake");
		logger.info("shake ----->" + ctx);
		// 正常情况下握手连接
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://"
				+ request.headers().get(HttpHeaders.Names.HOST), null, false);

		handshaker = wsFactory.newHandshaker(request);
		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
		} else {
			handshaker.handshake(ctx.channel(), request);
			// 记录管理处理上下文
			this.ctx = ctx;
		}
		logger.info("websocket end shake");

	}

	/**
	 * 处理Socket请求
	 * 
	 * @param ctx
	 * @param frame
	 */
	private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
		// 判断是否关闭链路的指令
		if (frame instanceof CloseWebSocketFrame) {
			logger.info("关闭握手.....");
			// 移除map缓存中的数据
		    ChannelHandlerContextManager.removeChannelHandlerContext(ctx);
			handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
		}
		// 判断是否Ping消息
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
		}
		// 当前只支持文本消息,不支持二进制消息
		logger.info("fraem class: " + frame.getClass());

		if (!(frame instanceof TextWebSocketFrame)) {
			throw new UnsupportedOperationException("unsupport nottext message");
		}
		// 处理
		try {
			TextWebSocketFrame webSocketFrame = (TextWebSocketFrame) frame;
			// 获取到客户对websocket服务器的请求
			logger.info("get client msg" + webSocketFrame.text());
			WebSocketMessage websocketMessage = WebSocketMessage.create(webSocketFrame.text());
			// 如果websocketMessage解析不成功,我们需要发送错误信息给客户端
			System.out.println("websocketMessage-->"+websocketMessage);
			if (websocketMessage == null) {
				sendWebSocket("decode websocket error");
			}
		  //sendWebSocket("hello client ! old huang websocketServer handle");
			 websocketService.receiveWebsocketMessage(websocketMessage, ctx);
		} catch (Exception e) {
			logger.error("send websocket msg error !!!");
			e.printStackTrace();
		}

	}

	/**
	 * Http返回
	 * 
	 * @param ctx
	 * @param request
	 * @param defaultFullHttpResponse
	 */
	private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response) {

		if (response.getStatus().code() != Constant.RESPONSE_GOOD) {
			ByteBuf buf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
			response.content().writeBytes(buf);
			buf.release();
			HttpHeaders.setContentLength(response, response.content().readableBytes());
		}
		ChannelFuture f = ctx.channel().writeAndFlush(response);
		// 如果非keep-alived,关闭连接
		if (!HttpHeaders.isKeepAlive(request) || response.getStatus().code() != Constant.RESPONSE_GOOD) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

	public void sendWebSocket(String msg) throws Exception {
		if (this.handshaker == null || this.ctx == null || this.ctx.isRemoved()) {
			throw new Exception("尚未握手成功,无法向客户端发送WebSocket消息");
		}
		this.ctx.channel().write(new TextWebSocketFrame(msg));
		this.ctx.flush();

	}

	// 出现异常
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}

	//
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	// 关闭
	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		logger.info("websocket close");
		super.close(ctx, promise);
	}

}
