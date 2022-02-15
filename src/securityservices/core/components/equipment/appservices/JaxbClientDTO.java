package securityservices.core.components.equipment.appservices;

import securityservices.core.components.client.appservices.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/*
    DTO generado para adaptarnos a la magia de JAXB
 */
@XmlRootElement(name = "client")
public class JaxbClientDTO {

    private final String name, ident, email, birthday, phone, address, password, clientCode;
    private final int numEquipments;
    

//String name, String email, String birthday, String password, String phone, String ident, String address, int numEquipments, int clientCode) {    
   public JaxbClientDTO(String name, String ident, String email, String phone, String address, String birthday,
            String password, String clientCode, int numEquipments) {
        this.name = name;
        this.ident = ident;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.clientCode = clientCode;
        this.numEquipments = numEquipments;
    }
    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "dni")
    public String getIdent() {
        return ident;
    }

    @XmlElement(name = "address")
    public String getAddress() {
        return address;
    }

    @XmlElement(name = "phone")
    public String getPhone() {
        return phone;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }
    
    @XmlElement(name = "birthday")
    public String getBirthday() {
        return birthday;
    }

    @XmlElement(name = "clientCode")
    public String getClientCode() {
        return clientCode;
    }

    @XmlElement(name = "numequipments")
    public int getNumEquipments() {
        return numEquipments;
    }

    @XmlElement(name = "password")
    public String getPassword() {
        return password;
    }
    
}
