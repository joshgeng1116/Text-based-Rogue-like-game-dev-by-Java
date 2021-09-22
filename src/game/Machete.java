package game;

public class Machete extends GameWeaponItem{
    private LordOfCinder lordOfCinder;

    /**
     * Constructor.
     */
    public Machete(LordOfCinder lordOfCinder) {
        super("Yhorm’s Great Machete", 'y', 95, "hit", 60);
        this.lordOfCinder = lordOfCinder;
    }

    @Override
    public int chanceToHit() {
        if (lordOfCinder.isEmberForm()) {
            return 90;
        } else {
            return 60;
        }
    }
}
