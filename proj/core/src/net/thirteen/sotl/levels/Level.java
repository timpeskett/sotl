package net.thirteen.sotl.levels;

import net.thirteen.sotl.tiles.Tile;
import net.thirteen.sotl.actors.Enemy;
import net.thirteen.sotl.actors.Hero;
import net.thirteen.sotl.actors.Actor;
import net.thirteen.sotl.utils.Tuple;
import net.thirteen.sotl.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Level {
    private Tile [][] tileMap;
    private ArrayList<Enemy> enemies;
    private Rectangle bounds;
    private World world;

    private int dimX, dimY;
    private int difficulty;

    
    /* The format of difficulty is (as of yet) undecided. */
    public Level(int dimX, int dimY, int difficulty, Rectangle bounds, World world) {
        tileMap = LevelGen.generate(dimX, dimY, difficulty);

        this.bounds = bounds;
        this.dimX = dimX;
        this.dimY = dimY;
        this.difficulty = difficulty;

        this.world = world;

        enemies = new ArrayList<Enemy>();

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

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Hero getHero() {
        return world.getHero();
    }

    public boolean isActorAtTile(Actor a, int x, int y) {
        Tuple t = worldToTile((int)a.getRekt().x, (int)a.getRekt().y);

        return t.first() == x && t.last() == y;
    }

    /* Converts a world coordinate to a tile coordinate */
    public Tuple worldToTile(int x, int y) {
        return new Tuple(x / getTileWidth(), y / getTileHeight());
    }

    /* For testing */
    protected Tile [][] getTileMap() {
        return tileMap;
    }
}
