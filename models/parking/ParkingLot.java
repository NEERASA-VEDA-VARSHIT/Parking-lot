package models.parking;
import java.util.ArrayList;
import java.util.List;

import models.map.DistanceMap;

public class ParkingLot {

    private String name;
    private String address;
    private List<Floor> floors;
    private List<Gate> gates;
    private DistanceMap distanceMap;

    public ParkingLot(String name, String address) {
        this.name = name;
        this.address = address;
        this.floors = new ArrayList<>();
        this.gates = new ArrayList<>();
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public List<Floor> getFloors() {
        return new ArrayList<>(floors);
    }

    public void addGate(Gate gate) {
        gates.add(gate);
    }

    public List<Gate> getGates() {
        return new ArrayList<>(gates);
    }

    public void setDistanceMap(DistanceMap distanceMap) {
        this.distanceMap = distanceMap;
    }
}