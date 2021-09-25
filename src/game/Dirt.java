package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	int burn = 0;

	public Dirt() {
		super('.');
	}

	public void setBurn(int burn) {
		this.burn = burn;
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		if (burn > 0) {
			Actor actor = location.getActor();
			if (actor != null) {
				if (!(actor instanceof LordOfCinder)) {
					actor.hurt(25);
				}
			}
		}

		if (burn > 0) burn--;
		if (burn > 0) {
			displayChar = 'v';
		} else {
			displayChar = '.';
		}
	}
}
