package game;

import edu.monash.fit2099.engine.*;

public class Bonfire extends Actor {
    //need to be implemented by Yee

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     */
    public Bonfire(String name, char displayChar) {
        super(name, displayChar, 0);

    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}