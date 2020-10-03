package net.celestemagisteel.engine;

import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.entity.Player;
import net.celestemagisteel.entity.PurpleProjectile;
import net.celestemagisteel.events.EntityFireProjectileEvent;
import net.celestemagisteel.events.EntityMoveEvent;
import net.celestemagisteel.handlers.EventManager;
import net.celestemagisteel.map.Location;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

public class ControlHandler extends TimerTask {

    private static final long MOVEMENT_TICK_SPEED = 1000 / 30;
    private static final long PROJECTILE_FIRE_SPEED = 1000 / 5;
    private boolean walkForward = false;
    private boolean walkBackwards = false;
    private boolean goUp = false;
    private boolean goDown = false;
    private boolean fireProjectile = false;
    private Player entity;
    private Timer fireTimer = new Timer();

    public ControlHandler(Player entity) {
        startMovementTimer(entity);
        this.entity = entity;
    }

    private void startMovementTimer(Entity entity) {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handleMovement(entity);
            }
        }, 0, MOVEMENT_TICK_SPEED);
    }

    private void handleMovement(Entity entity) {
        int x = entity.getX();
        int y = entity.getY();
        int newX = x;
        int newY = y;

        if (walkForward) newX++;
        if (walkBackwards) newX--;
        if (goUp) newY--;
        if (goDown) newY++;

        if (!(newX == x && newY == y)) {
            try {
                EventManager.raiseEvent(new EntityMoveEvent(entity, new Location(x, y), new Location(newX, newY)));
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void startProjectileTimer() {
        fireTimer = new Timer();
        fireTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (fireProjectile) {
                    try {
                        EventManager.raiseEvent(new EntityFireProjectileEvent(entity, new PurpleProjectile("projectile", 1, entity.getX() + 1, entity.getY())));
                    } catch (InvocationTargetException | IllegalAccessException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, PROJECTILE_FIRE_SPEED);
    }

    private void stopProjectileTimer() {
        fireTimer.cancel();
        fireTimer = null;
    }

    public void startWalkForward() { walkForward = true; }

    public void stopWalkForward() {
        walkForward = false;
    }

    public void startWalkBackwards() {
        walkBackwards = true;
    }

    public void stopWalkBackwards() {
        walkBackwards = false;
    }

    public void startGoUp() {
        goUp = true;
    }

    public void stopGoUp() {
        goUp = false;
    }

    public void startCrouch() {
        entity.crouch();
    }

    public void stopCrouch() {
        entity.uncrouch();
    }

    public void startFiring() {
        fireProjectile = true; startProjectileTimer();
    }

    public void stopFiring() {
        fireProjectile = false; stopProjectileTimer();
    }

    @Override
    public void run() {


    }
}
