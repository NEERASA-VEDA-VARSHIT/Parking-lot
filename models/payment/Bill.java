package models.payment;
import java.time.LocalDateTime;

import models.ticket.ParkingTicket;

public class Bill {

    private LocalDateTime exitTime;
    private ParkingTicket parkingTicket;
    private double amount;

    public Bill(
            LocalDateTime exitTime,
            ParkingTicket parkingTicket) {

        this.exitTime = exitTime;
        this.parkingTicket = parkingTicket;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}