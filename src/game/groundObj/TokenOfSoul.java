package game.groundObj;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.Player;

public class TokenOfSoul extends Item {

    private int souls;

    /**
     * Constructor.
     */
    public TokenOfSoul(int souls) {
        super("Token of Soul",'$', true);
        this.souls = souls;
    }

    /**
     * allow player to collect the souls when it on dying spot
     * @param location
     */
    @Override
    public void tick(Location location) {
        if(location.getActor() instanceof Player) {
            Player player = (Player) location.getActor();
            player.addSouls(souls);
            location.removeItem(this);
        }
        super.tick(location);
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }
}
