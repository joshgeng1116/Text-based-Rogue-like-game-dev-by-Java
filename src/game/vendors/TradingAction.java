package game.vendors;

import edu.monash.fit2099.engine.*;
import game.GameWeaponItem;
import game.Player;
import game.SwapWeaponAction;
import game.enemies_and_behaviours.Yhorm;

/**
 * classs used for trading lord of cinders
 */
public class TradingAction extends Action {
    Yhorm boss;
    GameWeaponItem weapon;
    /**
     * constructor for which lord of cinders will be traded with
     * @param boss
     */
    public TradingAction(Yhorm boss) {
        this.boss = boss;
        this.weapon = boss.getWeapon();
    }

    /**
     * swaps the lord of cinder on player (if in inventory) with the weapon of the lord
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string displaying if the trade is successful or not
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        if (player.getInventory().contains(boss.drop())){
            SwapWeaponAction w = new SwapWeaponAction(boss.getWeapon());
            return "Cinder of "+boss.name()+" traded successfully, "+ w.execute(actor, map);}
        else{
            return "Cinder of "+boss.name()+"does not exist in Inventory";
        }}


    /**
     * menu description that appears to player in console
     * @param actor The actor performing the action.
     * @return string for trading the lord of cinder
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled trades Cinder of "+ boss.name();}/* Unkindled trades Cinder of Aldrich the Devourer/Yhorm the Giant*/

}
