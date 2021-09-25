package game.weapons_and_skills;

import edu.monash.fit2099.engine.Action;
import game.GameWeaponItem;

import java.util.ArrayList;
import java.util.List;

public class StormRuler extends GameWeaponItem {

    private int chargeTimes;
    private int maxChargeTimes = 3;

    /**
     * Constructor of StormRuler.
     */
    public StormRuler() {
        super("StormRuler", '7', 70, "hits", 60);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new StormRulerChargeAction(this));
        return actions;
    }


    public boolean isFullCharged() {
        return chargeTimes==maxChargeTimes;
    }

    public void charge() {
        chargeTimes++;
        if (chargeTimes > maxChargeTimes) {
            chargeTimes = 0;
        }
        if (chargeTimes < maxChargeTimes) {
            damage = 70;
            hitRate = 60;
        } else {
            damage = 140;
            hitRate = 100;
        }
    }

    @Override
    public String toString() {
        if (chargeTimes == 0) {
            return super.toString();
        } else {
            if (chargeTimes < maxChargeTimes) {
                return String.format("%s(CHARGING)", super.toString());
            } else {
                return String.format("%s(FULLY CHARGED)", super.toString());
            }
        }
    }
}
