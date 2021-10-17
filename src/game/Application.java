package game;


import edu.monash.fit2099.engine.*;
import game.Bonfires.AnorLondoBonfire;
import game.Bonfires.FirelinkShrineBonfire;
import game.enemies_and_behaviours.Yhorm;
import game.enemies_and_behaviours.Skeleton;
import game.groundNitem.*;
import game.vendors.Vendor;
import game.weapons_and_skills.StormRuler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());


			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),  new FirelinkShrineBonfire(), new Cemetery(), new Vendor(), new Ambiguous(), new FogDoor(), new AnorLondoBonfire());

			List<String> map = Arrays.asList(
					"..++++++..+++...........................++++......+++.................+++.......",
					"........+++++..............................+++++++.................+++++........",
					"...........+++.......................................................+++++......",
					"........................................................................++......",
					".........................................................................+++....",
					"............................+.............................................+++...",
					".............................+++..c....++++.....................................",
					".............................++.......+......................++++...............",
					".............................................................+++++++............",
					".................c................###___###...................+++...............",
					"..................................#_______#......................+++............",
					"...........++.....................#__FB___#.......................+.............",
					".........+++......................#_______#...........c............++...........",
					"............+++...................####_####..........................+..........",
					"..............+.......c..............?=..............................++.........",
					"..............++.................................................++++++.........",
					"............+++...................................c...............++++..........",
					"+..................................................................++...........",
					"++...+++.........................................................++++...........",
					"+++......................................+++........................+.++........",
					"++++.......++++.........................++.........................+....++......",
					"#####___#####++++......................+...............................+..+.....",
					"_..._....._._#.++......................+...................................+....",
					"...+.__..+...#+++...........................................................+...",
					"...+.....+._.#.+.....+++++...++..............................................++.",
					"___.......___#.++++++++++++++.+++.............................................++");
			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);


			Player player = new Player("Unkindled (Player)", '@', 1000);
			world.addPlayer(player, gameMap.at(38, 12));

			player.getMapManager().addMap(gameMap);

			// Place Yhorm the Giant/boss in the map
			gameMap.at(6, 25).addActor(new Yhorm("Yhorm the Giant", 'Y', 500));
			gameMap.at(7, 25).addItem(new StormRuler());

			Random random = new Random(1);

		// Place a Hollow in the map
		// Place cemetery
		for (int i = 0; i < 4; i++) {
			Skeleton skeleton = new Skeleton("s"+i);
			while (true) {
				int x = random.nextInt(gameMap.getXRange().max() - gameMap.getXRange().min()) + gameMap.getXRange().min();
				int y = random.nextInt(gameMap.getYRange().max() - gameMap.getYRange().min()) + gameMap.getYRange().min();
				if (gameMap.at(x, y).getActor() == null && gameMap.at(x, y).getGround().getDisplayChar() == '.') {
					gameMap.at(x, y).addActor(skeleton);
					break;
				}
			}
		}
		List<String> map1 = Arrays.asList(
				"..++++++..+++................=..........++++......+++.................+++.......",
				"........+++++..............................+++++++.................+++++........",
				"...........+++.............#...A....#................................+++++......",
				"...........+++++...........#........#...................................++......",
				".........+++++++++.........##########....................................+++....",
				".............#............................................................+++...",
				"..####.......#....................c.............................................",
				"..#..........#.........................................c.....++++...............",
				"..#..........#...............................................+++++++............",
				"..####...........c............................................+++...............",
				"........................................................#........+++............",
				"...........++.....................................#.....#.........+.............",
				".........+++......................................######...........++...........",
				"............+++......................................................+..........",
				"..............+.......c..............................................++.........",
				"..............++.............................+...................++++++.........",
				"............+++.............................+++...c...............++++..........",
				"+..........................................++++++..................++...........",
				"++...+++............#.......############..++++++++++.............++++...........",
				"+++................#.........+....#...#...+++++++...................+.++........",
				"++++..............#..#...............#...++++++....................+....++......",
				".++++............#..................#...++++...........................+..+.....",
				"..++++++........#..............+...#......+................................+....",
				"...+++++++.....#..+...............#.........................c...............+...",
				"..+++++++++....###################...........................................++.",
				"...++++++++++.................................................................++");
		GameMap gameMap2 = new GameMap(groundFactory, map1);
		world.addGameMap(gameMap2);
		player.getMapManager().addMap(gameMap2);
		/**
		 * Random skeleton generator for second map
		 */

		for (int i = 0; i < 4; i++) {
			Skeleton skeleton = new Skeleton("s"+i);
			while (true) {
				int x = random.nextInt(gameMap2.getXRange().max() - gameMap2.getXRange().min()) + gameMap2.getXRange().min();
				int y = random.nextInt(gameMap2.getYRange().max() - gameMap2.getYRange().min()) + gameMap2.getYRange().min();
				if (gameMap2.at(x, y).getActor() == null && gameMap.at(x, y).getGround().getDisplayChar() == '.') {
					gameMap2.at(x, y).addActor(skeleton);
					break;
				}
			}
		}

		world.run();
	}
}
