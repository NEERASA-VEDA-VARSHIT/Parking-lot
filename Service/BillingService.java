package Service;

import java.time.Duration;
import java.time.LocalDateTime;

import models.payment.Bill;
import models.ticket.ParkingTicket;

public class BillingService {

    private ParkingSlotAllocator parkingSlotAllocator;

    public BillingService(
            ParkingSlotAllocator parkingSlotAllocator) {

        this.parkingSlotAllocator = parkingSlotAllocator;
    }

    public Bill exit(
            ParkingTicket ticket,
            LocalDateTime exitTime) {

        Bill bill = generateBill(ticket, exitTime);

        parkingSlotAllocator.releaseSlot(
                ticket.getSlot()
        );

        return bill;
    }

    private Bill generateBill(
            ParkingTicket ticket,
            LocalDateTime exitTime) {

        Bill bill = new Bill(
                exitTime,
                ticket
        );

        double amount = calculateAmount(
                ticket.getEntryTime(),
                exitTime
        );

        bill.setAmount(amount);

        return bill;
    }

    private double calculateAmount(
            LocalDateTime entryTime,
            LocalDateTime exitTime) {

        long minutesParked =
                Duration.between(
                        entryTime,
                        exitTime
                ).toMinutes();


        long hoursParked =
                (minutesParked + 59) / 60;

        return hoursParked * 10.0;
    }
}