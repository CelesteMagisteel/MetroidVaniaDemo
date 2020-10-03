package net.celestemagisteel.engine.ui;

import net.celestemagisteel.engine.GameState;
import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.entity.EntityState;
import net.celestemagisteel.map.TileMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static net.celestemagisteel.Game.SPRITE_SIZE;

public class GameDisplayPanel extends JPanel {

    private GameState gameState;

    public GameDisplayPanel(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setTileMap(GameState gameState) {
        this.gameState = gameState;
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int y = 0; y < TileMap.MAP_HEIGHT; y++) {
            for (int x = 0; x < TileMap.MAP_WIDTH; x++) {
                Image image = gameState.getMap().getTile(x, y) != null ? gameState.getMap().getTile(x, y).getTexture(SPRITE_SIZE, SPRITE_SIZE) : gameState.getMap().getBackgroundImage();
                graphics.drawImage(image, x * SPRITE_SIZE, y * SPRITE_SIZE, null);
            }
        }
        java.util.List<Entity> entityList = new ArrayList<>(gameState.getEntities());
        for (Entity entity : entityList) {
            graphics.drawImage(entity.getSprite(EntityState.DEFAULT, SPRITE_SIZE, SPRITE_SIZE), entity.getX() * SPRITE_SIZE, entity.getY() * SPRITE_SIZE, null);
        }
        graphics.drawImage(gameState.getPlayer().getSprite(EntityState.DEFAULT, SPRITE_SIZE, SPRITE_SIZE), gameState.getPlayer().getX() * SPRITE_SIZE, gameState.getPlayer().getY() * SPRITE_SIZE, null);
    }
}
