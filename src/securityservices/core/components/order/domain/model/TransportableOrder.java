package securityservices.core.components.order.domain.model;

import securityservices.core.components.shared.operations.Transportable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import securityservices.core.components.shared.catalogs.ProductCatalog;
import securityservices.core.components.shared.products.Storable;
import securityservices.core.components.shared.physics.PhysicalData;

public class TransportableOrder implements Transportable {

    protected String code, transporter, deliveryAddress, reciverName;
    protected LocalDateTime deliveryDate;
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'-'HH:mm:ss");
    protected PhysicalData physics;
    protected Map<String, TransportableDetail> transportableDetails = new TreeMap();
    protected ProductCatalog catalog;

    public TransportableOrder(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    public TransportableOrder(PhysicalData physics, ProductCatalog catalog) {
        this.physics = new PhysicalData();
        this.catalog = catalog;
    }

    public TransportableOrder(String code, String transporter, String deliveryAddres, String reciverName, String deliveryDate) {
        this.code = code;
        this.transporter = transporter;
        this.deliveryAddress = deliveryAddres;
        this.reciverName = reciverName;
        this.physics = new PhysicalData();
        this.setDeliveryDate(deliveryDate);
    }

    public TransportableOrder(String code, String transporter, String deliveryAddres, String reciverName, String deliveryDate,
            Double high, Double wide, Double deep, Double weight, Boolean fragile) {
        this.code = code;
        this.transporter = transporter;
        this.deliveryAddress = deliveryAddres;
        this.reciverName = reciverName;
        this.physics = new PhysicalData(high, wide, deep, weight, fragile);
        this.setDeliveryDate(deliveryDate);
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeliveryDate() {
        if (this.deliveryDate != null) {
            return this.deliveryDate.format(this.dateTimeFormatter);
        }
        return "";
    }

    public void setDeliveryDate(String receptionDate) throws DateTimeParseException {
        if (receptionDate != null && receptionDate.trim().length() > 0) {
            this.deliveryDate = LocalDateTime.parse(receptionDate, this.dateTimeFormatter);
        }
    }

    @Override
    public String getTransporter() {
        return this.transporter;
    }

    public void setTransporter(String transporter) {
        this.transporter = transporter;
    }

    @Override
    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public boolean setDeliveryAddress(String deliveryAddres) {
        if (deliveryAddres == null || deliveryAddres == "" || deliveryAddres.length() <= 4) {
            return false;
        }
        this.deliveryAddress = deliveryAddres;
        return true;
    }

    @Override
    public String getReciverName() {
        return reciverName;
    }

    public boolean setReciverName(String reciverName) {

        if (reciverName == null || reciverName == ""  || reciverName.length() <= 2) {
            return false;
        }
        this.reciverName = reciverName;
        return true;

    }

    public Double getHigh() {
        return this.physics.getHigh();
    }

    public void setHigh(Double high) {
        this.physics.setHigh(high);
    }

    public Double getWide() {
        return this.physics.getWide();
    }

    public void setWide(Double wide) {
        this.physics.setWide(wide);
    }

    public Double getDeep() {
        return this.physics.getDeep();
    }

    public void setDeep(Double deep) {
        this.physics.setDeep(deep);
    }

    @Override
    public Double getWeight() {
        return this.physics.getWeight();
    }

    public void setWeight(Double weight) {
        this.physics.setWeight(weight);
    }

    @Override
    public Boolean isFragile() {
        return this.physics.getFragile();
    }

    public void setFragile(Boolean fragile) {
        this.physics.setFragile(fragile);
    }

    @Override
    public String getDimensions() {
        return "W:" + this.physics.getWide() + ";D:" + this.physics.getDeep() + ";H:" + this.physics.getHigh();
    }

    @Override
    public Double getVolum() {
        return this.physics.getHigh() * this.physics.getWide() * this.physics.getDeep();
    }

    //Métodos creados para el arraylist transportableDetail
    public int getNumDetails() {
        //llamamos al arraylist y vemos su propiedad size
        System.out.println(transportableDetails.size());
        return transportableDetails.size();
    }

    //Método extra (NO LO PIDE LA PRÁCTICA) pero me ayuda a hacer una comprobación en los datos
    public boolean strIntValue(String str) {
        //comprueba que en la string hay solo 3 números
        Pattern pattern = Pattern.compile("[0-9]{3}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            //significa que se trata de 3 números entre 0 y nueve
            System.out.println("el paterrn  \"" + str + "\"  es  valido ");
            return true;
        }
        System.out.println("el paterrn  \"" + str + "\" no es  valido ");
        return false;
    }

    //Método para agregar detalles de tipo transportableOrder
    public int setDetail(String ref, int amount) {
        if (amount >= 1 && strIntValue(ref)) {
            if (this.catalog.getStorable(ref) != null) {

                if (this.transportableDetails.get(ref) == null) {

                    transportableDetails.put(ref, new TransportableDetail(this.catalog.getStorable(ref), amount));

                    return 1;
                } else {
                    System.out.println("Error, la referencia " + ref + " ya existe");
                    return 0;
                }
            } else {
                System.out.println("No se ha completado la inserción");
                return -1;
            }
        }
        return 0;
    }

    public String configJsonTransportableOrderDetail(String ref) {
        //Generamos un json en base al objeto OrderDetail
        String jsonString = "{\"ref\":\"" + this.transportableDetails.get(ref).getCode() + "\",\"amount\":\"" + this.transportableDetails.get(ref).getAmount() + "\",\"volum\":\"" + this.transportableDetails.get(ref).getVolum() + "\",\"weight\":\"" + this.transportableDetails.get(ref).getWeight() + "\"}";
        return jsonString;
    }

    public String getDetail(String ref) {
        if (strIntValue(ref) != true) {
            return "error, comprueba la referencia";
        }
        //si ya existe
        if (this.transportableDetails.get(ref) != null) {
            if (this.catalog.getStorable(ref) != null) {
                return configJsonTransportableOrderDetail(ref);
            }
        }
        return ("No se ha encontrado el detalle o no es valido: \"" + ref + "\"");
    }

    public int updateDetail(String ref, int amount) {
        System.out.println("usted ha indicado actualizar la orden \"" + ref + "\" a " + amount);
        if (strIntValue(ref) && amount >= 0) {  //la minima cantidad que puede haber (0: no hay stock)
            if (this.transportableDetails.get(ref) != null) {
                //si la cantidad que tenía es la msima que la que hemos indicado
                if (this.transportableDetails.get(ref).getAmount() == amount) {
                    System.out.println("Revisa la cantidad");
                    return -1;
                }
                //insertamos la cantidad deseada
                this.transportableDetails.get(ref).setAmount(amount);
                System.out.println("Actualización realizada con exito");
                return 1;
            } else {
                System.out.println("Revisa los datos introducidos");
                return -2;
            }
        }
        //en esta orden el objeto no es transportable o vacio
        System.out.println("Ha ocurrido algún tipo de error");
        //Podría redirigirle a el metodo de updateDetail de la clase order, pero sería modificar la acción de el usuario, por ahora no lo haré
        return -3;
    }

    public int deleteDetail(String ref) {
        System.out.println("usted ha indicado la orden  con referencia " + ref);
        //comprobamos lla referencia
        if (strIntValue(ref)) {   //Comprobamos que sea un string valido y que el campo existe
            //una vez comprobado esto lo que haremos será mirar el campo 
            if (this.transportableDetails.get(ref) != null) {
                //eliminiamos
                transportableDetails.remove(ref);
                System.out.println("campo eliminado");
                return 1;
            } else {
                System.out.println("NO se ha podido eliminar, la referencia no existe");
                return 0;
            }
        }
        System.out.println("Ha ocurrido otro tipo de error");
        return -1;
    }

    //suma de todo el peso de todos los productos
    //EL METODO GET WEIGHT YA EXISTE, ESTE ES EL MISMO QUE PIDE EL EJERCICIO PERO LE HE CAMBIADO EL NOMBRE
    public double getGeneralWeight() {
        double totalsum = 0;
        for (TransportableDetail p : transportableDetails.values()) {
            totalsum += p.product.getWeight();
        }
        return totalsum;
    }

    public void getWeightViewer() {
        System.out.println("-------------------------------------------------------------------------------------------------------");
        for (TransportableDetail p : transportableDetails.values()) {
            System.out.println("producto: \"" + p.product.getCode() + "\" \npeso: \"" + p.product.getWeight() + "\"");

        }
        System.out.println("-------------------------------------------------------------------------------------------------------");
    }
}
