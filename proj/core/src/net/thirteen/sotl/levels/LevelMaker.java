package net.thirteen.sotl.levels;

import net.thirteen.sotl.tiles.Tile;
import net.thirteen.sotl.tiles.DoorTile;
import net.thirteen.sotl.tiles.WallTile;
import net.thirteen.sotl.tiles.GrassTile;
import net.thirteen.sotl.World;
import net.thirteen.sotl.utils.Tuple;
import net.thirteen.sotl.actors.EnemyFactory;

import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;

public class LevelMaker {
    private int dimX, dimY;
    private Rectangle bounds;

    /* dimX and dimY are the number of horizontal and vertical tiles
     * respectively */
    public LevelMaker(int dimX, int dimY, Rectangle bounds) {
        this.dimX = dimX;
        this.dimY = dimY;

        this.bounds = bounds;
    }

    
    public Level generate(HashMap<Tuple, DoorTile> entrances, int difficulty, World world) {
        Tile [][] tileMap = new Tile[dimX][dimY];
        Level level;

        /* Start simple. Fill map with grass */
        for(int x = 0; x < dimX; x++) {
            for(int y = 0; y < dimY; y++) {
                tileMap[x][y] = new GrassTile();
            }
        }

        for(int x = 0; x < dimX; x++) {
            //we'll add a door
            if(x == 8){
                tileMap[x][dimY-1] = new DoorTile();
            }
            else{
                tileMap[x][dimY-1] = new WallTile();
            }

            tileMap[x][0] = new WallTile();
        }
            
        for(int y = 0; y < dimY; y++) {
            tileMap[0][y] = new WallTile();
            tileMap[dimX-1][y] = new WallTile();
        }


        level = new Level(dimX, dimY, bounds, tileMap, world);

        /*Add an enemy*/
        level.getEnemies().add(
            EnemyFactory.createEnemy(level, 
                EnemyFactory.Difficulty.SEEKING.val(),
                64, 64)
        );

        return level;
    }

    public Level load(/* Load from file */) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
