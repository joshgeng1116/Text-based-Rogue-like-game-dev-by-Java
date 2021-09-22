package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class VendorBuyGiantAxeAction extends VendorBuyAction{
    @Override
    public String execute(Actor actor, GameMap map) {
        return super.execute(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled buys Giant Axe (1000 souls)";
    }
}
