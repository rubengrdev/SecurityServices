/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestsJAXB;

import javax.xml.bind.JAXBException;
import securityservices.core.components.client.domain.services.JaxbClientDTO;
import securityservices.core.components.client.domain.model.Client;
import securityservices.core.components.client.domain.services.ClientDTO;
import securityservices.core.components.client.domain.services.ClientMapper;
import securityservices.core.components.order.appservices.JaxbOrderSerializer;
import securityservices.core.components.order.appservices.XmlOrderSerializer;
import securityservices.core.components.order.domain.model.Order;
import securityservices.core.components.order.domain.services.JaxbOrderDTO;
import securityservices.core.components.order.domain.services.OrderDTO;
import securityservices.core.components.order.domain.services.OrderMapper;
import securityservices.core.components.shared.catalogs.ProductCatalog;
import securityservices.core.components.shared.exception.BuildException;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Serializer;
import securityservices.core.components.shared.services.serializers.xmlapis.Jaxb;
import securityservices.management.catalogs.serializers.GetCatalogProduct;
import securityservices.management.catalogs.serializers.GetStakeHolder;
import securityservices.management.catalogs.serializers.SerializerCatalog;
import securityservices.management.catalogs.serializers.SerializerType;

/**
 *
 * @author ruben
 */
public class OrderSerializerJaxb {

    public static void main(String[] args) throws BuildException, ServiceException, JAXBException {
        try {
            //OBJETOS PARA LA DEMO     
//String name, String ident, String email, String phone, String address, String birthday, String password, int clientCode, int numEquipments
            ProductCatalog prod = GetCatalogProduct.getInstance();
            Client clientHolder = Client.getInstance("ruben", "4582948J", "ruben@mail.com", "123456789", "c/Barcelona", "05-11-1999", "DDADWDWDWDWD", "JDKWK", 2);
            //order delivery
            Order order1 = Order.getInstance(prod, "222DDD", GetStakeHolder.getStakeHolderCode(clientHolder.getCode()), 22.2, 22.2, "active", "no comments", "10-07-2005-23:34:01", "20-03-2009-18:34:01", "Visa", "10-07-2005-23:34:01", "namee", "C/montflorit");
            //order digital
            Order order2 = Order.getInstance(prod, "222DDD", GetStakeHolder.getStakeHolderCode(clientHolder.getCode()), 22.2, 22.2, "active", "no comments", "10-07-2005-23:34:01", "20-03-2009-18:34:01", "Visa", "10-07-2005-23:34:01");

            OrderDTO odto1 = OrderMapper.dtoFromOrder(order1);
            OrderDTO odto2 = OrderMapper.dtoFromOrder(order2);
            Order auxOrder;
            OrderDTO auxOdto;
            JaxbOrderDTO jaxbAuxOdto;

            String newXmlOrder1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><order>"
                    + "<code>222DDD</code>"
                    + "<interested>4582948J</interested>"
                    + "<value>22.2</value>"
                    + "<surcharges>22.2</surcharges>"
                    + "<status>active</status>"
                    + "<comments>no comments</comments>"
                    + "<beginDate>10-07-2005-23:34:01</beginDate>"
                    + "<finishDate>20-03-2009-18:34:01</finishDate>"
                    + "<paymentType>Visa</paymentType>"
                    + "<paymentDate>10-07-2005-23:34:01</paymentDate>"
                    + "<reciverName>namee</reciverName>"
                    + "<deliveryAddress>C/montflorit</deliveryAddress>"
                    + "<details>10,2</details>"
                    + "</order>";

            String newXmlOrder2 = "<order>"
                    + "<code>222DDD</code>"
                    + "<interested>4582948J</interested>"
                    + "<value>22.2</value>"
                    + "<surcharges>22.2</surcharges>"
                    + "<status>active</status>"
                    + "<comments>no comments</comments>"
                    + "<beginDate>10-07-2005-23:34:01</beginDate>"
                    + "<finishDate>20-03-2009-18:34:01</finishDate>"
                    + "<paymentType>Visa</paymentType>"
                    + "<paymentDate>10-07-2005-23:34:01</paymentDate>"
                    + "<reciverName>namee</reciverName>"
                    + "<deliveryAddress>C/montflorit</deliveryAddress>"
                    + "<details>{\"ref\":\"012\",\"name\":\"Proxy\",\"price\":\"150.0\"}</details>"
                    + "</order>";
System.out.println("Disculpa Jose, solo he podido hacer funcionar más o menos el serialize de order, el unserialize aún no he conseguido descubrir por qué no me consigue crear bien el DTO de order usando JAXB");
            try {

                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                //he hecho el ejercicio de varias maneras, en este ejemplo no usa el catálogo
                JaxbOrderSerializer joSerializer = (JaxbOrderSerializer) JaxbOrderSerializer.getInstance();
                String jaxbOrder = joSerializer.serialize(odto1);
                System.out.println(jaxbOrder);
                //prueba recogiendo xml ya exitente
                Serializer xmlSerializer;
                xmlSerializer = (JaxbOrderSerializer) JaxbOrderSerializer.getInstance();

                // auxOdto = (OrderDTO) joSerializer.unserialize(newXmlOrder1);
                //auxOrder = OrderMapper.orderFromDTO(auxOdto);
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

                System.out.println("En este caso estoy usando el catalogo: SerializerType.JaxbOrder");
                Serializer orderSerializer;
                orderSerializer = SerializerCatalog.getInstance(SerializerType.JaxbOrder);
                String jaxbOrderXml = orderSerializer.serialize(odto2);
                System.out.println(jaxbOrderXml);
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                //jaxbAuxOdto = (JaxbOrderDTO) orderSerializer.unserialize(newXmlOrder2);
                //auxOrder = OrderMapper.orderFromDTO(jaxbAuxOdto);
                //System.out.println(auxOrder.getCode() + ";" + auxOrder.getInterested() + ";" + auxOrder.getValue() + ";" + auxOrder.getSurcharges() + ";" + auxOrder.getStatus() + ";" + auxOrder.getComments() + ";" + auxOrder.getBeginDate() + ";" + auxOrder.getFinishDate() + ";" + auxOrder.getPaymentType() + ";");
                auxOdto = (OrderDTO) orderSerializer.unserialize(newXmlOrder1);
                auxOrder = OrderMapper.orderFromDTO(auxOdto);
            } catch (ServiceException ex) {
                System.out.println(ex);
            }
        } catch (BuildException ex) {
            System.out.println(ex);
        }
    }
}
