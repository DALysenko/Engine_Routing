import org.w3c.dom.Document;
import roads.model.ParserOsm;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class EngineRouting {
    public static void main(String[] args) {

        File file = new File("NCH.osm");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
        Document doc;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (Exception e) {
            System.out.println("Ошибка парсинга" + e);
            return;
        }
        System.out.println(System.currentTimeMillis());
        ParserOsm parser = new ParserOsm();
        parser.parseOsm(doc);
        parser.parseNode(doc);

        System.out.println(System.currentTimeMillis());

    }
}
