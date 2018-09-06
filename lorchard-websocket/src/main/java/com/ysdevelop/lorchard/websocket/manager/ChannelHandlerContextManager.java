package com.ysdevelop.lorchard.websocket.manager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ysdevelop.lorchard.common.utils.Constant;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.websocket.manager
 * 
 * @Description Netty websocket 客户端连接管理
 * 
 * @Date 2018年9月6日
 * 
 * @Version
 * 
 */
public class ChannelHandlerContextManager {

	private static final Map<Long, ChannelHandlerContext> CONEXT_STORE = new ConcurrentHashMap<>();

	public static void addChannelHandlerContext(Long userId, ChannelHandlerContext context) {
		CONEXT_STORE.put(userId, context);
	}
    /**
     * 
     * @param userId,根据用户id删除
     *
     */
	public static void removeChannelHandlerContext(Long userId) {
		ChannelHandlerContext context = CONEXT_STORE.get(userId);
		// 关闭连接
		context.close();
		CONEXT_STORE.remove(userId);
	}

	/**
	 * 
	 * @param context,根据ChannelHandlerContext 删除map
	 *
	 */
	public static void removeChannelHandlerContext(ChannelHandlerContext context) {
		List<ChannelHandlerContext> contexts = (List<ChannelHandlerContext>) CONEXT_STORE.values();
		if (context != null && contexts.size() > Constant.DEFALULT_ONE) {
			context.close();
			contexts.remove(context);
		}

	}

	public static ChannelHandlerContext getContextByUserId(Long userId) {
		ChannelHandlerContext context = CONEXT_STORE.get(userId);
		if (context == null) {
			return null;
		} else {
			return context;
		}
	}

	/**
	 * 发送消息给客户端
	 * 
	 * @param ctx
	 * @param msg
	 * @throws Exception
	 * 
	 */
	public static void sendWebSocket(ChannelHandlerContext ctx, String msg) throws Exception {
		if (ctx == null || ctx.isRemoved()) {
			throw new Exception("尚未握手成功,无法向客户端发送WebSocket消息");
		}
		ctx.channel().write(new TextWebSocketFrame(msg));
		ctx.flush();
	}

}
