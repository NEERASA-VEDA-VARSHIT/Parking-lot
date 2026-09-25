package models.parking;
import enums.slot.SlotStatus;
import enums.slot.SlotType;

public class ParkingSlot {

    private String id;
    private SlotType type;
    private SlotStatus status;

    public ParkingSlot(String id, SlotType type, SlotStatus status) {
        this.id = id;
        this.type = type;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public SlotType getType() {
        return type;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }
}