package securityservices.core.components.equipment.appservices;

import securityservices.core.components.client.appservices.*;
import securityservices.core.components.client.domain.services.ClientDTO;
import securityservices.core.components.equipment.domain.services.EquipmentDTO;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Serializer;
import securityservices.core.components.shared.services.serializers.Xml;

public class XmlEquipmentSerializer implements Serializer {

    protected Xml xmlConverter;

    public XmlEquipmentSerializer(Xml xmlConvert) {
        this.xmlConverter = xmlConvert;
    }

    @Override
    public EquipmentDTO unserialize(String data) throws ServiceException {
        xmlConverter.set(data);
        try {
//String name, String email, String birthday, String password, String phone, String ident, String address, int numEquipments, int clientCode) {                        
            EquipmentDTO equipment = new EquipmentDTO(
                    xmlConverter.getValueNode("code"),
                    xmlConverter.getValueNode("name"),
                    xmlConverter.getValueNode("type"),
                    xmlConverter.getValueNode("maker"),
                    xmlConverter.getValueNode("description"),
                    Double.valueOf(xmlConverter.getValueNode("price")),
                    Double.valueOf(xmlConverter.getValueNode("taxes")),
                    Double.valueOf(xmlConverter.getValueNode("high")),
                    Double.valueOf(xmlConverter.getValueNode("wide")),
                    Double.valueOf(xmlConverter.getValueNode("deep")),
                    Double.valueOf(xmlConverter.getValueNode("weight")),
                    Boolean.valueOf(xmlConverter.getValueNode("fragile")),
                    xmlConverter.getValueNode("function"),
                    xmlConverter.getValueNode("components"),
                    Integer.valueOf(xmlConverter.getValueNode("power"))
            );
            return equipment;
        } catch (Exception e) {
            throw new ServiceException("{\"Error\":\"EquipmentDTO unserialized Exception\","
                    + "\"Details\":\"" + e.toString() + "\"}");
        }
    }

    @Override
    public String serialize(Object e) {
        xmlConverter.createDocument();
        xmlConverter.setRootNode("equipment");
        xmlConverter.setNode("code", ((EquipmentDTO) e).getCode());
        xmlConverter.setNode("name", ((EquipmentDTO) e).getName());
        xmlConverter.setNode("type", ((EquipmentDTO) e).getType());
        xmlConverter.setNode("maker", ((EquipmentDTO) e).getMaker());
        xmlConverter.setNode("description", ((EquipmentDTO) e).getDescription());
        xmlConverter.setNode("price", String.valueOf(((EquipmentDTO) e).getPrice()));
        xmlConverter.setNode("taxes", String.valueOf(((EquipmentDTO) e).getTaxes()));
        xmlConverter.setNode("high", String.valueOf(((EquipmentDTO) e).getHigh()));
        xmlConverter.setNode("wide", String.valueOf(((EquipmentDTO) e).getWide()));
        xmlConverter.setNode("deep", String.valueOf(((EquipmentDTO) e).getDeep()));
        xmlConverter.setNode("weight", String.valueOf(((EquipmentDTO) e).getWeight()));
        xmlConverter.setNode("fragile", String.valueOf(((EquipmentDTO) e).getFragile()));
        xmlConverter.setNode("function", ((EquipmentDTO) e).getFunction());
        xmlConverter.setNode("components", ((EquipmentDTO) e).getComponents());
        xmlConverter.setNode("power", String.valueOf(((EquipmentDTO) e).getPower()));
        return xmlConverter.toString();
    }
}
