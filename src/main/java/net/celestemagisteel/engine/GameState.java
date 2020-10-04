package net.celestemagisteel.engine;

import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.entity.Player;
import net.celestemagisteel.entity.Projectile;
import net.celestemagisteel.events.EntityFireProjectileEvent;
import net.celestemagisteel.events.EntityMoveEvent;
import net.celestemagisteel.handlers.EventListener;
import net.celestemagisteel.handlers.EventManager;
import net.celestemagisteel.handlers.Listener;
import net.celestemagisteel.map.Location;
import net.celestemagisteel.map.TileMap;
import net.celestemagisteel.map.tiles.TileFace;
import sun.util.resources.cldr.ak.LocaleNames_ak;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GameState extends TimerTask implements Listener {

    private final TileMap map;
    private final Player player;
    private final List<Entity> entities;


    public GameState(TileMap map, Player player, List<Entity> entities) {
        this.map = map;
        this.player = player;
        this.entities = entities;
        new Timer().scheduleAtFixedRate(this, 0, ControlHandler.MOVEMENT_TICK_SPEED);
    }

    public TileMap getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    private boolean confirmLocation(Location location) {
        int x = location.getX();
        int y = location.getY();
        return (x < TileMap.MAP_WIDTH && x >= 0)
                && (y < TileMap.MAP_HEIGHT && y >= 0)
                && (map.getTile(x, y) == null
                || map.getTile(x, y).onCollide(player, TileFace.NORTH));

    }

    @EventListener
    public void onPlayerMove(EntityMoveEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!confirmLocation(event.getTo())) {
            event.setCancelled(true);
            return;
        }

        event.getEntity().setOnGround(!confirmLocation(new Location(event.getTo().getX(), event.getTo().getY()+1)));

        player.setX(event.getTo().getX());
        player.setY(event.getTo().getY());
    }

    @EventListener
    public void onProjectileMove(EntityMoveEvent event) {
        if (!(event.getEntity() instanceof Projectile)) return;
        if (!confirmLocation(event.getTo())) {
            entities.remove(event.getEntity());
        } else {
            event.getEntity().setX(event.getTo().getX());
            event.getEntity().setY(event.getTo().getY());
        }
    }

    @EventListener
    public void onProjectileFire(EntityFireProjectileEvent event) {
        entities.add(event.getProjectile());
    }

    @Override
    public void run() {
        try {
            for (Entity entity : new ArrayList<>(entities)) {
                if (entity == null) continue;
                int y = entity.getY() + 1;
                try {
                    if (entity.obeysGravity() && confirmLocation(new Location(entity.getX(), y))) {
                        EventManager.raiseEvent(new EntityMoveEvent(entity, new Location(entity.getX(), entity.getY()), new Location(entity.getX(), y)));
                    } else entity.setOnGround(true);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            int y = player.getY() + 1;
            try {
                if (player.obeysGravity() && confirmLocation(new Location(player.getX(), y)))
                    EventManager.raiseEvent(new EntityMoveEvent(player, new Location(player.getX(), player.getY()), new Location(player.getX(), y)));
                else player.setOnGround(true);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
