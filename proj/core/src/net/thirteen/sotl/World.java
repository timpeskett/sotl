package net.thirteen.sotl;

import net.thirteen.sotl.Main;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.levels.LevelMaker;
import net.thirteen.sotl.actors.Hero;
import net.thirteen.sotl.utils.Tuple;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;

public class World implements Disposable {

    private Hero hero;
    private HashMap<Tuple, Level> levelMap;
    private Tuple curLevelTup;
    private LevelMaker maker;
    private int dimX, dimY;
    private boolean levelChanged;
    

    public World(int dimX, int dimY, Rectangle bounds) {
        this.maker = new LevelMaker(dimX, dimY, bounds);
        this.dimX = dimX;
        this.dimY = dimY;

		hero = new Hero(this, 10 * Main.TILE_SIZE, 7 * Main.TILE_SIZE, Main.TILE_SIZE * 0.75f, Main.TILE_SIZE * 0.75f, 200);

        levelMap = new HashMap<Tuple, Level>();
        curLevelTup = new Tuple(0, 0);
        /* Set up initial level. (Eventually load) */
        /*Level curLevel = maker.generate(this, new Tuple(0, 0), 0);*/
        Level curLevel = maker.load(this, "start_map.dat");
        levelMap.put(curLevelTup, curLevel);

        levelChanged = false;
    }


    public Hero getHero() {
        return hero;
    }


    public Level getCurrentLevel() {
        return levelMap.get(curLevelTup);
    }

    public Tuple getCurrentLevelTup() {
        return curLevelTup;
    }


    /* Returns the coordinates of the level in the world map */
    public Tuple changeLevel(Tuple exitTile) {
        Level curLevel = getCurrentLevel();
        int newLevelX, newLevelY;

        newLevelX = curLevelTup.first();
        newLevelY = curLevelTup.last();

        if(exitTile.first() == 0) {
            newLevelX += -1;
        }
        if(exitTile.first() == curLevel.getTilesX() - 1) {
            newLevelX += 1;
        }
        if(exitTile.last() == 0) {
            newLevelY += -1;
        }
        if(exitTile.last() == curLevel.getTilesY() - 1) {
            newLevelY += 1;
        }

        curLevelTup = new Tuple(newLevelX, newLevelY);
        if(!levelMap.containsKey(curLevelTup)) {
            levelMap.put(curLevelTup, maker.generate(this, curLevelTup, 0));
        }

        levelChanged = true;

        return curLevelTup;
    }

    public boolean hasLevelChanged() {
        return levelChanged;
    }

    public void resetLevelChange() {
        levelChanged = false;
    }


    public Level getLevel(Tuple levelNo) {
        return levelMap.get(levelNo);
    }


    /* Returns the tile coordinates of the door tile in level 
     * at position `to' in the map that leads from level `from'.
     * Returns null if no such door exists. Note that only one of to/from
     * needs to actually be a generated level for this to be derived. */
    public Tuple getDoorPos(Tuple from, Tuple to) {
        Tuple outTuple = null;
        int doorX, doorY;
        boolean flipped = false;
        Level level;

        if(from == null || to == null) {
            throw new IllegalArgumentException("Both tuples must be instantiated");
        }

        if(levelMap.containsKey(to)){
            level = levelMap.get(to);
        }
        else if(levelMap.containsKey(from)) {
            level = levelMap.get(from);
            flipped = true;
        }
        else {
            throw new IllegalArgumentException("Must be a level corresponding to at least one of the tuples.");
        }

        doorX = doorY = -1;

        /* Going to right */
        if(to.first() - from.first() == 1) {
            doorX = 0;
        }
        /* Going to left */
        else if(to.first() - from.first() == -1) {
            doorX = dimX - 1;
        }

        /* Going up */
        if(to.last() - from.last() == 1) {
            doorY = 0;
        }
        /* Going down */
        else if(to.last() - from.last() == -1) {
            doorY = dimY - 1;
        }
        

        /* Validate answer to ensure directly adjacent */
        if(from.manhattan(to) == 1) {
            if(!flipped) {
                if(doorX == -1) {
                    doorX = findDoorInRow(level, doorY);
                }
                else if(doorY == -1) {
                    doorY = findDoorInCol(level, doorX);
                }
            }
            else {
                if(doorX == -1) {
                    int tempDoorY = ((doorY == 0) ? dimY - 1 : 0);
                    doorX = findDoorInRow(level, tempDoorY);
                }
                else if(doorY == -1) {
                    int tempDoorX = doorX == 0 ? dimX - 1 : 0;
                    doorY = findDoorInCol(level, tempDoorX);
                }
            }

            if(doorX != -1 && doorY != -1) {
                outTuple = new Tuple(doorX, doorY);
            }
        }

        return outTuple;
    }


    /* Finds a door in a particular row of a level. Returns -1 if no
     * door is found on that level. Otherwise returns the column that
     * the door is on */
    private int findDoorInRow(Level level, int y) {
        int result = -1;

        for(Tuple t : level.getDoors().keySet()) {
            if(t.last() == y) {
                result = t.first();
            }
        }

        return result;
    }


    private int findDoorInCol(Level level, int x) {
        int result = -1;

        for(Tuple t : level.getDoors().keySet()) {
            if(t.first() == x) {
                result = t.last();
            }
        }

        return result;
    }

    @Override 
    public void dispose(){
    }
}

