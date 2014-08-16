package net_handlers;

import com.google.protobuf.InvalidProtocolBufferException;
import com.my_point_game.MyPointGame;
import server.WorkerHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 10.06.14
 * Time: 21:45
 * To change this template use File | Settings | File Templates.
 */
public class SFinishGameHandler extends IncomingMessageHandler {
   MyPointGame.SFinishGame packet;
        public SFinishGameHandler(byte[] data)throws InvalidProtocolBufferException{
            packet = MyPointGame.SFinishGame.parseFrom(data);
        }


    @Override
    public void handle(WorkerHelper workerHelper) {
        MyPointGame.CFinishGame cFinishGame = MyPointGame.CFinishGame.newBuilder()
                .setOponentLogin(packet.getMyLogin()).build();
        workerHelper.sendPacket(cFinishGame);

    }
}
