package net.celestemagisteel.map;

import net.celestemagisteel.map.tiles.Tile;
import net.celestemagisteel.map.tiles.TileFactory;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import static net.celestemagisteel.Game.SPRITE_SIZE;

public class TileMap implements Serializable {

    public static final int MAP_WIDTH = 32;
    public static final int MAP_HEIGHT = 16;
    private final Tile[][] map;
    private final Image[][] imageMap;
    private final Image backgroundImage;

    private TileMap(Tile[][] map, Tile background) throws IOException {
        this.map = map;
        this.backgroundImage = background.getTexture(SPRITE_SIZE, SPRITE_SIZE);
        this.imageMap = new Image[MAP_HEIGHT][MAP_WIDTH];
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                Tile tile = this.map[y][x];
                imageMap[y][x] = tile != null ? tile.getTexture(SPRITE_SIZE, SPRITE_SIZE) : backgroundImage;
            }
        }
    }

    public static TileMap generateBasicTileMap() throws IOException {
        Tile[][] map = new Tile[MAP_HEIGHT][MAP_WIDTH];
        Tile wall = TileFactory.generateNewWallTile();
        Tile floor = TileFactory.generateNewFloorTile();
        for (int i = 0; i < MAP_HEIGHT; i++) {
            map[i][0] = wall;
            map[i][MAP_WIDTH - 1] = wall;
        }

        for (int i = 0; i < MAP_WIDTH; i++) {
            map[0][i] = wall;
            map[MAP_HEIGHT - 1][i] = floor;
        }

        return new TileMap(map, TileFactory.generateBackgroundTile());
    }

    public static TileMap generateRandomTileMap() throws IOException {
        Random random = new Random();
        Tile[][] map = new Tile[MAP_HEIGHT][MAP_WIDTH];
        map[random.nextInt(MAP_HEIGHT)][random.nextInt(MAP_WIDTH)] = TileFactory.generateNewFloorTile();
        map[random.nextInt(MAP_HEIGHT)][random.nextInt(MAP_WIDTH)] = TileFactory.generateNewWallTile();
        return new TileMap(map, TileFactory.generateBackgroundTile());
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public Tile[][] getMap() {
        return map;
    }

    public Image[][] getImageMap() {
        return imageMap;
    }

    public Tile getTile(int x, int y) {
        return map[y][x];
    }
}
