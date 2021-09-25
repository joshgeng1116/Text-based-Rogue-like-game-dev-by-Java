package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {
	final int valleyDamage = 500;

	public Valley() {
		super('+');
	}

	/**
	 * FIXME: At the moment, the Player cannot enter it. It is boring.
	 * @param actor the Actor to check
	 * @return false or actor cannot enter.
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		return true;
	}


	@Override
	public void tick(Location location) {
		super.tick(location);
		if(location.map().isAnActorAt(location)){
			location.getActor().hurt(valleyDamage);
		}


	}
}

