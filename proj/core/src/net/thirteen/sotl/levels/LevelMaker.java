package net.thirteen.sotl.levels;

import net.thirteen.sotl.tiles.Tile;
import net.thirteen.sotl.tiles.DoorTile;
import net.thirteen.sotl.tiles.WallTile;
import net.thirteen.sotl.tiles.GrassTile;
import net.thirteen.sotl.tiles.SlowTile;
import net.thirteen.sotl.World;
import net.thirteen.sotl.utils.Tuple;
import net.thirteen.sotl.actors.EnemyFactory;
import net.thirteen.sotl.actors.Enemy;
import net.thirteen.sotl.levels.PathFinder;
import net.thirteen.sotl.screens.DeathScreen;
import net.thirteen.sotl.tiles.MapTileFactory;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;

import java.util.HashMap;
import java.util.ArrayList;

public class LevelMaker {
    private int dimX, dimY;
    private Rectangle bounds;

    private static final int SAFE_DOOR_DISTANCE = 6;
    private static final int KEY_SQUARES_MAX = 20;
    private static final int KEY_SQUARES_MIN = 6;

    /* dimX and dimY are the number of horizontal and vertical tiles
     * respectively */
    public LevelMaker(int dimX, int dimY, Rectangle bounds) {
        this.dimX = dimX;
        this.dimY = dimY;

        this.bounds = bounds;
    }


    /* Difficulty should be between 0 and 1 */
    public Level generate(World world, Tuple levelTup, float difficulty) {
        Tile [][] tileMap = new Tile[dimX][dimY];
        ArrayList<Tuple> doors;

        /* Create open map with walls all around */
        genBasicMap(tileMap);
        doors = genDoors(tileMap, world, levelTup, 2, (float)Math.random());
        genMaze(tileMap, doors, difficulty);

        Level level = new Level(dimX, dimY, bounds, tileMap, world);

        level.setEnemies(genEnemies(level, doors, difficulty));
        
        return level;
    }

    private ArrayList<Enemy> genEnemies(Level level, ArrayList<Tuple> doors, float difficulty) {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        Tile [][] tileMap = level.getTileMap();
        int numEnemies;

        numEnemies = (int)(difficulty * 3 + 2);

        for(int i = 0; i < numEnemies; i++) {
            int infGuard;
            boolean validSpawn;
            Tuple t;

            do {
                t = getRandomTraversableTilePos(tileMap);
                validSpawn = true;
                for(Tuple door : doors) {
                    if(!(t.manhattan(door) > SAFE_DOOR_DISTANCE)) {
                        validSpawn = false;
                    }
                }

                infGuard = 0;
            } while(!validSpawn && infGuard++ < 10);


            if(validSpawn) {
                enemies.add(
                    EnemyFactory.createEnemy(level,
                        (float)Math.random() * difficulty,
                        level.tileToWorldX(t), level.tileToWorldY(t)));
            }
        }

        return enemies;
    }


    /* Note: Assumes that tMap is a rectangular matrix, and not a jagged
     * arrays of arrays.*/
    private void genBasicMap(Tile [][] tileMap) {
        /* These used instead of dimX and dimY for generality */
        int tMapWid = tileMap.length;
        int tMapHei = tileMap[0].length;

        MapTileFactory mtf = new MapTileFactory();

        /* Start simple. Fill map with grass */
        for(int x = 0; x < tMapWid; x++) {
            for(int y = 0; y < tMapHei; y++) {
                tileMap[x][y] = mtf.makeGroundTile("grass.png");
                /*tileMap[x][y] = new GrassTile();*/
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

        /* To guard against an infinite loop */
        int infGuard = 0;

        while(numDoors < minDoors && infGuard++ < 10) { 
            if(leftDoor == null) {
                leftDoor = genDoorTuple(world, level, level.firstDec(), probDoor);
                if(leftDoor != null) {
                    tileMap[leftDoor.first()][leftDoor.last()] = new DoorTile();
                    doorList.add(leftDoor);
                    numDoors++;
                }
            }
            if(rightDoor == null) {
                rightDoor = genDoorTuple(world, level, level.firstInc(), probDoor);
                if(rightDoor != null) {
                    tileMap[rightDoor.first()][rightDoor.last()] = new DoorTile();
                    doorList.add(rightDoor);
                    numDoors++;
                }
            }
            if(topDoor == null) {
                topDoor = genDoorTuple(world, level, level.lastInc(), probDoor);
                if(topDoor != null) {
                    tileMap[topDoor.first()][topDoor.last()] = new DoorTile();
                    doorList.add(topDoor);
                    numDoors++;
                }
            }
            if(botDoor == null) {
                botDoor = genDoorTuple(world, level, level.lastDec(), probDoor);
                if(botDoor != null) {
                    tileMap[botDoor.first()][botDoor.last()] = new DoorTile();
                    doorList.add(botDoor);
                    numDoors++;
                }
            }
        }

        return doorList;
    }


    private void genMaze(Tile [][] tileMap, ArrayList<Tuple> doors, float difficulty) {
        int tMapWid = tileMap.length;
        int tMapHei = tileMap[0].length;
        int numKeySquares;
        ArrayList<Tuple> reservedSquares = new ArrayList<Tuple>();
        ArrayList<Tuple> keySquares = new ArrayList<Tuple>();

        /* The higher the number of key squares, the more open the level
         * is */
        numKeySquares = (int)((1.0f - difficulty) * (KEY_SQUARES_MAX - KEY_SQUARES_MIN) + KEY_SQUARES_MIN);

        for(int i = 0; i < numKeySquares; i++) {
            keySquares.add(getRandomTraversableTilePos(tileMap));
        }

        for(int i = 0; i < doors.size(); i++) {
            for(int j = i; j < doors.size(); j++) {
                reservedSquares.addAll(genPath(tileMap, doors.get(i), keySquares, doors.get(j)));
            }
        }

        for(int i = 0; i < tMapWid; i++) {
            for(int j = 0; j < tMapHei; j++) {
                if(tileMap[i][j].isTileTraversable() && !reservedSquares.contains(new Tuple(i, j))) {
                    tileMap[i][j] = new WallTile();
                }
            }
        }
    }


    /* Assumes that there is *always* at least one traversable tile
     * in the map. Otherwise will infinite loop. */
    private Tuple getRandomTraversableTilePos(Tile [][] tileMap) {
        int tMapWid = tileMap.length;
        int tMapHei = tileMap[0].length;
        Tuple outTup = null;
        int x, y;

        while(outTup == null) {
            x = (int)(Math.random() * (tMapWid - 2) + 1);
            y = (int)(Math.random() * (tMapHei - 2) + 1);
            if(tileMap[x][y].isTileTraversable()) {
                outTup = new Tuple(x, y);
            }
        }

        return outTup;
    }


    /* Generates a path in a map that goes from src to dest and
     * passes through the tiles in intermediates. Will only pass through
     * traversable tiles. */
    private ArrayList<Tuple> genPath(Tile [][] tileMap, Tuple src, ArrayList<Tuple> intermediates, Tuple dest) {
        ArrayList<Tuple> points = new ArrayList<Tuple>();
        ArrayList<Tuple> curPath; 

        points.add(src);
        points.addAll(intermediates);
        points.add(dest);

        curPath = genPath(tileMap, src, points.get(0));

        for(int i = 0; i < points.size() - 1; i++) {
            curPath.addAll(genPath(tileMap, points.get(i), points.get(i + 1)));
        }

        return curPath;
    }


    private ArrayList<Tuple> genPath(Tile [][] tileMap, Tuple src, Tuple dest) {
        return PathFinder.getPath(tileMap, src, dest);
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
        }
        else {
            outDoorTup = world.getDoorPos(adj, level);
        }



        return outDoorTup;
    }

    
    public Level load(World world, String fileName) {
        String [] levData = Gdx.files.internal(fileName).readString().split("\n");
        Tile [][] tileMap;
        MapTileFactory mtf = new MapTileFactory();

        tileMap = new Tile[dimX][dimY];

        int inX, inY;

        inX = Integer.valueOf(levData[0].split(" ")[0]);
        inY = Integer.valueOf(levData[0].split(" ")[1]);

        if(inX != dimX && inY != dimY) {
            throw new IllegalStateException("File map size does not fit");
        }

        for(int i = 1; i < levData.length; i++) {
            if(levData[i].equals("") || levData[i].charAt(0) == '#') {
                continue;
            }
            String [] parts = levData[i].split(" ");
            int x, y;
            String type;
            String name;

            x = Integer.valueOf(parts[0]);
            y = Integer.valueOf(parts[1]);

            type = parts[2];
            name = parts.length >= 4 ? parts[3] : "";

            tileMap[x][y] = mtf.makeTile(type, name);
        }

        Level level = new Level(dimX, dimY, bounds, tileMap, world);

        return level;
    }
}
