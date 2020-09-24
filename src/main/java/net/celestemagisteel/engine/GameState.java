package net.celestemagisteel.engine;

import javafx.scene.canvas.Canvas;
import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.entity.EntityState;
import net.celestemagisteel.events.PlayerMoveEvent;
import net.celestemagisteel.handlers.EventListener;
import net.celestemagisteel.handlers.Listener;
import net.celestemagisteel.map.Location;
import net.celestemagisteel.map.TileMap;
import net.celestemagisteel.map.tiles.Tile;
import net.celestemagisteel.map.tiles.TileFace;

import java.util.List;

import static net.celestemagisteel.Game.SPRITE_SIZE;

public class GameState implements Listener {

    private final TileMap map;
    private final Entity player;
    private final List<Entity> entities;
    private final Tile background;
    private final Canvas canvas;


    public GameState(TileMap map, Tile background, Entity player, List<Entity> entities, Canvas canvas) {
        this.map = map;
        this.background = background;
        this.player = player;
        this.entities = entities;
        this.canvas = canvas;

    }

    public void drawFullGameState(Canvas canvas) {
        for (int y = 0; y < TileMap.MAP_HEIGHT; y++) {
            for (int x = 0; x < TileMap.MAP_WIDTH; x++) {
                Tile tile = map.getTile(x, y);
                canvas.getGraphicsContext2D().drawImage(tile == null ? background.getTexture(SPRITE_SIZE, SPRITE_SIZE) : tile.getTexture(SPRITE_SIZE, SPRITE_SIZE), x * SPRITE_SIZE, y * SPRITE_SIZE);
            }
        }
        for (Entity entity : entities) {
            canvas.getGraphicsContext2D().drawImage(entity.getSprite(EntityState.DEFAULT, SPRITE_SIZE, SPRITE_SIZE), entity.getX() * SPRITE_SIZE, entity.getY() * SPRITE_SIZE);
        }
        canvas.getGraphicsContext2D().drawImage(player.getSprite(EntityState.DEFAULT, SPRITE_SIZE, SPRITE_SIZE), player.getX() * SPRITE_SIZE, player.getY() * SPRITE_SIZE);
    }

    public void updateMovement(Location oldLocation) {
        canvas.getGraphicsContext2D().drawImage(player.getSprite(EntityState.DEFAULT, SPRITE_SIZE, SPRITE_SIZE), player.getX() * SPRITE_SIZE, player.getY() * SPRITE_SIZE);
        canvas.getGraphicsContext2D().drawImage(map.getTile(oldLocation.getX(), oldLocation.getY()) == null ?
                        background.getTexture(SPRITE_SIZE, SPRITE_SIZE) : map.getTile(oldLocation.getX(), oldLocation.getY()).getTexture(SPRITE_SIZE, SPRITE_SIZE),
                oldLocation.getX() * SPRITE_SIZE, oldLocation.getY() * SPRITE_SIZE);
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

    public Tile getBackground() {
        return background;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private boolean confirmLocation(Location location) {
        int x = location.getX(); int y = location.getY();
        return (x < TileMap.MAP_WIDTH && x >= 0) && (y < TileMap.MAP_HEIGHT && y >= 0)
                && (map.getTile(x,y) == null || map.getTile(x, y).onCollide(player, TileFace.NORTH));

    }

    @EventListener
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!confirmLocation(event.getTo())) { event.setCancelled(true); return; }
        player.setX(event.getTo().getX());
        player.setY(event.getTo().getY());
        updateMovement(event.getFrom());
    }
}
