package securityservices.core.components.shared.catalogs;

import java.util.ArrayList;
import securityservices.core.components.shared.products.Marketable;
import securityservices.core.components.shared.products.Storable;

public class ProductCatalog {

    private ArrayList<Marketable> catalog = new ArrayList();

    public boolean addCatalog(Marketable product) {
        if (product != null) {
            catalog.add(product);
            return true;
        }
        return false;

    }

    public String getSerializedProdCatalog(ProductCatalog catalog) {
        return "a";
    }

    public Marketable getMarketable(String ref) {

        for (Marketable product : catalog) {
            if (product.getCode().equals(ref)) {
                return product;
            }
        }
        return null;
    }

    // uso no extensible a otras partes del proyecto del binomio instanceof/cast
    public Storable getStorable(String ref) {

        if (getMarketable(ref) instanceof Storable) {
            return (Storable) getMarketable(ref);
        }
        return null;
    }
}
