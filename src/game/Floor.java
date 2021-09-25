package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.enemies_and_behaviours.LordOfCinder;
import game.enemies_and_behaviours.Undead;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	public Floor() {
		super('_');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		if(actor instanceof Undead) return false;
		if(actor instanceof LordOfCinder) return false;
		return super.canActorEnter(actor);
	}
}
