package game.Bonfires;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * It is an area in
 * the middle of the map that is surrounded by the walls (displayed as “#”, the player or
 * any other character cannot cross a wall), and its ground is made of Floors (one Floor
 * is displayed as an underscore “_”). You can see a Bonfire (displayed as “B”) sitting
 * exactly in the middle.
 */
public class Bonfire extends Ground {
    /**
     * Constructor of Bonfire.
     *
     */
    public Bonfire() {
        super('B');
    }

    /**
     * Contains all the actions that Bonfire can execute
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return array of actions that Bonfire can perform
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions =new Actions();
        actions.add(new BonfireResetAction("Firelink Shrine"));
        return actions;
    }
}