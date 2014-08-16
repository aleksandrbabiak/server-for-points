package net_handlers;

import com.google.protobuf.InvalidProtocolBufferException;
import com.my_point_game.MyPointGame;
import controler.GameControler;
import server.WorkerHelper;
import entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 10.06.14
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
public class SSendMessageHandler extends IncomingMessageHandler {
    private MyPointGame.SSendMessage packet;
    public SSendMessageHandler(byte[] data) throws InvalidProtocolBufferException{
        packet = MyPointGame.SSendMessage.parseFrom(data);
    }

    @Override
    public void handle(WorkerHelper workerHelper) {
        Player player = workerHelper.getPlayer();
        player.setId(packet.getMyId());
        System.out.println("my id = "+ player.getId());
        player.setOponent(packet.getOponentId());
        HashSet<Player> players =GameControler.getAllPlayer();
        int size = GameControler.getAllPlayer().size();
              Iterator iterator = players.iterator();
              while(iterator.hasNext()){
                 Player playerOponent =(Player) iterator.next();
                  if(playerOponent.getId() == player.getOponent()){
                       WorkerHelper workerHelperOponent = playerOponent.getWorkerHelper();
                      MyPointGame.CSendMessage cSendMessage = MyPointGame.CSendMessage.newBuilder()
                              .setOponentId(packet.getMyId())
                              .setSendMessageLoseLogin(packet.getSendMessageLoseLogin())
                              .build();
                        workerHelperOponent.sendPacket(cSendMessage);
                  }
              }
    }
}
