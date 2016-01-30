package net.thirteen.sotl.tiles;

import java.util.ArrayList;

/* `name' used here refers to the actual filename of the image. This
 * filename should be present within one of the tile classes. */
public class MapTileFactory {

    public MapTileFactory() {
    }

    /* Type is the string form of any of the tile type creation
     * methods in this class. For example: `Ground' will corresponds
     * to a call to the makeGroundTile() method. */
    public Tile makeTile(String type, String name) {
        if(type.toLowerCase().equals("ground")) {
            return makeGroundTile(name);
        }
        else if(type.toLowerCase().equals("door")) {
            return makeDoorTile(name);
        }
        else if(type.toLowerCase().equals("impassable")) {
            return makeImpassableTile(name);
        }
        else {
            return makeGroundTile("");
        }
    }

    public Tile makeGroundTile(String name) {
        if(isInTileSet(GrassTile.tileSets, name)) {
            return new GrassTile(name);
        }
        else if(isInTileSet(SlowTile.tileSets, name)) {
            return new SlowTile(name);
        }
        else {
            return new GrassTile();
        }
    }

    public Tile makeDoorTile(String name) {
        if(isInTileSet(DoorTile.tileSets, name)) {
            return new DoorTile(name);
        }
        else {
            return new DoorTile();
        }
    }

    public Tile makeImpassableTile(String name) {
        if(isInTileSet(WallTile.tileSets, name)) {
            return new WallTile(name);
        }
        else {
            return new WallTile();
        }
    }

    private boolean isInTileSet(ArrayList<ArrayList<String>> tileSets, String name) {
        boolean present = false;

        for(ArrayList<String> a : tileSets) {
            for(String s : a) {
                if(a.equals(name)) {
                    present = true;
                }
            }
        }
        return present;
    }
}
