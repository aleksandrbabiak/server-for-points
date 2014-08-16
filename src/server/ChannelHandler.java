package server;

import controler.GameControler;
import net_handlers.IncomingMessageHandler;
import org.jboss.netty.channel.*;
import entity.Player;


public class ChannelHandler extends SimpleChannelHandler {
    private WorkerHelper workerHelper;
    private Player player;
    public ChannelHandler() {
    }
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        workerHelper = new WorkerHelper(e.getChannel());
        player = new Player(workerHelper);
        workerHelper.setPlayer(player);
        player.setWorkerHelper(workerHelper);
    }
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        if (e.getChannel().isOpen()) {
            workerHelper.acceptPacket((IncomingMessageHandler) e.getMessage());
        }
    }
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {

        ctx.getChannel().close();
    }


}
