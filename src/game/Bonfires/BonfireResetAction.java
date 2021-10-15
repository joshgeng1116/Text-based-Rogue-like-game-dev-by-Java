package game.Bonfires;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.EstusFlask;
import game.Player;

/**
 * class that executes when rest at FireLink shrine Bonfire is selected
 */
public class BonfireResetAction extends Action {
    String bonfireName;
    public BonfireResetAction(String bonfire) {
        super();
        this.bonfireName = bonfire;

    }
    /**
     * resets the player's health/hit points to the maximum
     * Refill Estus Flask to maximum charges
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string depicting the status of Estus Flask and hitpoints
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        // Refill Player’s health/hit points to the maximum.
        player.heal(player.getMaxHitPoints());
        // Refill Estus Flask to maximum charges.
        for (Item item : player.getInventory()) {
            if (item instanceof EstusFlask) {
                EstusFlask estusFlask = (EstusFlask) item;
                estusFlask.resetInstance();
            }
        }
        return "All recovered";
    }

    /**
     * menu description for resting at Bonfire
     * @param actor The actor performing the action.
     * @return string "Rest at Firelink Shrine's Bonfire"
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Rest at "+bonfireName+"'s Bonfire";
    }
}
