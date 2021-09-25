package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class SpinAttackAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        List<String> result = new ArrayList<>();
        Weapon weapon = actor.getWeapon();
        Location loc = map.locationOf(actor);
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int x = loc.x() + dx;
                int y = loc.y() + dy;
                if (map.getXRange().contains(x) && map.getYRange().contains(y)) {
                    Actor target = map.at(x, y).getActor();
                    if (target != null) {
                        int damage = (int) (weapon.damage() * 1.5);
                        String r = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
                        result.add(r);
                    }
                }
            }
        }
        return String.join("\n", result);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " Spin attack";
    }
}
