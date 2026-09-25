package Service;

import enums.slot.SlotStatus;
import enums.slot.SlotType;
import models.map.DistanceMap;
import models.parking.Gate;
import models.parking.ParkingSlot;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class ParkingSlotAllocator {

    private Map<Gate, Map<SlotType, TreeSet<ParkingSlot>>> map;

    public ParkingSlotAllocator(
            List<Gate> gates,
            List<ParkingSlot> parkingSlots,
            DistanceMap distanceMap) {

        map = new HashMap<>();

        for (Gate gate : gates) {

            Map<SlotType, TreeSet<ParkingSlot>> slotsByType =
                    new HashMap<>();

            TreeSet<ParkingSlot> smallSlots =
                    createSlotSet(gate, SlotType.SMALL, parkingSlots, distanceMap);

            TreeSet<ParkingSlot> mediumSlots =
                    createSlotSet(gate, SlotType.MEDIUM, parkingSlots, distanceMap);

            TreeSet<ParkingSlot> largeSlots =
                    createSlotSet(gate, SlotType.LARGE, parkingSlots, distanceMap);

            slotsByType.put(SlotType.SMALL, smallSlots);
            slotsByType.put(SlotType.MEDIUM, mediumSlots);
            slotsByType.put(SlotType.LARGE, largeSlots);

            map.put(gate, slotsByType);
        }
    }

    private TreeSet<ParkingSlot> createSlotSet(
            Gate gate,
            SlotType slotType,
            List<ParkingSlot> parkingSlots,
            DistanceMap distanceMap) {

        TreeSet<ParkingSlot> slots = new TreeSet<>(
                (slot1, slot2) -> {

                    int distance1 =
                            distanceMap.getDistance(gate, slot1);

                    int distance2 =
                            distanceMap.getDistance(gate, slot2);

                    if (distance1 != distance2) {
                        return Integer.compare(distance1, distance2);
                    }

                    return slot1.getId().compareTo(slot2.getId());
                }
        );

        for (ParkingSlot slot : parkingSlots) {

            if (slot.getType() == slotType) {
                slots.add(slot);
            }
        }

        return slots;
    }

    public synchronized ParkingSlot getNearestAvailableSlot(
            Gate gate,
            SlotType slotType) {

        Map<SlotType, TreeSet<ParkingSlot>> slotsByType =
                map.get(gate);

        if (slotsByType == null) {
            return null;
        }

        TreeSet<ParkingSlot> slots =
                slotsByType.get(slotType);

        if (slots == null) {
            return null;
        }

        for (ParkingSlot slot : slots) {

            if (slot.getStatus() == SlotStatus.AVAILABLE) {

                slot.setStatus(SlotStatus.OCCUPIED);

                return slot;
            }
        }

        return null;
    }

    public synchronized void releaseSlot(ParkingSlot slot) {
        slot.setStatus(SlotStatus.AVAILABLE);
    }
}