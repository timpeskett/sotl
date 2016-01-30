package net.thirteen.sotl.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import net.thirteen.sotl.Main;

public class GrassTile extends Tile {
    private static final float SPEED_MULT = 1.0f;
    private Texture tex;

    public GrassTile() {
        setTexture(Main.manager.get("grass.png", Texture.class));
    }

    @Override
    public float getSpeedMult() {
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


