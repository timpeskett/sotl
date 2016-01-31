package net.thirteen.sotl.levels;

import java.util.ArrayList;
import java.util.HashMap;

import net.thirteen.sotl.tiles.Tile;
import net.thirteen.sotl.utils.Tuple;

public class PathFinder {

    /* Change from tileMap */
    public static ArrayList<Tuple> getPath(Level level, Tuple src, Tuple dest) {
        return getPath(level.getTileMap(), src, dest);
    }

    protected static ArrayList<Tuple> getPath(Tile [][] tileMap, Tuple src, Tuple dest) {
        HashMap<Tuple, ArrayList<Tuple>> paths;
        ArrayList<Tuple> frontier = new ArrayList<Tuple>();
        ArrayList<Tuple> explored = new ArrayList<Tuple>();
        ArrayList<Tuple> tempList = new ArrayList<Tuple>();
        ArrayList<Tuple> outPath = null;

        paths = new HashMap<Tuple, ArrayList<Tuple>>();

        frontier.add(src);

        tempList.add(src);
        paths.put(src, tempList);

        while(!frontier.isEmpty() && !dest.equals(frontier.get(0))) {
            Tuple curNode = frontier.get(0);
            ArrayList<Tuple> curPath = paths.get(curNode);
            ArrayList<Tuple> expand = getExpandNodes(tileMap, curNode);

            for(Tuple node : expand) {
                if(frontier.contains(node)) {
                    if(curPath.size() + 1 < paths.get(node).size()) {
                        tempList = new ArrayList<Tuple>(curPath);
                        tempList.add(node);
                        paths.put(node, tempList); 
                    }
                }
                else if(!explored.contains(node)) {
                    frontier.add(node);
                    tempList = new ArrayList<Tuple>(curPath);
                    tempList.add(node);
                    paths.put(node, tempList); 
                }
            }

            frontier.remove(0);
            explored.add(curNode);
        }

        if(paths.containsKey(dest)) {
            outPath = paths.get(dest);
        }
        
        return outPath;
    }



    private static ArrayList<Tuple> getExpandNodes(Tile [][] tileMap, Tuple tile) {
        int dimX = tileMap.length;
        int dimY = tileMap[0].length;
        ArrayList<Tuple> outTiles = new ArrayList<Tuple>();
        Tuple tempTile;

        if(tile.first() > 0) {
            tempTile = tile.firstDec();
            if(tileMap[tempTile.first()][tempTile.last()].isTileTraversable()) {
                outTiles.add(tempTile);
            }
        }
        if(tile.last() > 0) {
            tempTile = tile.lastDec();
            if(tileMap[tempTile.first()][tempTile.last()].isTileTraversable()) {
                outTiles.add(tempTile);
            }
        }
        if(tile.first() < dimX - 1) {
            tempTile = tile.firstInc();
            if(tileMap[tempTile.first()][tempTile.last()].isTileTraversable()) {
                outTiles.add(tempTile);
            }
        }
        if(tile.last() < dimY - 1) {
            tempTile = tile.lastInc();
            if(tileMap[tempTile.first()][tempTile.last()].isTileTraversable()) {
                outTiles.add(tempTile);
            }
        }

        return outTiles;
    }
}
