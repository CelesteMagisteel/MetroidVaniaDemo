package net.celestemagisteel.map.tiles.destructible;

import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.map.tiles.interfaces.Destructible;
import net.celestemagisteel.map.Tile;
import net.celestemagisteel.map.TileFace;

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

    @Override
    public boolean onStepOn(Entity entity, TileFace face) {
        return false;
    }
}
