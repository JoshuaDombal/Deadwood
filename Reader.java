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

    private static ArrayList<Set> sets;
    private static ArrayList<Room> rooms;


    public static ArrayList<Room>getRooms() {
        return rooms;
    }

    public static ArrayList<Set>getSets() {
        return sets;
    }

    public static void readRooms() {

        rooms = new ArrayList<Room>();
        sets = new ArrayList<Set>();

        try {
            File file1 = new File("board.xml");
            DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder1 = dbf1.newDocumentBuilder();
            Document doc1 = docBuilder1.parse(file1);

            sets = new ArrayList<Set>();
            rooms = new ArrayList<Room>();



            NodeList list = doc1.getElementsByTagName("board");
            Node bNode = list.item(0);
            Element el = (Element) bNode;

            //System.out.println("Board name: " + el.getAttribute("name"));



            // Casting Office read
            Room co;
            String oName = "office";
            String[] officeNeighbors = new String[3];


            NodeList officeList = el.getElementsByTagName("office");
            Node officeNode = officeList.item(0);
            Element oElement = (Element) officeNode;

            //NodeList nList = tElement.getElementsByTagName("neighbors");
            //Node neNode = trailerList.item(0);
            //Element neElement = (Element) neNode;


            NodeList neiList = oElement.getElementsByTagName("neighbor");
            //System.out.println(nList.getLength());
            for (int j = 0; j < neiList.getLength(); j++) {
                Node neNode = neiList.item(j);
                Element neElement = (Element) neNode;

                officeNeighbors[j] = neElement.getAttribute("name");

                System.out.println("OFFICE Neighbor: " + neElement.getAttribute("name"));
            }

            co = new Room(oName, officeNeighbors);
            rooms.add(co);











            //Trailer trailer;
            //CastingOffice castingOffice;
            Room t;
            String rName = "trailer";
            String[] ns = new String[3];


            NodeList trailerList = el.getElementsByTagName("trailer");
            Node trailerNode = trailerList.item(0);
            Element tElement = (Element) trailerNode;

            //NodeList nList = tElement.getElementsByTagName("neighbors");
            //Node neNode = trailerList.item(0);
            //Element neElement = (Element) neNode;


            NodeList nList = tElement.getElementsByTagName("neighbor");
            //System.out.println(nList.getLength());
            for (int j = 0; j < nList.getLength(); j++) {
                Node neNode = nList.item(j);
                Element neElement = (Element) neNode;

                ns[j] = neElement.getAttribute("name");

                //System.out.println("TRAILER Neighbor: " + neElement.getAttribute("name"));
            }

            t = new Room(rName, ns);
            rooms.add(t);

            //System.out.println("NEIBS: " + neElement.getAttribute("name"));



            NodeList setList = el.getElementsByTagName("set");
            //System.out.println(setList.getLength());
            for (int i = 0; i < setList.getLength(); i++) {

                Set set;

                Room room;

                String setName;
                int nShots = 0;
                int nRoles = 0;
                Role[] roles = new Role[4];

                String[] neighbors = new String[4];




                Node setNode = setList.item(i);
                Element setElement = (Element) setNode;

                //System.out.println("Set name: " + setElement.getAttribute("name"));
                setName = setElement.getAttribute("name");

                //GET NEIGHBORS
                NodeList neighborList = setElement.getElementsByTagName("neighbor");
                for (int j = 0; j < neighborList.getLength(); j++) {
                    Node neighborNode = neighborList.item(j);
                    Element neighborElement = (Element) neighborNode;

                    neighbors[j] = neighborElement.getAttribute("name");

                    //System.out.println("Neighbor: " + neighborElement.getAttribute("name"));
                }

                // Gets roles
                NodeList partList = setElement.getElementsByTagName("part");
                for (int j = 0; j < partList.getLength(); j++) {
                    Role role;
                    String roleName;
                    int roleRank;
                    String line;

                    Node partNode = partList.item(j);
                    Element partElement = (Element) partNode;

                    //System.out.println("Role name: " + partElement.getAttribute("name"));
                    roleName = partElement.getAttribute("name");

                    //System.out.println("Role level: " + partElement.getAttribute("level"));
                    roleRank = Integer.parseInt(partElement.getAttribute("level"));

                    //System.out.println("Role line: " + partElement.getElementsByTagName("line").item(0).getTextContent());
                    line = partElement.getElementsByTagName("line").item(0).getTextContent();

                    role = new Role(roleName, roleRank, line, false);
                    roles[j] = role;
                    nRoles++;
                }

                NodeList takeList = setElement.getElementsByTagName("take");
                for (int j = 0; j < takeList.getLength(); j++) {
                    Node takeNode = takeList.item(j);
                    Element takeElement = (Element) takeNode;

                    ////System.out.println("Take Number: " + takeElement.getAttribute("number"));
                    nShots++;
                }

                set = new Set(setName, nShots, nRoles, roles, neighbors);
                room = new Room(setName, neighbors);

                sets.add(set);
                rooms.add(room);


                //System.out.println();


            }

        } catch (SAXParseException err) {
            //System.out.println("Parse error");
        } catch (SAXException e) {
            //System.out.println("error");
        } catch (Throwable t) {
            t.printStackTrace ();
        }
    }



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
                    ////System.out.println("Card Name : " + cardElement.getAttribute("name"));
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

                        Role rRole = new Role(n, lev, l, true);
                        roles[j] = rRole;

                    }
                    card = new SceneCard(name, 3, b, roles, sceneNum);
                    sceneCards.add(card);
                }

                //System.out.println("\n");
            }

        } catch (SAXParseException err) {
            //System.out.println("Parse error");
        } catch (SAXException e) {
            //System.out.println("error");
        } catch (Throwable t) {
            t.printStackTrace ();
        }

        return sceneCards;
    }
}
