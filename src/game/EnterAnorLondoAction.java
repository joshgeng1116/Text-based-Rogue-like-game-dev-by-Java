package game;

import edu.monash.fit2099.engine.*;
import game.Bonfires.AnorLondoBonfire;
import game.Bonfires.FirelinkShrineBonfire;
import game.groundNitem.*;
import game.vendors.Vendor;

import java.util.Arrays;
import java.util.List;

public class EnterAnorLondoAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        GameMap NewMap = (GameMap) player.getMapManager().getMapList().get(1);
        map.removeActor(player);
        NewMap.addActor(player, NewMap.at(29,0));
        return  actor + "travels through the fog door";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "Travels to Andor Londo";
    }
}
