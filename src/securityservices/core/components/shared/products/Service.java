package securityservices.core.components.shared.products;

import securityservices.core.components.shared.exception.BuildException;

public class Service extends Product {

    protected String periodicity, conditions;

    public Service() {
    }

    public Service getInstance(String code, String name, String type, String maker, String description, double price,
            double taxes, String periodicity, String conditions) throws BuildException {
        String error = "";
        Service s = new Service();
        if (s.setCode(code) == false) {
            error += "Bad Code;";
        }

        if (s.setName(name) == false) {
            error += "Bad Name;";
        }

        if (s.setType(type) == false) {
            error += "Bad Type;";
        }
        if (s.setMaker(maker) == false) {
            error += "Bad Maker";
        }

        if (s.setDescription(description) == false) {
            error += "Bad Desription";
        }

        if (s.setPrice(price) == false) {
            error += "Bad Price;";
        }
        if (s.setTaxes(taxes) == false) {
            error += "Bad Taxes;";
        }
        if (s.setPrice(price) == false) {
            error += "Bad Price;";
        }
        if (s.setPeriodicity(periodicity) == false) {
            error += "Bad Periodicity;";
        }
        if (s.setConditions(conditions) == false) {
            error += "Bad Conditions;";
        }
        if (error.length() > 0) {
            s = null;
            throw new BuildException(error);
        }
        return s;
    }

    public Service(String code, String name, String type, String maker, String description, double price,
            double taxes, String periodicity, String conditions) {
        super(code, name, type, maker, description, price, taxes);
        this.periodicity = periodicity;
        this.conditions = conditions;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public boolean setPeriodicity(String periodicity) {
        if (periodicity == null) {
            return false;
        }
        this.periodicity = periodicity;
        return true;
    }

    public String getConditions() {
        return conditions;
    }

    public boolean setConditions(String conditions) {
        if (conditions == null) {
            return false;
        }
        this.conditions = conditions;
        return true;
    }

    @Override
    public String getDetails() {
        return "Periodicity:" + this.periodicity
                + ";Conditions:" + this.conditions;
    }
}
