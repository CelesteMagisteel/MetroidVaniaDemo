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
    private boolean goUp = false;
    private boolean goDown = false;

    public GameState(TileMap map, Tile background, Entity player, List<Entity> entities, Canvas canvas) {
        this.map = map;
        this.background = background;
        this.player = player;
        this.entities = entities;
        this.canvas = canvas;
        new Timer().scheduleAtFixedRate(this, 0, 1000/30);
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

    public void updateMovement(int oldX, int oldY) {
        canvas.getGraphicsContext2D().drawImage(player.getSprite(EntityState.DEFAULT, SPRITE_SIZE, SPRITE_SIZE), player.getX() * SPRITE_SIZE, player.getY() * SPRITE_SIZE);
        canvas.getGraphicsContext2D().drawImage(map.getTile(oldX, oldY) == null ?background.getTexture(SPRITE_SIZE, SPRITE_SIZE) : map.getTile(oldX, oldY).getTexture(SPRITE_SIZE, SPRITE_SIZE), oldX * SPRITE_SIZE, oldY * SPRITE_SIZE);
    }


    public void startWalkForward() { walkForward = true; }
    public void stopWalkForward() { walkForward = false; }
    public void startWalkBackwards() { walkBackwards = true; }
    public void stopWalkBackwards() { walkBackwards = false; }
    public void startGoUp() { goUp = true; }
    public void stopGoUp() { goUp = false; }
    public void startGoDown() { goDown = true; }
    public void stopGoDown() { goDown = false; }
    // TODO: Fix canvas stop updating after a random amount of time
    @Override
    public void run() {
        if (walkForward) {
            int newX = player.getX() + 1;
            if (newX < TileMap.MAP_WIDTH) {
                Tile newTile = map.getTile(newX, player.getY());
                if (newTile == null || newTile.onCollide(player, TileFace.WEST)) {
                    player.setX(newX);
                    updateMovement(newX - 1, player.getY());
                }
            }
        }
        if (walkBackwards) {
            int newX = player.getX() - 1;
            if (newX >= 0) {
                Tile newTile = map.getTile(newX, player.getY());
                if (newTile == null || newTile.onCollide(player, TileFace.EAST)) {
                    player.setX(newX);
                    updateMovement(newX + 1, player.getY());
                }
            }
        }
        if (goUp) {
            int newY = player.getY() - 1;
            if (newY >= 0) {
                Tile newTile = map.getTile(player.getX(), newY);
                if (newTile == null || newTile.onStepOn(player, TileFace.WEST)) {
                    player.setY(newY);
                    updateMovement(player.getX(), newY + 1);
                }
            }
        }
        if (goDown) {
            int newY = player.getY() + 1;
            if (newY < TileMap.MAP_HEIGHT) {
                Tile newTile = map.getTile(player.getX(), newY);
                if (newTile == null || newTile.onStepOn(player, TileFace.EAST)) {
                    player.setY(newY);
                    updateMovement(player.getX(), newY - 1);
                }
            }
        }

    }
}
