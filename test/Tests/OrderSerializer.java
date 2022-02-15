/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tests;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import securityservices.core.components.client.domain.model.Client;
import securityservices.core.components.client.domain.services.ClientDTO;
import securityservices.core.components.order.appservices.JsonOrderSerializer;
import securityservices.core.components.order.domain.model.Order;
import securityservices.core.components.order.domain.services.OrderDTO;
import securityservices.core.components.order.domain.services.OrderMapper;
import securityservices.core.components.shared.catalogs.ProductCatalog;
import securityservices.core.components.shared.exception.BuildException;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.management.catalogs.serializers.GetCatalogProduct;
import securityservices.management.catalogs.serializers.GetStakeHolder;

/**
 *
 * @author ruben
 */
public class OrderSerializer {

    public static void main(String[] args) throws BuildException, ServiceException {
        try {
            System.out.println("\n\n");
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

            //EEXEMPLE DE COM CREAR UN JSON AMB LES DADES D'UN CLIENT
            String newJsonOrderTransport = "{"
                    + "  \"code\": \"ORD2342\","
                    + "  \"interested\": \"4582948J\","
                    + "  \"value\": 23.2,"
                    + "  \"surcharges\": 1.54,"
                    + "  \"status\": \"ondelivery\","
                    + "  \"comments\": \"no comments\","
                    + "  \"beginDate\": \"10-03-2022-18:30:22\","
                    + "  \"finishDate\": \"15-03-2022-17:22:02\","
                    + "  \"paymentType\": \"VISA\","
                    + "  \"paymentDate\": \"13-03-2022-17:22:02\","
                    + "  \"reciverName\": \"rockye\","
                    + "  \"deliveryAddress\": \"c/Montflorit nº 3\""
                    + "}";

            String newJsonOrder = "{"
                    + "  \"code\": \"ORD3222\","
                    + "  \"interested\": \"4582948J\","
                    + "  \"value\": 199.2,"
                    + "  \"surcharges\": 24,"
                    + "  \"status\": \"in prepare\","
                    + "  \"comments\": \"delivery time: 15:00-22:00\","
                    + "  \"beginDate\": \"22-12-2021-22:01:17\","
                    + "  \"finishDate\": \"14-01-2022-10:30:22\","
                    + "  \"paymentType\": \"PayPal\","
                    + "  \"paymentDate\": \"23-12-2021-22:30:13\""
                    + "}";
//fake JsonOrder (Para probar los errores, se mostrarán al final del documento)
            String newFakeOnOrders = "{"
                    + "  \"code\": \"errorCodeJajaj\","
                    + "  \"interested\": \"undefinedInterested=Error\","
                    + "  \"value\": 30000.2,"
                    + "  \"surcharges\": 240000,"
                    + "  \"status\": \"sleeping\","
                    + "  \"comments\": \"this is a comment\","
                    + "  \"beginDate\": \"22-12-2021-22:01:17\","
                    + "  \"finishDate\": \"14-01-2022-10:30:22\","
                    + "  \"paymentType\": \"Moneditas\","
                    + "  \"paymentDate\": \"23-12-2021-22:30:13\""
                    + "}";
            //SERIALITZACIO JSON AMB UNA LLIBRERIA ESTABLERTA PER DEFECTE
            JsonOrderSerializer joSerializer = new JsonOrderSerializer();
            //order with delivery 
            System.out.println("Serialize with delivery");
            String jsonOrder = joSerializer.serialize(odto1);
            System.out.println(jsonOrder);
            //order without delivery 
            System.out.println("\n");
            System.out.println("Serialize without delivery");
            String jsonOrder2 = joSerializer.serialize(odto2);
            System.out.println(jsonOrder2);

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

            System.out.println("\n\n");

            //order with delivery   
            try {
                auxOdto = (OrderDTO) joSerializer.unserialize(newJsonOrderTransport);
                System.out.println("Unserialize");
                System.out.println(auxOdto.getCode() + ";" + auxOdto.getInterested() + ";" + auxOdto.getValue() + ";" + auxOdto.getSurcharges() + ";" + auxOdto.getStatus() + ";" + auxOdto.getComments() + ";" + auxOdto.getBeginDate() + ";" + auxOdto.getFinishDate() + ";" + auxOdto.getPaymentType() + ";" + auxOdto.getReciverName() + ";" + auxOdto.getDeliveryAddress());
                auxOrder = OrderMapper.orderFromDTO(auxOdto);
                System.out.println("Serialize -> OrderFromDTO (auxOrder) has transport");
                System.out.println(auxOrder.getCode() + ";" + auxOrder.getInterested() + ";" + auxOrder.getValue() + ";" + auxOrder.getSurcharges() + ";" + auxOrder.getStatus() + ";" + auxOrder.getComments() + ";" + auxOrder.getBeginDate() + ";" + auxOrder.getFinishDate() + ";" + auxOrder.getPaymentType() + ";" + auxOrder.getReciverName() + ";" + auxOrder.getDeliveryAddress());

            } catch (ServiceException s) {
                System.out.println(s);
            }

            System.out.println("\n\n");

            //order without delivery 
            try {
                auxOdto = (OrderDTO) joSerializer.unserialize(newJsonOrder);
                System.out.println("Unserialize");
                System.out.println(auxOdto.getCode() + ";" + auxOdto.getInterested() + ";" + auxOdto.getValue() + ";" + auxOdto.getSurcharges() + ";" + auxOdto.getStatus() + ";" + auxOdto.getComments() + ";" + auxOdto.getBeginDate() + ";" + auxOdto.getFinishDate() + ";" + auxOdto.getPaymentType() + ";");
                auxOrder = OrderMapper.orderFromDTO(auxOdto);
                System.out.println("Serialize -> OrderFromDTO (auxOrder) hasn't transport");
                System.out.println(auxOrder.getCode() + ";" + auxOrder.getInterested() + ";" + auxOrder.getValue() + ";" + auxOrder.getSurcharges() + ";" + auxOrder.getStatus() + ";" + auxOrder.getComments() + ";" + auxOrder.getBeginDate() + ";" + auxOrder.getFinishDate() + ";" + auxOrder.getPaymentType() + ";");
            } catch (ServiceException s) {
                System.out.println(s);
            }

            System.out.println("\n\n");

            //order with errors
            try {
                System.out.println("Order With Errors: (Ejemplo de error de JSON entrante con algún dato invalido 'en este caso el que debería de petar es el interested (StakeHolder) ya que no existe)");
                auxOdto = (OrderDTO) joSerializer.unserialize(newFakeOnOrders);
                System.out.println("Unserialize");
                System.out.println(auxOdto.getCode() + ";" + auxOdto.getInterested() + ";" + auxOdto.getValue() + ";" + auxOdto.getSurcharges() + ";" + auxOdto.getStatus() + ";" + auxOdto.getComments() + ";" + auxOdto.getBeginDate() + ";" + auxOdto.getFinishDate() + ";" + auxOdto.getPaymentType() + ";");
                auxOrder = OrderMapper.orderFromDTO(auxOdto);
                System.out.println("Serialize -> OrderFromDTO (auxOrder) fakeeee transport");
                System.out.println(auxOrder.getCode() + ";" + auxOrder.getInterested() + ";" + auxOrder.getValue() + ";" + auxOrder.getSurcharges() + ";" + auxOrder.getStatus() + ";" + auxOrder.getComments() + ";" + auxOrder.getBeginDate() + ";" + auxOrder.getFinishDate() + ";" + auxOrder.getPaymentType() + ";");
            } catch (ServiceException s) {
                System.out.println(s);
            }

        } catch (BuildException ex) {
            System.out.println(ex);
        }
    }
}
