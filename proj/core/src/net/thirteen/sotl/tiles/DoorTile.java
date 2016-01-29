package net.thirteen.sotl.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import net.thirteen.sotl.Main;

public class DoorTile extends Tile {
    private static final double SPEED_MULT = 1.0;
    private Texture tex;

    public DoorTile() {
        setTexture(Main.manager.get("door.png", Texture.class));
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

