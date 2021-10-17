package game.Bonfires;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;

public class AnorLandoTeleportAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        GameMap shrineMap = (GameMap) player.getMapManager().getMapList().get(2);
        map.removeActor(player);
        shrineMap.addActor(player, shrineMap.at(31,2));
        return actor + "Fast travelled";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Travel to Anor Lando shrine";
    }
}
