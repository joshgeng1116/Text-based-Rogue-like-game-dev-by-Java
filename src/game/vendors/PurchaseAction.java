package game.vendors;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.GameWeaponItem;
import game.Player;
import game.SwapWeaponAction;


/**
 * Parent class of the VendorBuy[Item]Action classes, all the actions that vendor wants to do, will be extended from this class
 */
public class PurchaseAction extends Action{
    GameWeaponItem weaponItem;

    public PurchaseAction(GameWeaponItem weapon) {
        super();
        weaponItem = weapon;
    }

    /**
     * In the class that if a new vendor Buy action class is doing nothing (does no purpose)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string saying the actor is doing nothing
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
     * prints the string saying that the actor is doing nothing (no actions were conducted)
     * @param actor The actor performing the action.
     * @return string saying that the actor is doing nothing
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled buys "+ weaponItem.weaponName()+ "("+weaponItem.cost()+" souls)";
    }
}
