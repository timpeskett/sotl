package net.thirteen.sotl.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import net.thirteen.sotl.Main;

import java.util.ArrayList;

public class GrassTile extends Tile {
    private static final float SPEED_MULT = 1.0f;
    public static final ArrayList<ArrayList<String>> tileSets;

    public GrassTile() {
        setTexture(Main.manager.get(tileSets.get(0).get(0), Texture.class));
    }

    public GrassTile(String fileName) {
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
        return true;
    }

    @Override
    public boolean isTileTransparent(){
        return true;
    }


    static {
        tileSets = new ArrayList<ArrayList<String>>();

        tileSets.add(new ArrayList<String>());
        tileSets.get(0).add("grass.png");
    }

}


