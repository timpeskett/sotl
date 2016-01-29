package net.thirteen.sotl.levels;

import net.thirteen.sotl.tiles.Tile;
import net.thirteen.sotl.tiles.WallTile;
import net.thirteen.sotl.tiles.GrassTile;

public class LevelGen {
    public static Tile [][] generate(int dimX,
                                     int dimY,
                                     int difficulty
                                     /* doors etc */) {
        Tile [][] tileMap = new Tile[dimX][dimY];

        /* Start simple. Fill map with grass */
        for(int x = 0; x < dimX; x++) {
            for(int y = 0; y < dimY; y++) {
                tileMap[x][y] = new GrassTile();
            }
        }

        for(int x = 0; x < dimX; x++) {
            tileMap[x][0] = new WallTile();
            tileMap[x][dimY-1] = new WallTile();
        }
            
        for(int y = 0; y < dimY; y++) {
            tileMap[0][y] = new WallTile();
            tileMap[dimX-1][y] = new WallTile();
        }

        return tileMap;
    }
}
