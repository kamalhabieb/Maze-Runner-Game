package models.charcter;

import models.engine.Matter;

public interface AliveObject extends Matter {

    /**
     * Get the health of the character.
     * @return the health bound by 0 - 100
     */
    int getHealth();

    /**
     *  Affect the character health by some effect. If the effect is positive, the health increases by that much.
     *  Else, the health decreases by that much. However, if the effect causes the health to surpass either bounds,
     *  the health will be set to that bound, the rest of the effect is neglected.
     * @param effect the magnitude of the effect.
     * @return true if the health is affected by any amount in that range ]0,effect], false otherwise.
     */
    boolean affectHealthBy(int effect);
}
