package game.Bonfires;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Player;

public class AnorLondoBonfire extends Ground {
    private boolean isBonfireLit = false;

    /**
     * Constructor
     */
    public AnorLondoBonfire() {
        super('A');
    }
    /**
     * Contains all the actions that AnorLondoBonfire can execute
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return array of actions that Bonfire can perform
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        Player player = (Player) actor;
        if (!isBonfireLit) {
            actions.add(new LightBonfireAction());
            isBonfireLit = true;
            player.getBonfires().addBonfire(this);}
        else {
            actions.add(new BonfireResetAction("Anor Londo Bonfire"));
            for (Ground i : player.getBonfires().getBonfiresList()) {
                if (i != this) {
                    //actions.add(new TeleportAction(i)); /* need teleportation implementation from Tawana to be finished first*/
                }
            }}
        return actions;
    }
}
