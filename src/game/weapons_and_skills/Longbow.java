package game.weapons_and_skills;

import game.GameWeaponItem;

import java.util.Random;

/**
 * Longbow weapon, display char 'l', 70 damage, 80% hit rate
 */
public class Longbow extends GameWeaponItem {
    Random random = new Random();
    public Longbow() {
        super("Devourer's Longbow", 'l', 70, "hit", 80);
    }

    /**
     * 15% double the damage
     * @return
     */
    @Override
    public int damage(){
        if(random.nextInt(100)<15){
            return damage*2;
        }
        else {
            return damage;
        }
    }
}
