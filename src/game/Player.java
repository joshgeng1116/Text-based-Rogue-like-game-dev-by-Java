package game;

import edu.monash.fit2099.engine.*;
import game.Bonfires.Bonfire;
import game.enemies_and_behaviours.BurnGroundAction;
import game.enemies_and_behaviours.FollowBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.groundNitem.TokenOfSoul;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.weapons_and_skills.*;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {
	private final Menu menu = new Menu();
	private int souls = 5000;
	private Location lastLocation;
	private Bonfire bonfires = new Bonfire();
	private boolean lastEmberForm = false;
	private Location resetPoint;
	private MapManager mapManager = new MapManager();

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

	public boolean isEmberForm() {
		return this.hitPoints * 1.0 / this.maxHitPoints < 0.5;
	}

	/**
	 * go through all the available actions in a playTurn
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return a menu with allowable actions
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (!this.isConscious()) {
			Player player = this;
			TokenOfSoul tokenOfSoul = new TokenOfSoul(player.getSouls());
			if (lastAction != null) {
				lastLocation.addItem(tokenOfSoul);
			}
			resetInstance();
			map.removeActor(player);
			resetPoint.addActor(player);
			System.out.println("YOU DIED!");
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


		//check the weapon hold by player, if it's a fully charged storm ruler, add storm ruler's skill action to actions
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

		if(getWeapon() instanceof Machete){
			if(!lastEmberForm && isEmberForm()){
				lastEmberForm = true;
				SwapWeaponAction s = new SwapWeaponAction(new Machete(90));
				s.execute(this,map);
				actions.add(new BurnGroundAction());
			}
		}

		if(getWeapon() instanceof Longbow){
			Location loc = map.locationOf(this);
			for (int dx = -3; dx <= 3; dx++) {
				for (int dy = -3; dy <= 3; dy++) {
					int x = loc.x() + dx;
					int y = loc.y() + dy;
					if (map.getXRange().contains(x) && map.getYRange().contains(y)) {
						Location cell = map.at(x, y);
						if(cell.getActor() != null){
							if (!(cell.getActor() instanceof Player)) {
								actions.add( new AttackAction(cell.getActor(), "x: "+cell.x()+", y: "+cell.y()));
							}
						}
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

	/** list of bonfires that exists/is currently lit to player
	 * @return list of bonfires
	 */
	public Bonfire getBonfires(){return bonfires;}

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
	 * Updates the location that player will reset to
	 */
	public void setResetPoint(){
		resetPoint = lastLocation;
	}

	/**
	 *
	 * @return the mapManager of the player class
	 */
	public MapManager getMapManager(){
		return mapManager;
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
