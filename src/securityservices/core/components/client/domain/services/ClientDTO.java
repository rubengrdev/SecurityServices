package securityservices.core.components.client.domain.services;

public class ClientDTO {

    private final String name, ident, email, birthday, phone, address, password, clientCode;
    private final int  numEquipments;

    public ClientDTO(String name, String ident, String email, String phone, String address, String birthday,
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

    public String getName() {
        return name;
    }

    public String getident() {
        return ident;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getNumEquipments() {
        return numEquipments;
    }

    public String getIdent() {
     return ident;
    }

    public String getClientCode() {
        return clientCode;
    }

    public String getPhone() {
       return phone;
    }

    public String getAddress() {
    return address;
    }

    public String getPassword() {
       return password;
    }
}
