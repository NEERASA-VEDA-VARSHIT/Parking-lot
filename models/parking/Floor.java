package models.parking;
import java.util.ArrayList;
import java.util.List;

public class Floor {

    private String id;
    private String name;
    private List<ParkingSlot> parkingSlots;

    public Floor(String id, String name, List<ParkingSlot> parkingSlots) {
        this.id = id;
        this.name = name;
        this.parkingSlots = parkingSlots;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ParkingSlot> getParkingSlots() {
        return new ArrayList<>(parkingSlots);
    }
}