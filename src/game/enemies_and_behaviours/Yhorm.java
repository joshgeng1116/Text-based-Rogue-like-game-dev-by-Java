package game.enemies_and_behaviours;

import edu.monash.fit2099.engine.*;
import game.AttackAction;
import game.GameWeaponItem;
import game.Player;
import game.SwapWeaponAction;
import game.enums.Status;
import game.groundNitem.CinderOfYhorm;
import game.interfaces.Behaviour;
import game.interfaces.Soul;
import game.weapons_and_skills.Machete;


public class Yhorm extends Actor implements Soul {
    private Behaviour followPlayer = null;
    private int souls = 5000;
    private boolean lastEmberForm = false;
    private String name;
    GameMap map;
    /**
     * Constructor.
     */
    public Yhorm(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        addCapability(Status.HOSTILE_TO_PLAYER);
        addItemToInventory(new Machete(60));
        this.name = name;

    }

    /**
     * gets the name of the lord of cinder
     * @return string containing lord of cinder
     */
    public String name(){return name;}

    /**
     * check if the heal of lord of cinder below 50%
     * @return
     */
    public boolean isEmberForm() {
        return this.hitPoints * 1.0 / this.maxHitPoints < 0.5;
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        this.map = map;

        Location loc = map.locationOf(this);
        if (!lastEmberForm && isEmberForm()) {
            lastEmberForm = true;
            SwapWeaponAction s = new SwapWeaponAction(new Machete(90));
            s.execute(this,map);
            return new BurnGroundAction();
        }

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

    /**
     *
     * @param points number of hitpoints to deduct.
     */
    @Override
    public void hurt(int points) {
        super.hurt(points);
        if(!isConscious()) {
            System.out.println("LORD OF CINDER FALLEN");
            map.locationOf(this).addItem(new CinderOfYhorm());
        }
    }

    /**
     * returns what the drop for the Yhorm is
     * @return the item that yhorm drops once defeated
     */
    public Item drop(){return new CinderOfYhorm();}

    /**
     * gets Yhorm's weapon
     * @return yhorm's weapon
     */
    public GameWeaponItem getWeapon() {
        return new Machete(90);
    }

    /**
     *make it can be attacked by player
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        this.map = map;
        Actions actions = new Actions();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }


    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(souls);
        souls = 0;
    }

    @Override
    public String toString() {
        return String.format("%s(%d/%d)", name, hitPoints, maxHitPoints);
    }
}
