package net.celestemagisteel.map.tiles;

import net.celestemagisteel.entity.Entity;

public class BackgroundTile extends Tile {

    public BackgroundTile(String texture) {
        super(texture);
    }

    @Override
    public boolean onCollide(Entity entity, TileFace face) {
        return true;
    }

}
