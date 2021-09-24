package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class VendorBuyBroadswordAction extends VendorBuyAction{
    /**
     * Method that executes when unkindled wants to buy and swap weapon with Broadsword
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string indicating if purchase was successful
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        if (player.getSouls() > Broadsword.cost()){
            player.subtractSouls(Broadsword.cost());
            return "Broadsword brought successfully, "+SwapWeaponAction(Broadsword);} //need a reference to the Broadsword
        else{
            return "Not enough Souls to purchase Broadsword";
        }

    /**
     * menu description for buying Broadsword
     * @param actor The actor performing the action.
     * @return string containing purchase description
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled buys Broadsword (500 souls)";
    }
}
