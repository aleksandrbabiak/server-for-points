package net_handlers;

import com.google.protobuf.InvalidProtocolBufferException;
import com.my_point_game.MyPointGame;
import server.WorkerHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 10.06.14
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
public class SNewGameHandler extends IncomingMessageHandler {
    private MyPointGame.SNewGame packet;
      public SNewGameHandler(byte[] data)throws InvalidProtocolBufferException{
          packet = MyPointGame.SNewGame.parseFrom(data);

      }

    @Override
    public void handle(WorkerHelper workerHelper) {
        MyPointGame.CNewGame cNewGame = MyPointGame.CNewGame.newBuilder().setOponentLogin(packet.getMyLogin()).build();
        workerHelper.sendPacket(cNewGame);
    }
}
