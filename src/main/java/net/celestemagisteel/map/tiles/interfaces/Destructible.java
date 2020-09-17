package net.celestemagisteel.map.tiles.interfaces;

import net.celestemagisteel.entity.Entity;

public interface Destructible {

    boolean onBlockHit(Entity entity);
}
