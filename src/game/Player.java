package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.weapons_and_skills.Broadsword;
import game.weapons_and_skills.StormRuler;
import game.weapons_and_skills.StormRulerStunAction;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {
	private final Menu menu = new Menu();

	private int souls = 0;

	private Location lastLocation;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.addItemToInventory(new Broadsword());
		this.addItemToInventory(new EstusFlask());
		ResetManager.getInstance().appendResetInstance(this);
	}

	/**
	 * A method to add actions to the player when near enemies
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return returns an arraylist of actions
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
			actions.add(new AttackAction(this, direction));
		}
		return actions;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (!this.isConscious()) {
			Player player = this;
			DyingSpot dyingSpot = new DyingSpot(player.getSouls());
			if (lastAction != null) {
				lastLocation.addItem(dyingSpot);
			}
			resetInstance();
			map.removeActor(player);
			map.at(38, 12).addActor(player);
			return new DoNothingAction();
		}
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		String weaponString = getWeapon().toString();
		System.out.printf("Unkindled (%d/%d), holding %s, %d souls\n",
				this.hitPoints,
				this.maxHitPoints,
				weaponString,
				souls
		);


		//check the weapon hold by player, if it's stormruler, add skill of stormruler to player's action
		if (getWeapon() instanceof StormRuler) {
			StormRuler stormRuler = (StormRuler) getWeapon();
			Location here = map.locationOf(this);
			for (Exit exit : here.getExits()) {
				Location destination = exit.getDestination();
				if (map.isAnActorAt(destination)) {
					Location loc = map.at(destination.x(), destination.y());
					if (stormRuler.isFullCharged()) {
						actions.add(new StormRulerStunAction(loc.getActor(), exit.getName()));
					}
				}
			}
		}
		lastLocation = map.locationOf(this);

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Accessor for players max HP
	 * @return maximum player hitpoints
	 */
	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	/**
	 * Accessor for players current HP
	 * @return current player hitpoints
	 */
	public int getHitPoints() {
		return hitPoints;
	}

	/**
	 * Accessor for the number of souls a player has
	 * @return number of souls
	 */
	public int getSouls() {
		return souls;
	}

	/**
	 * Adds weapons to players inventory
	 * @param item The Item to add.
	 */
	@Override
	public void addItemToInventory(Item item) {
		if (item instanceof GameWeaponItem) {
			Weapon w = getWeapon();
			if (w instanceof Item) {
				removeItemFromInventory((Item) getWeapon());
			}
		}
		super.addItemToInventory(item);
	}

	/**
	 * transfer Player's souls to another Soul's instance.
	 * @param soulObject a target souls.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(souls);
		souls = 0;
	}

	/**
	 * increments the number of souls the player has
	 * @param souls number of souls to be incremented.
	 * @return
	 */
	@Override
	public boolean addSouls(int souls) {
		if (souls >= 0) {
			this.souls += souls;
			return true;
		}
		return false;
	}
	/**
	 * decrements the number of souls the player has
	 * @param souls number of souls to be decremented.
	 * @return
	 */
	@Override
	public boolean subtractSouls(int souls) {
		if (souls >= 0) {
			if (this.souls >= souls) {
				this.souls -= souls;
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the player instances HP , souls and inventory
	 */
	@Override
	public void resetInstance() {
		souls = 0;
		hitPoints = maxHitPoints;
		for (Item item : inventory) {
			if (item instanceof Resettable) {
				Resettable resettable = (Resettable) item;
				resettable.resetInstance();
			}
		}
	}

	/**
	 * A  method to clean up the list of instances in the ResetManager class
	 * @return the existence of the player in the game.
	 * for example, true to keep it permanent, or false if instance needs to be removed from the reset list.
	 */
	@Override
	public boolean isExist() {
		return true;
	}
}
