package game.weapons_and_skills;

import edu.monash.fit2099.engine.Action;
import game.GameWeaponItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StormRuler extends GameWeaponItem {

    Random random = new Random();
    private int chargeTimes;
    private int maxChargeTimes = 3;

    /**
     * Constructor of StormRuler.
     */
    public StormRuler() {
        super("StormRuler", '7', 70, "hits", 60);
    }

    /**
     *add charging action to actions list
     * @return actions
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new StormRulerChargeAction(this));
        return actions;
    }


    /**
     * check if the storm ruler fully charge or not
     * @return true or false storm ruler fully charged or not
     */
    public boolean isFullCharged() {
        return chargeTimes==maxChargeTimes;
    }

    /**
     *20% double the damage
     * @return damage
     */
    @Override
    public int damage() {
        if (random.nextInt(100) < 20) {
            // Critical Strike
            return damage * 2;
        }
        return super.damage();
    }

    /**
     *charge the storm ruler
     * this method also will be called when storm ruler skill execute and change the damage and hit rate
     */
    public void charge() {
        if (chargeTimes < maxChargeTimes) {
            damage = 70;
            hitRate = 60;
            chargeTimes++;
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
