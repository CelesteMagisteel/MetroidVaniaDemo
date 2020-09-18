package net.celestemagisteel.engine;

import javafx.scene.canvas.Canvas;
import jdk.xml.internal.JdkXmlFeatures;
import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.entity.EntityState;
import net.celestemagisteel.map.Tile;
import net.celestemagisteel.map.TileFace;
import net.celestemagisteel.map.TileMap;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static net.celestemagisteel.Game.SPRITE_SIZE;

public class GameState extends TimerTask {

    private final TileMap map;
    private final Entity player;
    private final List<Entity> entities;
    private final Tile background;
    private final Canvas canvas;
    private boolean walkForward = false;
    private boolean walkBackwards = false;

    public GameState(TileMap map, Tile background, Entity player, List<Entity> entities, Canvas canvas) {
        this.map = map;
        this.background = background;
        this.player = player;
        this.entities = entities;
        this.canvas = canvas;
        new Timer().scheduleAtFixedRate(this, 0, 1000/30);
    }

    public void drawGameState(Canvas canvas) {
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

    public void startWalkForward() { walkForward = true; }
    public void stopWalkForward() { walkForward = false; }
    public void startWalkBackwards() { walkBackwards = true; }
    public void stopWalkBackwards() { walkBackwards = false; }
    // TODO: Fix canvas stop updating after a random amount of time
    @Override
    public void run() {
        if (walkForward) {
            System.out.println("Walking Right");
            int newX = player.getX() + 1;
            Tile newTile = map.getTile(newX, player.getY());
            if (newTile == null || newTile.onCollide(player, TileFace.WEST)) { player.setX(newX); }
            drawGameState(canvas);
        }
        if (walkBackwards) {
            System.out.println("Walking Left");
            int newX = player.getX() - 1;
            Tile newTile = map.getTile(newX, player.getY());
            if (newTile == null || newTile.onCollide(player, TileFace.EAST)) { player.setX(newX); }
            drawGameState(canvas);
        }
        System.out.println("--------------------------------------------");
    }
}
