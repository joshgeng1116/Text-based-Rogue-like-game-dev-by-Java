package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * class that executes when buying increase Max HP is selected
 */
public class VendorBuyHPAction extends VendorBuyAction{
    private final int costHP = 200;
    private final int increaseHP = 25;

    /**
     * Method that executes when unkindled wants to increase Max HP
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string indicating if the purchase was successful
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        if (player.getSouls() > costHP){
            player.subtractSouls(costHP);
            player.increaseMaxHp(increaseHP);
            return "Max HP brought successfully, Max HP increased by "+increaseHP;}
        else{
            return "Not enough Souls to purchase increase Max HP";
        }
    }

    /**
     * menu description for buying Max HP Modifier
     * @param actor The actor performing the action.
     * @return string containing purchase description
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled buys Max HP modifier (+25HP): 200 souls";
    }
}
