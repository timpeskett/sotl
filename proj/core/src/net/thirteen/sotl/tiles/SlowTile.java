package net.thirteen.sotl.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import net.thirteen.sotl.Main;

public class SlowTile extends Tile {
    private static final float SPEED_MULT = 0.5f;
    private Texture tex;

    public SlowTile() {
        setTexture(Main.manager.get("sand.png", Texture.class));
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


