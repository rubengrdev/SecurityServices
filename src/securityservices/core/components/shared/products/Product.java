package securityservices.core.components.shared.products;

public abstract class Product implements Marketable {

    protected String code, name, type, maker, description;
    protected double price, taxes;
    protected Boolean available;

    public Product() {
        this.available = true;
    }

    public Product(String code, String name, String type, String maker, String description,
            double price, double taxes) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.maker = maker;
        this.description = description;
        this.price = price;
        this.available = true;
        this.taxes = taxes;
    }

    public boolean setAvailable(Boolean available) {
        if (available != null) {
            this.available = available;
            return true;
        }
        return false;

    }

    public boolean setPrice(double price) {
        if (price >= 0) {
            this.price = price;
            return true;
        }
        return false;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    public Boolean isAvailable() {
        return available;
    }

    @Override
    public String getCode() {
        return code;
    }

    public boolean setCode(String code) {
        if (code != null && code.length() > 2 && code.length() < 10) {
            this.code = code;
            return true;
        }
        return false;
    }
@Override
    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (name == null) {
            return false;
        }
        this.name = name;
        return true;
    }

    public String getType() {
        return type;
    }

    public boolean setType(String type) {
        if (type != null) {
            this.type = type;
            return true;
        }
        return false;
    }

    public String getMaker() {
        return maker;
    }
    public Boolean setMaker(String maker) {
        if (maker != null) {
            this.maker = maker;
            return true;
        }
        return false;
    }

    public String getDescription() {
        return description;
    }

    public boolean setDescription(String description) {
        if (description != null && description.length() > 5) {
            this.description = description;
            return true;
        }
        return false;

    }

    @Override
    public double getTaxes() {
        return this.taxes;
    }

    public Boolean setTaxes(double taxes) {
        if (taxes >= 0) {
            this.taxes = taxes;
            return true;
        }
        return false;
    }

}
