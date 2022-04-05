package securityservices.core.components.order.appservices;

import securityservices.core.components.client.appservices.*;
import securityservices.core.components.client.domain.services.JaxbClientDTO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import securityservices.core.components.client.domain.services.ClientDTO;
import securityservices.core.components.order.domain.services.JaxbOrderDTO;
import securityservices.core.components.order.domain.services.OrderDTO;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Serializer;
import securityservices.core.components.shared.services.serializers.xmlapis.Dom;
import securityservices.core.components.shared.services.serializers.xmlapis.Jaxb;

public class JaxbOrderSerializer extends Jaxb implements Serializer {

   protected Jaxb jaxbConverter;

   

    private JaxbOrderSerializer() {
    }

    //apliquem sistemàticament el mateix concepte de tractament d'errors
    public static Serializer getInstance() throws JAXBException {
        try {
            JaxbOrderSerializer jaxbOrder = new JaxbOrderSerializer();
            //excepcio que genera la clase, i de la que volem fugir, mantenint el nostre tractament d'errors
            jaxbOrder.context = JAXBContext.newInstance(JaxbOrderDTO.class);
            return jaxbOrder;
        } catch (JAXBException ex) {
            throw ex;
        }
    }



    @Override
    public OrderDTO unserialize(String xresponse) throws ServiceException {
        try {
            JaxbOrderDTO jaxbodto = (JaxbOrderDTO) super.prepareUnmarshal(xresponse);
//String name, String email, String birthday, String password, String phone, String ident, String address, int numEquipments, int clientCode) {    
            OrderDTO odto = new OrderDTO(
                    jaxbodto.getCode(),
                    jaxbodto.getInterested(),
                    jaxbodto.getValue(),
                    jaxbodto.getSurcharges(),
                    jaxbodto.getStatus(),
                    jaxbodto.getComments(),
                    jaxbodto.getBeginDate(),
                    jaxbodto.getFinishDate(),
                    jaxbodto.getPaymentType(),
                    jaxbodto.getPaymentDate(),
                    jaxbodto.getReciverName(),
                    jaxbodto.getDeliveryAddress(),
                    jaxbodto.getDetails()
            );
            return odto;
        } catch (JAXBException ex) {
            throw new ServiceException("{\"Error\":\"JAXB unmarshal fails\","
                    + "\"Details\":\"Can't unserialize xmldata to OrderDTO. \""
                    + ex + "}");
        }
    }

    @Override
    public String serialize(Object oDTO) throws ServiceException {
        try {
            JaxbOrderDTO jaxbodto = new JaxbOrderDTO(
                    ((OrderDTO) oDTO).getCode(),
                    ((OrderDTO) oDTO).getInterested(),
                    ((OrderDTO) oDTO).getValue(),
                    ((OrderDTO) oDTO).getSurcharges(),
                    ((OrderDTO) oDTO).getStatus(),
                    ((OrderDTO) oDTO).getComments(),
                    ((OrderDTO) oDTO).getBeginDate(),
                    ((OrderDTO) oDTO).getFinishDate(),
                    ((OrderDTO) oDTO).getPaymentType(),
                    ((OrderDTO) oDTO).getPaymentDate(),
                    ((OrderDTO) oDTO).getReciverName(),
                    ((OrderDTO) oDTO).getDeliveryAddress(),
                    ((OrderDTO) oDTO).getDetails());
            return super.prepareMarshal(jaxbodto);
        } catch (JAXBException ex) {
            throw new ServiceException("{\"Error\":\"JAXB marshal fails\","
                    + "\"Details\":\"Can't serialize ClientDTO to xmldata. \""
                    + ex + "}");
        }
    }
}
