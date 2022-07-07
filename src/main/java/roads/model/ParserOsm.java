package roads.model;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ParserOsm {

    Set<String> roadType = Stream.of("motorway", "trunk", "primary", "secondary", "tertiary", "unclassified", "residential", "motorway_link", "trunk_link", "primary_link", "secondary_link", "tertiary_link", "living_street", "service").collect(Collectors.toSet());

    Set<Long> waysRefs = new HashSet<>();
    Set<Way> wayList = new HashSet<>();
    Map<Long, NodeOsm> nodesList = new HashMap<>();
    Set<Long> crossroads = new HashSet<>();



    public void parseOsm(Document doc) {
        org.w3c.dom.Node osmNode = doc.getFirstChild();
        NodeList osmChilds = osmNode.getChildNodes();
        org.w3c.dom.Node wayNode;


        for (int i = 0; i < osmChilds.getLength(); i++) {
            if (osmChilds.item(i).getNodeType() != org.w3c.dom.Node.ELEMENT_NODE) {
                continue;
            }

            if (osmChilds.item(i).getNodeName().equals("way")) {
                wayNode = osmChilds.item(i);
                NodeList wayChildNodes = wayNode.getChildNodes();

                for (int j = 0; j < wayChildNodes.getLength(); j++) {

                    org.w3c.dom.Node tagChilds = wayChildNodes.item(j);
                    if (tagChilds.getNodeType() != org.w3c.dom.Node.ELEMENT_NODE) {
                        continue;
                    }
                    if (tagChilds.getNodeName().equals("tag")) {
                        NamedNodeMap atributes = tagChilds.getAttributes();
                        String v = atributes.getNamedItem("v").getNodeValue();
                        if (atributes.getNamedItem("k").getNodeValue().equals("highway")) {
                            boolean right_road = roadType.add(v);
                            if (!right_road) {
                                Way rightRoad = new Way(wayNode);
                                waysRefs.addAll(rightRoad.getNdList());
                                wayList.add(rightRoad);
                            } else {
                                roadType.remove(v);
                            }
                        }
                    }
                }
            }
        }
    }

    public Map<Long, NodeOsm> parseNode(Document doc) {
        org.w3c.dom.Node osmNode = doc.getFirstChild();
        NodeList osmChilds = osmNode.getChildNodes();


        for (int i = 0; i < osmChilds.getLength(); i++) {
            if (osmChilds.item(i).getNodeType() != org.w3c.dom.Node.ELEMENT_NODE) {
                continue;
            }

            if (osmChilds.item(i).getNodeName().equals("node")) {
                Node nodeNode = osmChilds.item(i);
                NamedNodeMap atributes = nodeNode.getAttributes();
                long nodeId = Long.parseLong(atributes.getNamedItem("id").getNodeValue());
                boolean rightnode = waysRefs.add(nodeId);
                if (!rightnode) {
                    double lat = Double.parseDouble(atributes.getNamedItem("lat").getNodeValue());
                    double lon = Double.parseDouble(atributes.getNamedItem("lon").getNodeValue());
                    NodeOsm rightNodeOsm = new NodeOsm(lat, lon);
                    nodesList.put(nodeId, rightNodeOsm);

                    int k = 0;
                    for (Way way: wayList) {
                        if (way.getNdList().contains(nodeId)) {
                            k++;
                        }
                        if (k > 1) {
                            crossroads.add(nodeId);
                            break;
                        }
                    }
                }
                else {
                    waysRefs.remove(nodeId);
                }
            }
        }
        return nodesList;
    }

}

