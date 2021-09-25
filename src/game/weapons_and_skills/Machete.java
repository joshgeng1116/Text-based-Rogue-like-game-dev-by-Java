package game.weapons_and_skills;

import game.GameWeaponItem;
import game.enemies_and_behaviours.LordOfCinder;

public class Machete extends GameWeaponItem {
    private LordOfCinder lordOfCinder;

    /**
     * Constructor.
     */
    public Machete(LordOfCinder lordOfCinder) {
        super("Yhorm’s Great Machete", 'y', 95, "hit", 60);
        this.lordOfCinder = lordOfCinder;
    }

    /**
     * if lord of cinder's heal is below 50%, increase the chanceToHit
     * @return chanceToHit
     */
    @Override
    public int chanceToHit() {
        if (lordOfCinder.isEmberForm()) {
            return 90;
        } else {
            return 60;
        }
    }
}
