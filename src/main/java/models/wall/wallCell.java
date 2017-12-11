package models.wall;

public class wallCell implements wall {

    private String state = "breakable";
    private int MAX_HEALTH=100;
    private int MIN_HEALTH=0;
    private int health ;

    public wallCell ()
    {
        health = MAX_HEALTH;
    }


    @Override
    public void setCellState(boolean makeBreakable) {
        if(makeBreakable)
            state="breakable";
        else state="ubreakable";
    }

    @Override
    public boolean getCellState() {
        if (state=="breakable")
            return true;
        return false;

    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean affectHealthBy(int effect) {
        if (state=="breakable")
        {
            health-=effect;
            return true;
        }
        return false;
    }
}
