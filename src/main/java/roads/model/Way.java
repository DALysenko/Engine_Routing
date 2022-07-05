package roads.model;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Way {


    private long id;
    private List<Long> ndList = new ArrayList<>();



    public Way(Node node) {
        NamedNodeMap nodeMap = node.getAttributes();
        id = Long.parseLong(nodeMap.getNamedItem("id").getNodeValue());
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

    public void setId(long id) {
        this.id = id;
    }

    public void setNdList(List<Long> ndList) {
        this.ndList = ndList;
    }

    public long getId() {
        return id;
    }

    public List<Long> getNdList() {
        return ndList;
    }
}
