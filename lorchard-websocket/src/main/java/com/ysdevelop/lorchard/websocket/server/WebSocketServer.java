package com.ysdevelop.lorchard.websocket.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ysdevelop.lorchard.common.redis.RedisService;
import com.ysdevelop.lorchard.common.utils.ResourceUtil;
import com.ysdevelop.lorchard.websocket.service.WebSocketService;
import com.ysdevelop.lorchard.websocket.service.impl.MessageSchedule;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.websocket.server
 * 
 * @Description TODO
 * 
 * @Date 2018年9月6日
 * 
 * @Version
 * 
 */
@Component
public class WebSocketServer implements InitializingBean {
	/* websocket 启动端口 */
	@SuppressWarnings("static-access")
	public static final Integer WEBSOCKET_PORT = Integer.parseInt(ResourceUtil.getInstance("websocket-config").getConfigByName("port"));

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/* webscoketServerHandler */
	@Autowired
	private WebSocketServerHandler socketServerHandler;

	@Autowired
	private WebSocketService webSocketService;

	@Autowired
	private RedisService redisService;

	/**
	 * websocoket 启动主函数
	 * 
	 * @param port
	 *            端口号
	 * @throws Exception
	 *             启动异常
	 * 
	 * 
	 */
	public void run(int port) throws Exception {
		logger.info("消息队列发送数据");
		// 老板组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 工人组
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel channel) throws Exception {
					ChannelPipeline pipeline = channel.pipeline();
					pipeline.addLast("http-codec", new HttpServerCodec());
					pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
					pipeline.addLast("http-chunked", new ChunkedWriteHandler());
					pipeline.addLast("handler", socketServerHandler);

				}
			});
			Channel channel = bootstrap.bind(port).sync().channel();
			logger.info("websocket 已经启动,端口:" + port + ".");
			channel.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}

	/**
	 * 消息定时查询任务器,用于查询未读消息,然后推送给在线用户
	 */
	private void messagePoll() {
		logger.info("lorchard-message-poll server begin");
		ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
		MessageSchedule messageSchedule = new MessageSchedule();
		// 设置service 给线程任务类
		messageSchedule.setRedisService(redisService);
		messageSchedule.setWebSocketService(webSocketService);
		schedule.scheduleAtFixedRate(messageSchedule, 10, 10, TimeUnit.SECONDS);
		logger.info("lorchard-message-poll server finish");

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("lorchard-websocket server start");
		messagePoll();
		run(WEBSOCKET_PORT);
	}

}
