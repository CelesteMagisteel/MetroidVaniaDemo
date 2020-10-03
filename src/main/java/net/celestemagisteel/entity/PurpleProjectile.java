package net.celestemagisteel.entity;

import net.celestemagisteel.events.EntityMoveEvent;
import net.celestemagisteel.handlers.EventManager;
import net.celestemagisteel.map.Location;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

public class PurpleProjectile extends Projectile {

    private static final long PROJECTILE_FLIGHT_SPEED = 1000 / 10;

    public PurpleProjectile(String sprite, int maxHealth, int x, int y) throws IOException {
        super(sprite, maxHealth, x, y);
        Entity thisEntity = this;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    EventManager.raiseEvent(new EntityMoveEvent(thisEntity, new Location(thisEntity.getX(), thisEntity.getY()), new Location(thisEntity.getX() + 1, thisEntity.getY())));
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }, PROJECTILE_FLIGHT_SPEED, PROJECTILE_FLIGHT_SPEED);
    }

}
