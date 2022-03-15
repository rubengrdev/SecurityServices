package securityservices.core.components.client.appservices;

import securityservices.core.components.client.domain.services.ClientDTO;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Json;
import securityservices.core.components.shared.services.serializers.JsonObjectFactory;
import securityservices.core.components.shared.services.serializers.Serializer;

public class JsonClientSerializer implements Serializer {

    protected Json jClient = JsonObjectFactory.getInstance();

    public JsonClientSerializer() {
    }

    @Override
    public ClientDTO unserialize(String data) throws ServiceException {
        jClient.set(data);
        try {
//String name, String email, String birthday, String password, String phone, String ident, String address, int numEquipments, int clientCode) {            
            ClientDTO client = new ClientDTO(
                    jClient.get("name"),
                    jClient.get("ident"),
                    jClient.get("email"),
                    jClient.get("phone"),
                    jClient.get("address"),
                    jClient.get("birthday"),
                    jClient.get("password"),
                    jClient.get("clientCode"),
                    Integer.valueOf(jClient.get("numequipments"))
            );
            return client;

        } catch (Exception e) {
            throw new ServiceException("{\"Error\":\"ClientDTO unserialized Exception\","
                    + "\"Details\":\"" + e.toString() + "\"}");
        }
    }

    @Override
    public String serialize(Object client) {
        jClient.removeAll();
        jClient.set("ident", ((ClientDTO) client).getIdent());
        jClient.set("name", ((ClientDTO) client).getName());
        jClient.set("clientid", String.valueOf(((ClientDTO) client).getClientCode()));
        jClient.set("numequipments", String.valueOf(((ClientDTO) client).getNumEquipments()));
        jClient.set("email", ((ClientDTO) client).getEmail());
        jClient.set("phone", ((ClientDTO) client).getPhone());
        jClient.set("address", ((ClientDTO) client).getAddress());
        jClient.set("birthday", ((ClientDTO) client).getBirthday());
        jClient.set("password", ((ClientDTO) client).getPassword());
        return jClient.toString();
    }

}
