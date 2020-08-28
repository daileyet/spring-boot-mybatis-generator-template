/**
 * 
 */
package com.openthinks.demo.sprj.links.servers.tcp;

import java.util.concurrent.TimeUnit;

import com.openthinks.demo.sprj.core.AppConfig;
import com.openthinks.demo.sprj.core.util.SpringContextUtil;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author dailey.dai@openthinks.com
 *
 */
class TCPChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    AppConfig appConfig = SpringContextUtil.getContext().getBean(AppConfig.class);
    ch.pipeline().addLast("idleStateHandler",
        new IdleStateHandler(appConfig.getServerIdleTimeInMinute(), 0, 0, TimeUnit.MINUTES));
    ch.pipeline().addLast(new TCPServerHandler());
  }

}
