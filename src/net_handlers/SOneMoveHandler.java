package net_handlers;

import com.google.protobuf.InvalidProtocolBufferException;
import com.my_point_game.MyPointGame;
import controler.GameControler;
import server.WorkerHelper;
import entity.Player;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 10.06.14
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public class SOneMoveHandler extends IncomingMessageHandler {
   private MyPointGame.SOneMove packet;
    public SOneMoveHandler(byte[] data) throws InvalidProtocolBufferException{
           packet = MyPointGame.SOneMove.parseFrom(data);
       }

    @Override
    public void handle(WorkerHelper workerHelper) {
        //To change body of implemented methods use File | Settings | File Templates.
        Player player = workerHelper.getPlayer();
        HashSet<Player> listPlayers = GameControler.getAllPlayer();
        Iterator iterator = listPlayers.iterator();
        while(iterator.hasNext()){
            Player playerOponent = (Player) iterator.next();
            if(playerOponent.getId() == player.getOponent()){
                MyPointGame.COneMove cOneMove = MyPointGame.COneMove.newBuilder()
                        .setI(packet.getI())
                        .setJ(packet.getJ())
                        .setMoveType(packet.getMoveType()).build();
                WorkerHelper workerHalperOponent = playerOponent.getWorkerHelper();
                workerHalperOponent.sendPacket(cOneMove);

            }

    }
}    }
