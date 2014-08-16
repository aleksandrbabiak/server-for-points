package server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import server.ChannelHandler;
import server.PacketFrameDecoder;
import server.PacketFrameEncoder;


public class ServerPipelineFactory implements ChannelPipelineFactory {

    @Override
    public ChannelPipeline getPipeline() throws Exception {
        System.out.println("Client connect with Server");
        PacketFrameDecoder decoder = new PacketFrameDecoder();
        PacketFrameEncoder encoder = new PacketFrameEncoder();
        ChannelPipeline pipeline = Channels.pipeline();
        pipeline.addLast("decoder", decoder);
        pipeline.addLast("encoder", encoder);
        pipeline.addLast("handler", new ChannelHandler());
        return pipeline;
    }
}
