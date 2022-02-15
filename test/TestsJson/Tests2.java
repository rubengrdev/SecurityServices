package TestsJson;
//import converters.json.Json;
//import converters.json.JsonFormatterInstancer;

import securityservices.core.components.shared.services.serializers.Json;
import securityservices.core.components.shared.services.serializers.JsonObjectFactory;

public class Tests2 {

    public static void main(String[] args) {
        System.out.println("Prueba generando un json que incluye un array de objetos json: ");
        ExampleDTO edto = new ExampleDTO(1, "code:1,amount:2,price:12.99;code:2,amount:4;price:2.99;");

        Json jdata = JsonObjectFactory.getInstance();
        Json jobject = JsonObjectFactory.getInstance();
        Json jarray = JsonObjectFactory.getInstance();
        Json jdetail = JsonObjectFactory.getInstance();

        jobject.set("Code", String.valueOf(edto.getCode()));
        String details = edto.getDetails();

        String arrayDetails[] = details.split(";");

        for (int i = 0; i < arrayDetails.length; i++) {
            String detail = arrayDetails[i];
            String fields[] = detail.split(",");
            for (int j = 0; j < fields.length; j++) {
                String keyValue[] = fields[j].split(":");
                jdetail.set(fields[0], fields[1]);
            }
        }
        jdetail.set("id", "22");
        jdetail.set("amount", "2");
        jdetail.set("price", "9.99");
        jarray.set("details", jdetail);
        jdetail.removeAll();
        jdetail.set("id", "23");
        jdetail.set("amount", "3");
        jdetail.set("price", "6.99");
        jarray.set("details", jdetail);
        jdetail.removeAll();
        jdetail.set("id", "24");
        jdetail.set("amount", "4");
        jdetail.set("price", "3.99");
        jarray.set("details", jdetail);
        jobject.set(jarray.toString());
        jobject.set("Code", "1");
        jdata.set("order", jobject);
        System.out.println(jdata.toString());
    }
}
