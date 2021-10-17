package game;

import edu.monash.fit2099.engine.*;
import game.Bonfires.AnorLondoBonfire;
import game.Bonfires.FirelinkShrineBonfire;
import game.groundNitem.*;
import game.vendors.Vendor;

import java.util.Arrays;
import java.util.List;

public class EnterAnorLondoAction extends Action {
    /**
     * transports the player through the fog door
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String confirmation message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        GameMap NewMap = (GameMap) player.getMapManager().getMapList().get(1);
        map.removeActor(player);
        NewMap.addActor(player, NewMap.at(29,0));
        return  actor + "travels through the fog door";
    }
    /**
     * Outputs the description of the action
     * @param actor The actor performing the action.
     * @return action description
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "Travels to Andor Londo";
    }
}
