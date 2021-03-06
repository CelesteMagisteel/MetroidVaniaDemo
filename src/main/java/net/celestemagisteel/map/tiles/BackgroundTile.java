package net.celestemagisteel.map.tiles;

import net.celestemagisteel.entity.Entity;

import java.io.IOException;

public class BackgroundTile extends Tile {

    public BackgroundTile(String texture) throws IOException {
        super(texture);
    }

    @Override
    public boolean onCollide(Entity entity, TileFace face) {
        return true;
    }

}
