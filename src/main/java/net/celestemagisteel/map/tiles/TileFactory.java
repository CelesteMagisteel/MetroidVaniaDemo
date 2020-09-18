package net.celestemagisteel.map.tiles;

import net.celestemagisteel.map.Tile;
import net.celestemagisteel.map.tiles.solid.BasicTile;

public class TileFactory {

    public static Tile generateNewWallTile() {
        return new BasicTile("wall");
    }

    public static Tile generateNewFloorTile() {
        return new BasicTile("floor");
    }

    public static Tile generateBackgroundTile() { return new BackgroundTile("background"); }
}
