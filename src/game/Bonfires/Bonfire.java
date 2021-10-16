package game.Bonfires;
import edu.monash.fit2099.engine.Ground;
import java.util.ArrayList;

/**
 * class that keeps record of all the available bonfires to player
 */
public class Bonfire{

    private ArrayList<Ground> bonfires;

    /**
     * constructor for Bonfire class it automatically adds Fire link Shrine to the list
     */
    public Bonfire() {
        this.bonfires = new ArrayList<>();
        addBonfire(new FirelinkShrineBonfire());
    }

    /**
     * gets the list containing all bonfires
     * @return array list of bonfires
     */
    public ArrayList<Ground> getBonfiresList() {
        return bonfires;
    }

    /**
     * adds bonfire to list of bonfires
     * @param bonfire the bonfire that is to be added to the list of bonfires
     */
    public void addBonfire( Ground bonfire){
        bonfires.add(bonfire);}

    }


