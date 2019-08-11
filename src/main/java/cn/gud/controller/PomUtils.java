package cn.gud.controller;

import cn.gud.domain.Dependency;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class PomUtils {
    private Document xmldoc;
    private File f;

    PomUtils(File pomFile) {
        //System.out.println(pomFile);
        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        //使用工程，创建一个文档解析器
        DocumentBuilder builder = null;
        try {
            builder = fac.newDocumentBuilder();
            f = pomFile;
            xmldoc = builder.parse(pomFile);
            xmldoc.setXmlStandalone(true);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        //使用文档解析器解析一个文件，放到document对象中
    }

    public  String getVersion() {
        Element element = xmldoc.getDocumentElement();
        Node node = element.getFirstChild();
        while (node.getNodeType() > 0) {
            //System.out.println(node.getNodeName());
            if ("version".equals(node.getNodeName())) {
                //System.out.println(node.getTextContent());
                return node.getTextContent();
            }
            node = node.getNextSibling();
        }
        return "NotFound";
    }

    public  boolean setVersion(String projectVersion) throws TransformerException {
        Element element = xmldoc.getDocumentElement();
        Node node = element.getFirstChild();
        boolean change = false;
        while (node != null) {
            //System.out.println(node.getNodeName());
            if ("version".equals(node.getNodeName())) {
                //System.out.println(node.getTextContent());
                node.setTextContent(projectVersion);
                change = true;
                break;
            }
            node = node.getNextSibling();
        }
        if (change) {
            TransformerFactory factor = TransformerFactory.newInstance();
            Transformer former = factor.newTransformer();
            former.transform(new DOMSource(xmldoc), new StreamResult(f));
        }
        return true;
    }


    public  Map<String, String> getDependency() {
        Map<String, String> dependencies = new HashMap<String, String>();
        NodeList nodesDepend = xmldoc.getElementsByTagName("properties");
        //System.out.println(nodesDepend.getLength());
        Node dependency = nodesDepend.item(0).getFirstChild();
        while (dependency != null) {
            dependencies.put(dependency.getNodeName().replace(".version", ""), dependency.getTextContent());
            dependency = dependency.getNextSibling();
        }
        return dependencies;
    }

    public boolean setDependency (String projectName,String projectVersion) throws TransformerException{
        NodeList nodesDepend = xmldoc.getElementsByTagName("properties");
        projectName = projectName + ".version";
        //System.out.println(nodesDepend.getLength());
        Node dependency = nodesDepend.item(0).getFirstChild();
        boolean change = false;
        while (dependency != null) {
            String elementName = dependency.getNodeName();
            if (elementName.equals(projectName)) {
                dependency.setTextContent(projectVersion);
                change = true;
                break;
            }
            dependency = dependency.getNextSibling();
        }
        if (change) {
            TransformerFactory factor = TransformerFactory.newInstance();
            Transformer former = factor.newTransformer();
            former.transform(new DOMSource(xmldoc),new StreamResult(f));
        }
        return true;
    }
}
