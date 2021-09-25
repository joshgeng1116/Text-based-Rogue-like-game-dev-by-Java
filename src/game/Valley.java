package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {
	public Valley() {
		super('+');
	}

	/**
	 * FINISHED: At the moment, the Player cannot enter it. It is boring.
	 *
	 * @param actor the Actor to check
	 * @return false or actor cannot enter.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		if (actor instanceof Player) return true;
		return false;
	}

	@Override
	public void tick(Location location) {
		if (location.getActor() != null && location.getActor() instanceof Player) {
			Player player = (Player) location.getActor();
			player.hurt(player.getHitPoints());
		}
	}
}

