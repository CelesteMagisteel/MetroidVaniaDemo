package net.celestemagisteel.map.tiles.solid;

import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.map.tiles.Tile;
import net.celestemagisteel.map.tiles.TileFace;

import java.io.IOException;

public class BasicTile extends Tile {

    public BasicTile(String texture) throws IOException {
        super(texture);
    }

    @Override
    public boolean onCollide(Entity entity, TileFace face) {
        return false;
    }
}
