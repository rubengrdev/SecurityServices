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
import securityservices.core.components.shared.exception.BuildException;
import securityservices.core.components.shared.exception.ServiceException;
import securityservices.core.components.shared.services.serializers.Dom;
import securityservices.core.components.shared.services.serializers.Xml;

/**
 *
 * @author ruben
 */
public class ClientSerializerXml {

    public static void main(String[] args) throws BuildException, ServiceException {
        try {
            //OBJETOS PARA LA DEMO     
//String name, String ident, String email, String phone, String address, String birthday, String password, int clientCode, int numEquipments
            Client client1 = Client.getInstance("jose", "45454545X", "josem@gmail.cat", "666555444", "carrer kalea 1", "20-02-1997", "********", "1001", 3);
            ClientDTO cdto1 = ClientMapper.dtoFromClient(client1);
            Client auxClient;
            ClientDTO auxCdto;

            String newXmlClient = "<client>"
                    + "<name>newClientName</name>"
                    + "<email>josem@gmail.cat</email>"
                    + "<code>45454545X</code>"
                    + "<phone>666555444</phone>"
                    + "<address>carrer kalea 1</address>"
                    + "<birthday>20-02-1997</birthday>"
                    + "<password>********</password>"
                    + "<clientid>1001</clientid>"
                    + "<equipments>3</equipments>"
                    + "</client>";

            try {
                Dom dom = new Dom();
                XmlClientSerializer xcSerializer = new XmlClientSerializer(dom);
                String xmlClient = xcSerializer.serialize(cdto1);
                System.out.println(xmlClient);
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

                auxCdto = (ClientDTO) xcSerializer.unserialize(newXmlClient);
                auxClient = ClientMapper.clientFromDTO(auxCdto);
                System.out.println(auxClient.getName() + ";" + auxClient.getCode() + ";" + auxClient.getEmail() + ";" + auxClient.getPhone() + ";" + auxClient.getAddress() + ";" + auxClient.getDateOfBirth() + ";" + auxClient.getPassword() + ";" + auxClient.getClientCode() + ";" + auxClient.getNumEquipments());
            } catch (ServiceException ex) {
                System.out.println(ex);
            }
        } catch (BuildException ex) {
            System.out.println(ex);
        }
    }
}
