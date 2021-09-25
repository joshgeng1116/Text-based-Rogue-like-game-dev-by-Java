package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Resettable;

public class EstusFlask extends Action implements Resettable {

    private final int maxCharges = 3;
    private int currentCharges;

    public EstusFlask() {
        this.currentCharges = maxCharges;
    }


    public int getCurrentCharges() {
        return currentCharges;
    }

    @Override
    public void resetInstance() {
        currentCharges = maxCharges;
    }

    @Override
    public boolean isExist() {
        return false;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        if(currentCharges > 0  ){
            actor.heal(40);
            currentCharges--;
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "Drinks Estus Flask" + "(" + currentCharges + ")";
    }

    @Override
    public String hotkey() {
        return "a";
    }
}
