package game;

import edu.monash.fit2099.engine.Actor;
import game.interfaces.Resettable;

public class EstusFlask implements Resettable {

    private int maxCharges;
    private int currentCharges;

    public EstusFlask(int maxCharges, int currentCharges) {
        this.maxCharges = maxCharges;
        this.currentCharges = currentCharges;
    }

    public void drinkEstusFlask(Actor player){
        if(currentCharges > 0  ){
            player.heal(40);
        }
    }

    @Override
    public void resetInstance() {
        currentCharges = maxCharges;


    }

    @Override
    public boolean isExist() {
        return false;
    }


}
