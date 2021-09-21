package game;

import edu.monash.fit2099.engine.*;

public class Vendor extends Actor {
    private final Menu menu = new Menu();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     *
     */
    public Vendor(String name, char displayChar) {
        super(name, displayChar, 0);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        /*Actions a = new Actions(Player player, GameWeaponItem ,"Unkindled buys Broadsword (500 souls)");
        a.add("Unkindled buys Giant Axe (1000 souls)", );
        a.add("Unkindled buys Storm Ruler (2000 souls)");
        a.add("Unkindled buys Max HP modifier (+25HP): 200 souls")
        */
        return action;
    }
    public void buyWeaponItem(GameWeaponItem w){
        //buying the weapon
    }

    public void buyMaxHP(){
        //increasing max HP
    }
}
