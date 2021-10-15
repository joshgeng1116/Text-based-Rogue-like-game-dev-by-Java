package game.Bonfires;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class AnorLondoBonfire extends Ground {

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public AnorLondoBonfire(char displayChar) {
        super('A');
    }
    /**
     * Contains all the actions that Bonfire can execute
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return array of actions that Bonfire can perform
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions =new Actions();
        if !isBonfireLit(){
            actions.add( new LightBonfireAction());
        }else {actions.add( new BonfireResetAction("Anor Londo Bonfire"));}
        return actions;
}}
