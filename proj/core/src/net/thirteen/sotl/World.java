package net.thirteen.sotl;

import net.thirteen.sotl.Main;
import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.levels.LevelMaker;
import net.thirteen.sotl.actors.Hero;
import net.thirteen.sotl.utils.Tuple;

import java.util.HashMap;

public class World {

    private Hero hero;
    private HashMap<Tuple, Level> levelMap;
    private Tuple curLevelTup;
    private LevelMaker maker;
    

    public World(LevelMaker lm) {
        this.maker = lm;

		hero = new Hero(this, 10 * Main.TILE_SIZE, 7 * Main.TILE_SIZE, Main.TILE_SIZE, Main.TILE_SIZE);

        levelMap = new HashMap<Tuple, Level>();
        curLevelTup = new Tuple(0, 0);
        /* Set up initial level. (Eventually load) */
        Level curLevel = maker.generate(null, 0, this);
        levelMap.put(curLevelTup, curLevel);
    }


    public Hero getHero() {
        return hero;
    }


    public Level getCurrentLevel() {
        return levelMap.get(curLevelTup);
    }


    public Level changeLevel(Tuple exitTile) {
        Level curLevel = getCurrentLevel();
        int newLevelX, newLevelY;

        newLevelX = curLevelTup.first();
        newLevelY = curLevelTup.last();

        if(exitTile.first() == 0) {
            newLevelX -= 1;
        }
        if(exitTile.first() == curLevel.getTilesX() - 1) {
            newLevelX += 1;
        }
        if(exitTile.last() == 0) {
            newLevelY -= 1;
        }
        if(exitTile.last() == curLevel.getTilesY() - 1) {
            newLevelY += 1;
        }

        curLevelTup = new Tuple(newLevelX, newLevelY);

        if(!levelMap.containsKey(curLevelTup)) {
            /* FIX */
            levelMap.put(curLevelTup, maker.generate(null, 0, this));
        }

        return getCurrentLevel();
    }

}

