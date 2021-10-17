package game.weapons_and_skills;

import edu.monash.fit2099.engine.Actor;
import game.GameWeaponItem;
import game.enemies_and_behaviours.Yhorm;

public class Machete extends GameWeaponItem {

    /**
     * Constructor.
     */
    public Machete(int hitRate) {
        super("Yhorm’s Great Machete", 'y', 95, "hit", hitRate);
    }

}
