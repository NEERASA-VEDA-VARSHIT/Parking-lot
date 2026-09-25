package Service;

import java.time.LocalDateTime;

import enums.slot.SlotType;
import models.parking.Gate;
import models.parking.ParkingSlot;
import models.ticket.ParkingTicket;
import models.vehicle.Vehicle;

public class ParkingService {

    private ParkingSlotAllocator parkingSlotAllocator;
    private int ticketCounter;

    public ParkingService(ParkingSlotAllocator parkingSlotAllocator) {

        this.parkingSlotAllocator = parkingSlotAllocator;
        this.ticketCounter = 0;
    }

    public ParkingTicket park(
            Vehicle vehicle,
            SlotType slotType,
            Gate gate,
            LocalDateTime entryTime) {

        ParkingSlot nearestAvailableSlot =
                parkingSlotAllocator.getNearestAvailableSlot(
                        gate,
                        slotType
                );

        if (nearestAvailableSlot == null) {
            return null;
        }

        String ticketId = generateTicketId();

        return new ParkingTicket(
                ticketId,
                vehicle,
                nearestAvailableSlot,
                gate,
                entryTime
        );
    }

    private String generateTicketId() {

        ticketCounter++;

        return "T" + ticketCounter;
    }
}