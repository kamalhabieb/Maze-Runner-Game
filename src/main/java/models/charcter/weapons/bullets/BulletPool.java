package models.charcter.weapons.bullets;

import models.facade.ControlTower;

import java.util.Properties;


public class BulletPool extends ObjectPool<Bullet> {

    public static final Object DAMAGE_RATE = "damage";
    public static final Object LIFETIME = "lifetime";
    public static final Object VELOCITY = "velocity";
    public static final Object ACCELERATION = "acc";
    private static final Object X = "x";
    public static final Object Y = "y";
    private static BulletPool bulletPool;
    private Properties properties;
    private ControlTower controlTower;

    @Override
    protected Bullet create() {
        return new BulletImpl(controlTower);
    }

    @Override
    public boolean validate(final Bullet o) {
        return o.getBirthtime() - System.currentTimeMillis() >= o.getLifetime();
    }

    public Bullet checkOut(Properties properties,ControlTower controlTower) {
        this.properties = properties;
        this.controlTower = controlTower;
        return checkOut();
    }

    @Override
    protected void adjust(final Bullet bullet) {
        bullet.setBirthtime((int) System.currentTimeMillis());
        bullet.setDamageRate((Integer) properties.get(DAMAGE_RATE));
        bullet.setLifetime((Integer) properties.get(LIFETIME));
        bullet.setVelocity((Integer) properties.get(VELOCITY));
        bullet.setAcceleration((Integer) properties.get(ACCELERATION));
    }

    public static BulletPool getInstance() {
        if (bulletPool == null) bulletPool = new BulletPool();
        return bulletPool;
    }
}
