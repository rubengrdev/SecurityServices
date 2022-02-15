/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package securityservices.management.catalogs.serializers;

import securityservices.core.components.equipment.domain.model.Equipment;
import securityservices.core.components.shared.catalogs.ProductCatalog;
import securityservices.core.components.shared.products.Service;
import securityservices.core.components.shared.products.Software;

/**
 *
 * @author ruben 
 */
public class GetCatalogProduct {

    static ProductCatalog prodcatalog = new ProductCatalog();

    private static void loadCatalog() {
//Service(String code, String name, String type, String maker, String description, double price, double taxes, String periodicity, String conditions)  
        prodcatalog.addCatalog(new Service ("001", "Mantenimiento", "Informatico", "SS", "desc", 1000.0, 0.0, "mensual", "none"));
        prodcatalog.addCatalog(new Service("002", "Reparacion", "Informatico", "SS", "desc", 200.0, 0.0, "semanal", "none"));
        prodcatalog.addCatalog(new Service("003", "Seguridad", "Informatico", "SS", "desc", 2000.0, 0.0, "anual", "none"));
//Software(String code, String name, String type, String maker, String description, double price, double taxes, String version, String os, String medium)        
        prodcatalog.addCatalog(new Software("010", "Antivirus", "Informatico", "SS", "desc", 29.0, 0.0, "1.7", "linux", "link"));
        prodcatalog.addCatalog(new Software("011", "Firewall", "Informatico", "SS", "desc", 49.0, 0.0, "3.3", "windows", "link"));
        prodcatalog.addCatalog(new Software("012", "Proxy", "Informatico", "SS", "desc", 30.0, 0.0, "2.1", "ios", "link"));
//Equipment(String code, String name, String type, String maker, String description, Double price, Double taxes, Double high, Double wide, Double deep, Double weight, Boolean fragile, String function, String components, Integer power) 
        prodcatalog.addCatalog(new Equipment("020", "Server", "Informatico", "SS", "desc", 3000.0, 0.0, 0.8, 0.6, 0.45, 7.1, true, "dataservices", "hw list", 800));
        prodcatalog.addCatalog(new Equipment("021", "NetworkAnalyzer", "Informatico", "SS", "desc", 1000.0, 0.0, 0.8, 0.6, 0.45, 4.5, true, "Segurity", "hw list", 600));
        prodcatalog.addCatalog(new Equipment("022", "Firewall", "Informatico", "SS", "desc", 30.0, 600.0, 0.8, 0.6, 0.45, 3.8, true, "firewall", "hw list", 400));

    }

    public static ProductCatalog getInstance() {
        if (prodcatalog == null) {
            prodcatalog = new ProductCatalog();
            loadCatalog();
        }
        return prodcatalog;
    }
}
