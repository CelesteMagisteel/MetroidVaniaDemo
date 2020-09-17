package net.celestemagisteel.map;

import net.celestemagisteel.entity.Entity;

public interface Destructible {

    boolean onBlockHit(Entity entity);
}
