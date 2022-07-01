package roads.model;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Way {

    int id;
    List<Long> ndList = new ArrayList<>();


    public Way(Node node) {
        NamedNodeMap nodeMap = node.getAttributes();
        id = Integer.parseInt(nodeMap.getNamedItem("id").getNodeValue());
        ndList.add((long) id);
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node interimNode = nodeList.item(i);
            if (interimNode.getNodeName().equals("nd")) {
                NamedNodeMap nd = interimNode.getAttributes();
                long nodeId = Long.parseLong(nd.getNamedItem("ref").getNodeValue());
                ndList.add(nodeId);
            }
        }

    }
}
