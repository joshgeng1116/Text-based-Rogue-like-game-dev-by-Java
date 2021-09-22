package game;


import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.ArrayList;

/**
 * An undead minion.
 */
public class Undead extends Actor {
	// Will need to change this to a collection if Undeads gets additional Behaviours.
	private ArrayList<Behaviour> behaviours = new ArrayList<>();
	private Behaviour followPlayer = null;
	private int souls = 50;

	/** 
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 * @param name the name of this Undead
	 */
	public Undead(String name) {
		super(name, 'u', 50);
		behaviours.add(new WanderBehaviour());
	}

	/**
	 * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
	 * You can do something else with this method.
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this,direction));
		}
		return actions;
	}

	/**
	 * Figure out what to do next.
	 * FIXME: An Undead wanders around at random and it cannot attack anyone. Also, figure out how to spawn this creature.
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// loop through all behaviours
		Location loc = map.locationOf(this);
		int[] dxlst = new int[]{-1, 1, 0, 0};
		int[] dylst = new int[]{0, 0, -1, 1};
		for (int i = 0; i < 4; i++) {
			int dx = dxlst[i];
			int dy = dylst[i];
			int x = loc.x() + dx;
			int y = loc.y() + dy;
			if (map.getXRange().contains(x) && map.getYRange().contains(y)) {
				Location cell = map.at(x, y);
				if (cell.getActor() instanceof Player) {
					if (followPlayer == null) {
						followPlayer = new FollowBehaviour(cell.getActor());
					}
					return new AttackAction(cell.getActor(), "");
				}
			}
		}

		if (followPlayer != null) {
			Action followAction = followPlayer.getAction(this, map);
			if (followAction != null) return followAction;
		}

		return new DoNothingAction();
	}

}
