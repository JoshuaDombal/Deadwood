import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.util.*;
import java.io.*;


public class Reader{

    public static ArrayList<SceneCard> readCards(){

        ArrayList<SceneCard> sceneCards = null;

        try {
            File file = new File("cards.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList cardList = doc.getElementsByTagName("card");

            sceneCards = new ArrayList<SceneCard>();



            for (int i = 0; i < cardList.getLength(); i++) {
                Node nNode = cardList.item(i);
                SceneCard card;

                String name;
                int b;
                int sceneNum;


                // Change this 3!!!!!!!!!!!!!!!
                Role[] roles = new Role[3];
                //System.out.println("\nCurrent Element : " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element cardElement = (Element) nNode;
                    //System.out.println("Card Name : " + cardElement.getAttribute("name"));
                    name = cardElement.getAttribute("name");

                    b = Integer.parseInt(cardElement.getAttribute("budget"));

                    NodeList numNode = cardElement.getElementsByTagName("scene");
                    Node eNode = numNode.item(0);
                    Element e = (Element) eNode;
                    //System.out.println("Scene Num : " + e.getAttribute("number"));

                    sceneNum = Integer.parseInt(e.getAttribute("number"));


                    NodeList partList = cardElement.getElementsByTagName("part");

                    for (int j = 0; j < partList.getLength(); j++) {
                        Node pNode = partList.item(j);
                        Element partElement = (Element) pNode;
                        //System.out.println("Role Name : " + partElement.getAttribute("name"));
                        String n = partElement.getAttribute("name");

                        //System.out.println("Role Level : " + partElement.getAttribute("level"));
                        int lev = Integer.parseInt(partElement.getAttribute("level"));

                        //System.out.println("Role Line : " + partElement.getElementsByTagName("line").item(0).getTextContent());
                        String l = partElement.getElementsByTagName("line").item(0).getTextContent();

                        Role rRole = new Role(n, lev, l);
                        roles[j] = rRole;

                    }
                    card = new SceneCard(name, 3, b, roles, sceneNum);
                    sceneCards.add(card);
                }

                System.out.println("\n");
            }

        } catch (SAXParseException err) {
            System.out.println("Parse error");
        } catch (SAXException e) {
            System.out.println("error");
        } catch (Throwable t) {
            t.printStackTrace ();
        }

        return sceneCards;
    }
}
