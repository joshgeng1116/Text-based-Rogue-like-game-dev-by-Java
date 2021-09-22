package game;

import edu.monash.fit2099.engine.Action;

import java.util.ArrayList;
import java.util.List;

public class StormRuler extends GameWeaponItem{

    private int chargeTimes;
    private int maxChargeTimes = 4;

    /**
     * Constructor of StormRuler.
     */
    public StormRuler() {
        super("StormRuler", '7', 70, "hits", 60);
    }

    @Override
    public List<Action> getAllowableActions() {

        List<Action> actions = new ArrayList<>();
        if (chargeTimes == 0) {
            actions.addAll(super.getAllowableActions());
            actions.add(new StormRulerChargeAction(this));
        } else if (chargeTimes < maxChargeTimes) {
            actions.add(new StormRulerChargeAction(this));
        } else {
            actions.add(new StormRulerChargeAction(this));
        }
        return actions;
    }

    public void charge() {
        chargeTimes++;
        if (chargeTimes > maxChargeTimes) {
            chargeTimes = 0;
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
