package net.celestemagisteel.map;

import net.celestemagisteel.map.tiles.TileFactory;

import java.io.Serializable;
import java.util.Random;

public class TileMap implements Serializable {

    private static final int mapSize = 32;
    private Tile[][] map = new Tile[mapSize][mapSize];

    private TileMap(Tile[][] map) {
        this.map = map;
    }

    public TileMap() {

    }

    public Tile[][] getMap() { return map; }

    public Tile getTile(int x, int y) { return map[y][x]; }

    public void printTileMap() {
        for (int y = 0; y < mapSize-1; y++) {
            for (int x = 0; x < mapSize-1; x ++) {
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
        Tile[][] map = new Tile[mapSize][mapSize];
        Random random = new Random();

        // Outline
        for (int i = 0; i < mapSize-1; i++) {
            map[0][i] = TileFactory.generateNewWallTile();
            if (i < mapSize-2) {
                map[i][0] = TileFactory.generateNewWallTile();
                map[i][mapSize-1] = TileFactory.generateNewWallTile();
            }
            map[mapSize-1][i] = TileFactory.generateNewFloorTile();
        }

        // Random Block Obstacles

        for (int i = 0; i < Math.abs(random.nextInt() % 30); i++) {
            int height = Math.abs(random.nextInt() % 4) + 1;
            int x = Math.abs(random.nextInt() % mapSize-3) + 1;
            for (int y = 0; y < height; y++) {
                map[mapSize-1-y][x] = TileFactory.generateNewFloorTile();
            }
        }

        return new TileMap(map);
    }
}
