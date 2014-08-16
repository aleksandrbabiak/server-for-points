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
 * Time: 20:23
 * To change this template use File | Settings | File Templates.
 */
public class SWontPlayHandler extends IncomingMessageHandler {
    private MyPointGame.SWontPlay packet;

    public SWontPlayHandler(byte[] data) throws InvalidProtocolBufferException{
      packet = MyPointGame.SWontPlay.parseFrom(data);
    }

    @Override
    public void handle(WorkerHelper workerHelper) {
        //To change body of implemented methods use File | Settings | File Templates.
        //player received
        Player player = workerHelper.getPlayer();
        player.setOponent(packet.getLoseId());
        player.setId(packet.getMyId());
        HashSet<Player> listPlayers = GameControler.getAllPlayer();
             Iterator iterator = listPlayers.iterator();
        while(iterator.hasNext()){

            Player playerOponent = (Player) iterator.next();
           if(playerOponent.getId() == player.getOponent()){

             playerOponent.setOponent(player.getId());
             WorkerHelper workerHelperOponent = playerOponent.getWorkerHelper();

               MyPointGame.CWontPlay cWontPlay = MyPointGame.CWontPlay.newBuilder()
                       .setOponentId(playerOponent.getOponent()).build();

               MyPointGame.Player player1 = MyPointGame.Player.newBuilder()
                       .setName(player.getName())
                       .setOponentId(player.getId()).build();
               MyPointGame.CUpdateOnlinePlayer.Builder cUpdateOnlinePlayer = MyPointGame.CUpdateOnlinePlayer.newBuilder();
               cUpdateOnlinePlayer.addPlayer(player1);
               workerHelperOponent.sendPacket(cUpdateOnlinePlayer.build());
               workerHelperOponent.sendPacket(cWontPlay);
               break;
           }


        }



    }
}
