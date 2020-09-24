package net.celestemagisteel.map.tiles.destructible;

import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.map.tiles.Tile;
import net.celestemagisteel.map.tiles.TileFace;
import net.celestemagisteel.map.tiles.interfaces.Destructible;

public class PurpleCube extends Tile implements Destructible {

    public PurpleCube(String texture) {
        super(texture);
    }

    @Override
    public boolean onBlockHit(Entity entity) {
        return false;
    }

    @Override
    public boolean onCollide(Entity entity, TileFace face) {
        return false;
    }
}
