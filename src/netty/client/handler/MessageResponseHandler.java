package netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.protocol.command.MessageResponsePacket;

import java.util.Date;

/**
 * @author Cap_Sub
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        messageResponsePacket.setMessage(new Date() + "客户端发送: 我收到消息了");
        channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
    }

}
