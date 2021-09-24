package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class VendorBuyGiantAxeAction extends VendorBuyAction{
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        if (player.getSouls() > GiantAxe.cost()){
            player.subtractSouls(GiantAxe.cost());
            return SwapWeaponAction(GiantAxe); //need a reference to the Giant Axe
        }else{
            return "Not enough Souls to purchase Giant Axe";
        }}

    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled buys Giant Axe (1000 souls)";
    }
}
