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
import models.ticket.ParkingTicket;
import models.vehicle.Vehicle;

public class ParkingLotTest {

    public static void main(String[] args) {

        // ---------------------------------------------
        // SETUP
        // ---------------------------------------------

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

        List<ParkingSlot> slots =
                Arrays.asList(s1, s2, m1, m2);

        Gate g1 = new Gate(0, "Gate 1");
        Gate g2 = new Gate(1, "Gate 2");

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


        // ---------------------------------------------
        // TEST 1
        // Nearest SMALL slots
        // ---------------------------------------------

        System.out.println("TEST 1: Nearest slot");

        Vehicle vehicle1 = new Vehicle(
                "KA01AA1111",
                "White",
                "Swift"
        );

        Vehicle vehicle2 = new Vehicle(
                "KA01BB2222",
                "Black",
                "i20"
        );

        LocalDateTime entryTime =
                LocalDateTime.of(2026, 9, 25, 10, 0);

        ParkingTicket ticket1 =
                parkingService.park(
                        vehicle1,
                        SlotType.SMALL,
                        g1,
                        entryTime
                );

        ParkingTicket ticket2 =
                parkingService.park(
                        vehicle2,
                        SlotType.SMALL,
                        g1,
                        entryTime
                );

        System.out.println(
                "Vehicle 1 slot: "
                        + ticket1.getSlot().getId()
        );

        System.out.println(
                "Vehicle 2 slot: "
                        + ticket2.getSlot().getId()
        );

        assert ticket1.getSlot().getId().equals("S1");
        assert ticket2.getSlot().getId().equals("S2");

        System.out.println("PASS");
        System.out.println();


        // ---------------------------------------------
        // TEST 2
        // No SMALL slot available
        // ---------------------------------------------

        System.out.println("TEST 2: Parking full");

        Vehicle vehicle3 = new Vehicle(
                "KA01CC3333",
                "Blue",
                "Creta"
        );

        ParkingTicket ticket3 =
                parkingService.park(
                        vehicle3,
                        SlotType.SMALL,
                        g1,
                        entryTime
                );

        System.out.println(
                "Third vehicle ticket: "
                        + ticket3
        );

        assert ticket3 == null;

        System.out.println("PASS");
        System.out.println();


        // ---------------------------------------------
        // TEST 3
        // Release S1 and reuse it
        // ---------------------------------------------

        System.out.println("TEST 3: Slot reuse");

        LocalDateTime exitTime =
                entryTime.plusHours(2);

        billingService.exit(
                ticket1,
                exitTime
        );

        System.out.println(
                "S1 status after exit: "
                        + s1.getStatus()
        );

        assert s1.getStatus() == SlotStatus.AVAILABLE;


        Vehicle vehicle4 = new Vehicle(
                "KA01DD4444",
                "Red",
                "Nexon"
        );

        ParkingTicket ticket4 =
                parkingService.park(
                        vehicle4,
                        SlotType.SMALL,
                        g1,
                        exitTime
                );

        System.out.println(
                "Vehicle 4 slot: "
                        + ticket4.getSlot().getId()
        );

        assert ticket4.getSlot().getId().equals("S1");

        System.out.println("PASS");

        System.out.println();
        System.out.println("ALL TESTS PASSED");
    }
}