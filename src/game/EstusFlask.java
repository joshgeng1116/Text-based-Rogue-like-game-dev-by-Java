package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.interfaces.Resettable;

public class EstusFlask extends Item implements Resettable {

    private int total;
    private int current;

    /***
     * Constructor of EstusFlask.
     */
    public EstusFlask() {
        super("Estus Flask", 'F', false);
        allowableActions.add(new DrinkItemAction(this));
        reset();
    }

    public int getTotal() {
        return total;
    }

    public int getCurrent() {
        return current;
    }

    public void reset() {
        total = 3;
        current = 3;
    }

    public boolean drink() {
        if (current > 0) {
            current--;
            return true;
        }
        return false;
    }

    @Override
    public void resetInstance() {
        current = total;
    }

    @Override
    public boolean isExist() {
        return false;
    }
}
