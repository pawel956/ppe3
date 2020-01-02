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
public class Ville {

    private static Ville maVille = null;

    private static String abreviation_pays;
    private static String code_postal;

    private String[] villes;

    public String[] getVilles() {
        return villes;
    }

    public static String getAbreviation_pays() {
        return abreviation_pays;
    }

    public static void setAbreviation_pays(String abreviation_pays) {
        Ville.abreviation_pays = abreviation_pays;
    }

    public static String getCode_postal() {
        return code_postal;
    }

    public static void setCode_postal(String code_postal) {
        Ville.code_postal = code_postal;
    }

    private Ville() {
    }

    public static Ville getInstance() {
        if (Ville.maVille == null) {
            Ville.maVille = new Ville();
        }
        return Ville.maVille;
    }

    public void chargerInfoVille() {
        if (Ville.getAbreviation_pays() != null && Ville.getCode_postal() != null) {
            String contenuApi = Utilitaire.getApiString("http://api.zippopotam.us/" + Ville.getAbreviation_pays() + "/" + Ville.getCode_postal());

            if (contenuApi != null) {
                JsonElement JsonElement = JsonParser.parseString(contenuApi);

                if (JsonElement.isJsonObject()) {
                    JsonObject JsonObject = JsonElement.getAsJsonObject();

                    if (JsonObject.get("places").isJsonArray()) {
                        JsonArray JsonArray = JsonObject.get("places").getAsJsonArray();
                        String[] resultat = new String[JsonArray.size()];

                        for (int i = 0; i < JsonArray.size(); i++) {
                            if (JsonArray.get(i).isJsonObject()) {
                                JsonObject ville = JsonArray.get(i).getAsJsonObject();
                                resultat[i] = ville.get("place name").getAsString();
                            }
                        }

                        villes = resultat;
                    } else {
                        villes = null;
                    }
                } else {
                    villes = null;
                }
            } else {
                villes = null;
            }
        } else {
            villes = null;
        }
    }

    public String getVraiNomVille(String ville) {
        if (this.getVilles() != null) {
            for (String laVille : this.getVilles()) {
                if (getCleanedString(getNormalizerString(laVille)).equals(getCleanedString(getNormalizerString(ville)))) {
                    return laVille;
                }
            }
        }
        return null;
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
                tmp.append("_");
            }
            i++;
        }

        return tmp.toString().toLowerCase();
    }

}
