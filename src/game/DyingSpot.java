package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class DyingSpot extends Item {

    private int souls;

    /**
     * Constructor.
     */
    public DyingSpot(int souls) {
        super("DyingSpot",'$', true);
        this.souls = souls;
    }

    @Override
    public void tick(Location location) {
        if(location.getActor() instanceof Player) {
            Player player = (Player) location.getActor();
            player.addSouls(souls);

        }
        super.tick(location);
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }
}
