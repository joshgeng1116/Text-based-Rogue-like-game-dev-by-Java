package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * This is a that a class that all the items/things that the Vendor would like to sell needs to extend from
 */
public class VendorBuyAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        return "Unkindled does nothing";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
    /**
     * method to return the number of souls that the player currently has
     */
    public int soulsNum(Player player){
        return player.getSouls();
    }

}
