/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestsXML;
import securityservices.core.components.client.domain.model.Client;
import securityservices.core.components.order.appservices.XmlOrderSerializer;
import securityservices.core.components.order.domain.model.Order;
import securityservices.core.components.order.domain.services.OrderDTO;
import securityservices.core.components.order.domain.services.OrderMapper;
import securityservices.core.components.shared.catalogs.ProductCatalog;
import securityservices.core.components.shared.exception.BuildException;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Dom;
import securityservices.management.catalogs.serializers.GetCatalogProduct;
import securityservices.management.catalogs.serializers.GetStakeHolder;

/**
 *
 * @author ruben
 */
public class OrderSerializerXml {

    public static void main(String[] args) throws BuildException, ServiceException {
        try {
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

            String newXmlOrder1 = "<order>"
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

            try {
                Dom dom = new Dom();
                XmlOrderSerializer xoSerializer = new XmlOrderSerializer(dom);
                String xmlOrder = xoSerializer.serialize(odto1);
                System.out.println(xmlOrder);
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                xmlOrder = xoSerializer.serialize(odto2);
                System.out.println(xmlOrder);
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

                auxOdto = (OrderDTO) xoSerializer.unserialize(newXmlOrder1);
                auxOrder = OrderMapper.orderFromDTO(auxOdto);
                System.out.println(auxOrder.getCode() + ";" + auxOrder.getInterested() + ";" + auxOrder.getValue() + ";" + auxOrder.getSurcharges() + ";" + auxOrder.getStatus() + ";" + auxOrder.getComments() + ";" + auxOrder.getBeginDate() + ";" + auxOrder.getFinishDate() + ";" + auxOrder.getPaymentType() + ";" + auxOrder.getPaymentDate() + ";" + auxOrder.getReciverName() + ";" + auxOrder.getDeliveryAddress() + ";" + auxOrder.getAllDetails());

                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

                auxOdto = (OrderDTO) xoSerializer.unserialize(newXmlOrder2);
                auxOrder = OrderMapper.orderFromDTO(auxOdto);
                System.out.println(auxOrder.getCode() + ";" + auxOrder.getInterested() + ";" + auxOrder.getValue() + ";" + auxOrder.getSurcharges() + ";" + auxOrder.getStatus() + ";" + auxOrder.getComments() + ";" + auxOrder.getBeginDate() + ";" + auxOrder.getFinishDate() + ";" + auxOrder.getPaymentType() + ";" + auxOrder.getPaymentDate() + ";" + auxOrder.getReciverName() + ";" + auxOrder.getDeliveryAddress() + ";" + auxOrder.getAllDetails());

            } catch (ServiceException ex) {
                System.out.println(ex);
            }
        } catch (BuildException ex) {
            System.out.println(ex);
        }
    }
}
