package game.vendors;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enemies_and_behaviours.LordOfCinder;

/**
 * classs used for trading lord of cinders
 */
public class TradingAction extends Action {
    LordOfCinder boss;

    /**
     * constructor for which lord of cinders will be traded with
     * @param boss
     */
    public TradingAction(LordOfCinder boss) {
        this.boss = boss;
    }

    /**
     * swaps the lord of cinder on player (if in inventory) with the weapon of the lord
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string displaying if the trade is successful or not
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return null; /* unable to progress cos lord of cinder classes not implemented*/
    }

    /**
     * menu description that appears to player in console
     * @param actor The actor performing the action.
     * @return string for trading the lord of cinder
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled trades Cinder of "+ boss.name();}/* Unkindled trades Cinder of Aldrich the Devourer/Yhorm the Giant*/

}
