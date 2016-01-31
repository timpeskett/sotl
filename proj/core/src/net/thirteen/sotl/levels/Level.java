package net.thirteen.sotl.levels;

import net.thirteen.sotl.tiles.Tile;
import net.thirteen.sotl.tiles.DoorTile;
import net.thirteen.sotl.actors.Enemy;
import net.thirteen.sotl.actors.Hero;
import net.thirteen.sotl.actors.Actor;
import net.thirteen.sotl.utils.Tuple;
import net.thirteen.sotl.World;
import net.thirteen.sotl.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

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

        Main.score += 13;
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

    /* Returns the X coord of the center (in world coordinates) of the tile
     * addressed by tile */
    public float tileToWorldX(Tuple tile) {
        return tile.first() * getTileWidth();
    }

    public float tileToWorldY(Tuple tile) {
        return tile.last() * getTileHeight();
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public HashMap<Tuple, DoorTile> getDoors() {
        HashMap<Tuple, DoorTile> doors = new HashMap<Tuple, DoorTile>();

        for(int i = 0; i < dimX; i++) {
            for(int j = 0; j < dimY; j++) {
                if(getTile(i, j) instanceof DoorTile) {
                    /* This is a safe downcast */
                    doors.put(new Tuple(i, j), (DoorTile)getTile(i, j));
                }
            }
        }
        return doors;
    }


    /* Returns the tiles that are within a certain rectangle, described in
     * world coordinates */
    public HashMap<Tuple, Tile> getTilesInRect(Rectangle r) {
        HashMap<Tuple, Tile> tiles = new HashMap<Tuple, Tile>();
        Tuple botLeft, topRight;

        botLeft = worldToTile(r.x, r.y);
        topRight = worldToTile(r.x + r.width, r.y + r.height);

        for(int i = botLeft.first(); i <= topRight.first(); i++) {
            for(int j = botLeft.last(); j <= topRight.last(); j++) {
                tiles.put(new Tuple(i, j), getTile(i, j));
            }
        }

        return tiles;
    }


    protected Tile [][] getTileMap() {
        return tileMap;
    }
}
