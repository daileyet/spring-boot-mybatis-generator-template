/**
 * 
 */
package com.openthinks.demo.sprj.links.servers.tcp;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openthinks.demo.sprj.core.AppConfig;
import com.openthinks.demo.sprj.links.servers.ServerStarter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author dailey.dai@openthinks.com
 *
 */
@Component
public class TCPServerStarter implements ServerStarter {
  private final static Logger LOGGER = LoggerFactory.getLogger(TCPServerStarter.class);
  @Autowired
  AppConfig appConfig;

  private volatile boolean running = false;

  private EventLoopGroup bossGroup = null;
  private EventLoopGroup workGroup = null;

  /*
   * (non-Javadoc)
   * 
   * @see cn.ultrawire.connector.ServerStarter#start()
   */
  @Override
  public void start() throws Exception {
    LOGGER.info("TCP server connector start...");
    synchronized (this) {
      stop();
      bossGroup = new NioEventLoopGroup();
      workGroup = new NioEventLoopGroup();
      running = true;
    }
    try {
      Up();
    } catch (Throwable e) {
      LOGGER.error("TCP server connector caught exception:{}.", e);
      if (running) {
        reUp();
      }
    }
  }

  private void Up() throws InterruptedException, UnknownHostException {
    ServerBootstrap bootstrap = new ServerBootstrap();
    InetSocketAddress serverAddress = new InetSocketAddress(appConfig.getTcpServerPort());
    // @formatter:off
    bootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
    .localAddress(serverAddress).option(ChannelOption.SO_BACKLOG, 1024)
    .childOption(ChannelOption.SO_KEEPALIVE, true)
    .childHandler(new TCPChannelInitializer())
    ;
    // @formatter:off
    ChannelFuture future = bootstrap.bind().sync();
    LOGGER.info("TCP server started on:{}.",serverAddress);
    future.channel().closeFuture().sync();
  }
  
  private void reUp() throws InterruptedException {
    synchronized (this) {
      if (bossGroup != null) {
        bossGroup.shutdownGracefully();
        bossGroup = null;
      }
      bossGroup = new NioEventLoopGroup();
    }
    synchronized (this) {
      if (workGroup != null) {
        workGroup.shutdownGracefully();
        workGroup = null;
      }
      workGroup = new NioEventLoopGroup();
    }
    try {
      Up();
    } catch (UnknownHostException e) {
      LOGGER.error("Failed to reUp for reason:{}", e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.ultrawire.connector.ServerStarter#stop()
   */
  @PreDestroy
  @Override
  public synchronized void stop() throws Exception {
    running = false;
    LOGGER.info("TCP server connector stop...");
    if (bossGroup != null) {
      bossGroup.shutdownGracefully();
      bossGroup = null;
    }
    if (workGroup != null) {
      workGroup.shutdownGracefully();
      workGroup = null;
    }
    LOGGER.info("TCP server connector stoped.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.ultrawire.connector.ServerStarter#isRunning()
   */
  @Override
  public boolean isRunning() {
    return running;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.ultrawire.connector.ServerStarter#isActive()
   */
  @Override
  public boolean isActive() {
    return appConfig.getTcpServerEnabled();
  }

}
