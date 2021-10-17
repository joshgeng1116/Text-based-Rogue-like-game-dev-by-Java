package game.vendors;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.SwapWeaponAction;
import game.groundNitem.CinderOfDevourer;
import game.groundNitem.CinderOfYhorm;
import game.weapons_and_skills.Longbow;
import game.weapons_and_skills.Machete;

/**
 * class used for trading lord of cinders
 */
public class TradingAction extends Action {

    private CinderOfYhorm cinderOfYhorm;
    private CinderOfDevourer cinderOfDevourer;
    private char c;
    private char Y;
    private char D;

    /**
     * constructor for which lord of cinders will be traded with
     */
    public TradingAction(char c) {
        super();
        this.c = c;
    }

    /**
     * swaps the lord of cinder on player (if in inventory) with the weapon of the lord
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return string displaying if the trade is successful or not
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        if (c == Y) {
            if (player.getInventory().contains(cinderOfYhorm)) {
                SwapWeaponAction w = new SwapWeaponAction(new Machete(60));
                return "Cinder of " + "Yhorm" + " traded successfully, " + w.execute(actor, map);
            }
        }
        if (c == D) {
            if (player.getInventory().contains(cinderOfDevourer)) {
                SwapWeaponAction w = new SwapWeaponAction(new Longbow());
                return "Cinder of " + "Devourer" + " traded successfully, " + w.execute(actor, map);
            }
        }
        return "Cinder of Yhorm and Cinder of Devourer does not exist in Inventory";
    }


    /**
     * menu description that appears to player in console
     *
     * @param actor The actor performing the action.
     * @return string for trading the lord of cinder
     */
    @Override
    public String menuDescription(Actor actor) {
        if (c == Y) {return "Unkindled trades Cinder of Yhorm the Giant";}
        if (c == D) {return "Unkindled trades Cinder of Aldrich the Devourer";}
        return "Unkindled trades Cinder of Lord";
    }
}

