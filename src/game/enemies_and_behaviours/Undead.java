package game.enemies_and_behaviours;


import edu.monash.fit2099.engine.*;
import game.AttackAction;
import game.Player;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;
import java.util.Random;

/**
 * An undead minion.
 */
public class Undead extends Actor implements Soul{
	// Will need to change this to a collection if Undeads gets additional Behaviours.
	private ArrayList<Behaviour> behaviours = new ArrayList<>();
	private Behaviour followPlayer = null;
	/** 
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 * @param name the name of this Undead
	 */
	public Undead(String name) {
		super(name, 'u', 50);
		addCapability(Status.HOSTILE_TO_PLAYER);
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
	 * go through all the actions behaviours in play turn
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return action
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

		//check 9 locations near actor, contains where it is standing
		Location loc = map.locationOf(this);
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				int x = loc.x() + dx;
				int y = loc.y() + dy;
				if (map.getXRange().contains(x) && map.getYRange().contains(y)) {
					Location cell = map.at(x, y);

					//check if the player near Undead, check the followPlayer, if is null make it follow the player then attack player
					if (cell.getActor() instanceof Player) {
						if (followPlayer == null) {
							followPlayer = new FollowBehaviour(cell.getActor());
						}
						return new AttackAction(cell.getActor(), "");
					}
				}
			}
		}

		//if there's no player in 9 locations near actor, check if there's a followPlayer behaviour, if yes, follow the player
		if (followPlayer != null) {
			Action followAction = followPlayer.getAction(this, map);
			if (followAction != null) return followAction;
		}

		//if there's no player in 9 locations near actor and no followPlayer behaviour, runs other behaviours(wander)
		for (Behaviour Behaviour : behaviours) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

	/**
	 * a method to make hurt to actor, and with 10% chance dead instantly
	 * @param points number of hitpoints to deduct.
	 */
	@Override
	public void hurt(int points) {
		Random random = new Random();
		if (random.nextInt(100) < 10) {
			super.hurt(hitPoints);
		}else{
			super.hurt(points);
		}
	}


	/**
	 *
	 * @param soulObject a target souls.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(50);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return String.format("%s(%d/%d)", name, hitPoints, maxHitPoints);
	}
}
