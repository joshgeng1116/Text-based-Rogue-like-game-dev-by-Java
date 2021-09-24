package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {

	private final Menu menu = new Menu();
	private int souls = 0;

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
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	/**
	 *
	 * @return
	 */
	public int getEstusFlask(){
		return EstusFlask(); /// unfinished player not linked to estusflask class yet need to instantiate
	}

	/**
	 * gets the number of souls of player at that current time
	 * @return int number of souls
	 */
	public int getSouls() {
		return souls;
	}

	@Override
	public void addItemToInventory(Item item) {
		if (item instanceof GameWeaponItem) {
			Weapon w = getWeapon();
			if(w instanceof Item) {
				removeItemFromInventory((Item) getWeapon());
			}
		}
		super.addItemToInventory(item);
	}

	@Override
	public void transferSouls(Soul soulObject) {
		//FINISHED: transfer Player's souls to another Soul's instance.
		soulObject.addSouls(souls);
		souls = 0;
	}
	/**
	 * adds souls number of Souls from player
	 * @param souls number souls to be added
	 * @return true if it is successful in adding souls, else false
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
	 * deducts souls number of Souls from player
	 * @param souls number souls to be deducted
	 * @return true if it is successful in deducted souls, else false
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

	@Override
	public void resetInstance() {
		for (Item item : this.getInventory()) {
			removeItemFromInventory(item);
		}
	}

	@Override
	public boolean isExist() {
		return true;
	}
}
