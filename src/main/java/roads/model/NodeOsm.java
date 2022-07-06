package roads.model;

public class NodeOsm {

    private double lat;
    private double lon;
    public NodeOsm(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
    



    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
