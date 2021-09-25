package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * class that executes when buy Giant Axe is selected
 */
public class VendorBuyGiantAxeAction extends VendorBuyAction{
    private GiantAxe giantAxe = new GiantAxe();

    /**
     * Method that executes when unkindled wants to buy and swap weapon with Giant Axe
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string indicating if purchase was successful
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        if (player.getSouls() > GiantAxe.cost()){
            player.subtractSouls(GiantAxe.cost());
            SwapWeaponAction gA =new SwapWeaponAction(giantAxe);
            return "Giant Axe brought successfully, "+ gA.execute(actor, map) ;} //need a reference to the Giant Axe
        else{
            return "Not enough Souls to purchase Giant Axe";
        }}

    /**
     * menu description for buying Giant Axe
     * @param actor The actor performing the action.
     * @return string containing purchase description
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled buys Giant Axe (1000 souls)";
    }
}
