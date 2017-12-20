package models.wall;
import javafx.scene.image.Image;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;
import views.Drawable;
import views.flyweight.WallImage;

public class WallCell extends Drawable implements Wall  {

    private boolean breakable ;
    private int MAX_HEALTH=50;
    private int HALF_HEALTH=25;
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
        if (breakable && health > MIN_HEALTH)
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

            if (health<=HALF_HEALTH)
            {
                return WallImage.getImage(WallImage.breakingState1);
            }

            return WallImage.getImage(WallImage.breakable);
        }

        return WallImage.getImage(WallImage.unBreakable);
    }

    @Override
    public void accept(final Visitor visitor) {

    }

}