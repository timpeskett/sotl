package net.thirteen.sotl;

import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.actors.Hero;
import net.thirteen.sotl.utils.Tuple;
import net.thirteen.sotl.Main;

import java.util.HashMap;

public class World {

    private Hero hero;
    private HashMap<Tuple, Level> levelMap;
    

    public World() {
		hero = new Hero(this, 10 * Main.TILE_SIZE, 7 * Main.TILE_SIZE, Main.TILE_SIZE, Main.TILE_SIZE);
        levelMap = new HashMap<Tuple, Level>();
    }

    public Hero getHero() {
        return hero;
    }
}

