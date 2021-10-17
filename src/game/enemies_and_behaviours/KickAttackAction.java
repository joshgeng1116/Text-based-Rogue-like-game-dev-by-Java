package game.enemies_and_behaviours;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.ResetManager;
import game.groundNitem.TokenOfSoul;

public class KickAttackAction extends Action {
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     *
     * @param target attack target
     * @param direction target direction
     */
    public KickAttackAction(Actor target, String direction){
        this.target = target;
        this.direction = direction;
    }

    /**
     * change the description of attack action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int damage = 55;
        String result = actor + " " + "kick" + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            Actions dropActions = new Actions();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            map.removeActor(target);
            result += System.lineSeparator() + target + " is killed.";
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " kicks " + target + " at " + direction;
    }
}
