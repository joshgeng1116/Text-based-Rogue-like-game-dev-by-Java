package game.weapons_and_skills;

import edu.monash.fit2099.engine.Action;
import game.GameWeaponItem;

import java.util.ArrayList;
import java.util.List;

public class GiantAxe extends GameWeaponItem {

    /**
     * Constructor.
     *
     */
    public GiantAxe() {
        super("Giant Axe", 'g', 50, "hits", 80);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> result = new ArrayList<>();
        result.add(new SpinAttackAction());
        return result;
    }

    public static int cost(){
        return 1000;
    }
}
