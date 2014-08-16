package entity;

import controler.GameControler;
import server.WorkerHelper;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static controler.GameControler.registrationNewPlayers;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 03.07.14
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class Player {

    private int id;
    private String name;
    private int oponent;
    private WorkerHelper workerHelper;

    public Player (WorkerHelper workerHelper){
        this.workerHelper = workerHelper;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   public int getId(){
   return id;
   }
   public void setId(int id){
       this.id = id;
   }
    public int getOponent() {
        return oponent;
    }
    public void setOponent(int oponent) {
        this.oponent = oponent;
    }
    public WorkerHelper getWorkerHelper(){
        return workerHelper;
    }
    public void setWorkerHelper(WorkerHelper workerHelper){
       this.workerHelper = workerHelper;
    }

    @Override
    public int hashCode() {
        final int CONST = 31;
        int result = 1;
        result = CONST * result + getId();
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player player = (Player) obj;

        List<Player> players = (List<Player>) GameControler.getAllPlayer();
        int size = players.size();
        for(int i = 0; i < size; i++){

            if ( players.get(i).getId() == player.id)
                i = size;
                return false;
        }

        return true;
    }
}
