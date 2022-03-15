package securityservices.core.components.shared.catalogs;

import java.util.ArrayList;
import securityservices.core.components.equipment.domain.model.Equipment;
import securityservices.core.components.shared.products.Marketable;
import securityservices.core.components.shared.products.Service;
import securityservices.core.components.shared.products.Software;
import securityservices.core.components.shared.products.Storable;

public class ProductCatalog {

    private ArrayList<Marketable> catalog = new ArrayList();

    public boolean addCatalog(Marketable product) {
        this.loadCatalog();
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
        this.loadCatalog();
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

    private void loadCatalog() {
//Service(String code, String name, String type, String maker, String description, double price, double taxes, String periodicity, String conditions)  
        catalog.add(new Service("001", "Mantenimiento", "Informatico", "SS", "desc", 1000.0, 0.0, "mensual", "none"));
        catalog.add(new Service("002", "Reparacion", "Informatico", "SS", "desc", 200.0, 0.0, "semanal", "none"));
        catalog.add(new Service("003", "Seguridad", "Informatico", "SS", "desc", 2000.0, 0.0, "anual", "none"));
//Software(String code, String name, String type, String maker, String description, double price, double taxes, String version, String os, String medium)        
        catalog.add(new Software("010", "Antivirus", "Informatico", "SS", "desc", 29.0, 0.0, "1.7", "linux", "link"));
        catalog.add(new Software("011", "Firewall", "Informatico", "SS", "desc", 49.0, 0.0, "3.3", "windows", "link"));
        catalog.add(new Software("012", "Proxy", "Informatico", "SS", "desc", 30.0, 0.0, "2.1", "ios", "link"));
//Equipment(String code, String name, String type, String maker, String description, Double price, Double taxes, Double high, Double wide, Double deep, Double weight, Boolean fragile, String function, String components, Integer power) 
        catalog.add(new Equipment("020", "Server", "Informatico", "SS", "desc", 3000.0, 0.0, 0.8, 0.6, 0.45, 7.1, true, "dataservices", "hw list", 800));
        catalog.add(new Equipment("021", "NetworkAnalyzer", "Informatico", "SS", "desc", 1000.0, 0.0, 0.8, 0.6, 0.45, 4.5, true, "Segurity", "hw list", 600));
        catalog.add(new Equipment("022", "Firewall", "Informatico", "SS", "desc", 30.0, 600.0, 0.8, 0.6, 0.45, 3.8, true, "firewall", "hw list", 400));

    }
}
