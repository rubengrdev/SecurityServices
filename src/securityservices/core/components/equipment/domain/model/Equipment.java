package securityservices.core.components.equipment.domain.model;

import securityservices.core.components.shared.exception.BuildException;
import securityservices.core.components.shared.products.PhysicalProduct;
import securityservices.core.components.shared.products.Storable;

public class Equipment extends PhysicalProduct implements Storable {

    protected String function, components;
    protected Integer power;

    public static Equipment getInstance(String code, String name, String type, String maker, String description, double price, double taxes, double high, double wide, double deep, double weight, boolean fragile,
            String function, String components, Integer power) throws BuildException {

        String error = "";
        Equipment e = new Equipment();
        if (e.setCode(code) == false) {
            error += "Bad Code;";
        }

        if (e.setName(name) == false) {
            error += "Bad Name;";
        }

        if (e.setType(type) == false) {
            error += "Bad Type;";
        }
        if (e.setMaker(maker) == false) {
            error += "Bad Maker";
        }
         
        if (e.setDescription(description) == false) {
            error += "Bad Desription";
        }

        if (e.setPrice(price) == false) {
            error += "Bad Price;";
        }

        if (e.setHigh(high) == false) {
            error += "Bad High;";
        }

        if (e.setWide(wide) == false) {
            error += "Bad Wide;";
        }

        if (e.setDeep(deep) == false) {
            error += "Bad Deep;";
        }

        if (e.setWeight(weight) == false) {
            error += "Bad Weight;";
        }

        if (e.setFragile(fragile) == null) {
            error += "Bad Fragile;";
        }

        if (e.setFunction(function) == null) {
            error += "Bad Function;";
        }

        if (e.setComponents(components) == null) {
            error += "Bad Components;";
        }

        if (e.setPower(power) == -1) {
            error += "Bad Power;";
        }

        if (error.length() > 0) {
            e = null;
            throw new BuildException(error);
        }

        return e;
    }

    public Equipment() {
    }

    public Equipment(String code, String name, String type, String maker, String description,
            Double price, Double taxes, Double high, Double wide, Double deep, Double weight, Boolean fragile,
            String function, String components, Integer power) {
        super(code, name, type, maker, description, price, taxes, high, wide, deep, weight, fragile);
        this.function = function;
        this.components = components;
        this.power = power;
    }

    public String getFunction() {
        return function;
    }

    public String setFunction(String function) {
        if (function == null || function.trim().length() < 3) {
            return null;
        }
        return this.function = function;
    }

    @Override
    public String getName() {
        return name;
    }

    public Integer getPower() {
        return power;
    }

    public boolean getFragile() {
        return this.physics.getFragile();
    }

    public int setPower(Integer power) {
        if (power <= 0) {
            return -1;
        }
        return this.power = power;
    }

    public String getComponents() {
        return components;
    }

    public String setComponents(String components) {
        if (components == null || components.trim().length() < 3) {
            return null;
        }
        return this.components = components;
    }

    @Override
    public String getDetails() {
        return "Function:" + function + ";Components:" + this.components;
    }

    @Override
    public String getDimensions() {
        return "W:" + this.physics.getWide() + ";D:" + this.physics.getDeep() + ";H:" + this.physics.getHigh();
    }

    @Override
    public Double getVolum() {
        return this.physics.getHigh() * this.physics.getWide() * this.physics.getDeep();
    }

    @Override
    public String getCode() {
        return code;
    }

}
