/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestsXML;

import org.json.XML;
import securityservices.core.components.client.appservices.XmlClientSerializer;
import securityservices.core.components.client.domain.model.Client;
import securityservices.core.components.client.domain.services.ClientDTO;
import securityservices.core.components.client.domain.services.ClientMapper;
import securityservices.core.components.equipment.appservices.XmlEquipmentSerializer;
import securityservices.core.components.equipment.domain.model.Equipment;
import securityservices.core.components.equipment.domain.services.EquipmentDTO;
import securityservices.core.components.equipment.domain.services.EquipmentMapper;
import securityservices.core.components.shared.exception.BuildException;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Dom;
import securityservices.core.components.shared.services.serializers.Serializer;
import securityservices.core.components.shared.services.serializers.Xml;
import securityservices.management.catalogs.serializers.SerializerCatalog;
import securityservices.management.catalogs.serializers.SerializerType;

/**
 *
 * @author ruben
 */
public class EquipmentSerializerXml {

    public static void main(String[] args) throws BuildException, ServiceException {
        try {
//Creamos una instancia de la clase Equipment
            Equipment equipment1 = Equipment.getInstance("R2D2", "RobotR2D2", "robot mecanico?", "start wars", "esto es una desc", 999, 21, 120, 50, 50, 130, true, "hacer bip bip", "metal y cosas de esas", 200);
            EquipmentDTO edto1 = EquipmentMapper.dtoFromEquipment(equipment1);
            Equipment equipment2 = Equipment.getInstance("SRV12", "Mantenimiento Redes", "Mantenimiento de redes colgadas?", "sservice", "Descirpción de mantenimiento de redes", 10, 2, 3, 2, 1, 23, true, "maintinance", "redes", 2);
            EquipmentDTO edto2 = EquipmentMapper.dtoFromEquipment(equipment2);
            Equipment auxEquipment;
            EquipmentDTO auxEdto;

            String newXmlEquipment = "<equipment>"
                    + "<code>C3PO</code>"
                    + "<name>RobotC3PO</name>"
                    + "<type>robot mecanico que no hace nada?</type>"
                    + "<maker>start wars 2</maker>"
                    + "<price>25.99</price>"
                    + "<description>esto es una desc de el XML Equipment</description>"
                    + "<taxes>12</taxes>"
                    + "<high>20.0</high>"
                    + "<wide>10.0</wide>"
                    + "<deep>2.0</deep>"
                    + "<weight>20.0</weight>"
                    + "<fragile>false</fragile>"
                    + "<function>hacer bip bip y cosas</function>"
                    + "<components>metal y cosas de esas, amigo de R2D2</components>"
                    + "<power>10</power>"
                    + "</equipment>";

            String newXmlEquipment2 = "<equipment>"
                    + "<code>W1117</code>"
                    + "<name>RobotWilly</name>"
                    + "<type>robot mecanico que hace lo mismo que yo en java: cosas</type>"
                    + "<maker>robot wolly</maker>"
                    + "<price>1.99</price>"
                    + "<description>esto es una desc de el XML Equipment</description>"
                    + "<taxes>1</taxes>"
                    + "<high>20.0</high>"
                    + "<wide>10.0</wide>"
                    + "<deep>2.0</deep>"
                    + "<weight>20.0</weight>"
                    + "<fragile>false</fragile>"
                    + "<function>hacer bip bip y cosas</function>"
                    + "<components>metal y cosas de esas super robot wolly</components>"
                    + "<power>999</power>"
                    + "</equipment>";

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

            try {
                Dom dom = new Dom();
                XmlEquipmentSerializer xeSerializer = new XmlEquipmentSerializer(dom);
                String xmlClient = xeSerializer.serialize(edto1);
                System.out.println(xmlClient);
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                xmlClient = xeSerializer.serialize(edto2);
                System.out.println(xmlClient);
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

                auxEdto = (EquipmentDTO) xeSerializer.unserialize(newXmlEquipment);
                auxEquipment = EquipmentMapper.equipmentFromDTO(auxEdto);
                System.out.println(auxEquipment.getCode() + ";" + auxEquipment.getName() + ";" + auxEquipment.getType() + ";" + auxEquipment.getMaker() + ";" + auxEquipment.getDescription() + ";" + auxEquipment.getPrice() + ";" + auxEquipment.getTaxes() + ";" + auxEquipment.getHigh() + ";" + auxEquipment.getWide() + ";" + auxEquipment.getDeep() + ";" + auxEquipment.getWeight() + ";" + auxEquipment.getFragile() + ";" + auxEquipment.getFunction() + ";" + auxEquipment.getComponents() + ";" + auxEquipment.getPower());
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

                auxEdto = (EquipmentDTO) xeSerializer.unserialize(newXmlEquipment2);
                auxEquipment = EquipmentMapper.equipmentFromDTO(auxEdto);
                System.out.println(auxEquipment.getCode() + ";" + auxEquipment.getName() + ";" + auxEquipment.getType() + ";" + auxEquipment.getMaker() + ";" + auxEquipment.getDescription() + ";" + auxEquipment.getPrice() + ";" + auxEquipment.getTaxes() + ";" + auxEquipment.getHigh() + ";" + auxEquipment.getWide() + ";" + auxEquipment.getDeep() + ";" + auxEquipment.getWeight() + ";" + auxEquipment.getFragile() + ";" + auxEquipment.getFunction() + ";" + auxEquipment.getComponents() + ";" + auxEquipment.getPower());

                try {
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("Usando el Catalogo: Serializer.XmlEquipment");
                    Serializer equipmentSerializer = SerializerCatalog.getInstance(SerializerType.XmlEquipment);
                    String xmlEquipment = equipmentSerializer.serialize(edto1);
                    System.out.println(xmlEquipment);
//unserialize
                    System.out.println("Unserialize de SerializerType.XMLEquipment");
                    auxEdto = (EquipmentDTO) equipmentSerializer.unserialize(newXmlEquipment2);
                    auxEquipment = EquipmentMapper.equipmentFromDTO(auxEdto);
                    System.out.println(auxEquipment.getCode() + ";" + auxEquipment.getName() + ";" + auxEquipment.getType() + ";" + auxEquipment.getMaker() + ";" + auxEquipment.getDescription() + ";" + auxEquipment.getPrice() + ";" + auxEquipment.getTaxes() + ";" + auxEquipment.getHigh() + ";" + auxEquipment.getWide() + ";" + auxEquipment.getDeep() + ";" + auxEquipment.getWeight() + ";" + auxEquipment.getFragile() + ";" + auxEquipment.getFunction() + ";" + auxEquipment.getComponents() + ";" + auxEquipment.getPower());

                } catch (ServiceException ex) {
                    System.out.println(ex);
                }

                try {
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("Usando el Catalogo: Serializer.JSONEquipment");
                    Serializer equipmentSerializer = SerializerCatalog.getInstance(SerializerType.JsonEquipment);
                    String xmlEquipment = equipmentSerializer.serialize(edto1);
                    System.out.println(xmlEquipment);
                } catch (ServiceException ex) {
                    System.out.println(ex);
                }

            } catch (ServiceException ex) {
                System.out.println(ex);
            }
        } catch (BuildException ex) {
            System.out.println(ex);
        }
    }
}
