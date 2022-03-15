/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestsJSON;

import securityservices.core.components.equipment.appservices.JsonEquipmentSerializer;
import securityservices.core.components.equipment.domain.model.Equipment;
import securityservices.core.components.equipment.domain.services.EquipmentDTO;
import securityservices.core.components.equipment.domain.services.EquipmentMapper;
import securityservices.core.components.shared.exception.BuildException;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Serializer;
import securityservices.management.catalogs.serializers.SerializerCatalog;
import securityservices.management.catalogs.serializers.SerializerType;

/**
 *
 * @author ruben
 */
public class EquipmentSerializer {

    public static void main(String[] args) throws BuildException, ServiceException {
        try {
//Creamos una instancia de la clase Equipment
            Equipment equipment1 = Equipment.getInstance("R2D2", "RobotR2D2", "robot mecanico?", "start wars", "esto es una desc", 999, 21, 120, 50, 50, 130, true, "hacer bip bip", "metal y cosas de esas", 200);
            EquipmentDTO edto1 = EquipmentMapper.dtoFromEquipment(equipment1);
            Equipment equipment2 = Equipment.getInstance("SRV12", "Mantenimiento Redes", "Mantenimiento de redes colgadas?", "sservice", "Descirpción de mantenimiento de redes", 10, 2, 3, 2, 1, 23, true, "maintinance", "redes", 2);
            EquipmentDTO edto2 = EquipmentMapper.dtoFromEquipment(equipment2);
            Equipment auxEquipment;
            EquipmentDTO auxEdto;

//eXEMPLE DE CREAR UN EQUIPMENT AMB UN JSON
            String newJsonEquipment = "{"
                    + "\"deep\": \"50.0\","
                    + " \"components\": \"metal y cosas de esas\","
                    + "\"code\": \"R2D2\","
                    + "\"wide\": \"50.0\","
                    + "\"maker\": \"start wars\","
                    + "\"description\": \"esto es una desc\","
                    + "\"weight\": \"130.0\","
                    + "\"type\": \"robot mecanico?\","
                    + "\"high\": \"120.0\","
                    + "\"price\": \"999.0\","
                    + "\"function\": \"hacer bip bip\","
                    + "\"name\": \"RobotR2D2\","
                    + "\"fragile\": \"true\","
                    + "\"taxes\": \"0.2\","
                    + "\"power\": \"200\""
                    + "}";
//eXEMPLE DE CREAR UN EQUIPMENT AMB UN JSON
            String newJsonEquipment2 = "{"
                    + "\"deep\": \"1.0\","
                    + " \"components\": \"redes\","
                    + "\"code\": \"SRV12\","
                    + "\"wide\": \"2.0\","
                    + "\"maker\": \"sservice\","
                    + "\"description\": \"Descirpción de mantenimiento de redes\","
                    + "\"weight\": \"23.0\","
                    + "\"type\": \"Mantenimiento de redes colgadas?\","
                    + "\"high\": \"3.0\","
                    + "\"price\": \"10.0\","
                    + "\"function\": \"maintinance\","
                    + "\"name\": \"Mantenimiento Redes\","
                    + "\"fragile\": \"true\","
                    + "\"taxes\": \"0.0\","
                    + "\"power\": \"2\""
                    + "}";
            JsonEquipmentSerializer jeSerializer = new JsonEquipmentSerializer();

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

            try {
                String jsonEquipment = jeSerializer.serialize(edto1);
                String jsonEquipment2 = jeSerializer.serialize(edto2);
                System.out.println(jsonEquipment);
                System.out.println(jsonEquipment2);
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                auxEdto = (EquipmentDTO) jeSerializer.unserialize(newJsonEquipment);
                auxEquipment = EquipmentMapper.equipmentFromDTO(auxEdto);
                System.out.println(auxEquipment.getCode() + ":" + auxEquipment.getName() + ":" + auxEquipment.getType() + ":" + auxEquipment.getMaker() + ":"
                        + auxEquipment.getDescription() + ":" + auxEquipment.getPrice() + ":" + auxEquipment.getTaxes() + ":" + auxEquipment.getHigh() + ":"
                        + auxEquipment.getWide() + ":" + auxEquipment.getDeep() + ":" + auxEquipment.getWeight() + ":" + auxEquipment.getFragile() + ":"
                        + auxEquipment.getFunction() + ":" + auxEquipment.getComponents() + ":" + auxEquipment.getPower());
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                auxEdto = (EquipmentDTO) jeSerializer.unserialize(newJsonEquipment2);
                auxEquipment = EquipmentMapper.equipmentFromDTO(auxEdto);
                System.out.println(auxEquipment.getCode() + ":" + auxEquipment.getName() + ":" + auxEquipment.getType() + ":" + auxEquipment.getMaker() + ":"
                        + auxEquipment.getDescription() + ":" + auxEquipment.getPrice() + ":" + auxEquipment.getTaxes() + ":" + auxEquipment.getHigh() + ":"
                        + auxEquipment.getWide() + ":" + auxEquipment.getDeep() + ":" + auxEquipment.getWeight() + ":" + auxEquipment.getFragile() + ":"
                        + auxEquipment.getFunction() + ":" + auxEquipment.getComponents() + ":" + auxEquipment.getPower());
             

            } catch (ServiceException ex) {
                System.out.println(ex);
            }

        } catch (BuildException ex) {
            System.out.println(ex);
        }

    }
}
