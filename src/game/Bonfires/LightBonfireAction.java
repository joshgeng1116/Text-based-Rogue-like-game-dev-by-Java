package game.Bonfires;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * class that lights bonfires when player interacts for the first time
 */
public class LightBonfireAction extends Action {
    /**
     * displays that the bonfire is lit and is now capable of being interacted with
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string containing bonfire is lit message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return "Bonfire Lit";
    }

    /**
     * menu description that the player sees in the console
     * @param actor The actor performing the action.
     * @return string containing description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Player lights the Bonfire";
    }
}
