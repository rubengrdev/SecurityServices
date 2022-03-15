package securityservices.core.components.order.domain.services;

import securityservices.core.components.client.domain.services.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/*
    DTO generado para adaptarnos a la magia de JAXB
 */
@XmlRootElement(name = "order")
public class JaxbOrderDTO {

    private String code, paymentType, status, comments, paymentDate, reciverName, deliveryAddress;
    private double value, surcharges;
    private String beginDate, finishDate, interested, details;

    public JaxbOrderDTO() {
    }

    public JaxbOrderDTO(String code, String interested, double value,
            double surcharges, String status, String comments, String beginDate,
            String finishDate, String paymentType, String paymentDate, String reciverName,
            String deliveryAddress, String details) {

        this.code = code;
        this.interested = interested;
        this.value = value;
        this.surcharges = surcharges;
        this.status = status;
        this.comments = comments;
        this.beginDate = beginDate;
        this.finishDate = finishDate;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.reciverName = reciverName;
        this.deliveryAddress = deliveryAddress;
        this.details = details;
    }

    @XmlElement(name = "code")
    public String getCode() {
        return code;
    }

    @XmlElement(name = "paymentType")
    public String getPaymentType() {
        return paymentType;
    }

    @XmlElement(name = "status")
    public String getStatus() {
        return status;
    }

    @XmlElement(name = "comments")
    public String getComments() {
        return comments;
    }

    @XmlElement(name = "paymentDate")
    public String getPaymentDate() {
        return paymentDate;
    }

    @XmlElement(name = "interested")
    public String getInterested() {
        return interested;
    }

    @XmlElement(name = "value")
    public double getValue() {
        return value;
    }

    @XmlElement(name = "surcharges")
    public double getSurcharges() {
        return surcharges;
    }

    @XmlElement(name = "beginDate")
    public String getBeginDate() {
        return beginDate;
    }

    @XmlElement(name = "finishDate")
    public String getFinishDate() {
        return finishDate;
    }

    @XmlElement(name = "reciverName")
    public String getReciverName() {
        return reciverName;
    }

    @XmlElement(name = "deliveryAddress")
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    @XmlElement(name = "details")
    public String getDetails() {
        return details;
    }

}
