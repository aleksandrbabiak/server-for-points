package controler;

import entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 03.07.14
 * Time: 14:34
 * To change this template use File | Settings | File Templates.
 */
public class GameControler {

    private static HashSet<Player> setPlayers = null;
    public static int idPlayer = 1;
    public static void registrationNewPlayers(Player player){
        if(setPlayers == null){
            setPlayers = new HashSet<Player>();
        }
        setPlayers.add(player);
    }
    public static HashSet<Player> getAllPlayer(){
        return setPlayers;
    }



}
