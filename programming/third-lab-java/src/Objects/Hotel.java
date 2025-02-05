package Objects;

public class Hotel {
    protected String hotelName;
    protected int lowestRoomPrice;
    protected int mediumRoomPrice;
    protected int highestRoomPrice;


    public Hotel(String hotelName, int lowestRoomPrice, int mediumRoomPrice, int highestRoomPrice) {
        this.hotelName = hotelName;
        this.lowestRoomPrice = lowestRoomPrice;
        this.mediumRoomPrice = mediumRoomPrice;
        this.highestRoomPrice = highestRoomPrice;
    }
    public String getHotelName() {
        return hotelName;
    }

    public int getHighestRoomPrice() {
        return highestRoomPrice;
    }

    public int getMediumRoomPrice() {
        return mediumRoomPrice;
    }

    public int getLowestRoomPrice() {
        return lowestRoomPrice;
    }
}
