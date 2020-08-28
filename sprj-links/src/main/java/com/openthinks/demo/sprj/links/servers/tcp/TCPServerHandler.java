package com.openthinks.demo.sprj.links.servers.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openthinks.demo.sprj.core.util.SpringContextUtil;
import com.openthinks.demo.sprj.links.service.PartDataService;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

/**
 * 
 * @author dailey.dai@openthinks.com
 *
 */
@Sharable
class TCPServerHandler extends ChannelInboundHandlerAdapter {
  private static final Logger LOGGER = LoggerFactory.getLogger(TCPServerHandler.class);
  private final PartDataService partDataService;

  public TCPServerHandler() {
    partDataService = SpringContextUtil.getContext().getBean(PartDataService.class);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf in = (ByteBuf) msg;
    String content = in.toString(CharsetUtil.UTF_8);
    LOGGER.trace("TCP Receive message content:{}", content);
    partDataService.put(ctx.channel().id(), content);
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
    LOGGER.debug("TCP server Channel active:{}", ctx.channel().id());
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    super.channelInactive(ctx);
    partDataService.remove(ctx.channel().id());
    LOGGER.debug("TCP server Channel inactive:{}", ctx.channel().id());
  }

  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent) {
      IdleStateEvent event = (IdleStateEvent) evt;
      if (event.state() == IdleState.READER_IDLE) {
        ctx.disconnect();// 断开
      } else if (event.state() == IdleState.WRITER_IDLE) {
        ctx.disconnect();
      } else if (event.state() == IdleState.ALL_IDLE) {
        ctx.disconnect();
      }
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
