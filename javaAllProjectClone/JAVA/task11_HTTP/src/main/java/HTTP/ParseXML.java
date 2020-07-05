package HTTP;

import com.google.gson.Gson;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseXML {
    public void parseToList(String link) throws ParserConfigurationException, IOException, SAXException {
        File inputFile = new File(link);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("ticket");

        List<Ticket> ticketList = new ArrayList<Ticket>();
        for (int i = 0; i < nList.getLength(); i++) {
            Ticket ticket = new Ticket();
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                ticket.setCategory(eElement
                        .getElementsByTagName("category")
                        .item(0)
                        .getTextContent());
                ticket.setDate(eElement
                        .getElementsByTagName("date")
                        .item(0)
                        .getTextContent());
                ticket.setId(Integer.parseInt(eElement
                        .getElementsByTagName("id")
                        .item(0)
                        .getTextContent()));
                ticket.setPlace(Integer.parseInt(eElement
                        .getElementsByTagName("place")
                        .item(0)
                        .getTextContent()));
                ticket.setTitle(eElement
                        .getElementsByTagName("title")
                        .item(0)
                        .getTextContent());
                ticketList.add(ticket);
            }
        }

        ticketList = filterByStandart(ticketList);
        toJSON(ticketList);
    }

    public void toJSON(List<Ticket> list) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("tickets.json")) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> filterByStandart(List<Ticket> list) {
        List<Ticket> newList = new ArrayList<Ticket>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCategory().equals("STANDART"))
                newList.add(list.get(i));
        }
        return newList;
    }
}
