package net.celestemagisteel.map.tiles;

import net.celestemagisteel.entity.Entity;
import net.celestemagisteel.map.Tile;
import net.celestemagisteel.map.TileFace;

public class BackgroundTile extends Tile {

    public BackgroundTile(String texture) { super(texture); }

    @Override
    public boolean onCollide(Entity entity, TileFace face) { return true; }

    @Override
    public boolean onStepOn(Entity entity, TileFace face) { return true; }
}