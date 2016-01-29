package net.thirteen.sotl.tiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

public abstract class Tile {
    private Texture tex;

    
    public Texture getTexture() {
        return tex;
    }

    protected void setTexture(Texture t) {
        if(t != null) tex = t;
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

