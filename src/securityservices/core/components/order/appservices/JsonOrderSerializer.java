package securityservices.core.components.order.appservices;

import securityservices.core.components.order.domain.model.OrderDetail;
import securityservices.core.components.order.domain.services.OrderDTO;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Json;
import securityservices.core.components.shared.services.serializers.JsonObjectFactory;
import securityservices.core.components.shared.services.serializers.Serializer;

public class JsonOrderSerializer implements Serializer {

    protected Json jOrder = JsonObjectFactory.getInstance();

    public JsonOrderSerializer() {
    }


    @Override
    public OrderDTO unserialize(String data) throws ServiceException {
        jOrder.set(data);
        try {

            OrderDTO order = new OrderDTO(
                    jOrder.get("code"),
                    jOrder.get("interested"),
                    Double.valueOf(jOrder.get("value")),
                    Double.valueOf(jOrder.get("surcharges")),
                    jOrder.get("status"),
                    jOrder.get("comments"),
                    jOrder.get("beginDate"),
                    jOrder.get("finishDate"),
                    jOrder.get("paymentType"),
                    jOrder.get("paymentDate"),
                    jOrder.get("reciverName"),
                    jOrder.get("deliveryAddress"),
                    jOrder.get("details")
            );
            return order;

        } catch (Exception e) {
            throw new ServiceException("{\"Error\":\"OrdertDTO unserialized Exception\","
                    + "\"Details\":\"" + e.toString() + "\"}");
        }
    }

    @Override
    public String serialize(Object order) {

        jOrder.removeAll();
        jOrder.set("code", ((OrderDTO) order).getCode());
        jOrder.set("interested", ((OrderDTO) order).getInterested());
        jOrder.set("value", String.valueOf(((OrderDTO) order).getValue()));
        jOrder.set("surcharges", String.valueOf(((OrderDTO) order).getSurcharges()));
        jOrder.set("status", ((OrderDTO) order).getStatus());
        jOrder.set("comments", ((OrderDTO) order).getComments());
        jOrder.set("beginDate", ((OrderDTO) order).getBeginDate());
        jOrder.set("finishDate", ((OrderDTO) order).getFinishDate());
        jOrder.set("paymentType", ((OrderDTO) order).getPaymentType());
        jOrder.set("paymentDate", ((OrderDTO) order).getPaymentDate());
//COMPROBACIÓN DE DATOS:
        if (((OrderDTO) order).getReciverName() != null) {
            jOrder.set("reciverName", ((OrderDTO) order).getReciverName());
            jOrder.set("deliveryAddress", ((OrderDTO) order).getDeliveryAddress());
        } else {
//en el caso de que no haya estos campos devuelve un espacio vacio
            jOrder.set("reciverName", "");
            jOrder.set("deliveryAddress", "");
        }

        // jOrder.set("details", "detalles reffff");
        jOrder.set("details", ((OrderDTO) order).getDetails());
        return jOrder.toString();

    }



    }


