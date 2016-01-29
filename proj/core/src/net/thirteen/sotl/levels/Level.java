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


    Level(int dimX, int dimY, Rectangle bounds, Tile [][] tileMap, World world) {
        this.dimX = dimX;
        this.dimY = dimY;
        this.bounds = bounds;

        this.world = world;
        this.tileMap = tileMap;
        
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

    public Tile getTile(Tuple t) {
        return tileMap[t.first()][t.last()];
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
    public Tuple worldToTile(float x, float y) {
        return new Tuple((int)x / getTileWidth(), (int)y / getTileHeight());
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }
    /* For testing */
    protected Tile [][] getTileMap() {
        return tileMap;
    }
}
