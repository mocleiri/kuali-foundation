package org.kuali.rice.core.util.xml;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Utility class that helps retrieve string representations of org.jdom & org.w3c
 * xml Objects
 */
public final class XmlJotter {

    private XmlJotter() {
        throw new UnsupportedOperationException("do not call");
    }

    /**
     * Retrieves a string representation of a document in a indented format.
     *
     * @param document the document
     * @return document as a string
     */
    public static String jotDocument(org.jdom.Document document) {
        return jotDocument(document, true);
    }

    /**
     * Retrieves a string representation of a document in either indented or raw format.
     *
     * @param document the document
     * @param indent   whether to use indented or raw format
     * @return document as a string
     */
    public static String jotDocument(org.jdom.Document document, boolean indent) {
        XMLOutputter outputer = new XMLOutputter(getJdomFormat(indent));
        StringWriter writer = new StringWriter();
        try {
            outputer.output(document, writer);
        } catch (IOException e) {
            throw new XmlException("Could not write XML data export.", e);
        }
        return writer.toString();
    }

    /**
     * Retrieves a string representation of a document in a indented format.
     *
     * @param document the document
     * @return document as a string
     */
    public static String jotDocument(org.w3c.dom.Document document) {
        return jotNode(document.getDocumentElement());
    }

    /**
     * Retrieves a string representation of a document in either indented or raw format.
     *
     * @param document the document
     * @param indent   whether to use indented or raw format
     * @return document as a string
     */
    public static String jotDocument(org.w3c.dom.Document document, boolean indent) {
        return jotNode(document.getDocumentElement(), indent);
    }

    /**
     * Retrieves a string representation of a node in a indented format.
     *
     * @param node the node
     * @return node as a string
     */
    public static String jotNode(org.jdom.Element node) {
        return jotNode(node, true);
    }

    /**
     * Retrieves a string representation of a node in either indented or raw format.
     *
     * @param node   the node
     * @param indent whether to use indented or raw format
     * @return node as a string
     */
    public static String jotNode(org.jdom.Element node, boolean indent) {
        XMLOutputter outputer = new XMLOutputter(getJdomFormat(indent));
        StringWriter writer = new StringWriter();
        try {
            outputer.output(node, writer);
        } catch (IOException e) {
            throw new XmlException("Could not write XML data export.", e);
        }
        return writer.toString();
    }

    /**
     * Retrieves a string representation of a node in a indented format.
     *
     * @param node the node
     * @return node as a string
     */
    public static String jotNode(org.w3c.dom.Node node) {
        return jotNode(node, true);
    }

    /**
     * Retrieves a string representation of a node in either indented or raw format.
     *
     * @param node   the node
     * @param indent whether to use indented or raw format
     * @return node as a string
     */
    public static String jotNode(org.w3c.dom.Node node, boolean indent) {
        try {
            return nodeToString(node, indent);
        } catch (TransformerException te) {
            throw new XmlException(te);
        }
    }

    /**
     * Internal function to retrieve a string representation of a node in either indented or raw format.
     *
     * @param node   the node
     * @param indent whether to use indented or raw format
     * @return node as a string
     * @throws TransformerException if the node cannnot be transformed to a string
     */
    private static String nodeToString(org.w3c.dom.Node node, boolean indent) throws TransformerException {
        Source source = new DOMSource(node);
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        if (indent) {
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        }
        transformer.transform(source, result);
        return writer.toString();
    }

    /**
     * Internal function to choose a format based on a boolean flag.
     *
     * @param indent whether to use indented or raw format
     * @return The format
     */
    private static Format getJdomFormat(boolean indent) {
        return indent ? Format.getPrettyFormat() : Format.getRawFormat();
    }
}
