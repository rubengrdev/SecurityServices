package securityservices.core.components.client.appservices;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import securityservices.core.components.client.domain.services.ClientDTO;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Serializer;
import securityservices.core.components.shared.services.serializers.xmlapis.Jaxb;

public class JaxbClientSerializer extends Jaxb implements Serializer {

    private JaxbClientSerializer() {
    }

    //apliquem sistemàticament el mateix concepte de tractament d'errors
    public static Serializer getInstance() throws JAXBException {
        try {
            JaxbClientSerializer jaxbClient = new JaxbClientSerializer();
            //excepcio que genera la clase, i de la que volem fugir, mantenint el nostre tractament d'errors
            jaxbClient.context = JAXBContext.newInstance(JaxbClientDTO.class);
            return jaxbClient;
        } catch (JAXBException ex) {
            throw ex;
        }
    }

    @Override
    public ClientDTO unserialize(String xresponse) throws ServiceException {
        try {
            JaxbClientDTO jaxbcdto = (JaxbClientDTO) super.prepareUnmarshal(xresponse);
//String name, String email, String birthday, String password, String phone, String ident, String address, int numEquipments, int clientCode) {    
            ClientDTO cdto = new ClientDTO(
                    jaxbcdto.getName(),
                    jaxbcdto.getEmail(),
                    jaxbcdto.getBirthday(),
                    jaxbcdto.getPassword(),
                    jaxbcdto.getPhone(),
                    jaxbcdto.getClientCode(),
                    jaxbcdto.getAddress(),
                    jaxbcdto.getClientCode(),
                    Integer.valueOf(jaxbcdto.getNumEquipments())
            );
            return cdto;
        } catch (JAXBException ex) {
            throw new ServiceException("{\"Error\":\"JAXB unmarshal fails\","
                    + "\"Details\":\"Can't unserialize xmldata to ClientDTO. \""
                    + ex + "}");
        }
    }

    @Override
    public String serialize(Object clientDto) throws ServiceException {
        try {
            JaxbClientDTO jaxbcdto = new JaxbClientDTO(
                    ((ClientDTO) clientDto).getName(),
                    ((ClientDTO) clientDto).getEmail(),
                    ((ClientDTO) clientDto).getBirthday(),
                    ((ClientDTO) clientDto).getPassword(),
                    ((ClientDTO) clientDto).getPhone(),
                    ((ClientDTO) clientDto).getIdent(),
                    ((ClientDTO) clientDto).getAddress(),
                    ((ClientDTO) clientDto).getClientCode(),
                    ((ClientDTO) clientDto).getNumEquipments());
            return super.prepareMarshal(jaxbcdto);
        } catch (JAXBException ex) {
            throw new ServiceException("{\"Error\":\"JAXB marshal fails\","
                    + "\"Details\":\"Can't serialize ClientDTO to xmldata. \""
                    + ex + "}");
        }
    }

}
