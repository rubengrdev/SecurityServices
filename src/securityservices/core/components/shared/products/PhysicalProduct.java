package securityservices.core.components.shared.products;

import securityservices.core.components.shared.physics.PhysicalData;

public abstract class PhysicalProduct extends Product implements Storable{
    
    protected PhysicalData physics;

    public PhysicalProduct() {
        this.physics = new PhysicalData();
    }

    public PhysicalProduct(String code, String name, String type, String maker, String description, Double price, 
            double taxes, Double high, Double wide, Double deep, Double weight, Boolean fragile) {
        super(code, name, type, maker, description, price, taxes);

        physics = new PhysicalData( high, wide, deep, weight, fragile);
    }

 public Double getHigh() {
        return this.physics.getHigh();
    }

    public Boolean setHigh(Double high) {
        if (high > 0) {
            this.physics.setHigh(high);
            return true;
        }
        return false;
    }

    public Double getWide() {
        return this.physics.getWide();
    }

    public Boolean setWide(Double wide) {
        if (wide > 0) {
            this.physics.setWide(wide);
            return true;
        }
        return false;

    }

    public Double getDeep() {
        return this.physics.getDeep();
    }

    public Boolean setDeep(Double deep) {
        if (deep > 0) {
            this.physics.setDeep(deep);
            return true;
        }
        return false;
    }

    @Override
    public Double getWeight() {
        return this.physics.getWeight();
    }

    public Boolean setWeight(Double weight) {
        if (weight > 0) {
            this.physics.setWeight(weight);
            return true;
        }
        return false;

    }

    @Override
    public Boolean isFragile() {
        return this.physics.getFragile();
    }

    public Boolean setFragile(Boolean fragile) {
        if (fragile != null) {
            this.physics.setFragile(fragile);
            return true;
        }
        return false;
    }

}