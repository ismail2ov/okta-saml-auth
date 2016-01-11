package es.develex.saml.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XMLErrorHandler extends DefaultHandler {

    private static final Logger log = LoggerFactory.getLogger(XMLErrorHandler.class);
    protected final static Marker FATAL = MarkerFactory.getMarker("FATAL");
    List<String> errorXML = new ArrayList<String>();

    @Override
    public void error(SAXParseException e) throws SAXException {
        errorXML.add("ERROR: " + (e.getMessage()));
        log.error("ERROR: " + (e.getMessage()));
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        errorXML.add("FATALERROR: " + (e.getMessage()));
        log.error(FATAL, "FATALERROR: " + (e.getMessage()));
    }

    public List<String> getErrorXML() {
        return errorXML;
    }

    public void setErrorXML(List<String> errorXML) {
        this.errorXML = errorXML;
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        errorXML.add("WARNING: " + (e.getMessage()));
        log.warn("WARNING: " + (e.getMessage()));
    }


}