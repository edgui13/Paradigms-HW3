
//Name: Edgar Alcocer
//Date: Feb 28 2023
//Assignment Description: Build a map editor for a four-room video game.
//This is the model/world view of the video game.
import java.util.ArrayList;

class Model {
    ArrayList<Tile> tiles;

    Model() {
        tiles = new ArrayList<Tile>();
    }

    // Add tile function
    public void addTile(int x, int y) {
        Tile t = new Tile(x, y);
        tiles.add(t);
        // System.out.println("Added Tile");
    }

    // marshal the tiles
    Json marshal() {
        Json ob = Json.newObject();
        Json tmpList = Json.newList();
        ob.add("tiles", tmpList);
        for (int i = 0; i < tiles.size(); i++) {
            tmpList.add(tiles.get(i).marshal());
        }
        return ob;
    }

    // unmarshal the tiles
    public void unmarshal(Json ob) {
        tiles.clear();
        // System.out.println("Loading the model. ArrayList is currently at " +
        // tiles.size() + "items.");
        Json tmpList = ob.get("tiles");
        for (int i = 0; i < tmpList.size(); i++)
            tiles.add(new Tile(tmpList.get(i)));
    }
}