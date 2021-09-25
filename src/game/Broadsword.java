package game;

import java.util.Random;

public class Broadsword extends GameWeaponItem{

    Random random = new Random();

    /**
     * Constructor of boardsword.
     */
    public Broadsword() {
        super("Boardsword", 'B', 30, "hits", 80);
    }

    @Override
    public int damage() {
        if (random.nextInt(100) < 20) {
            // Critical Strike
            return damage * 2;
        }
        return super.damage();
    }

    public static int cost(){
        return 500;
    }
}
