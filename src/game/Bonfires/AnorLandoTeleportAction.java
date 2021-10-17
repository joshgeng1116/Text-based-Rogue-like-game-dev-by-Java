package game.Bonfires;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;

public class AnorLandoTeleportAction extends Action {
    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return returns a string confirming the player has moved
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        GameMap shrineMap = (GameMap) player.getMapManager().getMapList().get(2);
        map.removeActor(player);
        shrineMap.addActor(player, shrineMap.at(31,2));
        return actor + "Fast travelled";
    }
    /**
     *
     * @param actor The actor performing the action.
     * @return description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Travel to Anor Lando shrine";
    }
}
