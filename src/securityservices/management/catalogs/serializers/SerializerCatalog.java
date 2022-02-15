package securityservices.management.catalogs.serializers;

import java.util.TreeMap;
import javax.xml.bind.JAXBException;
import securityservices.core.components.client.appservices.JaxbClientSerializer;
import securityservices.core.components.client.appservices.JsonClientSerializer;
import securityservices.core.components.client.appservices.XmlClientSerializer;
import securityservices.core.components.shared.services.serializers.Serializer;
import securityservices.core.components.shared.services.serializers.xmlapis.Dom;

public class SerializerCatalog {
    
    private static TreeMap<SerializerType, Serializer> catalog = new TreeMap<>();
    
    private static void loadCatalog(){
        
        catalog.put(SerializerType.XmlClient, new XmlClientSerializer(new Dom()));
        
        try{
            Serializer s = JaxbClientSerializer.getInstance();
            catalog.put(SerializerType.JaxbClient, s);
        }catch (JAXBException ex){
            //to logFile
        }
       
        catalog.put(SerializerType.JsonClient, new JsonClientSerializer());
    }
    
    public static Serializer getInstance(SerializerType type){
        if (catalog.isEmpty()){
            loadCatalog();
        }
        return catalog.get(type);
    }
}
