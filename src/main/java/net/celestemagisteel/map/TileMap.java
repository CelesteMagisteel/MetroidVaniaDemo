package net.celestemagisteel.map;

import net.celestemagisteel.map.tiles.Tile;
import net.celestemagisteel.map.tiles.solid.BasicTile;

import java.io.Serializable;

public class TileMap implements Serializable {

    public static final int MAP_WIDTH = 32;
    public static final int MAP_HEIGHT = 16;
    private Tile[][] map = new Tile[MAP_HEIGHT][MAP_WIDTH];

    private TileMap(Tile[][] map) {
        this.map = map;
    }

    public TileMap() {
    }

    public static TileMap generateBasicTileMap() {
        Tile[][] map = new Tile[MAP_HEIGHT][MAP_WIDTH];

        for (int i = 0; i < MAP_HEIGHT; i++) {
            map[i][0] = new BasicTile("wall");
            map[i][MAP_WIDTH - 1] = new BasicTile("wall");
        }

        for (int i = 0; i < MAP_WIDTH; i++) {
            map[0][i] = new BasicTile("wall");
            map[MAP_HEIGHT - 1][i] = new BasicTile("floor");
        }

        return new TileMap(map);
    }

    public Tile[][] getMap() {
        return map;
    }

    public Tile getTile(int x, int y) {
        return map[y][x];
    }
}
