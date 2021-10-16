package game.enemies_and_behaviours;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.ResetManager;
import game.groundObj.TokenOfSoul;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KickAttackAction extends Action {
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;


    public KickAttackAction(Actor target, String direction){
        this.target = target;
        this.direction = direction;
    }

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
            if (target instanceof Player) {
                Player player = (Player) target;
                TokenOfSoul tokenOfSoul = new TokenOfSoul(player.getSouls());
                map.locationOf(target).addItem(tokenOfSoul);
                ResetManager.getInstance().run();
                map.removeActor(target);
                map.at(38, 12).addActor(target);
            }
            result += System.lineSeparator() + target + " is killed.";
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " kicks " + target + " at " + direction;
    }
}
