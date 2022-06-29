package roads.model;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Parsing_Osm {

    Set<String> roadType = Stream.of("motorway", "trunk", "primary", "secondary", "tertiary", "unclassified", "residential", "motorway_link", "trunk_link", "primary_link", "secondary_link", "tertiary_link", "living_street", "service").collect(Collectors.toSet());

    public void Osm(Document doc) {
        NodeList wayList = doc.getElementsByTagName("way");

    }
}
