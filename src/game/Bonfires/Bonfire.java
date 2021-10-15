package game.Bonfires;
import edu.monash.fit2099.engine.Ground;
import java.util.ArrayList;

public class Bonfire{
    private ArrayList<Ground> bonfires;

    public Bonfire() {
        this.bonfires = new ArrayList<>();
        addBonfire(new FirelinkShrineBonfire());
    }

    public ArrayList<Ground> getBonfiresList() {
        return bonfires;
    }

    public void addBonfire( Ground bonfire){
        bonfires.add(bonfire);}

    }


