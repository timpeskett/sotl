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
import java.util.ArrayList;

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


    public Level generate(World world, Tuple levelTup, int difficulty) {
        Tile [][] tileMap = new Tile[dimX][dimY];
        ArrayList<Tuple> doors;
        float prob = 0.5f;

        /* Create open map with walls all around */
        genBasicMap(tileMap);
        doors = genDoors(tileMap, world, levelTup, 2, prob);

        Level level = new Level(dimX, dimY, bounds, tileMap, world);

        /*Add an enemy*/
        level.getEnemies().add(
            EnemyFactory.createEnemy(level, 
                EnemyFactory.Difficulty.STATIONARY.val(),
                64, 64)
        );

        return level;
    }


    /* Note: Assumes that tMap is a rectangular matrix, and not a jagged
     * arrays of arrays.*/
    private void genBasicMap(Tile [][] tileMap) {
        /* These used instead of dimX and dimY for generality */
        int tMapWid = tileMap.length;
        int tMapHei = tileMap[0].length;

        /* Start simple. Fill map with grass */
        for(int x = 0; x < tMapWid; x++) {
            for(int y = 0; y < tMapHei; y++) {
                tileMap[x][y] = new GrassTile();
            }
        }

        for(int x = 0; x < tMapWid; x++) {
            tileMap[x][tMapHei-1] = new WallTile();
            tileMap[x][0] = new WallTile();
        }
            
        for(int y = 0; y < tMapHei; y++) {
            tileMap[0][y] = new WallTile();
            tileMap[tMapWid-1][y] = new WallTile();
        }

    }


    /* Returns a list of tuples that correspond to doors in the level */
    /* Mindoors is unimplemented as of yet */
    private ArrayList<Tuple> genDoors(Tile [][] tileMap, World world, Tuple level, int minDoors, float probDoor) {
        Tuple leftDoor = null, topDoor = null, rightDoor = null, botDoor = null;
        ArrayList<Tuple> doorList = new ArrayList<Tuple>();
        int numDoors = 0;

        while(numDoors < minDoors) { 
            if(leftDoor == null) {
                leftDoor = genDoorTuple(world, level, new Tuple(level.first() -1, level.last()), probDoor);
                if(leftDoor != null) {
                    tileMap[leftDoor.first()][leftDoor.last()] = new DoorTile();
                    doorList.add(leftDoor);
                    numDoors++;
                }
            }
            if(rightDoor == null) {
                rightDoor = genDoorTuple(world, level, new Tuple(level.first() +1, level.last()), probDoor);
                if(rightDoor != null) {
                    tileMap[rightDoor.first()][rightDoor.last()] = new DoorTile();
                    doorList.add(rightDoor);
                    numDoors++;
                }
            }
            if(topDoor == null) {
                topDoor = genDoorTuple(world, level, new Tuple(level.first(), level.last() + 1), probDoor);
                if(topDoor != null) {
                    tileMap[topDoor.first()][topDoor.last()] = new DoorTile();
                    doorList.add(topDoor);
                    numDoors++;
                }
            }
            if(botDoor == null) {
                botDoor = genDoorTuple(world, level, new Tuple(level.first(), level.last() - 1), probDoor);
                if(botDoor != null) {
                    tileMap[botDoor.first()][botDoor.last()] = new DoorTile();
                    doorList.add(botDoor);
                    numDoors++;
                }
            }
        }

        return doorList;
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
                    doorY = (int)(Math.random() * (dimY - 3) + 1);
                }
                if(level.first() - adj.first() == -1) {
                    doorX = dimX - 1;
                    doorY = (int)(Math.random() * (dimY - 3) + 1);
                }

                if(level.last() - adj.last() == 1) {
                    doorY = 0;
                    doorX = (int)(Math.random() * (dimX - 3) + 1);
                }
                if(level.last() - adj.last() == -1) {
                    doorY = dimY - 1;
                    doorX = (int)(Math.random() * (dimX - 3) + 1);
                }

                outDoorTup = new Tuple(doorX, doorY);
            }
            System.out.println("if " +outDoorTup);
        }
        else {
            outDoorTup = world.getDoorPos(adj, level);
            System.out.println("else " + outDoorTup);
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
