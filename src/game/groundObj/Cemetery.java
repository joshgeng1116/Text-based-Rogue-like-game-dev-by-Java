package game.groundObj;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enemies_and_behaviours.Undead;

import java.util.Random;

public class Cemetery extends Ground {
    Random random = new Random();

    public Cemetery() {
        super('c');
    }

    /**
     * 25% chance to create undead each turn
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.getActor() == null) {
            if (random.nextInt(100) < 25) {
                location.addActor(new Undead("undead"));
            }
        }
    }
}
