/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Arrays;
import java.util.Locale;

/**
 *
 * @author Ali Abdoulkader Ali
 */
public class Country implements Comparable<Country> {
    private String code;
    private String name;
 
    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    @Override
    public String toString() {
        return this.name;
    }
 
    @Override
    public int compareTo(Country anotherCountry) {
        return this.name.compareTo(anotherCountry.getName());
    }
    
    public static Country[] createCountryList() {
    String[] countryCodes = Locale.getISOCountries();
    Country[] listCountry = new Country[countryCodes.length];
 
    for (int i = 0; i < countryCodes.length; i++) {
        Locale locale = new Locale("", countryCodes[i]);
        String code = locale.getCountry();
        String name = locale.getDisplayCountry();
 
        listCountry[i] = new Country(code, name);
    }
 
    Arrays.sort(listCountry);
 
    return listCountry;
}
}
