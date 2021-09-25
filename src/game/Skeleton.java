package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Soul;


import java.util.ArrayList;
import java.util.Random;

public class Skeleton extends Actor implements Soul{

    // Will need to change this to a collection if Skeleton gets additional Behaviours.
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    private Behaviour followPlayer = null;
    private boolean firstTime = true;

    /**
     * Constructor.
     * All Skeleton are represented by an 's' and have 100 hit points.
     *
     * @param name the name of this Undead
     */
    public Skeleton(String name) {
        super(name, 's', 100);
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            addItemToInventory(new Broadsword());
        } else {
            addItemToInventory(new GiantAxe());
        }
        addCapability(Status.HOSTILE_TO_PLAYER);
        behaviours.add(new WanderBehaviour());
    }

    public boolean revival() {
        if (firstTime) {
            firstTime = false;
            Random random = new Random();
            if (random.nextInt(2) == 0) {
                hitPoints = maxHitPoints;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
     * You can do something else with this method.
     *
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
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Figure out what to do next.
     * FINISHED: An Undead wanders around at random and it cannot attack anyone. Also, figure out how to spawn this creature.
     *
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // loop through all behaviours
        Location loc = map.locationOf(this);
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
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

        for (Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(250);
    }

    @Override
    public String toString() {
        return String.format("%s(%d/%d)", name, hitPoints, maxHitPoints);
    }
}
