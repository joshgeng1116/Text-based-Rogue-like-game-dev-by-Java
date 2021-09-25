package game.weapons_and_skills;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.weapons_and_skills.StormRuler;

public class StormRulerChargeAction extends Action {
    private StormRuler stormRuler;

    public StormRulerChargeAction(StormRuler stormRuler) {
        this.stormRuler = stormRuler;
    }

    /**
     * charge the storm ruler
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string which tell player storm ruler charged
     */
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
