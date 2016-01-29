package net.thirteen.sotl.levels;

import net.thirteen.sotl.tiles.Tile;
import net.thirteen.sotl.tiles.GrassTile;
import net.thirteen.sotl.tiles.WallTile;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LevelTest {
    @Test
    public void test() {
        Level l = new Level(20, 14, 1); 
        Tile [][] tMap = l.getTileMap();
        /* Do testing here */
        
        assertEquals(tMap.length, 20);
        assertEquals(tMap[0].length, 14);

        assertEquals(153, 100 + 53);
    }
}
