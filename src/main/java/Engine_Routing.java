import org.w3c.dom.Document;
import roads.model.Parsing_Osm;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Engine_Routing {
    public static void main(String[] args) {

        File file = new File("NCH.osm");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
        Document doc = null;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (Exception e) {
            System.out.println("Ошибка парсинга" + e.toString());
            return;
        }

        Parsing_Osm parsing_osm = new Parsing_Osm();
        parsing_osm.Osm(doc);
    }
}
