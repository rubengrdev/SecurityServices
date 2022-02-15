package securityservices.core.components.equipment.domain.services;

/**
 * @author ruben
 */
public class EquipmentDTO {

    private final String code, name, type, maker, description, function, components;
    private final double price, taxes, high, wide, deep, weight;
    private final boolean fragile;
    private final int power;

    public EquipmentDTO(String code, String name, String type, String maker, String description, double price, double taxes, double high, double wide, double deep, double weight, boolean fragile, String function, String components, Integer power) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.maker = maker;
        this.price = price;
        this.description = description;
        this.taxes = taxes;
        this.high = high;
        this.wide = wide;
        this.deep = deep;
        this.weight = weight;
        this.fragile = fragile;
        this.function = function;
        this.components = components;
        this.power = power;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMaker() {
        return maker;
    }

    public String getDescription() {
        return description;
    }

    public String getFunction() {
        return function;
    }

    public boolean getFragile() {
        return fragile;
    }

    public String getComponents() {
        return components;
    }

    public int getPower() {
        return power;
    }

    public double getTaxes() {
        return taxes;
    }

    public double getWeight() {
        return weight;
    }

    public double getDeep() {
        return deep;
    }

    public double getWide() {
        return wide;
    }

    public double getHigh() {
        return high;
    }

    public double getPrice() {
        return price;
    }

}
