package game.groundNitem;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.EnterAndorLondoAction;

public class FogDoor extends Ground {
    /**
     * Constructor.
     *
     */
    public FogDoor() {
        super('=');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions =  new Actions();
        actions.add(new EnterAndorLondoAction());
        return actions;
    }
}
