package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;


    public class FogDoor extends Ground {
        /**
         * Constructor.
         *
         */
        public FogDoor() {
            super('=');
        }

        @Override
        public Actions allowableActions(Actor actor, Location location, String direction) {

            Actions actions =  new Actions();
            Player player = (Player) actor;
            if(player.getMapManager().getMapList().get(0).equals(location.map())){
                actions.add(new EnterAnorLondoAction());
            }
            else if(player.getMapManager().getMapList().get(1).equals(location.map())){
                actions.add(new EnterProfaneCapitalAction());
            }

            return actions;
        }
    }

