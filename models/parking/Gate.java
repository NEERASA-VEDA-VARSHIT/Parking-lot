package models.parking;
public class Gate {

    private int gateId;
    private String gateName;

    public Gate(int gateId, String gateName) {
        this.gateId = gateId;
        this.gateName = gateName;
    }

    public int getGateId() {
        return gateId;
    }

    public String getGateName() {
        return gateName;
    }
}