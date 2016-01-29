package net.thirteen.sotl;

import net.thirteen.sotl.levels.Level;
import net.thirteen.sotl.actors.Hero;
import net.thirteen.sotl.utils.Tuple;

import java.util.HashMap;

public class World {

    private Hero hero;
    private HashMap<Tuple, Level> levelMap;
    

    public World(Hero hero) {
        this.hero = hero;
        levelMap = new HashMap<Tuple, Level>();
    }

    public Hero getHero() {
        return hero;
    }
}

