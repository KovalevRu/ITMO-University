package Objects;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomId;
    private List<Furniture> furniture;

    public Room(int roomId) {
        this.roomId = roomId;
        this.furniture = new ArrayList<>();
    }

    public void addFurniture(Furniture furnitureItem) {
        furniture.add(furnitureItem);
    }
}
