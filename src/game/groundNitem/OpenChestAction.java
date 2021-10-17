package game.groundNitem;

import edu.monash.fit2099.engine.*;
import game.Dirt;
import game.enemies_and_behaviours.Mimi;

import java.util.Random;

public class OpenChestAction extends Action {
    Random random = new Random();
    Random random1 = new Random();
    Location location;
    Ground dirt = new Dirt();

    /**
     *
     * @param location
     */
    public OpenChestAction(Location location){
        super();
        this.location = location;
    }

    /**
     * when it execute, 50% add an actor Mimi, 50% add item token of soul on it location, token of soul have different souls
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (random.nextInt(2) == 0) {
            location.addActor(new Mimi("Mimi"));
            location.setGround(dirt);
            return "Unfortunately! It becomes a Mimi";
        }else {
            location.setGround(dirt);
            if(random1.nextInt(3) == 0){
                location.addItem(new TokenOfSoul(100));
                return "You are lucky! It becomes 100 souls";
            }else if(random1.nextInt(3)==1){
                location.addItem(new TokenOfSoul(200));
                return "You are lucky! It becomes 200 souls";
            }else {
                location.addItem(new TokenOfSoul(300));
                return "You are lucky! It becomes 300 souls";
            }
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Unkindled open the chest";
    }
}
