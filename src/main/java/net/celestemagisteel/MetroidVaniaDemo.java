package net.celestemagisteel;

import net.celestemagisteel.map.TileMap;

public class MetroidVaniaDemo {

    public static void main(String[] args) {
        TileMap map = TileMap.generateBasicTileMap();

        map.printTileMap();
    }
}
