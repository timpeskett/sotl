package net.thirteen.sotl.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GrassTile extends Tile {
    private static final double SPEED_MULT = 1.0;

    public GrassTile() {
        super(new Texture(Gdx.files.internal("grass.png")));
    }

    @Override
    public double getSpeedMult() {
        return SPEED_MULT;
    }

    @Override
    public boolean isTileTraversable() {
        return true;
    }
}


