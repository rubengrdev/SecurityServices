/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestsJSON;

import org.json.JSONException;
import org.json.JSONObject;
import securityservices.core.components.client.domain.model.Client;
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

    public static void main(String[] args) throws BuildException, ServiceException, JSONException {
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
                    + "  \"deliveryAddress\": \"c/Montflorit nº 3\","
                    + "  \"details\": \" 001\""
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
                    + "  \"paymentDate\": \"23-12-2021-22:30:13\","
                    + "  \"details\": \" 010\""
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
                    + "  \"paymentDate\": \"23-12-2021-22:30:13\","
                    + "  \"details\": \" 010\""
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
                System.out.println(auxOdto.getCode() + ";" + auxOdto.getInterested() + ";" + auxOdto.getValue() + ";" + auxOdto.getSurcharges() + ";" + auxOdto.getStatus() + ";" + auxOdto.getComments() + ";" + auxOdto.getBeginDate() + ";" + auxOdto.getFinishDate() + ";" + auxOdto.getPaymentType() + ";" + auxOdto.getReciverName() + ";" + auxOdto.getDeliveryAddress() + ";");
                //aunque para poder utilizar esta cadena me iría bien dejar una String delimitada por comas ";" para hacerlo más visual para ESTA Práctica he separado de esta manera para no ver un null en el caso de que no haya Detalles de Orden
                if (auxOdto.getDetails() != null) {
                    System.out.println(auxOdto.getDetails() + ";");
                }
                auxOrder = OrderMapper.orderFromDTO(auxOdto);
                System.out.println("Serialize -> OrderFromDTO (auxOrder) has transport");
                System.out.println(auxOrder.getCode() + ";" + auxOrder.getInterested() + ";" + auxOrder.getValue() + ";" + auxOrder.getSurcharges() + ";" + auxOrder.getStatus() + ";" + auxOrder.getComments() + ";" + auxOrder.getBeginDate() + ";" + auxOrder.getFinishDate() + ";" + auxOrder.getPaymentType() + ";" + auxOrder.getReciverName() + ";" + auxOrder.getDeliveryAddress() + ";");
                if (auxOrder.getAllDetails() != null) {
                    System.out.println(auxOrder.getAllDetails() + ";");
                    System.out.println(getJsonDetails(auxOrder.getAllDetails()));
                }
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

            } catch (ServiceException s) {
                System.out.println(s);
            }

            System.out.println("\n\n");

            //order without delivery 
            try {
                auxOdto = (OrderDTO) joSerializer.unserialize(newJsonOrder);
                System.out.println("Unserialize");
                System.out.println(auxOdto.getCode() + ";" + auxOdto.getInterested() + ";" + auxOdto.getValue() + ";" + auxOdto.getSurcharges() + ";" + auxOdto.getStatus() + ";" + auxOdto.getComments() + ";" + auxOdto.getBeginDate() + ";" + auxOdto.getFinishDate() + ";" + auxOdto.getPaymentType() + ";");
                //aunque para poder utilizar esta cadena me iría bien dejar una String delimitada por comas ";" para hacerlo más visual para ESTA Práctica he separado de esta manera para no ver un null en el caso de que no haya Detalles de Orden
                if (auxOdto.getDetails() != null) {
                    System.out.println(auxOdto.getDetails() + ";");
                }
                auxOrder = OrderMapper.orderFromDTO(auxOdto);
                System.out.println("Serialize -> OrderFromDTO (auxOrder) hasn't transport");
                System.out.println(auxOrder.getCode() + ";" + auxOrder.getInterested() + ";" + auxOrder.getValue() + ";" + auxOrder.getSurcharges() + ";" + auxOrder.getStatus() + ";" + auxOrder.getComments() + ";" + auxOrder.getBeginDate() + ";" + auxOrder.getFinishDate() + ";" + auxOrder.getPaymentType() + ";");
                if (auxOrder.getAllDetails() != null) {
                    System.out.println(auxOrder.getAllDetails() + ";");
                    System.out.println(getJsonDetails(auxOrder.getAllDetails()));
                }
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
                if (auxOrder.getAllDetails() != null) {
                    System.out.println(auxOrder.getAllDetails() + ";");
                    System.out.println(getJsonDetails(auxOrder.getAllDetails()));
                }
            } catch (ServiceException s) {
                System.out.println(s);
            }

        } catch (BuildException ex) {
            System.out.println(ex);
        }
    }

    public static String getJsonDetails(String json) throws JSONException {
        JSONObject jsonDetail = new JSONObject(json);
        String bigString = "";
        for (int i = 0; i < jsonDetail.names().length(); i++) {
            String ref = jsonDetail.getString("ref");
            String name = jsonDetail.getString("name");
            Double price = jsonDetail.getDouble("price");

            bigString += ref + ";" + name + ";" + price + ";";
        }
        return bigString;
    }
}
