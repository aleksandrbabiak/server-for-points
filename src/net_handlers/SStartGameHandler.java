package net_handlers;

import com.google.protobuf.InvalidProtocolBufferException;
import com.my_point_game.MyPointGame;
import controler.GameControler;
import server.WorkerHelper;
import entity.Player;

import java.util.Iterator;

public class SStartGameHandler extends IncomingMessageHandler {
      private MyPointGame.SStartGame packet;
    public SStartGameHandler(byte[] data) throws InvalidProtocolBufferException {
        packet = MyPointGame.SStartGame.parseFrom(data);

    }
    @Override
    public void handle(WorkerHelper workerHelper) {
        //To change body of implemented methods use File | Settings | File Templates.
        Player player = workerHelper.getPlayer();
        player.setOponent(packet.getOpponentId());
        player.setId(packet.getMyId());
        Iterator iterator = GameControler.getAllPlayer().iterator();
              while(iterator.hasNext()){
                  Player playerOponent = (Player) iterator.next();
                if(playerOponent.getId() == player.getOponent()){
                    playerOponent.setOponent(player.getId());
                  WorkerHelper workerHelperOponent = playerOponent.getWorkerHelper();
                    MyPointGame.CStartGame cStartGame = MyPointGame.CStartGame.newBuilder()
                            .setOpponentId(playerOponent.getOponent()).build();
                    workerHelperOponent.sendPacket(cStartGame);
                    MyPointGame.CStartGame cStartGame1 = MyPointGame.CStartGame.newBuilder()
                            .setOpponentId(packet.getOpponentId()).build();
                    workerHelper.sendPacket(cStartGame1);
                    break;
                }
              }
    }
}
