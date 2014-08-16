package net_handlers;

import com.google.protobuf.InvalidProtocolBufferException;
import com.my_point_game.MyPointGame;
import server.WorkerHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 10.06.14
 * Time: 21:40
 * To change this template use File | Settings | File Templates.
 */
public class SWinGameHandler extends IncomingMessageHandler {
    private MyPointGame.SWinGame packet;

    public SWinGameHandler(byte[] data)throws InvalidProtocolBufferException{
        packet = MyPointGame.SWinGame.parseFrom(data);
    }

    @Override
    public void handle(WorkerHelper workerHelper) {
        //To change body of implemented methods use File | Settings | File Templates.
        MyPointGame.CWinGame cWinGame = MyPointGame.CWinGame.newBuilder().setLoseLogin(packet.getMyLogin()).build();
        workerHelper.sendPacket(cWinGame);
    }
}
