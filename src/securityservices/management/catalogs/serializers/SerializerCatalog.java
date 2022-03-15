package securityservices.management.catalogs.serializers;

import java.util.TreeMap;
import javax.xml.bind.JAXBException;
import securityservices.core.components.client.appservices.JaxbClientSerializer;
import securityservices.core.components.client.appservices.JsonClientSerializer;
import securityservices.core.components.client.appservices.XmlClientSerializer;
import securityservices.core.components.equipment.appservices.JsonEquipmentSerializer;
import securityservices.core.components.equipment.appservices.XmlEquipmentSerializer;
import securityservices.core.components.order.appservices.JaxbOrderSerializer;
import securityservices.core.components.shared.services.serializers.Serializer;
import securityservices.core.components.shared.services.serializers.xmlapis.Dom;

public class SerializerCatalog {

    private static TreeMap<SerializerType, Serializer> catalog = new TreeMap<>();

    private static void loadCatalog() {

        catalog.put(SerializerType.XmlClient, new XmlClientSerializer(new Dom()));
        //lo he intentado hacer bien pero me ha dado muchos problemas al insertar el JaxbOrder en el catalogo de Serializadores
        //catalog.put(SerializerType.JaxbOrder, new JaxbOrderSerializer(new Dom()));
        try {
            Serializer s = JaxbClientSerializer.getInstance();
            catalog.put(SerializerType.JaxbClient, s);
        } catch (JAXBException ex) {
            //to logFile
        }
        try {
            Serializer x = JaxbOrderSerializer.getInstance();
            catalog.put(SerializerType.JaxbOrder, x);
        } catch (JAXBException ex) {
            //to logFile
        }

        catalog.put(SerializerType.JsonEquipment, new JsonEquipmentSerializer());
        catalog.put(SerializerType.JsonClient, new JsonClientSerializer());
        catalog.put(SerializerType.XmlEquipment, new XmlEquipmentSerializer(new Dom()));
    }

    public static Serializer getInstance(SerializerType type) {
        if (catalog.isEmpty()) {
            loadCatalog();
        }
        return catalog.get(type);
    }
}
