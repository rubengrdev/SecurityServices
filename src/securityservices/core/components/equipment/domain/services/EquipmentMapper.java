/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package securityservices.core.components.equipment.domain.services;

import securityservices.core.components.client.domain.model.Client;
import securityservices.core.components.client.domain.services.ClientDTO;
import securityservices.core.components.equipment.domain.services.EquipmentDTO;
import securityservices.core.components.equipment.domain.model.Equipment;
import securityservices.core.components.shared.exception.BuildException;

/**
 * @author ruben
 */
public class EquipmentMapper {

    public static Equipment equipmentFromDTO(EquipmentDTO edto) throws BuildException {
        return Equipment.getInstance(
                edto.getCode(),
                edto.getName(),
                edto.getType(),
                edto.getMaker(),
                edto.getDescription(),
                edto.getPrice(),
                edto.getTaxes(),
                edto.getHigh(),
                edto.getWide(),
                edto.getDeep(),
                edto.getWeight(),
                edto.getFragile(),
                edto.getFunction(),
                edto.getComponents(),
                edto.getPower()
        );
    }

    public static EquipmentDTO dtoFromEquipment(Equipment e) {
        return new EquipmentDTO(
                e.getCode(),
                e.getName(),
                e.getType(),
                e.getMaker(),
                e.getDescription(),
                e.getPrice(),
                e.getTaxes(),
                e.getHigh(),
                e.getWide(),
                e.getDeep(),
                e.getWeight(),
                e.getFragile(),
                e.getFunction(),
                e.getComponents(),
                e.getPower()
        );
    }
}
