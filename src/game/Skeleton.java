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


import java.util.ArrayList;

public class Skeleton extends Actor {

    // Will need to change this to a collection if Undeads gets additional Behaviours.
    private ArrayList<Behaviour> behaviours = new ArrayList<>();

    /**
     * Constructor.
     * All Skeleton are represented by an 's' and have 100 hit points.
     * @param name the name of this Skeleton
     */
    public Skeleton(String name) {
        super(name, 's', 100);
        behaviours.add(new WanderBehaviour());
        Actor otherActor;
        behaviours.add(new FollowBehaviour());
    }

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
        for(Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
