package server;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import protobuff.Envelope;
import protobuff.PacketStructure;
import protobuff.ProtoFactory;
import protobuff.ProtoType;

import static protobuff.PacketStructure.*;


public class PacketFrameDecoder extends ReplayingDecoder<PacketStructure> {
    Envelope message = new Envelope();

    public PacketFrameDecoder() {
        this.reset();
    }

    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, PacketStructure packetStructure) throws Exception {

        switch (packetStructure) {
            case TYPE:
                System.out.println(TYPE);
                byte b = channelBuffer.readByte();

                ProtoType type = ProtoType.fromByte(b);

                this.message.setType(type);

                checkpoint(LENGTH);

            case LENGTH:
                int size = channelBuffer.readInt();
                if (size < 0) {
                    throw new Exception("Invalid content");
                }
                byte content[] = new byte[size];
                this.message.setData(content);
                checkpoint(DATA);

            case DATA:
                channelBuffer.readBytes(this.message.getData(), 0, this.message.getData().length);
                try {
                    return ProtoFactory.createHandler(this.message.getData(), this.message.getType());
                } finally {
                    reset();
                }
            default:
                throw new Exception("Unknown decoding state: " + packetStructure);
        }
    }
    private void reset() {
        checkpoint(TYPE);
        this.message = new Envelope();
    }
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
            throws Exception {
        ctx.sendUpstream(e);
    }
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx,
                                    ChannelStateEvent e) throws Exception {
        ctx.sendUpstream(e);
    }
}
