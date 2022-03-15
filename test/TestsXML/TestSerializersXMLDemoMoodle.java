package TestsXML;

import javax.xml.bind.JAXBException;
import securityservices.core.components.client.appservices.JaxbClientSerializer;
import securityservices.core.components.client.appservices.XmlClientSerializer;
import securityservices.core.components.client.domain.model.Client;
import securityservices.core.components.client.domain.services.ClientDTO;
import securityservices.core.components.client.domain.services.ClientMapper;
import securityservices.core.components.shared.exception.BuildException;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Dom;
import securityservices.core.components.shared.services.serializers.Serializer;
import securityservices.core.components.shared.services.serializers.Xml;
import securityservices.management.catalogs.serializers.SerializerCatalog;
import securityservices.management.catalogs.serializers.SerializerType;

public class TestSerializersXMLDemoMoodle {

    public static void main(String[] args) {
        try {
            //OBJETOS PARA LA DEMO     
//Client(String name,String ident,String email,String phone,String address,String birthday,String password,int clientCode,int numEquipments)            
            Client client1 = Client.getInstance("jose", "45454545X", "josem@gmail.cat", "666555444", "carrer kalea 1", "20-02-1997", "********", "1001", 3);
            Client client2 = Client.getInstance("juan", "45464748X", "junam@gmail.cat", "666555555", "carrer kalea 3", "28-04-1987", "********", "1001", 1);
            Client client3 = Client.getInstance("jordi", "46474849X", "jordi@gmail.cat", "666555666", "carrer kalea 5", "10-06-1990", "********", "1001", 5);
            ClientDTO cdto1 = ClientMapper.dtoFromClient(client1);
            ClientDTO cdto2 = ClientMapper.dtoFromClient(client2);
            ClientDTO cdto3 = ClientMapper.dtoFromClient(client3);
            Client auxClient;
            ClientDTO auxCdto;

            String newJsonClient = "{"
                    + "  \"birthday\": \"22-08-1987\","
                    + "  \"ident\": \"45454545X\","
                    + "  \"clientid\": \"1008\","
                    + "  \"address\": \"carrer kalea 13\","
                    + "  \"phone\": \"666555888\","
                    + "  \"name\": \"alex\","
                    + "  \"numequipments\": \"4\","
                    + "  \"password\": \"xela1234\","
                    + "  \"email\": \"alexm@gmail.cat\""
                    + "}";

            //SERIALITZACIO JSON OBTENIR EL OBJECTE A TRAVES DEL CATALOG/DICCIONARI QUE ENS PROPORCIONA L'OBJECTE ADIENT
            Serializer clientSerializer;
            clientSerializer = SerializerCatalog.getInstance(SerializerType.JsonClient);

            try {
                String jsonClient = clientSerializer.serialize(cdto1);
                System.out.println(jsonClient);

                auxCdto = (ClientDTO) clientSerializer.unserialize(newJsonClient);
                auxClient = ClientMapper.clientFromDTO(auxCdto);
                System.out.println(auxClient.getName() + ":" + auxClient.getNumEquipments());
            } catch (ServiceException ex) {
                System.out.println(ex);
            }

            String xmlClient;

            //SERIALITZACIO XML AMB LA LLIBRERIA DOM (revisseu que aprofitem els components adients de la serialitzacio anterior)
            Xml xmlConverter = new Dom();
            clientSerializer = new XmlClientSerializer(xmlConverter);
            
            //SERIALITZACIO XML AMB LA LLIBRERIA DOM SENSE QUE EL CLIENT TINGUI CONSTANCIA GRACIES AL CATALOG
            //clientSerializer = SerializerCatalog.getInstance(SerializerType.XmlClient);
            try {
                xmlClient = clientSerializer.serialize(cdto2);
                System.out.println(xmlClient);

                //APROFITEM EL MATEIX STRING AMB EL XML PER SIMULAR LA ENTRADA D'UN DOCUMENT XML AL SISTEMA
                auxCdto = (ClientDTO) clientSerializer.unserialize(xmlClient);
                auxClient = ClientMapper.clientFromDTO(auxCdto);
                System.out.println(auxClient.getName() + ":" + auxClient.getNumEquipments());

            } catch (ServiceException | BuildException ex) {
                System.out.println(ex);
            }

            try {
                //SERIALITZACIO XML AMB LA LLIBRERIA JAXB (revisseu que aprofitem els components adients de la serialitzacio anterior)           
                clientSerializer = JaxbClientSerializer.getInstance();
                //SERIALITZACIO XML AMB JAXB (revisseu que aprofitem els components adients de la serialitzacio anterior)
                //clientSerializer = SerializerCatalog.getInstance(SerializerType.JaxbClient);
                xmlClient = clientSerializer.serialize(cdto3);
                System.out.println(xmlClient);
System.out.println("-----------------------------------------------------------------");
                auxCdto = (ClientDTO) clientSerializer.unserialize(xmlClient);
                auxClient = ClientMapper.clientFromDTO(auxCdto);
                System.out.println(auxClient.getName() + ":" + auxClient.getNumEquipments());

            } catch (ServiceException | BuildException | JAXBException ex) {
                System.out.println(ex);
            }

        } catch (BuildException ex) {
            System.out.println(ex);
        }
    }
}
