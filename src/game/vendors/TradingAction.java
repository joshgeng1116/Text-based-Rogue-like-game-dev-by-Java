package game.vendors;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enemies_and_behaviours.LordOfCinder;

public class TradingAction extends Action {
    LordOfCinder boss;
    public TradingAction(LordOfCinder boss) {
        this.boss = boss;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return null; /* unable to progress cos lord of cinder classes not implemented*/
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled trades Cinder of "+ boss.name();}/* Unkindled trades Cinder of Aldrich the Devourer/Yhorm the Giant*/

}
