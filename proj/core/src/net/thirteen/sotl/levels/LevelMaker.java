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


    public Level generate(World world, Tuple to, int difficulty) {
        Tuple leftDoor = null, topDoor = null, rightDoor = null, botDoor = null;
        int numDoors = 0;
        Tile [][] tileMap = new Tile[dimX][dimY];
        float prob = 0.5f;


        /* Start simple. Fill map with grass */
        for(int x = 0; x < dimX; x++) {
            for(int y = 0; y < dimY; y++) {
                tileMap[x][y] = new GrassTile();
            }
        }

        for(int x = 0; x < dimX; x++) {
            tileMap[x][dimY-1] = new WallTile();
            tileMap[x][0] = new WallTile();
        }
            
        for(int y = 0; y < dimY; y++) {
            tileMap[0][y] = new WallTile();
            tileMap[dimX-1][y] = new WallTile();
        }


        leftDoor = genDoorTuple(world, to, new Tuple(to.first() -1, to.last()), prob);
        rightDoor = genDoorTuple(world, to, new Tuple(to.first() +1, to.last()), prob);
        topDoor = genDoorTuple(world, to, new Tuple(to.first(), to.last() + 1), prob);
        botDoor = genDoorTuple(world, to, new Tuple(to.first(), to.last() - 1), prob);


        if(leftDoor != null) {
            System.out.println("Left door: " + leftDoor);
            tileMap[leftDoor.first()][leftDoor.last()] = new DoorTile();
        }
        if(rightDoor != null) {
            System.out.println("Right door: " + rightDoor);
            tileMap[rightDoor.first()][rightDoor.last()] = new DoorTile();
        }
        if(topDoor != null) {
            System.out.println("Top door: " + topDoor);
            tileMap[topDoor.first()][topDoor.last()] = new DoorTile();
        }
        if(botDoor != null) {
            System.out.println("Bottom door: " + botDoor);
            tileMap[botDoor.first()][botDoor.last()] = new DoorTile();
        }


        Level level = new Level(dimX, dimY, bounds, tileMap, world);

        /*Add an enemy*/
        level.getEnemies().add(
            EnemyFactory.createEnemy(level, 
                EnemyFactory.Difficulty.STATIONARY.val(),
                64, 64)
        );

        return level;
    }


    /* Generates a tuple describing where a door should be placed on the
     * current level. Places doors according to adjacent levels, and if
     * not then will randomly place a door in a suitable position. */
    private Tuple genDoorTuple(World world, Tuple level, Tuple adj, float probDoor) {
        Tuple outDoorTup = null;

        if(world.getLevel(level) == null && world.getLevel(adj) == null) {
            if(Math.random() > probDoor) {
                int doorX = 0, doorY = 0;

                /* These cases should all be mutually exclusive */
                if(level.first() - adj.first() == 1) {
                    doorX = 0;
                    doorY = (int)(Math.random() * (dimY - 2) + 1);
                }
                if(level.first() - adj.first() == -1) {
                    doorX = dimX - 1;
                    doorY = (int)(Math.random() * (dimY - 2) + 1);
                }

                if(level.last() - adj.last() == 1) {
                    doorY = 0;
                    doorX = (int)(Math.random() * (dimX - 2) + 1);
                }
                if(level.last() - adj.last() == -1) {
                    doorY = dimY - 1;
                    doorX = (int)(Math.random() * (dimX - 2) + 1);
                }

                outDoorTup = new Tuple(doorX, doorY);
            }
        }
        else {
            outDoorTup = world.getDoorPos(adj, level);
            System.out.println("New door at " + outDoorTup);
        }

        return outDoorTup;
    }


    
    public Level generate(HashMap<Tuple, DoorTile> entrances, int difficulty, World world) {
        Tile [][] tileMap = new Tile[dimX][dimY];

        /* Start simple. Fill map with grass */
        for(int x = 0; x < dimX; x++) {
            for(int y = 0; y < dimY; y++) {
                tileMap[x][y] = new GrassTile();
            }
        }

        for(int x = 0; x < dimX; x++) {
            tileMap[x][dimY-1] = new WallTile();
            tileMap[x][0] = new WallTile();
        }
            
        for(int y = 0; y < dimY; y++) {
            tileMap[0][y] = new WallTile();
            tileMap[dimX-1][y] = new WallTile();
        }


        Level level = new Level(dimX, dimY, bounds, tileMap, world);

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
