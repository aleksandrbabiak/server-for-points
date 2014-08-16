package server;

import com.google.protobuf.AbstractMessageLite;
import entity.Player;
import net_handlers.IncomingMessageHandler;
import org.jboss.netty.channel.Channel;
import protobuff.Envelope;
import protobuff.ProtoType;


public class WorkerHelper {

    private Channel channel;
    private Player player;
    public WorkerHelper(Channel channel) {
        this.channel = channel;

    }
    public void acceptPacket(IncomingMessageHandler message) {
        message.handle(this);
    }
    public void sendPacket(AbstractMessageLite object) {
        Envelope message = new Envelope();
        byte[] data = object.toByteArray();
        message.setData(data);
        message.setLength(data.length);
        message.setType(ProtoType.fromClass(object.getClass()));
        channel.write(message);
    }
    public Player getPlayer(){
        return player;
    }
    public void setPlayer(Player player){
        this.player = player;
    }

}

