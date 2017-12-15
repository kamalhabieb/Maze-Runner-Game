package models.wall;
import javafx.scene.image.Image;
import views.Drawable;
import views.flyweight.WallImage;

public class WallCell extends Drawable implements Wall  {

    private boolean breakable ;
    private int MAX_HEALTH=100;
    private int THREE_QUARTERS_HEALTH=75;
    private int HALF_HEALTH=50;
    private int QUARTER_HEALTH=25;
    private int MIN_HEALTH=0;
    private int health ;

    public WallCell()
    {
        health = MAX_HEALTH;
    }


    @Override
    public void setBreakable(boolean breakable) {
        if(breakable)
            this.breakable=true;
        else this.breakable=false;
    }

    @Override
    public boolean isBreakable() {
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

    @Override
    public Image getImage() {

        if (this.isBreakable())
        {
            int health = this.getHealth();

            if (health<=THREE_QUARTERS_HEALTH)
            {
                return WallImage.getImage(WallImage.breakingState1);
            }else if (health<=HALF_HEALTH)
            {
                return WallImage.getImage(WallImage.breakingState2);
            }else if (health<=QUARTER_HEALTH)
            {
                return WallImage.getImage(WallImage.breakingState3);
            }

            return WallImage.getImage(WallImage.breakable);
        }

        return WallImage.getImage(WallImage.unBreakable);
    }
}