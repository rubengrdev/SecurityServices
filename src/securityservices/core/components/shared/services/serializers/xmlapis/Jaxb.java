package securityservices.core.components.shared.services.serializers.xmlapis;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.xml.sax.InputSource;

public abstract class Jaxb {
    protected JAXBContext context;
    protected Marshaller javaToXml;
    protected Unmarshaller xmltojava;

    protected Object prepareUnmarshal(String xresponse) throws JAXBException {
        try {
            xmltojava = context.createUnmarshaller();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xresponse));
            return xmltojava.unmarshal(is);
        } catch (JAXBException ex) {
            throw ex; 
        }
    }

    protected String prepareMarshal(Object o) throws JAXBException{
        StringWriter writer = new StringWriter();
        try {
            javaToXml = context.createMarshaller();
            javaToXml.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            javaToXml.marshal(o, writer);
            return writer.getBuffer().toString(); 
        } catch (JAXBException ex) {
            throw ex;
        }
    }  
}