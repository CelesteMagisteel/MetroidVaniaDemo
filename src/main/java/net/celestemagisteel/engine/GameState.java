package net.celestemagisteel.engine;

import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.entity.Player;
import net.celestemagisteel.entity.Projectile;
import net.celestemagisteel.events.EntityFireProjectileEvent;
import net.celestemagisteel.events.EntityMoveEvent;
import net.celestemagisteel.handlers.EventListener;
import net.celestemagisteel.handlers.Listener;
import net.celestemagisteel.map.Location;
import net.celestemagisteel.map.TileMap;
import net.celestemagisteel.map.tiles.TileFace;

import java.util.List;

public class GameState implements Listener {

    private final TileMap map;
    private final Entity player;
    private final List<Entity> entities;


    public GameState(TileMap map, Entity player, List<Entity> entities) {
        this.map = map;
        this.player = player;
        this.entities = entities;
    }

    public TileMap getMap() {
        return map;
    }

    public Entity getPlayer() {
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
}
