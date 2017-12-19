package models.charcter;

import models.Observer.Observer;

public interface PlayerObserver extends Observer {
    void notifyAboutHealth(int effect);

    void notifyAboutAmmo(int effect);

    void notifyAboutLives(int effect);


}
