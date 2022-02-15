package securityservices.core.components.equipment.appservices;


import securityservices.core.components.equipment.domain.services.EquipmentDTO;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Json;
import securityservices.core.components.shared.services.serializers.JsonObjectFactory;
import securityservices.core.components.shared.services.serializers.Serializer;

public class JsonEquipmentSerializer implements Serializer {

    protected Json jEquipment = JsonObjectFactory.getInstance();

    public JsonEquipmentSerializer() {
    }

    @Override
    public EquipmentDTO unserialize(String data) throws ServiceException {
        jEquipment.set(data);
        try {
      EquipmentDTO equipment = new EquipmentDTO(
                    jEquipment.get("code"),
                    jEquipment.get("name"),
                    jEquipment.get("type"),
                    jEquipment.get("maker"),
                    jEquipment.get("description"),
                    Double.valueOf(jEquipment.get("price")),
                    Double.valueOf(jEquipment.get("taxes")),
                    Double.valueOf(jEquipment.get("high")),
                    Double.valueOf(jEquipment.get("wide")),
                    Double.valueOf(jEquipment.get("deep")),
                    Double.valueOf(jEquipment.get("weight")),
                    Boolean.valueOf(jEquipment.get("fragile")),
                    jEquipment.get("function"),
                    jEquipment.get("components"),
                    Integer.valueOf(jEquipment.get("power"))
            );
            return equipment;

        } catch (Exception e) {
            throw new ServiceException("{\"Error\":\"EquipmentDTO unserialized Exception\","
                    + "\"Details\":\"" + e.toString() + "\"}");
        }
    }

    @Override
    public String serialize(Object equipment) {
        jEquipment.removeAll();
        jEquipment.set("code", ((EquipmentDTO) equipment).getCode());
        jEquipment.set("name", ((EquipmentDTO) equipment).getName());
        jEquipment.set("type", ((EquipmentDTO) equipment).getType());
        jEquipment.set("maker", ((EquipmentDTO) equipment).getMaker());
        jEquipment.set("description", ((EquipmentDTO) equipment).getDescription());
        jEquipment.set("price", String.valueOf(((EquipmentDTO) equipment).getPrice()));
jEquipment.set("taxes", String.valueOf(((EquipmentDTO) equipment).getTaxes()));
        jEquipment.set("high", String.valueOf(((EquipmentDTO) equipment).getHigh()));
        jEquipment.set("wide", String.valueOf(((EquipmentDTO) equipment).getWide()));
        jEquipment.set("deep", String.valueOf(((EquipmentDTO) equipment).getDeep()));
        jEquipment.set("weight", String.valueOf(((EquipmentDTO) equipment).getWeight()));
        jEquipment.set("fragile", String.valueOf(((EquipmentDTO) equipment).getFragile()));
        jEquipment.set("function", ((EquipmentDTO) equipment).getFunction());
        jEquipment.set("components", ((EquipmentDTO) equipment).getComponents());
        jEquipment.set("power", String.valueOf(((EquipmentDTO) equipment).getPower()));
        return jEquipment.toString();
    }

}
