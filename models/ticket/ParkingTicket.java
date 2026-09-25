package models.ticket;
import java.time.LocalDateTime;

import models.parking.Gate;
import models.parking.ParkingSlot;
import models.vehicle.Vehicle;

public class ParkingTicket {

    private String ticketId;
    private Vehicle vehicle;
    private ParkingSlot slot;
    private Gate gate;
    private LocalDateTime entryTime;

    public ParkingTicket(
            String ticketId,
            Vehicle vehicle,
            ParkingSlot slot,
            Gate gate,
            LocalDateTime entryTime) {

        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.slot = slot;
        this.gate = gate;
        this.entryTime = entryTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public Gate getGate() {
        return gate;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}