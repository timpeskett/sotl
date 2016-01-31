package net.thirteen.sotl.tiles;

import java.util.ArrayList;

public class TileFactory {
    int baseTileSetNum;
    double internalVariance, externalVariance;

    public TileFactory(int baseTileSetNum, double internalVariance, double externalVariance) {
        this.baseTileSetNum = baseTileSetNum;
        this.internalVariance = internalVariance;
        this.externalVariance = externalVariance;

    }

    /* Each variable determines how `crazy' the variation in
     * tiles is */


    public Tile makeImpassableTile() {
        return makeImpassableTile(0);
    }

    public Tile makeImpassableTile(int tileNum) {
        ArrayList<String> tileSet = WallTile.tileSets.get(getTileSetNum());
        return new WallTile(tileSet.get(getTileNum(tileNum, tileSet.size())));
    }

    public Tile makeGrassTile() {
        return makeGrassTile(0);
    }

    public Tile makeGrassTile(int tileNum) {
        ArrayList<String> tileSet = GrassTile.tileSets.get(getTileSetNum());
        return new GrassTile(tileSet.get(getTileNum(tileNum, tileSet.size())));
    }

    public Tile makeSlowTile() {
        return makeSlowTile(0);
    }

    public Tile makeSlowTile(int tileNum) {
        ArrayList<String> tileSet = SlowTile.tileSets.get(getTileSetNum());
        return new SlowTile(tileSet.get(getTileNum(tileNum, tileSet.size())));
    }

    public Tile makeDoorTile() {
        return makeDoorTile(0);
    }

    public Tile makeDoorTile(int tileNum) {
        ArrayList<String> tileSet = DoorTile.tileSets.get(getTileSetNum());
        return new DoorTile(tileSet.get(getTileNum(tileNum, tileSet.size())));
    }

    public static int getNumTileSets() {
        return GrassTile.tileSets.size();
    }

    /* Uses randomness with the externalVariance variable to introduce
     * the possibility of mixing tilesets */
    public int getTileSetNum() {
        int tileSetNum;
        if(Math.random() > (1 - externalVariance)) {
            tileSetNum = (int)(Math.random() * 0.99 * getNumTileSets());
        }
        else {
            tileSetNum = baseTileSetNum;
        }

        return tileSetNum;
    }


    /* Uses randomness with the internalVariance variable to introduce
     * the possibility of different tiles within the same tileset */
    public int getTileNum(int baseTileNum, int numTiles) {
        int tileNum;
        if(Math.random() > (1 - internalVariance)) {
            tileNum = (int)(Math.random() * 0.99 * numTiles);
        }
        else {
            if(baseTileNum < numTiles) {
                tileNum = baseTileNum;
            }
            else {
                tileNum = 0;
            }
        }

        return tileNum;
    }

}
