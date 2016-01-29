package net.thirteen.sotl.levels;

import net.thirteen.sotl.tiles.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Level {
    private Tile [][] tileMap;
    private ArrayList enemies;
    private Rectangle bounds;

    int dimX, dimY;
    int difficulty;

    
    /* The format of difficulty is (as of yet) undecided. */
    public Level(int dimX, int dimY, int difficulty, Rectangle bounds) {
        tileMap = LevelGen.generate(dimX, dimY, difficulty);

        this.bounds = bounds;
        this.dimX = dimX;
        this.dimY = dimY;
        this.difficulty = difficulty;

    }


    /* Returns the bounding box of the level. This is the screen space that
     * the level takes up. */
    /* sic */
    public Rectangle getRekt() {
        return bounds;
    }

    public int getTilesX() {
        return dimX;
    }

    public int getTilesY() {
        return dimY;
    }

    public int getTileWidth() {
        return (int)(bounds.getWidth() / dimX);
    }

    public int getTileHeight() {
        return (int)(bounds.getHeight() / dimY);
    }

    public Tile getTile(int x, int y) {
        return tileMap[x][y];
    }
}
