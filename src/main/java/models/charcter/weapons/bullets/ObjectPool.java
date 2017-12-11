package models.charcter.weapons.bullets;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class ObjectPool<T> {


    protected ArrayList<T> locked, unlocked;

    public ObjectPool() {
        locked = new ArrayList<T>();
        unlocked = new ArrayList<T>();
    }

    protected abstract T create();

    public abstract boolean validate(T o);


    protected synchronized T checkOut() {
        T t;
        if (unlocked.size() > 0) {
            Iterator<T> e = unlocked.iterator();
            while (e.hasNext()) {
                t = e.next();

                if (validate(t)) {
                    unlocked.remove(t);
                    adjust(t);
                    locked.add(t);
                    return (t);
                } else {
                    // object failed validation
                    unlocked.remove(t);
                    t = null;
                }

            }
        }
        // no objects available, create a new one
        t = create();
        locked.add(t);
        return (t);
    }

    protected abstract void adjust(final T t);

    public synchronized void checkIn(T t) {
        locked.remove(t);
        unlocked.add(t);
    }

}
