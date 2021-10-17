package game.vendors;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.weapons_and_skills.Broadsword;
import game.weapons_and_skills.GiantAxe;
import game.weapons_and_skills.StormRuler;

/**
 *  Vendor class that the player is able to trade in souls to buy items from, Vendor is located next to the bonfire shrine
 */
public class Vendor extends Ground {
    /**
     * Constructor of Vendor Class
     */
    public Vendor() {
        super('F');
    }

    /**
     * Contains all the actions that Vendor can execute
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return list of actions that Vendor is able to perform
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions =new Actions();
        actions.add(new PurchaseAction(new Broadsword()));
        actions.add(new PurchaseAction(new GiantAxe()));
        actions.add(new PurchaseAction(new StormRuler()));
        actions.add(new VendorBuyHPAction());
        actions.add(new TradingAction('Y'));
        actions.add(new TradingAction('D'));
        return actions;
    }
}

