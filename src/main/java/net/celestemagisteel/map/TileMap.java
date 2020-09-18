package net.celestemagisteel.map;

import net.celestemagisteel.map.tiles.TileFactory;

import java.io.Serializable;
import java.util.Random;

public class TileMap implements Serializable {

    public static final int MAP_SIZE = 32;
    private Tile[][] map = new Tile[MAP_SIZE][MAP_SIZE];

    private TileMap(Tile[][] map) {
        this.map = map;
    }

    public TileMap() {

    }

    public Tile[][] getMap() { return map; }

    public Tile getTile(int x, int y) { return map[y][x]; }

    public void printTileMap() {
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x ++) {
                if (map[y][x] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(map[y][x].getShortCode());
                }
            }
            System.out.println();
        }
    }

    public static TileMap generateBasicTileMap() {
        Tile[][] map = new Tile[MAP_SIZE][MAP_SIZE];
        Random random = new Random();

        // Outline
        for (int i = 0; i < MAP_SIZE; i++) {
            map[0][i] = TileFactory.generateNewWallTile();
            if (i < MAP_SIZE-1) {
                map[i][0] = TileFactory.generateNewWallTile();
                map[i][MAP_SIZE-1] = TileFactory.generateNewWallTile();
            }
            map[MAP_SIZE-1][i] = TileFactory.generateNewFloorTile();
        }


        return new TileMap(map);
    }
}
