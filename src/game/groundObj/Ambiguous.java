package game.groundObj;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
public class Ambiguous extends Ground{
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Ambiguous() {
        super('?');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions actions =new Actions();
        actions.add(new OpenChestAction(location));
        return actions;
    }
}
