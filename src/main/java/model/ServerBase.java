package model;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.*;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ServerBase {

    private static List<ServerInfo> serverbase = new LinkedList<>();

    public static List<ServerInfo> getTemp() {
        return serverbase;
    }

    public static List<ServerInfo> getFromFile() {
        loadServers();
        return serverbase;
    }


    public static void loadServers() {
        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            try {
                Files.createFile(Paths.get("serverbase.xml"));
            } catch (FileAlreadyExistsException e) {}
            Document doc = docBuilder.parse (new File("serverbase.xml"));
            doc.getDocumentElement ().normalize();
            NodeList serverlist = doc.getDocumentElement().getElementsByTagName("server");
            for (int i = 0; i<serverlist.getLength(); i++) {
                Node server = serverlist.item(i);
                NamedNodeMap attributes = server.getAttributes();

                String name = null;
                String IP = null;
                int port = 0;

                for (int p = 0; p<attributes.getLength(); p++) {
                    Node a  = attributes.item(p);
                    if (a.getNodeName().equals("ip")) {
                        IP = a.getNodeValue();
                    } else if (a.getNodeName().equals("port")) {
                        port = Integer.parseInt(a.getNodeValue());
                    } else if (a.getNodeName().equals("name")) {
                        name = a.getNodeValue();
                    }
                }
                serverbase.add(new ServerInfo(name, IP, port));
            }


        }catch (SAXParseException err) {
            System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
            System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
            Exception x = e.getException ();
            ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
            t.printStackTrace ();
        }
    }

}
