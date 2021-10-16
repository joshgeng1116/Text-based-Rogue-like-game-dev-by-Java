package game.vendors;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.GameWeaponItem;
import game.Player;
import game.SwapWeaponAction;


/**
 * Class used to purchase items
 */
public class PurchaseAction extends Action{
    GameWeaponItem weaponItem;

    /**
     * constructor of purchase action class
     * @param weapon weapon that wants to be purchased
     */
    public PurchaseAction(GameWeaponItem weapon) {
        super();
        weaponItem = weapon;
    }

    /**
     * checks if player has enough souls and if yes, swaps the current weapon item with the one purchased
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string saying the actor has either purchased successfully or not
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        if (player.getSouls() >= weaponItem.cost()){
            player.subtractSouls(weaponItem.cost());
            SwapWeaponAction w = new SwapWeaponAction(weaponItem);
            return "Broadsword brought successfully, "+ w.execute(actor, map);}
        else{
            return "Not enough Souls to purchase "+ weaponItem.weaponName();
        }}

    /**
     * menu description that appears to player in console
     * @param actor The actor performing the action.
     * @return string saying what weapon is available and its cost
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled buys "+ weaponItem.weaponName()+ "("+weaponItem.cost()+" souls)";
    }
}
