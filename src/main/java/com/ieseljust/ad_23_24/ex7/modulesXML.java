/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.ad_23_24.ex7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author samuel
 */
class fileXML {

    public Document OpenXML(String name) throws IOException, SAXException, ParserConfigurationException, FileNotFoundException {

        // Create an instance of DocumentBuilderFactory 
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(name));

        return doc;

    }

    public void printModules(Element root) {
        NodeList modules = root.getElementsByTagName("modul");
        for (int i = 0; i < modules.getLength(); i++) {
            Element e = (Element) modules.item(i);
//            System.out.println(e.getNodeName() + " " + (i + 1));
            
            System.out.println("Nom: " + e.getElementsByTagName("nom").item(0).getFirstChild().getNodeValue());
            System.out.println("Hores: " + e.getElementsByTagName("hores").item(0).getFirstChild().getNodeValue());
            System.out.println("Qualificació: " + e.getElementsByTagName("qualificacio").item(0).getFirstChild().getNodeValue());
        }
    }
}

public class modulesXML {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        fileXML M = new fileXML();

        Document doc = M.OpenXML(args[0]);
        Element root = doc.getDocumentElement();

        M.printModules(root);
    }

}
