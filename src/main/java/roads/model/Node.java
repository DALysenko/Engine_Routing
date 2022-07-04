package roads.model;

import org.w3c.dom.NamedNodeMap;

public class Node {
    private long id;
    private double lat;
    private double lon;
    public Node(org.w3c.dom.Node node) {
        NamedNodeMap nodeMap = node.getAttributes();
        id = Long.parseLong(nodeMap.getNamedItem("id").getNodeValue());
        lat = Double.parseDouble(nodeMap.getNamedItem("lat").getNodeValue());
        lon = Double.parseDouble(nodeMap.getNamedItem("lon").getNodeValue());
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
