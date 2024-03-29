package game.weapons_and_skills;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.AttackAction;
import game.weapons_and_skills.StormRuler;

public class StormRulerStunAction extends AttackAction {
    public StormRulerStunAction(Actor target, String direction) {
        super(target, direction);
    }

    /**
     * double the damage and increase the hit chance in an attack action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of attack action with using storm ruler's skill
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        StormRuler stormRuler = (StormRuler) actor.getWeapon();
        String r = super.execute(actor, map);
        stormRuler.charge();
        return r;
    }

    public String menuDescription(Actor actor) {
        return actor + " stuns " + target + " at " + direction;
    }
}
