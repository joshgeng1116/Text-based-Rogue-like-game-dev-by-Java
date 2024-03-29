package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.enemies_and_behaviours.Skeleton;
import game.groundNitem.TokenOfSoul;
import game.interfaces.Soul;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * execute attack actions, apply hurt to target
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return result of attack action
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		//if target dead
		if (!target.isConscious()) {
			Actions dropActions = new Actions();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);

			//transfer soul from target to actor
			if (actor instanceof Soul && target instanceof Soul) {
				Soul soul1 = (Soul) actor;
				Soul soul2 = (Soul) target;
				soul2.transferSouls(soul1);
			}

			//if player dead
			if (target instanceof Player) {
				Player player = (Player) target;
				TokenOfSoul tokenOfSoul = new TokenOfSoul(player.getSouls());
				map.locationOf(target).addItem(tokenOfSoul);
				ResetManager.getInstance().run();
				map.removeActor(target);
				map.at(38, 12).addActor(target);

			//if skeleton dead
			} else if (target instanceof Skeleton) {
				Skeleton skeleton = (Skeleton) target;
				if (!skeleton.revival()) {
					map.removeActor(target);
				}
			} else {
				map.removeActor(target);
			}
			result += System.lineSeparator() + target + " is killed.";

		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
