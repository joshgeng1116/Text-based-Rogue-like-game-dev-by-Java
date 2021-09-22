package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class StormRulerChargeAction extends Action {
    private StormRuler stormRuler;

    public StormRulerChargeAction(StormRuler stormRuler) {
        this.stormRuler = stormRuler;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        stormRuler.charge();
        return "Charge Storm Ruler";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled charges Storm Ruler";
    }
}
