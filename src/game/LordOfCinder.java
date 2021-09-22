package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

/**
 * The boss of Design o' Souls
 * FIXME: This boss is Boring. It does nothing. You need to implement features here.
 * TODO: Could it be an abstract class? If so, why and how?
 */
public class LordOfCinder extends Actor implements Soul {
    private Behaviour followPlayer = null;
    private int souls = 5000;

    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        addCapability(Status.HOSTILE_TO_PLAYER);
        addItemToInventory(new Machete(this));

    }

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


    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
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
