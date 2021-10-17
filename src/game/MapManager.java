package game;

import edu.monash.fit2099.engine.GameMap;
import java.util.ArrayList;

public class MapManager {
    private ArrayList<GameMap> maps;

    /**
     * constructor for map Manager class
     */
    public MapManager() {
        this.maps = new ArrayList<>();
    }

    public void addMap(GameMap newMap){
        maps.add(newMap);
    }
    public ArrayList getMapList (){
        return maps;
    }
}
