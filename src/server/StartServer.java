package server;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;



public class StartServer {

    public static void main(String args[]) throws UnknownHostException {

        ExecutorService bossExec = new OrderedMemoryAwareThreadPoolExecutor(1,
                400_000_000, 2_000_000_000, 60, TimeUnit.SECONDS);
        ExecutorService ioExec = new OrderedMemoryAwareThreadPoolExecutor(4,
                400_000_000, 2_000_000_000, 60, TimeUnit.SECONDS);
ChannelFactory factory = new NioServerSocketChannelFactory(bossExec,ioExec,4);
ServerBootstrap networkServer = new ServerBootstrap(factory);
networkServer.setOption("backlog", 500);
networkServer.setOption("connectTimeoutMillis", 10000);
networkServer.setOption("child.tcpNoDelay", true);
networkServer.setOption("child.keepAlive", true);
networkServer.setOption("readWriteFair", true);
networkServer.setPipelineFactory(new ServerPipelineFactory());
Channel channel = networkServer.bind(new InetSocketAddress("192.168.1.101", 1910));
System.out.println(channel.getLocalAddress());

}


        }
