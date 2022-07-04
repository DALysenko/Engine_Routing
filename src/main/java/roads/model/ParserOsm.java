package roads.model;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ParserOsm {

    Set<String> roadType = Stream.of("motorway", "trunk", "primary", "secondary", "tertiary", "unclassified", "residential", "motorway_link", "trunk_link", "primary_link", "secondary_link", "tertiary_link", "living_street", "service").collect(Collectors.toSet());

    Map<Long, Way> wayList = new HashMap<>();
    Map<Long, Node> nodesList = new HashMap<>();



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
                                wayList.put(rightRoad.getId(), rightRoad);
                            } else {
                                roadType.remove(v);
                            }
                        }
                    }
                }
            }
        }
    }

    public Map<Long, Node> parseNode(Document doc) {
        org.w3c.dom.Node osmNode = doc.getFirstChild();
        NodeList osmChilds = osmNode.getChildNodes();
        org.w3c.dom.Node nodeNode;

        for (int i = 0; i < osmChilds.getLength(); i++) {
            if (osmChilds.item(i).getNodeType() != org.w3c.dom.Node.ELEMENT_NODE) {
                continue;
            }

            if (osmChilds.item(i).getNodeName().equals("node")) {
                nodeNode = osmChilds.item(i);
                NamedNodeMap atributes = nodeNode.getAttributes();
                String id = atributes.getNamedItem("id").getNodeValue();
                boolean rightnode = wayList.containsKey(id);

                if (!rightnode) {
                    Node rightNode = new Node(nodeNode);
                    nodesList.put(rightNode.getId(), rightNode);
                }
                else {
                    wayList.remove(id);
                }


            }
        }
        return nodesList;
    }
}
