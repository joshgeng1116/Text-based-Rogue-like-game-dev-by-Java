package game;
import edu.monash.fit2099.engine.*;

public class EnterProfaneCapitalAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
       GameMap NewMap = (GameMap) player.getMapManager().getMapList().get(0);
        map.moveActor(player, NewMap.at(40,25));
        return  actor + "travels through the fog door";
    }

    @Override
    public String menuDescription(Actor actor) {return actor +"Enter Profane Capital";}
}
