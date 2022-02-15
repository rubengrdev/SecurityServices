package securityservices.core.components.order.domain.services;

import java.io.IOException;
import securityservices.core.components.order.domain.model.Order;
import securityservices.core.components.shared.catalogs.ProductCatalog;
import securityservices.core.components.shared.exception.BuildException;
import securityservices.management.catalogs.serializers.GetCatalogProduct;
import securityservices.management.catalogs.serializers.GetStakeHolder;

public class OrderMapper {

    public static Order orderFromDTO(OrderDTO odto) throws BuildException {
        ProductCatalog prod = GetCatalogProduct.getInstance();
        if (odto.getReciverName() == null || odto.getReciverName().equals("") == true) {
            //no tiene envio
            return Order.getInstance(
                    prod, //INSERTAMOS EL CATÁLOGO
                    odto.getCode(),
                    GetStakeHolder.getStakeHolderCode(odto.getInterested()),
                    odto.getValue(),
                    odto.getSurcharges(),
                    odto.getStatus(),
                    odto.getComments(),
                    odto.getBeginDate(),
                    odto.getFinishDate(),
                    odto.getPaymentType(),
                    odto.getPaymentDate()
            );
        }
        //tiene envio
        return Order.getInstance(
                prod, //INSERTAMOS EL CATÁLOGO
                odto.getCode(),
                GetStakeHolder.getStakeHolderCode(odto.getInterested()),
                odto.getValue(),
                odto.getSurcharges(),
                odto.getStatus(),
                odto.getComments(),
                odto.getBeginDate(),
                odto.getFinishDate(),
                odto.getPaymentType(),
                odto.getPaymentDate(),
                odto.getReciverName(),
                odto.getDeliveryAddress()
        );
    }

    public static OrderDTO dtoFromOrder(Order o) {
        return new OrderDTO(
                o.getCode(),
                o.getInterested(),
                o.getValue(),
                o.getSurcharges(),
                o.getStatus(),
                o.getComments(),
                o.getBeginDate(),
                o.getFinishDate(),
                o.getPaymentType(),
                o.getPaymentDate(),
                o.getReciverName(),
                o.getDeliveryAddress()
        );
    }
}

/*
    public static Order orderFromDTO(OrderDTO odto) throws BuildException {
        ProductCatalog prod = GetCatalogProduct.getInstance();
        return Order.getInstance(
                prod, //INSERTAMOS EL CATÁLOGO
                odto.getCode(),
                GetStakeHolder.getStakeHolderCode(odto.getInterested()),
                odto.getValue(),
                odto.getSurcharges(),
                odto.getStatus(),
                odto.getComments(),
                odto.getBeginDate(),
                odto.getFinishDate(),
                odto.getPaymentType(),
                odto.getPaymentDate(),
                odto.getReciverName(),
                odto.getDeliveryAddress()
        );
    }
 */
