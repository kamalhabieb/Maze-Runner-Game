package models.facade;

import views.Drawable;

import java.util.ArrayList;

public interface DrawObserver {
    void notifyDraw(ArrayList<Drawable> drawables);
}
