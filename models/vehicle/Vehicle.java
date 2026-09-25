package models.vehicle;
public class Vehicle {

    private String vehicleNo;
    private String colour;
    private String model;

    public Vehicle(String vehicleNo, String colour, String model) {
        this.vehicleNo = vehicleNo;
        this.colour = colour;
        this.model = model;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getColour() {
        return colour;
    }

    public String getModel() {
        return model;
    }
}