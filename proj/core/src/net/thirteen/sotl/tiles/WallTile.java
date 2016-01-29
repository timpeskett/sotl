package net.thirteen.sotl.tiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class WallTile extends Tile {
    private static final double SPEED_MULT = 0.0;

    public WallTile() {
        setTexture(new Texture(Gdx.files.internal("wall.png")));
    }

    @Override
    public double getSpeedMult() {
        return SPEED_MULT;
    }

    @Override public boolean isTileTraversable() {
        return true;
    }

    @Override
    public boolean isTileTransparent(){
        return false;
    }
}
