//birthdat con formato de fecha distinto
package securityservices.core.components.shared.stakeholders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Person {

    protected String name, ident, email, phone, address;
    protected LocalDate birthday;
    protected DateTimeFormatter birthdayFormatter = DateTimeFormatter.ofPattern("dd'-'MM'-'yyyy");

    public Person() {
    }

    public Person(String name, String code, String email, String phone, String address, String birthday) {
        this.name = name;
        this.ident = code;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.setBirthday(birthday);
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        if (name == null || name.trim().length() <= 1) {
            return null;
            }
        return this.name = name;
    }
    

    public String getIdent() {
        return ident;
    }

    public String setIdent(String ident) {
        if (ident == null || ident.trim().length() < 8) {
            return null;
            }
        return this.ident = ident;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        if (email == null || email.trim().length() < 8) {
            return null;
            }
        return this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String setPhone(String phone) {
        if (phone == null || phone.trim().length() < 9) {
            return null;
            }
        return this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String setAddress(String address) {
        if (address == null || address.trim().length() < 6) {
            return null;
            }
        return this.address = address;
    }

    public String getDateOfBirth() {
        if (this.birthday != null) {
            return this.birthday.format(this.birthdayFormatter);
        }
        return "";
    }

    public String setBirthday(String birthday) throws DateTimeParseException {
        
        if (birthday != null && birthday.trim().length() > 7) {
            this.birthday = LocalDate.parse(birthday, this.birthdayFormatter);
            return birthday;
        } else {
            return null;
        }
    }

    public int yearsOld() {
        LocalDate today = LocalDate.now();
        return (today.minusYears(this.birthday.getYear())).getYear();
    }
}
