/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package securityservices.core.components.shared.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ruben
 */
public class Stock extends Operation {

    protected int amount;
    protected Map<String, Integer> stock = new HashMap();

    public Stock() {
    }

    //Método extra (NO LO PIDE LA PRÁCTICA) pero me ayuda a hacer una comprobación en los datos
    public boolean strIntValue(String str) {
        //comprueba que en la string hay solo 3 números
        Pattern pattern = Pattern.compile("[0-9]{3}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            //significa que se trata de 3 números entre 0 y nueve
            System.out.println("el paterrn  \"" + str + "\"  es  valido ");
            return true;
        }
        System.out.println("el paterrn  \"" + str + "\" no es  valido ");
        return false;
    }

    public int updateStock(String ref, int amount) {
        System.out.println("Usted ha indicado actualizar el campo: \"" + ref + "\" con la cantidad de \"" + amount + "\"");
        if (strIntValue(ref) != true) {
            System.out.println("Comprueba los datos");
            return -1;
        }
        //si la referencia no existe la crea
        //no actualiza la cantidad directamente, si no que tiene en cuenta si agregamos o quitamos cantidad de stock (ejemplo: si tengo 10 le puedo pasar un -5 y me quedan 5)
        if (this.stock.get(ref) == null) {
            if (amount >= 1) {
                //producto no existe (esta función hace de setter)
                this.stock.put(ref, amount);
                System.out.println("Stock " + ref + " de " + amount + "uds. creado");
                return 1;
            } else {
                System.out.println("Comprueba la cantidad");
                return -1;
            }
        } else {
            //si no es igual a null solamente lo actualizamos
            //recogemos la cantidad que hay
            int oldAmount = this.stock.get(ref);
            //comprobamos que la cantidad nunca sea menor a 0
            if ((oldAmount + amount) > 0) {
                int newAmount = oldAmount + amount;
                this.stock.put(ref, newAmount);
                System.out.println("Se ha actualizado la cantidad de " + "Stock " + ref + " de " + amount);
                return 1;
            } else {
                System.out.println("La cantidad no puede ser menor a 0");
                return -1;
            }
        }

    }

    public int getAmount(String ref) {
        if (strIntValue(ref)) {
            if (this.stock.get(ref) != null) {
                return this.stock.get(ref);
            }
        }
        return 0;
    }

    public int delStock(String ref) {
        if (strIntValue(ref) != true) {
            System.out.println("La referencia no es correcta");
            return -1;
        }
        if (this.stock.get(ref) != null) {
            //la eliminamos
            this.stock.remove(ref);
            System.out.println(ref + " eliminado");
            return 1;
        } else {
            System.out.println("Esta referencia no existe");
            return 0;
        }
    }

    public String[] getLines() {
        String[] stringArray = new String[this.stock.size()];
        int c = 0;
        Iterator<Entry<String, Integer>> it = stock.entrySet().iterator();

        while (it.hasNext()) {
            Entry<String, Integer> e = it.next();
            stringArray[c] = configJsonStockl(e.getKey(), e.getValue());
            c++;
        }
        return stringArray;
    }

    public int getNumLines() {
        return stock.size();
    }

    public String configJsonStockl(String ref, int amount) {
        //Generamos un json en base al objeto OrderDetail
        String jsonString = "{\"ref\":\"" + ref + "\",\"amount\":\"" + amount + "\"}";
        return jsonString;
    }
}
