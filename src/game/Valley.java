package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {
	final int valleyDamage =100;

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

	public void isPlayerInValley(Actor player, GameMap map){
		if(map.locationOf(player).getGround().getDisplayChar() == displayChar ){
			player.hurt(valleyDamage);
		}
	}
}

