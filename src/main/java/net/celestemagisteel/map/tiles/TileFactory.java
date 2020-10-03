package net.celestemagisteel.map.tiles;

import net.celestemagisteel.map.tiles.solid.BasicTile;

import java.io.IOException;

public class TileFactory {

    public static Tile generateNewWallTile() throws IOException {
        return new BasicTile("wall");
    }

    public static Tile generateNewFloorTile() throws IOException {
        return new BasicTile("floor");
    }

    public static Tile generateBackgroundTile() throws IOException {
        return new BackgroundTile("background");
    }
}
