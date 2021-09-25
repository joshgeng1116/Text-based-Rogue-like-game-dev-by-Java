package game.enemies_and_behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Dirt;

/**
 * This action will be returned if hp of lord of cinder is below 50% and it never executed
 */
public class BurnGroundAction extends Action {

    /**
     * burn 9 locations for 4 turns
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location loc = map.locationOf(actor);
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int x = loc.x() + dx;
                int y = loc.y() + dy;
                if (map.getXRange().contains(x) && map.getYRange().contains(y)) {
                    Location cell = map.at(x, y);
                    if (cell.getGround() instanceof Dirt) {
                        Dirt dirt = (Dirt) cell.getGround();
                        dirt.setBurn(4);
                    }
                }
            }
        }
        return "The ground around " + actor.toString() + " burned!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Burn ground";
    }
}
