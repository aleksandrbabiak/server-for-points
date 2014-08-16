package net_handlers;

import com.google.protobuf.InvalidProtocolBufferException;
import com.my_point_game.MyPointGame;
import controler.GameControler;
import entity.Player;
import server.WorkerHelper;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 10.06.14
 * Time: 19:04
 * To change this template use File | Settings | File Templates.
 */
public class SWontGameHandler extends IncomingMessageHandler {
    private MyPointGame.SWontGame packet;

    public SWontGameHandler(byte[] data) throws InvalidProtocolBufferException {
        packet = MyPointGame.SWontGame.parseFrom(data);
    }

    @Override
    public void handle(WorkerHelper workerHelper) {
        Player player = workerHelper.getPlayer();
        int playerId = GameControler.idPlayer;
        if(player.getId() == 0){
        player.setId(playerId);
        }
        playerId = ++GameControler.idPlayer;
        player.setName(packet.getLogin());
        GameControler.registrationNewPlayers(player);
        HashSet<Player> setPlayers = GameControler.getAllPlayer();
        setPlayers.add(player);
        MyPointGame.CWontGame cWontGame = MyPointGame.CWontGame.newBuilder().setMyId(player.getId()).build();
        MyPointGame.CUpdateOnlinePlayer.Builder cUpdateOnlinePlayer = MyPointGame.CUpdateOnlinePlayer.newBuilder();
        for (Player playerQ : setPlayers) {
            MyPointGame.Player player1 = MyPointGame.Player.newBuilder()
                    .setName(playerQ.getName())
                    .setOponentId(playerQ.getId()).build();
                cUpdateOnlinePlayer.addPlayer(player1);
        }
        workerHelper.sendPacket(cWontGame);
        workerHelper.sendPacket(cUpdateOnlinePlayer.build());
    }
}
