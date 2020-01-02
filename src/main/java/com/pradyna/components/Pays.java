/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.text.Normalizer;

/**
 *
 * @author pawel
 */
public class Pays {

    private static Pays monPays = null;

    private static String nom_pays;

    private String[] pays;

    public String[] getPays() {
        return pays;
    }

    public static String getNom_pays() {
        return nom_pays;
    }

    public static void setNom_pays(String nom_pays) {
        Pays.nom_pays = nom_pays;
    }

    private Pays() {
    }

    public static Pays getInstance() {
        if (Pays.monPays == null) {
            Pays.monPays = new Pays();
        }
        return Pays.monPays;
    }

    public void chargerInfoPays() {
        String contenuApi = Utilitaire.getApiString("https://restcountries.eu/rest/v2/name/" + getCleanedString(getNormalizerString(Pays.getNom_pays())));

        if (contenuApi != null) {
            JsonElement JsonElement = JsonParser.parseString(contenuApi);

            if (JsonElement.isJsonArray()) {
                JsonArray JsonArray = JsonElement.getAsJsonArray();

                String[] resultat = new String[3];
                for (int i = 0; i < JsonArray.size(); ++i) {
                    if (JsonArray.get(i).isJsonObject()) {
                        JsonObject JsonObject = JsonArray.get(i).getAsJsonObject();
                        String str_formatte = getCleanedString(getNormalizerString(Pays.getNom_pays()));

                        if (getCleanedString(getNormalizerString(JsonObject.get("name").getAsString())).contains(str_formatte) || getCleanedString(getNormalizerString(JsonObject.get("alpha2Code").getAsString())).contains(str_formatte) || getCleanedString(getNormalizerString(JsonObject.get("alpha3Code").getAsString())).contains(str_formatte)) {
                            resultat[0] = JsonObject.get("name").getAsString();

                            if (JsonObject.get("translations").isJsonObject()) {
                                JsonObject JsonObjectBis = JsonObject.get("translations").getAsJsonObject();
                                resultat[1] = JsonObjectBis.get("fr").getAsString();
                            }

                            resultat[2] = JsonObject.get("alpha2Code").getAsString();
                        }
                    }
                }
                pays = resultat;
            } else {
                pays = null;
            }
        } else {
            pays = null;
        }
    }

    private String getNormalizerString(String string) {
        return Normalizer
                .normalize(string, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    private String getCleanedString(String string) {
        StringBuilder tmp = new StringBuilder();
        char car;

        int i = 0;
        while (i < string.length()) {
            car = string.charAt(i);

            if (Character.isJavaIdentifierPart(car)
                    && Character.getNumericValue(car) >= 0) {
                tmp.append(car);
            } else {
                if (String.valueOf(car).equals(" ")) {
                    tmp.append("%20");
                } else {
                    tmp.append("_");
                }
            }
            i++;
        }

        return tmp.toString().toLowerCase();
    }

}
