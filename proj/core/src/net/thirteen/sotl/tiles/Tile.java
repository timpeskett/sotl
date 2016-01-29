package net.thirteen.sotl.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

public abstract class Tile extends Sprite {

    public Tile(Texture t) {
        super(t);
    }

    /* Returns the speed multiplier for this tile. Will usually be 1. Will
     * be 0 if this tile cannot be traversed. */
    abstract public double getSpeedMult();

    /* Returns true if this tile can be traversed. Equivalent to checking
     * whether getSpeedMult == 0, but without the worries of floating
     * point errors. */
    public boolean isTileTraversable() {
        return getSpeedMult() > 0.0;
    }
}

