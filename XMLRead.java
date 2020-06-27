package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.StringReader;

public class XMLRead {
    public static void main(String[] args) throws SAXException, IOException,
            ParserConfigurationException, XPathExpressionException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        String input =  "<Fields>";
        input += "<Field Name=\"usr1\">";
        input += "<value>system testing</value>";
        input += "</Field>";
        input += "<Field Name=\"usr2\">";
        input += "<value>regression testing</value>";
        input += "</Field>";
        input += "</Fields>";

        Document document = builder.parse(new org.xml.sax.InputSource(new StringReader(input)));

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("//Fields/Field[@Name='usr1']/value/text()");

        Object result = expr.evaluate(document, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        String val = "";
        for (int i = 0; i < nodes.getLength(); i++) {
            val = nodes.item(i).getTextContent();
        }
        System.out.println(val);
    }
}
