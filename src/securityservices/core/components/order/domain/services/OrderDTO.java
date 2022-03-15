/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package securityservices.core.components.order.domain.services;

/**
 *
 * @author ruben
 */
public class OrderDTO {

    private final String code, paymentType, status, comments, paymentDate, reciverName, deliveryAddress;
    private final double value, surcharges;
    private final String beginDate, finishDate, interested, details;

//con envio
    public OrderDTO(String code, String interested, double value,
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

    public String getCode() {
        return code;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getStatus() {
        return status;
    }

    public String getComments() {
        return comments;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getInterested() {
        return interested;
    }

    public double getValue() {
        return value;
    }

    public double getSurcharges() {
        return surcharges;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public String getReciverName() {
        return reciverName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getDetails() {
        return details;
    }

}
