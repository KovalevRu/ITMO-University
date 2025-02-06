package Environment;

//Запись (record), описывающая предмет в номере.
public record Item(RoomItem type, String description) {
    @Override
    public String toString() {
        return description + " (" + type + ")";
    }
}
