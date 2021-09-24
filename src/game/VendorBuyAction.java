package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Parent class of the VendorBuy[Item]Action classes, all the actions that vendor wants to do, will be extended from this class
 */
public class VendorBuyAction extends Action {
    Action nothing = new DoNothingAction();

    /**
     * In the class that if a new vendor Buy action class is doing nothing (does no purpose)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string saying the actor is doing nothing
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return nothing.execute(actor,map);}

    /**
     * prints the string saying that the actor is doing nothing (no actions were conducted)
     * @param actor The actor performing the action.
     * @return string saying that the actor is doing nothing
     */
    @Override
    public String menuDescription(Actor actor) {
        return nothing.menuDescription(actor);
    }}
