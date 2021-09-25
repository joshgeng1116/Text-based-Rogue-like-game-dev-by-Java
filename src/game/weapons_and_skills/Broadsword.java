package game.weapons_and_skills;

import game.GameWeaponItem;

import java.util.Random;

public class Broadsword extends GameWeaponItem {

    Random random = new Random();

    /**
     * Constructor of boardsword.
     */
    public Broadsword() {
        super("Boardsword", 'B', 30, "hits", 80);
    }

    /**
     *20% double the damage
     * @return damage
     */
    @Override
    public int damage() {
        if (random.nextInt(100) < 20) {
            // Critical Strike
            return damage * 2;
        }
        return super.damage();
    }

    /**
     *
     * @return cost to buy it in Vendor
     */
    public static int cost(){
        return 500;
    }
}
