package models.facade;

import models.charcter.PlayerObserver;

public class Score implements PlayerObserver {
    private static final double GIFT_FACTOR = 10.0;
    private int currentScore;

    public Score() {
        this.currentScore = 0;
    }

    @Override
    public void notifyAboutHealth(int effect) {
        currentScore += Math.ceil(effect / GIFT_FACTOR);
    }

    @Override
    public void notifyAboutAmmo(int effect) {
        currentScore += Math.ceil(effect / GIFT_FACTOR);

    }

    @Override
    public void notifyAboutLives(int effect) {
        currentScore += effect*50;

    }
}
