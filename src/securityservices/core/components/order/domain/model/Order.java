package securityservices.core.components.order.domain.model;

import securityservices.core.components.shared.operations.Transportable;
import securityservices.core.components.shared.operations.Operation;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import securityservices.core.components.shared.catalogs.ProductCatalog;
import securityservices.core.components.shared.exception.BuildException;
import securityservices.core.components.shared.stakeholders.StakeHolder;
import securityservices.management.catalogs.serializers.GetCatalogProduct;

public class Order extends Operation {

    protected String paymentType;
    protected LocalDateTime paymentDate;
    protected ArrayList<OrderDetail> orderDetail = new ArrayList<>();
    protected ProductCatalog productCatalog;
    protected TransportableOrder transport = new TransportableOrder(productCatalog);
    protected boolean freeTaxes;

//Este getInstance es para ordenes sin envio
    public static Order getInstance(ProductCatalog catalog, String code, StakeHolder interested, double value,
            double surcharges, String status, String comments, String beginDate, String finishDate,
            String paymentType, String paymentDate) throws BuildException {

        String error = "";
        Order o = new Order(catalog);

        if (o.setCode(code) == false) {
            error += "Bad Code";
        }

        if (o.setInterested(interested) == false) {
            error += "Bad Interested (StakeHolder)";
        }

        if (o.setValue(value) == false) {
            error += "Bad Value";
        }
        if (o.setSurcharges(surcharges) == false) {
            error += "Bad Surcharges";
        }

        if (o.setStatus(status) == false) {
            error += "Bad Status";
        }

        if (o.setComments(comments) == false) {
            error += "Bad Comments";
        }

        if (o.setBeginDate(beginDate) == false) {
            error += "Bad Date (Begin)";
        }

        if (o.setFinishDate(finishDate) == false) {
            error += "Bad Date (Finish)";
        }

        if (o.setPaymentType(paymentType) == false) {
            error += "Bad Payment Type";
        }

        if (o.setPaymentDate(paymentDate) == false) {
            error += "Bad Payment Date";
        }

        if (error.length() > 0) {
            o = null;
            throw new BuildException(error);
        }
        return o;
    }
//Este getInstance es para ordenes CON ENVIO

    public static Order getInstance(ProductCatalog catalog, String code, StakeHolder interested, double value,
            double surcharges, String status, String comments, String beginDate, String finishDate, String paymentType,
            String paymentDate, String reciverName, String deliveryAddress) throws BuildException {

        String error = "";
        Order o = new Order(catalog);

        if (o.setCode(code) == false) {
            error += "Bad Code";
        }

        if (o.setInterested(interested) == false) {
            error += "Bad Interested (StakeHolder);";
        }

        if (o.setValue(value) == false) {
            error += "Bad Value;";
        }
        if (o.setSurcharges(surcharges) == false) {
            error += "Bad Surcharges;";
        }

        if (o.setStatus(status) == false) {
            error += "Bad Status;";
        }

        if (o.setComments(comments) == false) {
            error += "Bad Comments;";
        }

        if (o.setBeginDate(beginDate) == false) {
            error += "Bad Date (Begin);";
        }

        if (o.setFinishDate(finishDate) == false) {
            error += "Bad Date (Finish);";
        }

        if (o.setPaymentType(paymentType) == false) {
            error += "Bad Payment Type;";
        }

        if (o.setPaymentDate(paymentDate) == false) {
            error += "Bad Payment Date;";
        }

        if (o.transport.setReciverName(reciverName) == false) {
            error += "Bad Reciver Name;";
        }

        if (o.transport.setDeliveryAddress(deliveryAddress) == false) {
            error += "Bad Delivery Address;";
        }
        if (error.length() > 0) {
            o = null;
            throw new BuildException(error);
        }
        return o;
    }

    public Order(ProductCatalog catalog) {
        this.productCatalog = catalog;
    }

    public Order(ProductCatalog catalog, String code, StakeHolder interested, double value, double surcharges, String status, String comments, String beginDate, String finishDate, String paymentType, String paymentDate) {
        super(code, interested, value, surcharges, status, comments, beginDate, finishDate);
        this.productCatalog = catalog;
        this.paymentType = paymentType;
        this.setPaymentDate(paymentDate);
    }

    public Order(ProductCatalog catalog, String code, StakeHolder interested, double value, double surcharges, String status, String comments, String beginDate, String finishDate, String paymentType, String paymentDate, String reciverName, String deliveryAddress) {
        super(code, interested, value, surcharges, status, comments, beginDate, finishDate);
        this.productCatalog = catalog;
        this.paymentType = paymentType;
        this.setPaymentDate(paymentDate);
        this.transport = new TransportableOrder(code, null, deliveryAddress, reciverName, null);

    }

    private void prepareTransport() {
        this.transport.setCode(this.code);
        this.transport.setReciverName(this.interested.getName());
        this.transport.setDeliveryAddress(this.interested.getAddress());
    }

    public Transportable getTranportable() {
        if (this.transport != null) {
            this.prepareTransport();
        }
        return this.transport;
    }

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getReciverName() {
        return this.transport.getReciverName();

    }

    public String getDeliveryAddress() {
        return this.transport.getDeliveryAddress();
    }

    public boolean setPaymentType(String paymentType) {
        if (paymentType == null) {
            return false;
        }
        this.paymentType = paymentType;
        return true;
    }

    public String getPaymentDate() {
        if (this.paymentDate != null) {
            return this.paymentDate.format(this.dateTimeFormatter);
        }
        return "";
    }

    public boolean setPaymentDate(String paymentDate) throws DateTimeParseException {
        if (paymentDate != null && paymentDate.trim().length() > 0) {
            this.paymentDate = LocalDateTime.parse(paymentDate, this.dateTimeFormatter);
            return true;
        }
        return false;
    }

//Método que nos devuelve el nº de detalles encontrados en el ArrayList <OrderDetail>
    public int getNumDetails() {
        int countArray = this.orderDetail.size();   //Función size que funciona como un "count" sin tener que recorrer manualmente todo el array
        return countArray;
    }

    //Método extra (NO LO PIDE LA PRÁCTICA) pero me ayuda a hacer una comprobación en los datos
    public boolean strIntValue(String str) {
        //comprueba que en la string hay solo 3 números
        Pattern pattern = Pattern.compile("[0-9]{3}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            //significa que se trata de 3 números entre 0 y nueve
            //System.out.println("el paterrn  \"" + str + "\"  es  valido ");
            return true;
        }
        //System.out.println("el paterrn  \"" + str + "\" no es  valido ");
        return false;
    }

    //Método que agrega un nuevo "detalle" a el ArrayList de OrderDetail
    public int setDetail(String ref, int amount) {
        //Primeramente comprovaremos los datos que nos ha enviado el usuario (no sabemos lo que hay, solo conocemos que es un strring y un int)
        if (amount <= 0) {
            return 0;//lo echamos de la función
        }
        //comparamos el valor del usuario, no le permitimos tener letras o ser igual a 000, esta última comprovación no es realmente obligatoria pero no me parece bien que exista un producto 000
        if (strIntValue(ref) == false || ref.equals("000")) {
            // System.out.println("la cantidad  \"" + ref + "\" no es  valida ");
            return 0;//lo echamos de la función
        }
        //en el caso de que la referencia ya exista (es como una clave primaria, no podemos permitir que haya redundancia)
        for (OrderDetail row : orderDetail) {
            if (row.getRef().equals(ref)) {
                //Si se da el caso de que la referencia ya existe
                // System.out.println("la referencia  \"" + ref + "\" no es  valida ");
                return 0;
            }
        }

        //Registramos el tamaño que tenía antes de hacer nada el ArrayList
        int count = this.getNumDetails();
        //Creamos un nuevo objeto tipo OrderDetail
//nueva instancia del catálogo y esta misma carga los datos del catalogo con el método loadCatalog
        
        OrderDetail od = new OrderDetail(productCatalog.getMarketable(ref), amount);    //Este objeto recibe una transformación a el tipo Marketable mediante la clase ProductCatalog
        this.orderDetail.add(od);    //Usamos la función .add para agregar e campo
        //Devolvemos un 1 o un 0 según el tipo de resultado
        if (count < this.getNumDetails()) {
            //en el caso de que el contador inicial sea menor a el nuevo recuento
            return 1;
        } else {
            //en el caso de que sean iguales significa que por alguna razón no se ha agregado
            return 0;
        }
    }

    //Método extra (NO LO PIDE LA PRÁCTICA)
    public String configJsonDetail(OrderDetail row) {
        //Generamos un json en base al objeto OrderDetail
        String jsonString = "{\"ref\":\"" + row.getRef() + "\",\"name\":\"" + row.getName() + "\"}";
        return jsonString;
    }

    //Método que nos devuelve una posición del ArrayList según su posición
    public String getDetail(int pos) {
System.out.println("Esto es un get DEtails!");
        if (pos >= 1 && this.getNumDetails() >= pos) {   //al tratarse de un array la posición más pequeña será 1. Todas las posiciones inferiores a 1 serán inexistentes, no lo comprobaremos, y si son más grandes que nuestro ArrayList no las comprobaremos
            OrderDetail row = this.orderDetail.get(pos - 1);    //Creamos una variable de tipo OrderDetail
            String rowString = this.configJsonDetail(row);  //Usamos el método creado para poder generar la respuesta en base a la posición seleccionada
            return rowString;
        } else {
            //Comentario para dar información al usuario sobre su error
            return "Comprueba la posición";
        }
    }
//Mñetodo que nos devuelve una posición del ArrayList según su referencia

    public String getDetail(String ref) {
System.out.println("Esto es un get DEtails!");
        if (this.strIntValue(ref)) {
            //Recorremos el arrayList orderDetail
            for (OrderDetail product : orderDetail) {
                //Comparamos el resultado (en el caso de que la  referencia que se encuentra en el arrayList sea igual a la referencia pasada por el usuario la devolvemos)
                if (product.getRef().equals(ref)) {
                    //Devolvemos la referencia tratada de manera que el usuario consigue toda la información en formato Json
                    String rowString = this.configJsonDetail(product);
                    return rowString;
                }
            }
        }
        //Comentario para dar información al usuario sobre un probable error en el dato introducido
        return "no se ha encontrado este producto";
    }

    //Método extra (no lo pide el ejercicio)
    public String getAllDetails() {
        if (this.getNumDetails() >= 1) {
            String bigString = "";
            int counter = 0;
            for (OrderDetail product : orderDetail) {
                counter++;
                if ((this.getNumDetails() - 1) == counter) {
                    bigString += "{\"ref\":\"" + product.getRef() + "\",\"name\":\"" + product.getName() + "\",\"price\":\"" + product.getPrice() + "\"},";
                } else {
                    bigString += "{\"ref\":\"" + product.getRef() + "\",\"name\":\"" + product.getName() + "\",\"price\":\"" + product.getPrice() + "\"}";
                }
                //System.out.println("{\"ref\":\"" + product.getRef() + "\",\"name\":\"" + product.getName() + "\",\"price\":\"" + product.getPrice() + "\"}");
            }
            return bigString;
        }
        return null;
    }

    //Método que nos actualiza el detalle mediante su posición en el arrayList
    public int updateDetail(int pos, int amount) {
        System.out.println("usted ha indicado actualizar la cantidad de la posición " + pos + " a " + amount);

        if (pos >= 1 && this.getNumDetails() >= pos) {   //al tratarse de un array la posición más pequeña será 0. Todas las posiciones inferiores a 0 serán inexistentes, no lo comprobaremos, y si son más grandes que nuestro ArrayList no las comprobaremos

            if (amount >= 0) {    //la minima cantidad que puede haber (0: no hay stock)
                //miramos la posición
                int getAmount = this.orderDetail.get(pos - 1).getAmount();
                //si se da el caso que el usuario ha puesto la misma cantidad que ya había
                if (getAmount == amount) {
                    //no hacemos nada
                    System.out.println("Se ha detectado la misma cantidad, no se efectuará ningún cambio");
                    return -1;
                }
                //aplicamos la nueva cantidad
                this.orderDetail.get(pos - 1).setAmount(amount);

                System.out.println("se ha actualizado el producto \"" + this.orderDetail.get(pos - 1).getRef() + "\" ( " + this.orderDetail.get(pos - 1).getName() + " ) con la cantidad de \"" + amount + "\"");
                return 1;
            }
            System.out.println("Revisa la cantidad");
            return -1;
        }
        System.out.println("Revisa los datos introducidos");
        return -2;
    }

    //Método que nos actualiza el detalle mediante su referencia en el arrayList
    public int updateDetail(String ref, int amount) {
        System.out.println("usted ha indicado actualizar la cantidad de la referencia " + ref + " a " + amount);
        //comprobamos la referencia:
        if (strIntValue(ref) != true) {
            System.out.println("La referencia no es correcta");
            return 0; //si la referencia no es correcta no dejaremos que la busque 
        }

        //si la cantidad que el usuario ha especifiacdo es menor o igual que 0
        if (amount >= 0) {
            //recorremos el arraylist para buscar la posición
            for (OrderDetail row : orderDetail) {
                //preguntamos a la referencia de cada posición si es igual a la que el usuario nos ha indicado
                if (row.getRef().equals(ref)) {
                    int oldAmount = row.getAmount();
                    row.setAmount(amount);
                    int newAmount = row.getAmount();
                    if (oldAmount != newAmount) {
                        System.out.println("se ha actualizado el producto \"" + row.getRef() + "\" ( " + row.getName() + " ) con la cantidad de \"" + amount + "\"");
                        return 1;
                    }
                    System.out.println("La referencia no se ha actualizado, error inesperado");
                    return 0;
                }

            }
            System.out.println("La referencia no se ha encontrado");
            return -1;
        }
        System.out.println("La cantidad no es valida");
        return 0;
    }

//Método para eliminar un detalle mediante la posición
    public int deleteDetail(int pos) {
        System.out.println("usted ha indicado eliminar el producto que se encuentra en la posición " + pos);
        //comprobamos la posición
        if (pos >= 1 && this.getNumDetails() >= pos) {   //al tratarse de un array la posición más pequeña será 0. Todas las posiciones inferiores a 0 serán inexistentes, no lo comprobaremos, y si son más grandes que nuestro ArrayList no las comprobaremos
            //una vez comprobado esto lo que haremos será mirar el producto que se encuentra en el campo
            System.out.println("Usted ha eliminado el producto " + orderDetail.get(pos - 1).getName() + " con referencia " + orderDetail.get(pos - 1).getRef());
            //al ser un arrayList no sirve de nada comprobar el dato que hay una vez lo eliminemos ya que se organizará de nuevo y se puede dar el caso de que tenga el mismo nombre y misma cantidad (no voy a hacerlo ya que puedo borrar sin querer otro producto)
            //eliminamos

            orderDetail.remove(pos - 1);
            return 1;
        }
        System.out.println("Producto no encontrado, comprueba la posición");
        return 0;
    }

//Método para eliminar un detalle mediante la referencia
    public int deleteDetail(String ref) {
        System.out.println("usted ha indicado eliminar el producto con referencia " + ref);

        //comprobamos la referencia:
        if (strIntValue(ref) != true) {
            System.out.println("La referencia no es correcta");
            return 0; //si la referencia no es correcta no dejaremos que la busque 
        }

        for (int i = 0; i < this.getNumDetails(); i++) {
            //si se recorre el arraylist y se encuentra una coincidencia
            // System.out.println("\n\n\nPRUEBAAAAA"  +orderDetail.get(i).getRef()+ "\n\n\n");
            if (orderDetail.get(i).getRef().equals(ref)) {
                String deletedRef = orderDetail.get(i).getRef();
                String deletedName = orderDetail.get(i).getName();
                //eliminamos la posición en la que se encuentra
                orderDetail.remove(i);
                System.out.println("Usted ha eliminado el producto " + deletedName + " con referencia " + deletedRef);
                return 1;
            }

        }
        System.out.println("Producto no encontrado, comprueba la posición");
        return 0;
    }

    //Método para conseguir el precio de la suma de todos los productos de la orden
    public double getPrice() {
        double sum = 0;
        //recorremos el arrayList
        for (OrderDetail order : orderDetail) {
            sum += order.getPrice();    //el método get price suma el precio y tasas  de cada producto y los multiplica por su cantidad
        }//cada vez que pasamos por el bucle sumamos a la variable sum
        return sum;
    }

//Método para conseguir el precio de los productos especificados por referencia
    public double getPrice(String ref) {
        System.out.println("Usted ha solicitado el precio de el producto con referencia: \"" + ref + "\"");

        //si no existe ni un solo producto no podremos obtener ningún resultado
        //comprobamos que la referencia es valida (que el usuario no nos ha enviado ningún valor extraño)
        if (this.getNumDetails() > 1 && this.strIntValue(ref)) {
            //recorremos el arrayList y mostramos todos los datos
            for (OrderDetail product : orderDetail) {
                //comprobamos que la referencia existe
                if (product.getRef().equals(ref)) {
                    //si la referencia es correcta y se ha encontrado en alguna posición
                    //devolvemos el precio
                    System.out.println("el precio de el producto " + product.getName());
                    return product.getPrice();
                }
            }
        }
        System.out.println("No se ha encontrado el producto solicitado, comprueba los datos");
        return 0.00;
    }

}
