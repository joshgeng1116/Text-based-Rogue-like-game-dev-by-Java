package game;

import edu.monash.fit2099.engine.*;
import game.enemies_and_behaviours.LordOfCinder;
import game.enemies_and_behaviours.Skeleton;
import game.weapons_and_skills.StormRuler;

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

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),  new Bonfire(), new Cemetery(), new Vendor());

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
					"..............+.......c..............................................++.........",
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

			Actor player = new Player("Unkindled (Player)", '@', 100);
			world.addPlayer(player, gameMap.at(38, 12));

			// Place Yhorm the Giant/boss in the map
			gameMap.at(6, 25).addActor(new LordOfCinder("Yhorm the Giant", 'Y', 500));
			gameMap.at(7, 25).addItem(new StormRuler());

			Random random = new Random(1);

		// Place a Hollow in the the map
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
		world.run();
	}
}
