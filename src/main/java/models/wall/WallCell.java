package models.wall;

public class WallCell implements Wall {

    private boolean breakable ;
    private int MAX_HEALTH=100;
    private int MIN_HEALTH=0;
    private int health ;

    public WallCell()
    {



        health = MAX_HEALTH;
    }


    @Override
    public void setCellState(boolean makeBreakable) {
        if(makeBreakable)
            breakable=true;
        else breakable=false;
    }

    @Override
    public boolean getCellState() {
        if (breakable)
            return true;
        return false;

    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean affectHealthBy(int effect) {
        if (breakable)
        {
            health-=effect;
            return true;
        }
        return false;
    }
}