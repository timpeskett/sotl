package net.thirteen.sotl.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GrassTile extends Tile {
    private static final double SPEED_MULT = 1.0;
    private Texture tex;

    public GrassTile() {
        setTexture(new Texture(Gdx.files.internal("grass.png")));
    }

    @Override
    public double getSpeedMult() {
        return SPEED_MULT;
    }

    @Override
    public boolean isTileTraversable() {
        return true;
    }

    @Override
    public boolean isTileTransparent(){
        return true;
    }
}


