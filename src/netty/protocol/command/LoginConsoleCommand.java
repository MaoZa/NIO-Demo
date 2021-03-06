package netty.protocol.command;

import io.netty.channel.Channel;
import netty.protocol.command.packet.LoginRequestPacket;
import netty.utils.LoginUtil;

import java.util.Scanner;

/**
 * @author Cap_Sub
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        if (LoginUtil.hasLogin(channel)){
            System.out.println("已登录，无需重复登录");
            return;
        }
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        System.out.print("输入邮箱: ");
        String email = scanner.next();

        System.out.print("输入密码: ");
        String password = scanner.next();

        loginRequestPacket.setEmail(email);
        loginRequestPacket.setPassword(password);

        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();

    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
