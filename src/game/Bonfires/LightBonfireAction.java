package game.Bonfires;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class LightBonfireAction extends Action {
    private boolean bonfireLit = false;

    @Override
    public String execute(Actor actor, GameMap map) {
        this.bonfireLit = true;
        return "Bonfire Lit";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Player lights the Bonfire";
    }
}
