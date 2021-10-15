package game.vendors;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.vendors.VendorBuyBroadswordAction;

/**
 *  Vendor class that the player is able to trade in souls to buy items from, Vendor/FireLink is located next to the bonfire shrine
 */
public class Vendor extends Ground {
    /**
     * Constructor of Vendor Class
     */
    public Vendor() {
        super('F');
    }

    /**
     * Contains all the actions that Vendor/Fire Keeper can execute
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return list of actions that Vendor/Fire Keeper is able to perform
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions =new Actions();
        actions.add(new VendorBuyBroadswordAction());
        actions.add(new VendorBuyGiantAxeAction());
        actions.add(new VendorBuyHPAction());
        return actions;
    }
}

