package securityservices;

import securityservices.core.components.client.domain.model.Client;
import securityservices.core.components.equipment.domain.model.Equipment;
import securityservices.core.components.order.domain.model.Order;
import securityservices.core.components.shared.catalogs.ProductCatalog;
import securityservices.core.components.shared.exception.BuildException;
import securityservices.management.catalogs.serializers.GetCatalogProduct;
import securityservices.management.catalogs.serializers.GetStakeHolder;

public class SecurityServices {

    public static void main(String[] args) {
        try {
            System.out.println("This is Client Instance");
            Client c = Client.getInstance("", "331241412U", "hhhhhhh.com", "921841415", "calle del pocho 8", "22-12-2021", "****345", "aaaa", 1);
        } catch (BuildException ex) {
            System.out.println(ex);
        }

        try {
            System.out.println("This is Client Equipment");
            // "020", "Server", "Informatico", "SS", "desc", 3000.0, 0.0, 0.8, 0.6, 0.45, 7.1, true, "dataservices", "hw list", 800
            Equipment e = Equipment.getInstance("", "", "", "", "", -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, false, "", "", -1);
        } catch (BuildException ex2) {
            System.out.println(ex2);
        }

        try {
            ProductCatalog prod = GetCatalogProduct.getInstance();
            Client clientHolder = Client.getInstance("ruben", "4582948J", "ruben@mail.com", "123456789", "c/Barcelona", "05-11-1999", "DDADWDWDWDWD", "JDKWK", 2);
Order order1 = Order.getInstance(prod, "222DDD", GetStakeHolder.getStakeHolderCode(clientHolder.getCode()), 22.2, 22.2, "active", "no comments", "10-07-2005-23:34:01", "20-03-2009-18:34:01", "Visa", "10-07-2005-23:34:01", "ReciverName", "C/montflorit");
            System.out.println(GetStakeHolder.getStakeHolderCode(clientHolder.getCode()));
        } catch (BuildException ex3) {
            System.out.println(ex3);
        }

        /*
        System.out.println("\nSección de código de ejemplo que se ha entregado en la práctica (saltar)\n");
        //EJEMPLO DE LAS POSIBILIDADES DE LAS CLASE ORDER (FALTA POR DESARROLLAR LA GESTION DE LOS ORDERDETAIL)      
        CompanyClient cc = new CompanyClient("NURIA", "B-21213", "cefpnuria@nuria.cat", "936622113", "Apeles Mestres 48",
                "01-09-1970", "***", 311, 124, "SR", "Educacio");

        ProductCatalog prodCatalog = new ProductCatalog();  //CREAMOS EL CATALOGO DE PRODUCTOS DE NUESTRO NEGOCIO
        loadCatalog(prodCatalog);      //LLAMAMOS AL METODO QUE NOS CARGA EL CATALOGO DE PRODUCTOS

        System.out.println("MARKETABLES:");
        Marketable m = prodCatalog.getMarketable("002");
        System.out.println(m.getCode() + ";" + m.getName() + ";" + m.getPrice() + ";" + m.getDetails());

        m = prodCatalog.getMarketable("012");
        System.out.println(m.getCode() + ";" + m.getName() + ";" + m.getPrice() + ";" + m.getDetails());

        m = prodCatalog.getMarketable("021");
        System.out.println(m.getCode() + ";" + m.getName() + ";" + m.getPrice() + ";" + m.getDetails());

        System.out.println("STORABLES:");
        Storable s = prodCatalog.getStorable("020");
        System.out.println(s.getCode() + ";" + s.getDimensions() + ";" + s.getWeight());
        s = prodCatalog.getStorable("022");
        System.out.println(s.getCode() + ";" + s.getDimensions() + ";" + s.getWeight());

        Order ord = new Order(prodCatalog, "00011", cc, 0.0, "prepared", "", "09/12/2021-12:00:05", null, "cc", "19/12/2021-20:00:00", "Lluis", "carrer kalea 7");
        System.out.println("BEGIN DATE: " + ord.getBeginDate());
        System.out.println("FINISH DATE: " + ord.getFinishDate());

        Transportable t = ord.getTranportable();
        System.out.println(t.getCode() + ";" + t.getReceiverName() + ";" + t.getDeliveryAddress() + ";" + t.getDeliveryDate() + ";" + t.getDimensions() + ";" + t.getWeight());

        //el Security Services hecho por el alumno empieza  a continuación:
        System.out.println("\n\nDemostración del código\n");
        System.out.println("\n\n\n--------------------------------------CLASE ORDER-------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("\n\n\n");

        //a parte de las inserciones de productos que se han heho anteriormente, yo también voy a crear algunos objetos de tipo Order para poder demostrar que el código funciona
        Order order = new Order(prodCatalog);
        //Tamaño del ArrayList
        System.out.println("\n--------------------------------------GET NUM DETAIL--------------------------------------");
        System.out.println("Tamaño del ArrayList: " + order.getNumDetails());
        //set de algunos detalles
        System.out.println("\n---------------------------------------SET DETAIL--------------------------------------");
        order.setDetail("001", -1); //este debe de dar error
        order.setDetail("AAA", 1);  //este debe de dat error
        order.setDetail("010", 3);
        order.setDetail("010", 2); //este debe dar error ya que esta referencia ya existe
        order.setDetail("012", 2);
        order.setDetail("001", 2);
        order.setDetail("011", 2);

        //comprovación de los datos introducidos con exito
        System.out.println("\n--------------------------------------SEGUNDO GET NUM DETAIL (para comprobar los sets)--------------------------------------");
        System.out.println("Tamaño del ArrayList: " + order.getNumDetails());

        //get de algunos detalles
        System.out.println("\n---------------------------------------GET DETAIL--------------------------------------");
        System.out.println(order.getDetail("AAA")); //este es uno de los que no ha funcionado antes al insetarlo
        System.out.println(order.getDetail(23)); //este dará error si la posición no existe
        System.out.println(order.getDetail(-1));    //este dará error porque la posición es negativa
        System.out.println(order.getDetail(0)); //esta no es valida, he planteado que para este ejercico la mínima posición que puede seleccionar un usuario es la 1 
        System.out.println(order.getDetail("010")); //este si que es valido (por referencia)
        System.out.println(order.getDetail(1)); //este si que es valido ya que es la posició mínima
        System.out.println(order.getDetail(2)); //este si que es valido 
        System.out.println(order.getDetail(3)); //este si que es valido 

        //Posibles formatos de salida
        //formato tipo ("csv" no se si es correcto llamarlo así/delimitado por comas):
        //"002;Producto de Ejemplo;25; 49.99€;"
        //Formato tipo Json:
        //Se usa \" para forzar a que se use la comilla doble y que no finalice la String
        //Como ejemplo para poder validar el json de la sentencia he utilizado el siguiente servicio web: https://jsonformatter.curiousconcept.com/ o https://codebeautify.org/jsonviewer
        //"{\"ref\":\"002\",\"name\":\"Producto de ejemplo\",\"amount\":\"25 uds.\",\"price\":\"49.99€\"}"
        //Si eliminamos las contrabarras nos quedará así: {"ref":"002","name":"Producto de ejemplo","amount":"25 uds.","price":"49.99€"}
   
        
        //Método extra (no lo pide el ejercicio)
        System.out.println("\n\n" + "Get All Details");
        order.getAllDetails();

        System.out.println("\n---------------------------------------UPDATE DETAIL--------------------------------------");
        //update de prueba (son actualizaciones que no se pueden llevar a cabo ya que los datos no son validos)
        order.updateDetail(0, 0);
        System.out.println("\n");
        order.updateDetail(-1, 2);
        System.out.println("\n");
        order.updateDetail(2, -3);
        System.out.println("\n");

        //hacemos un update
        order.updateDetail(1, 1);
        System.out.println("\n");
        order.updateDetail(2, 4);
        System.out.println("\n");
        order.updateDetail(3, 7);
        System.out.println("\n");

        //a continuación haremos los update por referencia
        System.out.println("\n\nUpdate por Referencia\n");
        //prueba de errores
        order.updateDetail("0DD", 2);
        System.out.println("\n");
        order.updateDetail("333", 2);
        System.out.println("\n");
        order.updateDetail("001", -2);
        System.out.println("\n");
        order.updateDetail("-0D01", -2);
        System.out.println("\n");
        order.updateDetail("-023", -2);
        System.out.println("\n");

        //updates validos
        order.updateDetail("010", 23);
        System.out.println("\n");
        order.updateDetail("012", 5);
        System.out.println("\n");

        //Hacemos algunas inserciones de nuevo por si se han borrado en las pruebas algunos productos
        order.setDetail("012", 2);
        order.setDetail("001", 2);
        order.setDetail("011", 2);

        System.out.println("\n---------------------------------------DELETE DETAIL--------------------------------------");
        //prueba de errores
        order.deleteDetail(0);
        System.out.println("\n");
        order.deleteDetail(-1);
        System.out.println("\n");
        order.deleteDetail(100);
        System.out.println("\n");
        order.deleteDetail(150000000);
        System.out.println("\n");

        //deletes validos
        order.deleteDetail(1);
        System.out.println("\n");
        order.deleteDetail(2);
        System.out.println("\n");
        order.deleteDetail(3);
        System.out.println("\n");

        //Hacemos algunas inserciones de nuevo por si se han borrado en las pruebas algunos productos
        order.setDetail("012", 2);
        order.setDetail("001", 2);
        order.setDetail("011", 2);

        System.out.println("\n\nDelete por Referencia\n");

        //prueba de errores
        order.deleteDetail("01D2");
        System.out.println("\n");
        order.deleteDetail("-123");
        System.out.println("\n");
        order.deleteDetail("-AAA");
        System.out.println("\n");

        //mostramos lo que tenemos
        order.getAllDetails();
        System.out.println("\n");

        //prueba de valores correctos
        order.deleteDetail("001");
        System.out.println("\n");
        order.deleteDetail("011");
        System.out.println("\n");
        order.deleteDetail("012");
        System.out.println("\n");

        //nuevos set
        order.setDetail("012", 2);
        order.setDetail("001", 2);
        order.setDetail("011", 2);

        System.out.println("\n---------------------------------------GET PRICE--------------------------------------");

        System.out.println("\n\nGetPrice por Referencia\n");
        //metodos con errores (prueba de datos invalidos)
        System.out.println(order.getPrice("0021"));
        System.out.println("\n");
        System.out.println(order.getPrice("101"));
        System.out.println("\n");
        System.out.println(order.getPrice("AAA"));
        System.out.println("\n");
        System.out.println(order.getPrice("-001"));
        System.out.println("\n");

        //Método get price por referencia validos
        System.out.println(order.getPrice("001") + " €");
        System.out.println("\n");
        System.out.println(order.getPrice("011") + " €");
        System.out.println("\n");
        System.out.println(order.getPrice("012") + " €");
        System.out.println("\n");

        System.out.println("\nGetPrice de todos los productos\n");
        //este método devuelve la suma de el producto y sus tasas por su cantidad, esto lo hace por todos los objetos del arraylist
        System.out.println(order.getPrice() + " €");
        System.out.println("\nVamos a crear un nuevo producto\n");
        prodCatalog.addCatalog(new Service("777", "campo  de prueba", "hola", "aa", "aa", 15000.0, 0.0, "diario", "none"));
        //agregamos el producto
        order.setDetail("777", 1);
        System.out.println(order.getPrice() + " €");

        //elimino este producto del catálogo ya que es unicamente una prueba
        order.deleteDetail("777");

        System.out.println("\n\n\n--------------------------------------CLASE TRANSPORTABLEORDER-------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("\n\n\n");

        //Creamos el objeto tipo TransportableOrder vacio (solo con el prodCatalog
        TransportableOrder newOTransportableOrder = new TransportableOrder(prodCatalog);

        System.out.println("\n--------------------------------------GET NUM DETAIL--------------------------------------");

        newOTransportableOrder.getNumDetails();

        System.out.println("\n--------------------------------------SET  DETAIL--------------------------------------");
        //Setters erroneos
        newOTransportableOrder.setDetail("001", 0);
        newOTransportableOrder.setDetail("0DD", 2);
        newOTransportableOrder.setDetail("001", -2);
        System.out.println("\ninserciones validas\n");
        //Dejamos que mientras la cantidad sea correcta el usuario pueda meter todos los productos que quiera, proximamente cuando queramos obtener si un producto es transportable o no comprobaremos que lo ha hecho de forma correcta y que no se trata de un software o servicio
        newOTransportableOrder.setDetail("001", 2);
        newOTransportableOrder.setDetail("002", 3);
        newOTransportableOrder.setDetail("020", 310);
        newOTransportableOrder.setDetail("021", 100);
        newOTransportableOrder.setDetail("022", 20);

        System.out.println("\ncomprobamos que han funcionado las inserciones \nNumero de inserciones: ");
        newOTransportableOrder.getNumDetails();  //vemos las inserciones
        System.out.println("\n--------------------------------------GET DETAIL--------------------------------------");
        //Estos detalles no son validos

        System.out.println(newOTransportableOrder.getDetail("001"));
        System.out.println(newOTransportableOrder.getDetail("002"));
        System.out.println(newOTransportableOrder.getDetail("003"));
        System.out.println(newOTransportableOrder.getDetail("0DO"));

        //Estos detalles si que son validos
        System.out.println("\nDetalles validos:\n ");
        System.out.println(newOTransportableOrder.getDetail("020"));
        System.out.println(newOTransportableOrder.getDetail("021"));
        System.out.println(newOTransportableOrder.getDetail("022"));

        System.out.println("\n--------------------------------------UPDATE DETAIL--------------------------------------");
        //Updates no validos
        newOTransportableOrder.updateDetail("0D0", 1);
        System.out.println("\n");
        newOTransportableOrder.updateDetail("000", -1);
        System.out.println("\n");
        newOTransportableOrder.updateDetail("PPP", -1);
        System.out.println("\n");
        newOTransportableOrder.updateDetail("899", 1);
        System.out.println("\n");
        newOTransportableOrder.updateDetail("001", 1);
        System.out.println("\n");

        //Updates validos
        System.out.println("\nUpdates validos:");
        newOTransportableOrder.updateDetail("020", 5);
        System.out.println("\n");
        newOTransportableOrder.updateDetail("021", 7);
        System.out.println("\n");
        newOTransportableOrder.updateDetail("022", 10);
        System.out.println("\n");
        newOTransportableOrder.updateDetail("020", 7);
        System.out.println("\n");

        System.out.println("\n--------------------------------------DELETE DETAIL--------------------------------------");
        //Delete invalido
        System.out.println(newOTransportableOrder.getDetail("199"));
        System.out.println("\n");
        newOTransportableOrder.deleteDetail("199");
        System.out.println("\n");
        System.out.println(newOTransportableOrder.getDetail("199"));
        System.out.println("\n");
        System.out.println("\n");

        System.out.println(newOTransportableOrder.getDetail("ODD"));
        System.out.println("\n");
        newOTransportableOrder.deleteDetail("ODD");
        System.out.println("\n");
        System.out.println(newOTransportableOrder.getDetail("ODD"));
        System.out.println("\n");
        System.out.println("\n");

        //en esta comprobación lo que hacemos es ver que hay en el detail, despues eliminarlo y luego volverlo a mirar
        newOTransportableOrder.setDetail("020", 20);
        System.out.println(newOTransportableOrder.getDetail("020"));
        System.out.println("\n");
        newOTransportableOrder.deleteDetail("020");
        System.out.println("\n");
        System.out.println(newOTransportableOrder.getDetail("020"));
        System.out.println("\n");
        System.out.println("\n");

        newOTransportableOrder.setDetail("021", 21);
        System.out.println(newOTransportableOrder.getDetail("021"));
        System.out.println("\n");
        newOTransportableOrder.deleteDetail("021");
        System.out.println("\n");
        System.out.println(newOTransportableOrder.getDetail("021"));
        System.out.println("\n");
        System.out.println("\n");

        System.out.println("\n--------------------------------------GET WEIGHT--------------------------------------");
        //Este es el método que solicita el ejercicio
        System.out.println(newOTransportableOrder.getGeneralWeight());
        //Para poder hacer una comprobación precisa he creado un metodo que recorre todo el TreeMap y que te enseña los resultados de el peso que tienen todos los productos, así poder comprobar que realmente funciona
        newOTransportableOrder.getWeightViewer();

        //Comprobación añadiendo un nuevo campo
        newOTransportableOrder.setDetail("021", 3);
        newOTransportableOrder.getWeightViewer();

        System.out.println(newOTransportableOrder.getGeneralWeight());

        System.out.println("\n\n\n--------------------------------------CLASE STOCK-------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("\n\n\n");
        //creación de nuevo objeto de tipo stock
        Stock stock = new Stock();

        System.out.println("\n--------------------------------------UPDATE STOCK--------------------------------------");
        //errores
        stock.updateStock("ODD", 2);
        System.out.println("\n");
        stock.updateStock("-11", 2);
        System.out.println("\n");
        //en este caso da error porque esta referencia no existe
        stock.updateStock("111", -2);
        System.out.println("\n");

        stock.updateStock("001", 2);
        System.out.println("\n");
        stock.updateStock("001", 44);
        System.out.println("\n");
        //si usamos una referencia negativa en un campo que ya existe
        stock.updateStock("001", -1);
        System.out.println("\n");
        stock.updateStock("001", -5);
        System.out.println("\n");
        stock.updateStock("020", 44);
        System.out.println("\n");

        System.out.println("\n--------------------------------------GET AMOUNT--------------------------------------");
        System.out.println("La cantidad de stock de el 001 es de: " + stock.getAmount("001"));
        System.out.println("La cantidad de stock de el 0DD es de: " + stock.getAmount("ODD")); //esta no debe de existir nunca
        System.out.println("La cantidad de stock de el 001 es de: " + stock.getAmount("002"));

        System.out.println("\n--------------------------------------DELETE STOCK--------------------------------------");
        stock.delStock("001");
        System.out.println("\n");
        stock.delStock("001");
        System.out.println("\n");
        stock.delStock("00D");
        System.out.println("\n");
        stock.delStock("020");

        System.out.println("\n--------------------------------------GET LINES--------------------------------------");
        System.out.println("Creamos algunos objetos despues de haberlos borrado");

        stock.updateStock("001", 44);
        System.out.println("\n");
        stock.updateStock("020", 33);
        System.out.println("\n");
        stock.updateStock("002", 2);
        System.out.println("\n");

        System.out.println("Pattern: \"ref\" -> \"amount\"\n");
        System.out.println(Arrays.toString(stock.getLines()));

        System.out.println("\n--------------------------------------GET NUM LINES--------------------------------------");
        System.out.println(stock.getNumLines());
         */
    }
    /*
    private static void loadCatalog(ProductCatalog prodCatalog) {
//Service(String code, String name, String type, String maker, String description, double price, double taxes, String periodicity, String conditions)  
        prodCatalog.addCatalog(new Service("001", "Mantenimiento", "Informatico", "SS", "desc", 1000.0, 0.0, "mensual", "none"));
        prodCatalog.addCatalog(new Service("002", "Reparacion", "Informatico", "SS", "desc", 200.0, 0.0, "semanal", "none"));
        prodCatalog.addCatalog(new Service("003", "Seguridad", "Informatico", "SS", "desc", 2000.0, 0.0, "anual", "none"));
//Software(String code, String name, String type, String maker, String description, double price, double taxes, String version, String os, String medium)        
        prodCatalog.addCatalog(new Software("010", "Antivirus", "Informatico", "SS", "desc", 29.0, 0.0, "1.7", "linux", "link"));
        prodCatalog.addCatalog(new Software("011", "Firewall", "Informatico", "SS", "desc", 49.0, 0.0, "3.3", "windows", "link"));
        prodCatalog.addCatalog(new Software("012", "Proxy", "Informatico", "SS", "desc", 30.0, 0.0, "2.1", "ios", "link"));
//Equipment(String code, String name, String type, String maker, String description, Double price, Double taxes, Double high, Double wide, Double deep, Double weight, Boolean fragile, String function, String components, Integer power) 
        prodCatalog.addCatalog(new Equipment("020", "Server", "Informatico", "SS", "desc", 3000.0, 0.0, 0.8, 0.6, 0.45, 7.1, true, "dataservices", "hw list", 800));
        prodCatalog.addCatalog(new Equipment("021", "NetworkAnalyzer", "Informatico", "SS", "desc", 1000.0, 0.0, 0.8, 0.6, 0.45, 4.5, true, "Segurity", "hw list", 600));
        prodCatalog.addCatalog(new Equipment("022", "Firewall", "Informatico", "SS", "desc", 30.0, 600.0, 0.8, 0.6, 0.45, 3.8, true, "firewall", "hw list", 400));

    }
     */
}
