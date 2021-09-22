package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class VendorBuyHPAction extends VendorBuyAction{
    @Override
    public String execute(Actor actor, GameMap map) {
        return super.execute(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled buys Max HP modifier (+25HP): 200 souls";
    }
}
