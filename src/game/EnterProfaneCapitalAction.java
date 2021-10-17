package game;
import edu.monash.fit2099.engine.*;

public class EnterProfaneCapitalAction extends Action {
    /**
     * transports the player through the fog door
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String confirmation message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
       GameMap NewMap = (GameMap) player.getMapManager().getMapList().get(0);
        map.moveActor(player, NewMap.at(40,25));
        return  actor + "travels through the fog door";
    }

    /**
     * Outputs the description of the action
     * @param actor The actor performing the action.
     * @return action description
     */
    @Override
    public String menuDescription(Actor actor) {return actor +"Enter Profane Capital";}
}
