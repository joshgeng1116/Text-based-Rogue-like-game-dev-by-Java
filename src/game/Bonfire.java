package game;

import edu.monash.fit2099.engine.*;

public class Bonfire extends Ground {
    //need to be implemented by Yee
    public Bonfire() {
        super('B');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions =new Actions();
        actions.add(new BonfireResetAction());
        return actions;
    }
}