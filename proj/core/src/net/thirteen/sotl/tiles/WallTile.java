package net.thirteen.sotl.tiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import net.thirteen.sotl.Main;

public class WallTile extends Tile {
    private static final float SPEED_MULT = 0.0f;

    public WallTile() {
        setTexture(Main.manager.get("wall.png", Texture.class));
    }

    @Override
    public float getSpeedMult() {
        return SPEED_MULT;
    }

    @Override public boolean isTileTraversable() {
        return false;
    }

    @Override
    public boolean isTileTransparent(){
        return false;
    }
}
