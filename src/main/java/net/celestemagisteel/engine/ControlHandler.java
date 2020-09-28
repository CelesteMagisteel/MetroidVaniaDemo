package net.celestemagisteel.engine;

import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.events.PlayerMoveEvent;
import net.celestemagisteel.handlers.EventManager;
import net.celestemagisteel.map.Location;

import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

public class ControlHandler extends TimerTask {

    private boolean walkForward = false;
    private boolean walkBackwards = false;
    private boolean goUp = false;
    private boolean goDown = false;
    private final Entity entity;

    public ControlHandler(Entity entity) {
        new Timer().scheduleAtFixedRate(this, 0, 1000 / 30);
        this.entity = entity;
    }

    public void startWalkForward() {
        walkForward = true;
    }

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

    public void startGoDown() {
        goDown = true;
    }

    public void stopGoDown() {
        goDown = false;
    }

    @Override
    public void run() {
        int x = entity.getX(); int y = entity.getY();
        int newX = x; int newY = y;

        if (walkForward) newX++;
        if (walkBackwards) newX--;
        if (goUp) newY--;
        if (goDown) newY++;

        if (!(newX == x && newY == y)) {
            try {
                EventManager.raiseEvent(new PlayerMoveEvent(entity, new Location(x, y), new Location(newX, newY)));
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
