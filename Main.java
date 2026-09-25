import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import Service.BillingService;
import Service.ParkingService;
import Service.ParkingSlotAllocator;
import enums.slot.SlotStatus;
import enums.slot.SlotType;
import models.map.DistanceMap;
import models.parking.Gate;
import models.parking.ParkingSlot;
import models.payment.Bill;
import models.ticket.ParkingTicket;
import models.vehicle.Vehicle;

public class Main {

    public static void main(String[] args) {

        ParkingSlot s1 = new ParkingSlot(
                "S1",
                SlotType.SMALL,
                SlotStatus.AVAILABLE
        );

        ParkingSlot s2 = new ParkingSlot(
                "S2",
                SlotType.SMALL,
                SlotStatus.AVAILABLE
        );

        ParkingSlot m1 = new ParkingSlot(
                "M1",
                SlotType.MEDIUM,
                SlotStatus.AVAILABLE
        );

        ParkingSlot m2 = new ParkingSlot(
                "M2",
                SlotType.MEDIUM,
                SlotStatus.AVAILABLE
        );

        List<ParkingSlot> slots =Arrays.asList(s1, s2, m1, m2);

        Gate g1 = new Gate(
                0,
                "Gate 1"
        );

        Gate g2 = new Gate(
                1,
                "Gate 2"
        );

        List<Gate> gates =
                Arrays.asList(g1, g2);


       

        int[][] distances = {
                {10, 20, 30, 40},
                {40, 30, 20, 10}
        };


        DistanceMap distanceMap =
                new DistanceMap(
                        gates,
                        slots,
                        distances
                );




        ParkingSlotAllocator allocator =
                new ParkingSlotAllocator(
                        gates,
                        slots,
                        distanceMap
                );



        ParkingService parkingService =
                new ParkingService(allocator);

        BillingService billingService =
                new BillingService(allocator);



        Vehicle vehicle = new Vehicle(
                "KA01AB1234",
                "White",
                "Swift"
        );



        LocalDateTime entryTime =
                LocalDateTime.now();

        ParkingTicket ticket =
                parkingService.park(
                        vehicle,
                        SlotType.SMALL,
                        g1,
                        entryTime
                );


        if (ticket == null) {

            System.out.println(
                    "No available slot for the vehicle."
            );

            return;
        }



        System.out.println(
                "========== PARKING TICKET =========="
        );

        System.out.println(
                "Ticket ID: "
                        + ticket.getTicketId()
        );

        System.out.println(
                "Vehicle: "
                        + ticket.getVehicle().getVehicleNo()
        );

        System.out.println(
                "Assigned Slot: "
                        + ticket.getSlot().getId()
        );

        System.out.println(
                "Gate: "
                        + ticket.getGate().getGateName()
        );

        System.out.println(
                "Entry Time: "
                        + ticket.getEntryTime()
        );


        LocalDateTime exitTime =
                entryTime
                        .plusHours(2)
                        .plusMinutes(30);

        Bill bill =
                billingService.exit(
                        ticket,
                        exitTime
                );


        System.out.println();

        System.out.println(
                "============== BILL =============="
        );

        System.out.println(
                "Exit Time: "
                        + bill.getExitTime()
        );

        System.out.println(
                "Amount: $"
                        + bill.getAmount()
        );


        System.out.println();

        System.out.println(
                "Slot Status after exit: "
                        + ticket.getSlot().getStatus()
        );
    }
}