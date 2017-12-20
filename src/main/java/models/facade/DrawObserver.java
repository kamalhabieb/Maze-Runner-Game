package models.facade;

import views.Drawable;

import java.util.ArrayList;
import java.util.List;

public interface DrawObserver {
    void notifyDraw(ArrayList<Drawable> drawables);

    void notifyDrawStatic(List<Drawable> drawables);

    void notifyDrawGameOver(List<Drawable> drawables);

    void notifyDrawWin(ArrayList<Drawable> drawables);
}
