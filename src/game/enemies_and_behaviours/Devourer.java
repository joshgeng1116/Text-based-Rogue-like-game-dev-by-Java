package game.enemies_and_behaviours;

import edu.monash.fit2099.engine.*;
import game.AttackAction;
import game.GameWeaponItem;
import game.Player;
import game.SwapWeaponAction;
import game.enums.Status;
import game.groundNitem.CinderOfDevourer;
import game.groundNitem.CinderOfYhorm;
import game.interfaces.Behaviour;
import game.weapons_and_skills.Longbow;
import game.weapons_and_skills.Machete;

public class Devourer extends Actor {
    private Behaviour followPlayer = null;
    private int souls = 5000;
    private boolean lastEmberForm = false;
    private String name;
    GameMap map;

    public Devourer(String name, char displayChar, int hitPoints) {
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

        for (int dx = -3; dx <= 3; dx++) {
            for (int dy = -3; dy <= 3; dy++) {
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
            map.locationOf(this).addItem(new CinderOfDevourer());
        }
    }

    /**
     * returns what the drop for the Devourer is
     * @return the item that Devourer drops once defeated
     */
    public Item drop(){return new CinderOfDevourer();}

    /**
     * gets Devourer's weapon
     * @return Devourer's weapon
     */
    public GameWeaponItem getWeapon() {
        return new Longbow();
    }

}
