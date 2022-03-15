package securityservices.core.components.order.appservices;

import securityservices.core.components.equipment.appservices.*;
import securityservices.core.components.client.appservices.*;
import securityservices.core.components.client.domain.services.ClientDTO;
import securityservices.core.components.equipment.domain.services.EquipmentDTO;
import securityservices.core.components.order.domain.services.OrderDTO;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Serializer;
import securityservices.core.components.shared.services.serializers.Xml;

public class XmlOrderSerializer implements Serializer {

    protected Xml xmlConverter;

    public XmlOrderSerializer(Xml xmlConvert) {
        this.xmlConverter = xmlConvert;
    }

    @Override
    public OrderDTO unserialize(String data) throws ServiceException {
        xmlConverter.set(data);
        try {
//String name, String email, String birthday, String password, String phone, String ident, String address, int numEquipments, int clientCode) {                        
            OrderDTO order = new OrderDTO(
                    xmlConverter.getValueNode("code"),
                    xmlConverter.getValueNode("interested"),
                    Double.valueOf(xmlConverter.getValueNode("value")),
                    Double.valueOf(xmlConverter.getValueNode("surcharges")),
                    xmlConverter.getValueNode("status"),
                    xmlConverter.getValueNode("comments"),
                    xmlConverter.getValueNode("beginDate"),
                    xmlConverter.getValueNode("finishDate"),
                    xmlConverter.getValueNode("paymentType"),
                    xmlConverter.getValueNode("paymentDate"),
                    xmlConverter.getValueNode("reciverName"),
                    xmlConverter.getValueNode("deliveryAddress"),
                    xmlConverter.getValueNode("details")
            );
            return order;
        } catch (Exception e) {
            throw new ServiceException("{\"Error\":\"OrdertDTO unserialized Exception\","
                    + "\"Details\":\"" + e.toString() + "\"}");
        }
    }

    @Override
    public String serialize(Object order) {
        xmlConverter.createDocument();
        xmlConverter.setRootNode("order");
        xmlConverter.setNode("code", ((OrderDTO) order).getCode());
        xmlConverter.setNode("interested", ((OrderDTO) order).getInterested());
        xmlConverter.setNode("value", String.valueOf(((OrderDTO) order).getValue()));
        xmlConverter.setNode("surcharges", String.valueOf(((OrderDTO) order).getSurcharges()));
        xmlConverter.setNode("status", ((OrderDTO) order).getStatus());
        xmlConverter.setNode("comments", ((OrderDTO) order).getComments());
        xmlConverter.setNode("beginDate", ((OrderDTO) order).getBeginDate());
        xmlConverter.setNode("finishDate", ((OrderDTO) order).getFinishDate());
        xmlConverter.setNode("paymentType", ((OrderDTO) order).getPaymentType());
        xmlConverter.setNode("paymentDate", ((OrderDTO) order).getPaymentDate());
//COMPROBACIÓN DE DATOS:
        if (((OrderDTO) order).getReciverName() != null) {
            xmlConverter.setNode("reciverName", ((OrderDTO) order).getReciverName());
            xmlConverter.setNode("deliveryAddress", ((OrderDTO) order).getDeliveryAddress());
        } else {
//en el caso de que no haya estos campos no ejecuta nada (en el Serializer de Json si que me interesaba mostrar que estuvieran vacios, pero en este no me interesa ya que el tag que abre podría dar problemas
            //xmlConverter.setNode("reciverName", "");
            //xmlConverter.setNode("deliveryAddress", "");
        }

        xmlConverter.setNode("details", ((OrderDTO) order).getDetails());
        return xmlConverter.toString();
    }
}
