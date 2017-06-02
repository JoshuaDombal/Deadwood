package model;

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
    private static CastingOffice castingOff;
    private static Trailer trail;


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



            // CASTING OFFICE ************************************************//
            Room co;
            String oName = "office";
            String[] officeNeighbors = new String[3];
            Upgrade[] upgrades = new Upgrade[10];


            NodeList officeList = el.getElementsByTagName("office");
            Node officeNode = officeList.item(0);
            Element oElement = (Element) officeNode;


            NodeList neiList = oElement.getElementsByTagName("neighbor");

            for (int j = 0; j < neiList.getLength(); j++) {
                Node neNode = neiList.item(j);
                Element neElement = (Element) neNode;

                officeNeighbors[j] = neElement.getAttribute("name");
            }


            // UPGRADES FOR OFFICE ---------------------------------------------


            NodeList upgradeList = oElement.getElementsByTagName("upgrade");

            for (int j = 0; j < upgradeList.getLength(); j++) {

                Upgrade upg;
                int level;
                String currency;
                int amount;

                int[] upgArea;
                int ux;
                int uy;
                int uh;
                int uw;

                Node upgradeNode = upgradeList.item(j);
                Element upgradeElement = (Element) upgradeNode;

                level = Integer.parseInt(upgradeElement.getAttribute("level"));
                currency = upgradeElement.getAttribute("currency");
                amount = Integer.parseInt(upgradeElement.getAttribute("amt"));

                NodeList upgradeAreaList = upgradeElement.getElementsByTagName("area");
                Node upgradeAreaNode = upgradeAreaList.item(0);
                Element upgradeAreaElement = (Element) upgradeAreaNode;

                ux = Integer.parseInt(upgradeAreaElement.getAttribute("x"));
                uy = Integer.parseInt(upgradeAreaElement.getAttribute("y"));
                uh = Integer.parseInt(upgradeAreaElement.getAttribute("h"));
                uw = Integer.parseInt(upgradeAreaElement.getAttribute("w"));

                upgArea = new int[] {ux, uy, uh, uw};

                upg = new Upgrade(level, currency, amount, upgArea);
                upgrades[j] = upg;


                //System.out.println("TRAILER Neighbor: " + neElement.getAttribute("name"));
            }








            // AREA OF CASTING OFFICE ------------------------------------------

            int[] oArea;
            int ox;
            int oy;
            int oh;
            int ow;

            NodeList officeAreaList = oElement.getElementsByTagName("area");
            Node officeAreaNode = officeAreaList.item(0);
            Element officeAreaElement = (Element) officeAreaNode;

            ox = Integer.parseInt(officeAreaElement.getAttribute("x"));
            oy = Integer.parseInt(officeAreaElement.getAttribute("y"));
            oh = Integer.parseInt(officeAreaElement.getAttribute("h"));
            ow = Integer.parseInt(officeAreaElement.getAttribute("w"));

            oArea = new int[] {ox, oy, oh, ow};

            //------------------------------------------------------------------


            castingOff = new CastingOffice(oName, officeNeighbors, oArea, upgrades);
            co = new Room(oName, officeNeighbors, oArea);
            rooms.add(co);

            // END CASTING OFFICE ********************************************//






            // TRAILER *********************************************************

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



            // AREA OF TRAILER ------------------------------------------

            int[] tArea;
            int tx;
            int ty;
            int th;
            int tw;

            NodeList trailerAreaList = tElement.getElementsByTagName("area");
            Node trailerAreaNode = trailerAreaList.item(0);
            Element trailerAreaElement = (Element) trailerAreaNode;

            tx = Integer.parseInt(trailerAreaElement.getAttribute("x"));
            ty = Integer.parseInt(trailerAreaElement.getAttribute("y"));
            th = Integer.parseInt(trailerAreaElement.getAttribute("h"));
            tw = Integer.parseInt(trailerAreaElement.getAttribute("w"));
            tArea = new int[] {tx, ty, th, tw};
            //------------------------------------------------------------------


            t = new Room(rName, ns, tArea);
            rooms.add(t);

            //System.out.println("NEIBS: " + neElement.getAttribute("name"));

            // END TRAILER ***************************************************//




            // GET SETS ********************************************************
            int nShots = 0;

            NodeList setList = el.getElementsByTagName("set");
            //System.out.println(setList.getLength());
            for (int i = 0; i < setList.getLength(); i++) {

                Set set;
                Room room;
                Take[] takes = new Take[3];
                int p = 0;

                String setName;
                nShots = 0;
                int nRoles = 0;
                Role[] roles = new Role[5];
                roles[4] = null;

                int[] sArea;
                int sx;
                int sy;
                int sh;
                int sw;

                String[] neighbors = new String[4];

                Node setNode = setList.item(i);
                Element setElement = (Element) setNode;

                NodeList sAreaList = setElement.getElementsByTagName("area");
                Node sAreaNode = sAreaList.item(0);
                Element sAreaElement = (Element) sAreaNode;

                sx = Integer.parseInt(sAreaElement.getAttribute("x"));
                sy = Integer.parseInt(sAreaElement.getAttribute("y"));
                sh = Integer.parseInt(sAreaElement.getAttribute("h"));
                sw = Integer.parseInt(sAreaElement.getAttribute("w"));

                //System.out.println("PART AREA x : " + sAreaElement.getAttribute("x"));
                //System.out.println("PART AREA y : " + sAreaElement.getAttribute("y"));
                //System.out.println("PART AREA h : " + sAreaElement.getAttribute("h"));
                //System.out.println("PART AREA w : " + sAreaElement.getAttribute("w"));

                sArea = new int[] {sx, sy, sh, sw};



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


                NodeList takeList = setElement.getElementsByTagName("take");
                for (int j = 0; j < takeList.getLength(); j++) {
                    Node takeNode = takeList.item(j);
                    Element takeElement = (Element) takeNode;

                    Take take;

                    int takeNum;
                    int tax;
                    int tay;
                    int tah;
                    int taw;

                    takeNum = Integer.parseInt(takeElement.getAttribute("number"));

                    NodeList aList = takeElement.getElementsByTagName("area");
                    Node aNode = aList.item(0);
                    Element aElement = (Element) aNode;

                    tax = Integer.parseInt(aElement.getAttribute("x"));
                    tay = Integer.parseInt(aElement.getAttribute("y"));
                    tah = Integer.parseInt(aElement.getAttribute("h"));
                    taw = Integer.parseInt(aElement.getAttribute("w"));

                    System.out.println("PART AREA x : " + aElement.getAttribute("x"));
                    System.out.println("PART AREA y : " + aElement.getAttribute("y"));
                    System.out.println("PART AREA h : " + aElement.getAttribute("h"));
                    System.out.println("PART AREA w : " + aElement.getAttribute("w"));

                    take = new Take(takeNum, tax, tay, tah, taw);
                    takes[p] = take;
                    p++;
                }

                // Gets roles
                NodeList partList = setElement.getElementsByTagName("part");
                for (int j = 0; j < partList.getLength(); j++) {
                    Role role;
                    String roleName;
                    int roleRank;
                    String line;

                    int[] rArea;
                    int rx;
                    int ry;
                    int rh;
                    int rw;

                    Node partNode = partList.item(j);
                    Element partElement = (Element) partNode;

                    NodeList areaLi = partElement.getElementsByTagName("area");
                    Node areaNo = areaLi.item(0);
                    Element areaEl = (Element) areaNo;

                    rx = Integer.parseInt(areaEl.getAttribute("x"));
                    ry = Integer.parseInt(areaEl.getAttribute("y"));
                    rh = Integer.parseInt(areaEl.getAttribute("h"));
                    rw = Integer.parseInt(areaEl.getAttribute("w"));

                    //System.out.println("PART AREA x : " + areaEl.getAttribute("x"));
                    //System.out.println("PART AREA y : " + areaEl.getAttribute("y"));
                    //System.out.println("PART AREA h : " + areaEl.getAttribute("h"));
                    //System.out.println("PART AREA w : " + areaEl.getAttribute("w"));

                    rArea = new int[] {rx, ry, rh, rw};

                    //System.out.println("Role name: " + partElement.getAttribute("name"));
                    roleName = partElement.getAttribute("name");

                    //System.out.println("Role level: " + partElement.getAttribute("level"));
                    roleRank = Integer.parseInt(partElement.getAttribute("level"));

                    //System.out.println("Role line: " + partElement.getElementsByTagName("line").item(0).getTextContent());
                    line = partElement.getElementsByTagName("line").item(0).getTextContent();

                    role = new Role(roleName, roleRank, line, false, rArea);
                    roles[j] = role;
                    nRoles++;



                    ////System.out.println("Take Number: " + takeElement.getAttribute("number"));
                    nShots++;
                }

                System.out.println("setName: " + setName + "nShots: " + nShots);

                //band-aid
                if(setName.equals("Bank") || setName.equals("Ranch") || setName.equals("Jail") || setName.equals("Main Street") || setName.equals("Hotel") || setName.equals("Secret Hideout") || setName.equals("Train Station")){
                    nShots--;
                }

                set = new Set(setName, nShots, nRoles, roles, neighbors, sArea, takes);
                room = new Room(setName, neighbors, sArea);

                sets.add(set);
                rooms.add(room);

                // END GET SETS ************************************************


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


            // for each card
            for (int i = 0; i < cardList.getLength(); i++) {
                Node nNode = cardList.item(i);
                SceneCard card;

                String name;
                int budget;
                int sceneNum;
                String cardImage;


                // Change this 3!!!!!!!!!!!!!!!
                Role[] roles = new Role[3];
                //System.out.println("\nCurrent Element : " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element cardElement = (Element) nNode;
                    ////System.out.println("Card Name : " + cardElement.getAttribute("name"));
                    name = cardElement.getAttribute("name");

                    budget = Integer.parseInt(cardElement.getAttribute("budget"));

                    cardImage = cardElement.getAttribute("img");

                    NodeList numNode = cardElement.getElementsByTagName("scene");
                    Node eNode = numNode.item(0);
                    Element e = (Element) eNode;
                    //System.out.println("IMG : " + cardElement.getAttribute("img"));

                    sceneNum = Integer.parseInt(e.getAttribute("number"));


                    NodeList partList = cardElement.getElementsByTagName("part");

                    // for each role
                    for (int j = 0; j < partList.getLength(); j++) {
                        int[] area;

                        int x;
                        int y;
                        int h;
                        int w;

                        Node pNode = partList.item(j);
                        Element partElement = (Element) pNode;
                        //System.out.println("Role Name : " + partElement.getAttribute("name"));
                        String n = partElement.getAttribute("name");

                        //System.out.println("Role Level : " + partElement.getAttribute("level"));
                        int lev = Integer.parseInt(partElement.getAttribute("level"));

                        NodeList areaList = partElement.getElementsByTagName("area");
                        Node areaNode = areaList.item(0);

                        Element areaElement = (Element) areaNode;

                        x = Integer.parseInt(areaElement.getAttribute("x"));
                        y = Integer.parseInt(areaElement.getAttribute("y"));
                        h = Integer.parseInt(areaElement.getAttribute("h"));
                        w = Integer.parseInt(areaElement.getAttribute("w"));

                        area = new int[] {x, y, h, w};
                        //System.out.println("PART AREA x : " + areaElement.getAttribute("x"));
                        //System.out.println("PART AREA y : " + areaElement.getAttribute("y"));
                        //System.out.println("PART AREA h : " + areaElement.getAttribute("h"));
                        //System.out.println("PART AREA w : " + areaElement.getAttribute("w"));



                        //System.out.println("Role Line : " + partElement.getElementsByTagName("line").item(0).getTextContent());
                        String l = partElement.getElementsByTagName("line").item(0).getTextContent();

                        Role rRole = new Role(n, lev, l, true, area);
                        roles[j] = rRole;

                    }
                    card = new SceneCard(name, 3, budget, roles, sceneNum, cardImage);
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
