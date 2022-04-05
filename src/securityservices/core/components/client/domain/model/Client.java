package securityservices.core.components.client.domain.model;

import java.time.format.DateTimeParseException;
import securityservices.core.components.shared.exception.BuildException;
import securityservices.core.components.shared.stakeholders.StakeHolder;
import securityservices.core.components.shared.stakeholders.Person;

public class Client extends Person implements StakeHolder {

    protected String password, clientCode;
    protected int numEquipments;

    public Client() {
    }

    public static Client getInstance(String name, String ident, String email, String phone, String address, String birthday,
            String password, String clientCode, int numEquipments) throws BuildException {

        String error = "";
        Client c = new Client();

        if (c.setName(name) == null) {
            error += "Bad Name;";
        }

        if (c.setIdent(ident) == null) {
            error += "Bad Ident;";
        }

        if (c.setEmail(email) == null) {
            error += "Bad Email;";
        }

        if (c.setPhone(phone) == null) {
            error += "Bad Phone;";
        }

        if (c.setAddress(address) == null) {
            error += "Bad Address;";
        }

        try {
            if (c.setBirthday(birthday) == null) {
                error += "Bad Birthday;";
            }
        } catch (DateTimeParseException e) {
            error += "Bad Birthday:" + e.getMessage();
        }

        if (c.setPassword(password) == null) {
            error += "Bad Password;";
        }

        if (c.setClientCode(clientCode) == null) {
            error += "Bad ClientCode;";
        }

        if (c.setNumEquipments(numEquipments) == -1) {
            error += "Bad Equipments number";
        }

        if (error.length() > 0) {
            c = null;
            throw new BuildException(error);
        }

        return c;
    }

    public Client(String name, String ident, String email, String phone, String address, String birthday,
            String password, String clientCode, int numEquipments) {
        super(name, ident, email, phone, address, birthday);
        this.password = password;
        this.clientCode = clientCode;
        this.numEquipments = numEquipments;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        if (password != null && password.trim().length() > 6) {
            this.password = password;
            return this.password;
        } else {
            return null;
        }
    }

    public String getClientCode() {
        return clientCode;
    }

    public String setClientCode(String clientCode) {
        if (clientCode == null) {
            return "";
        }
        this.clientCode = clientCode;
        return clientCode;
    }

    public int getNumEquipments() {
        return numEquipments;
    }

    public int setNumEquipments(int numEquipments) {
        if (numEquipments < 1) {
            return -1;
        }
        return this.numEquipments = numEquipments;
    }

    @Override
    public String getDetails() {
        return "clientcode:" + this.clientCode + ";equipments:" + this.numEquipments;
    }

    @Override
    public String getCode() {
        return this.ident;
    }

}
