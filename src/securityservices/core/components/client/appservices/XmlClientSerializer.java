package securityservices.core.components.client.appservices;

import securityservices.core.components.client.domain.services.ClientDTO;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Serializer;
import securityservices.core.components.shared.services.serializers.Xml;

public class XmlClientSerializer implements Serializer {

    protected Xml xmlConverter;

    public XmlClientSerializer(Xml xmlConvert) {
        this.xmlConverter = xmlConvert;
    }

    @Override
    public ClientDTO unserialize(String data) throws ServiceException {
        xmlConverter.set(data);
        try {
//String name, String email, String birthday, String password, String phone, String ident, String address, int numEquipments, int clientCode) {                        
            ClientDTO client = new ClientDTO(
                    xmlConverter.getValueNode("name"),
                    xmlConverter.getValueNode("email"),
                    xmlConverter.getValueNode("birthday"),
                    xmlConverter.getValueNode("password"),
                    xmlConverter.getValueNode("phone"),
                    xmlConverter.getValueNode("code"),
                    xmlConverter.getValueNode("address"),
                    xmlConverter.getValueNode("clientCode"),
                    Integer.valueOf(xmlConverter.getValueNode("equipments"))
            );
            return client;
        } catch (Exception e) {
            throw new ServiceException("{\"Error\":\"ClientDTO unserialized Exception\","
                    + "\"Details\":\"" + e.toString() + "\"}");
        }
    }

    @Override
    public String serialize(Object c) {
        xmlConverter.createDocument();
        xmlConverter.setRootNode("client");
        xmlConverter.setNode("code", ((ClientDTO) c).getIdent());
        xmlConverter.setNode("name", ((ClientDTO) c).getName());
        xmlConverter.setNode("address", ((ClientDTO) c).getAddress());
        xmlConverter.setNode("phone", ((ClientDTO) c).getPhone());
        xmlConverter.setNode("email", ((ClientDTO) c).getEmail());
        xmlConverter.setNode("birthday", ((ClientDTO) c).getBirthday());
        xmlConverter.setNode("password", ((ClientDTO) c).getPassword());
        xmlConverter.setNode("clientid", String.valueOf(((ClientDTO) c).getClientCode()));
        xmlConverter.setNode("equipments", String.valueOf(((ClientDTO) c).getNumEquipments()));
        return xmlConverter.toString();
    }
}
