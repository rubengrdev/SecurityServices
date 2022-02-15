package securityservices;

import securityservices.core.components.client.appservices.JsonClientSerializer;
import securityservices.core.components.client.domain.model.Client;
import securityservices.core.components.client.domain.services.ClientDTO;
import securityservices.core.components.client.domain.services.ClientMapper;
import securityservices.core.components.shared.exception.BuildException;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Serializer;

public class TestSerializers {

    public static void main(String[] args) {
        try {
            //OBJETOS PARA LA DEMO     
//String name, String ident, String email, String phone, String address, String birthday, String password, int clientCode, int numEquipments
            Client client1 = Client.getInstance("jose", "45454545X", "josem@gmail.cat", "666555444", "carrer kalea 1", "20-02-1997", "********", "232332", 3);
            ClientDTO cdto1 = ClientMapper.dtoFromClient(client1);
            Client auxClient;
            ClientDTO auxCdto;
            
            //EEXEMPLE DE COM CREAR UN JSON AMB LES DADES D'UN CLIENT
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
            //Serializer jcSerializer = SerializerCatalog.getInstance(SerializerType.JsonClient);
            //SERIALITZACIO JSON AMB UNA LLIBRERIA ESTABLERTA PER DEFECTE
            JsonClientSerializer jcSerializer = new JsonClientSerializer();
            
            try {
                String jsonClient = jcSerializer.serialize(cdto1);
                System.out.println(jsonClient);
                
                auxCdto = (ClientDTO) jcSerializer.unserialize(newJsonClient);
                auxClient = ClientMapper.clientFromDTO(auxCdto);
                System.out.println(auxClient.getName() + ":" + auxClient.getNumEquipments());
            } catch (ServiceException ex) {
                System.out.println(ex);
            }
        } catch (BuildException ex) {
            System.out.println(ex);
        }
    }
}
