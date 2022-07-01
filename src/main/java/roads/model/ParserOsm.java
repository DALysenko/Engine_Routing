package roads.model;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ParserOsm {

    Set<String> roadType = Stream.of("motorway", "trunk", "primary", "secondary", "tertiary", "unclassified", "residential", "motorway_link", "trunk_link", "primary_link", "secondary_link", "tertiary_link", "living_street", "service").collect(Collectors.toSet());

    List<Way> wayList = new ArrayList<>();



    public void parseOsm(Document doc) {
        Node osmNode = doc.getFirstChild();
        NodeList osmChilds = osmNode.getChildNodes();
        Node wayNode = null;


        for (int i = 0; i < osmChilds.getLength(); i++) {
            if (osmChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (osmChilds.item(i).getNodeName().equals("way")) {
                wayNode = osmChilds.item(i);
                NodeList wayChildNodes = wayNode.getChildNodes();

                for (int j = 0; j < wayChildNodes.getLength(); j++) {

                    Node tagChilds = wayChildNodes.item(j);
                    if (tagChilds.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    if (tagChilds.getNodeName().equals("tag")) {
                        NamedNodeMap atributes = tagChilds.getAttributes();
                        String v = atributes.getNamedItem("v").getNodeValue();
                        if (atributes.getNamedItem("k").getNodeValue().equals("highway")) {
                            boolean right_road = roadType.add(v);
                            if (!right_road) {
                                Way rightRoad = new Way(wayNode);
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
}
