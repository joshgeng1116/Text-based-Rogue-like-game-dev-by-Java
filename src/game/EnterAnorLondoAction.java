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
    public String execute(Actor actor, GameMap AndorLondo) {
        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(), new Cemetery(), new FogDoor(), new AnorLondoBonfire());

        List<String> map2 = Arrays.asList(
                "..++++++..+++................=..........++++......+++.................+++.......",
                "........+++++...........................A..+++++++.................+++++........",
                "...........+++.......................................................+++++......",
                "...........+++++........................................................++......",
                ".........+++++++++.......................................................+++....",
                ".............#...........+................................................+++...",
                ".............#...............+++..c....++++.....................................",
                ".............#...............++.......+......................++++...............",
                ".............#...............................................+++++++............",
                ".................c............................................+++...............",
                ".................................................................+++............",
                "...........++.....................................................+.............",
                ".........+++..........................................c............++...........",
                "............+++......................................................+..........",
                "..............+.......c..............................................++.........",
                "..............++.................................................++++++.........",
                "............+++...................................c...............++++..........",
                "+..................................................................++...........",
                "++...+++.........................................................++++...........",
                "+++......................................+++........................+.++........",
                "++++.......++++.........................++.........................+....++......",
                ".............++++......................+...............................+..+.....",
                "...............++......................+...................................+....",
                "..............+++...........................................................+...",
                "...............+.....+++++...++..............................................++.",
                "..............++++++++++++++.+++..............................................++");
        AndorLondo = new GameMap(groundFactory,map2);
        world.addGameMap(AndorLondo);
        world.addPlayer(actor, AndorLondo.at(29, 0));
        world.run();
        return actor + "travels through the fog door" ;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "Travels to Andor Londo";
    }
}
