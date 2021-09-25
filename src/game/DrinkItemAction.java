package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class DrinkItemAction extends Action {

    public DrinkItemAction() {

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Actions actions = new Actions();
        actions.add(new EstusFlask());

        return null;

    }

    @Override
    public String menuDescription(Actor actor) {
        return "Drink Item";
    }

    @Override
    public String hotkey() {
        return null;
    }
}
