package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;


public class Vendor extends Ground {
    /**
     * Constructor of Vendor Class
     */
    public Vendor() {
        super('F');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions =new Actions();
        actions.add(new VendorBuyBroadswordAction());
        actions.add(new VendorBuyGiantAxeAction());
        actions.add(new VendorBuyHPAction());
        return actions;
    }
}

