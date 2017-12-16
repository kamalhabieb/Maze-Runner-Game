package models.charcter;

public interface PlayerObserver {
    void notifyAboutHealth(int effect);
    void notifyAboutAmmo(int effect);
    void notifyAboutLives(int effect);



}
