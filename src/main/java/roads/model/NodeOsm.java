package roads.model;

public class NodeOsm {
    private long id;

    private double lat;
    private double lon;
    public NodeOsm(long id, double lat, double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
