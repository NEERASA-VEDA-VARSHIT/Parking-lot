package models.map;
import java.util.ArrayList;
import java.util.List;

import models.parking.Gate;
import models.parking.ParkingSlot;

public class DistanceMap {

    private List<List<Integer>> distanceMap;
    private List<Gate> gates;
    private List<ParkingSlot> parkingSlots;

    public DistanceMap(
            List<Gate> gates,
            List<ParkingSlot> parkingSlots,
            int[][] distanceMap) {

        this.gates = new ArrayList<>(gates);
        this.parkingSlots = new ArrayList<>(parkingSlots);

        this.distanceMap = new ArrayList<>();

        for (int[] row : distanceMap) {

            List<Integer> list = new ArrayList<>();

            for (int value : row) {
                list.add(value);
            }

            this.distanceMap.add(list);
        }
    }

    public int getDistance(Gate gate, ParkingSlot slot) {

        int gateIndex = gates.indexOf(gate);
        int slotIndex = parkingSlots.indexOf(slot);

        if (gateIndex == -1 || slotIndex == -1) {
            throw new IllegalArgumentException(
                    "Gate or parking slot does not exist in DistanceMap"
            );
        }

        return distanceMap.get(gateIndex).get(slotIndex);
    }
}