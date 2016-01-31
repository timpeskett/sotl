package net.thirteen.sotl.tiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import net.thirteen.sotl.Main;

import java.util.ArrayList;

public class WallTile extends Tile {
    private static final float SPEED_MULT = 0.0f;
    public static final ArrayList<ArrayList<String>> tileSets;

    public WallTile() {
        setTexture(Main.manager.get(tileSets.get(0).get(0), Texture.class));
    }

    public WallTile(String fileName) {
        if(fileName.equals("")) {
            setTexture(Main.manager.get(tileSets.get(0).get(0), Texture.class));
        }
        else {
            setTexture(Main.manager.get(fileName, Texture.class));
        }
    }

    @Override
    public float getSpeedMult() {
        return SPEED_MULT;
    }

    @Override 
    public boolean isTileTraversable() {
        return false;
    }

    @Override
    public boolean isTileTransparent(){
        return false;
    }

    static {
        tileSets = new ArrayList<ArrayList<String>>();

        tileSets.add(new ArrayList<String>());
        tileSets.get(0).add("wall.png");
        tileSets.add(new ArrayList<String>());
        tileSets.get(1).add("stonewall.png");
        tileSets.get(1).add("stonepillar.png");
    }
}
