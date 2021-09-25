package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class DrinkItemAction extends Action {

    private EstusFlask estusFlask;

    public DrinkItemAction(EstusFlask estusFlask) {
        this.estusFlask = estusFlask;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        if (estusFlask.drink()) {
            int old = player.getHitPoints();
            int recover = (int) (player.getMaxHitPoints() * 0.4);
            player.heal(recover);

            int actualRecovered = player.getHitPoints() - old;
            return String.format("Recovered %d", actualRecovered);
        } else {
            return "Estus Flask is empty";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("Drink Estus Flask (%d/%d)", estusFlask.getCurrent(), estusFlask.getTotal());
    }
}
