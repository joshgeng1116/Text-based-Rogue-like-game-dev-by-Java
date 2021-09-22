package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

public class Cemetery extends Ground {
    Random random = new Random();

    public Cemetery() {
        super('c');
    }

    @Override
    public void tick(Location location) {
        if (location.getActor() == null) {
            if (random.nextInt(100) < 25) {
                location.addActor(new Undead("undead"));
            }
        }
    }
}
