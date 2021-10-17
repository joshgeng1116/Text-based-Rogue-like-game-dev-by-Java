package game.groundNitem;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.Player;

public class CinderOfYhorm extends Item {
    /***
     * Constructor.
     */
    public CinderOfYhorm() {
        super("Cinder of Yhorm", 'y', true);
    }

    @Override
    public void tick(Location location) {
        if(location.getActor() instanceof Player) {
            Player player = (Player) location.getActor();
            player.addItemToInventory(this);
            location.removeItem(this);
        }
        super.tick(location);
    }
}
