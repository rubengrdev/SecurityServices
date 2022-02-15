/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package securityservices.management.catalogs.serializers;

import java.util.ArrayList;
import securityservices.core.components.client.domain.model.Client;
import securityservices.core.components.shared.exception.BuildException;
import securityservices.core.components.shared.stakeholders.StakeHolder;

/**
 *
 * @author ruben
 */
public class GetStakeHolder {

    private static ArrayList<StakeHolder> catalog = new ArrayList();

    public static void addStakeHolder(StakeHolder sh) {
        catalog.add(sh);
    }

    private static void loadCatalog() throws BuildException {
        addStakeHolder(Client.getInstance("ruben", "4582948J", "ruben@mail.com", "123456789", "c/Barcelona", "05-11-1999", "DAJDAKDAKDKADKADK", "codeClient123", 2));

    }

    public static StakeHolder getStakeHolderCode(String ref) throws BuildException {
        loadCatalog();
        for (StakeHolder stakeh : catalog) {
            if (stakeh.getCode().equals(ref)) {
                return stakeh;
            }
        }
        return null;
    }
}
