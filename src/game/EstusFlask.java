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

    /**
     * Accessor for the maximum number of estus flask charges
     * @return max number of estus flask charges
     */
    public int getTotal() {
        return total;
    }

    /**
     * Accessor for current number of estus flask charges
     * @return current estus flask charges
     */
    public int getCurrent() {
        return current;
    }

    /**
     * A method for resetting the estus flask
     */
    public void reset() {
        total = 3;
        current = 3;
    }

    /**
     * A method for determining if the player has any charges left
     * @return true if the player is able to drink
     */
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
