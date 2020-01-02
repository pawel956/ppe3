/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karimandco.auth;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.karimandco.bdd.DaoSIO;
import com.pradyna.components.Utilitaire;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * @author Damien F, Pawel R, Théo M
 */
public class Champ extends JTextField implements KeyListener {

    public Champ() {
        super();

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Cette méthode vérifie si l'idenitifiant saisi est correct et retourne
     * vrai ou faux.
     *
     * @return Boolean true or false
     */
    public Boolean verifIdentifiant() {
        Boolean identifiantOK = true;
        String identifiant = this.getText().trim();

        if (identifiant.equals("")) {
            identifiantOK = false;
        } else {
            Pattern p = Pattern.compile("^(\\p{Alnum})+([.]\\p{Alnum}+){0,1}");
            Matcher m = p.matcher(identifiant);
            identifiantOK = m.matches();
        }
        return identifiantOK;
    }

    /**
     * Cette méthode vérifie si le nom saisi est correct et retourne vrai ou
     * faux.
     *
     * @return Boolean true or false
     */
    public Boolean verifNom() {
        Boolean nomOK = true;
        String nom = this.getText().trim();

        if (nom.equals("")) {
            nomOK = false;
        } else {
            Pattern p = Pattern.compile("^(\\p{Alpha})+([ ]\\p{Alpha}+){0,2}");
            Matcher m = p.matcher(nom);
            nomOK = m.matches();
        }

        return nomOK;
    }

    /**
     * Cette méthode vérifie si le prénom saisi est correct et retourne vrai ou
     * faux.
     *
     * @return Boolean true or false
     */
    public Boolean verifPrenom() {
        Boolean prenomOK = true;
        String prenom = this.getText().trim();
        prenom = Normalizer
                .normalize(prenom, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        if (prenom.equals("")) {
            prenomOK = false;
        } else {
            Pattern p = Pattern.compile("^(\\p{Alpha})+([-]{1}\\p{Alpha}+)?");
            Matcher m = p.matcher(prenom);
            prenomOK = m.matches();
        }

        return prenomOK;
    }

    /**
     * Cette méthode vérifie si le numéro de téléphone saisi est correct et
     * retourne vrai ou faux.
     *
     * @return Boolean true or false
     */
    public Boolean verifNumeroTelephone() {
        Boolean numeroTelephoneOK = true;
        String numeroTelephone = this.getText().trim();

        if (numeroTelephone.equals("")) {
            numeroTelephoneOK = false;
        } else {
            Pattern p = Pattern.compile("(^[+](\\p{Digit}){1,3}?[ ](\\p{Digit}){9})|(^(\\p{Digit}){10})");
            Matcher m = p.matcher(numeroTelephone);
            numeroTelephoneOK = m.matches();
        }

        return numeroTelephoneOK;
    }

    /**
     * Cette méthode vérifie si la date de naissance saisie est correct et
     * retourne vrai ou faux.
     *
     * @return Boolean true or false
     */
    public Boolean verifDateNaissance() {
        Boolean dateNaissanceOK = true;
        String dateNaissance = this.getText().trim();

        if (dateNaissance.equals("")) {
            dateNaissanceOK = false;
        } else {
            Pattern p = Pattern.compile("^(\\p{Digit}){1,2}[/](\\p{Digit}){1,2}[/](\\p{Digit}){4}");
            Matcher m = p.matcher(dateNaissance);
            dateNaissanceOK = m.matches();

            if (dateNaissanceOK) {
                String[] dateNaissance_split = dateNaissance.split("/");
                GregorianCalendar calendrier = new GregorianCalendar(Integer.parseInt(dateNaissance_split[2]), (Integer.parseInt(dateNaissance_split[1]) - 1), Integer.parseInt(dateNaissance_split[0]));

                String date_gregorian;
                String date_gregorian_jour;
                String date_gregorian_mois;

                if ((calendrier.get(Calendar.DATE) + 1) <= 10 && dateNaissance_split[0].length() == 2) {
                    date_gregorian_jour = "0" + calendrier.get(Calendar.DATE);
                } else {
                    date_gregorian_jour = String.valueOf(calendrier.get(Calendar.DATE));
                }

                if ((calendrier.get(Calendar.MONTH) + 1) < 10 && dateNaissance_split[1].length() == 2) {
                    date_gregorian_mois = "0" + (calendrier.get(Calendar.MONTH) + 1);
                } else {
                    date_gregorian_mois = String.valueOf(calendrier.get(Calendar.MONTH) + 1);
                }

                date_gregorian = date_gregorian_jour + "/" + date_gregorian_mois + "/" + calendrier.get(Calendar.YEAR);

                if (!dateNaissance.equals(date_gregorian)) {
                    dateNaissanceOK = false;
                }

                if (dateNaissanceOK) {
                    String contenuApi = Utilitaire.getApiString("http://worldclockapi.com/api/json/est/now");
                    if (contenuApi != null) {
                        JsonElement JsonElement = JsonParser.parseString(contenuApi);
                        if (JsonElement.isJsonObject()) {
                            JsonObject JsonObject = JsonElement.getAsJsonObject();
                            String[] date_server_split = JsonObject.get("currentDateTime").getAsString().split("T")[0].split("-");

                            GregorianCalendar date_server_split_gregorian = new GregorianCalendar(Integer.parseInt(date_server_split[0]), (Integer.parseInt(date_server_split[1]) - 1), Integer.parseInt(date_server_split[2]));
                            if (date_server_split_gregorian.compareTo(calendrier) < 0) {
                                dateNaissanceOK = false;
                            }
                        }

                    } else {
                        ResultSet lesResultats = DaoSIO.getInstance().requeteSelection("SELECT CURRENT_DATE");
                        try {
                            if (lesResultats.next()) {
                                String[] date_bdd = lesResultats.getString("CURRENT_DATE").split("-");
                                GregorianCalendar date_bdd_gregorian = new GregorianCalendar(Integer.parseInt(date_bdd[0]), (Integer.parseInt(date_bdd[1]) - 1), Integer.parseInt(date_bdd[2]));
                                if (date_bdd_gregorian.compareTo(calendrier) < 0) {
                                    dateNaissanceOK = false;
                                }
                            }
                        } catch (NumberFormatException | SQLException ex) {
                        }
                    }
                }
            }
        }

        return dateNaissanceOK;
    }

    /**
     * Cette méthode vérifie si le courriel saisi est correct et retourne vrai
     * ou faux.
     *
     * @return Boolean true or false
     */
    public Boolean verifCourriel() {
        Boolean courrielOK = true;
        String courriel = this.getText().trim();

        if (courriel.equals("")) {
            courrielOK = false;
        } else {
            Pattern p = Pattern.compile("^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$");
            Matcher m = p.matcher(courriel);
            courrielOK = m.matches();
        }

        return courrielOK;
    }

    public Boolean verifSiteWeb() {
        String siteWeb = this.getText().trim();

        if (siteWeb.equals("")) {
            return false;
        } else {
            try {
                URL url = new URL(siteWeb);
                return true;
            } catch (MalformedURLException ex) {
                return false;
            }
        }
    }

    public Boolean verifPays() {
        Boolean paysOK;
        String pays = this.getText().trim();

        if (pays.equals("")) {
            paysOK = false;
        } else {
            Pattern p = Pattern.compile("^(\\p{Alpha})+([ ]\\p{Alpha}+){0,}");
            Matcher m = p.matcher(pays);
            paysOK = m.matches();
        }

        return paysOK;
    }

    public Boolean verifCodePostal() {
        Boolean codePostalOK;
        String codePostal = this.getText().trim();

        if (codePostal.equals("")) {
            codePostalOK = false;
        } else {
            Pattern p = Pattern.compile("^(\\p{Alnum})+");
            Matcher m = p.matcher(codePostal);
            codePostalOK = m.matches();
        }

        return codePostalOK;
    }

    public Boolean verifVille() {
        Boolean villeOK;
        String ville = this.getText().trim();
        ville = Normalizer
                .normalize(ville, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        if (ville.equals("")) {
            villeOK = false;
        } else {
            Pattern p = Pattern.compile("^(\\p{Alnum})+([ -]\\p{Alnum}+){0,}");
            Matcher m = p.matcher(ville);
            villeOK = m.matches();
        }

        return villeOK;
    }

    public Boolean verifNumeroRue() {
        Boolean numeroRueOK;
        String numeroRue = this.getText().trim();

        if (numeroRue.equals("")) {
            numeroRueOK = false;
        } else {
            Pattern p = Pattern.compile("^(\\p{Digit})+");
            Matcher m = p.matcher(numeroRue);
            numeroRueOK = m.matches();
        }

        return numeroRueOK;
    }

    public Boolean verifAdresse() {
        Boolean adresseOK;
        String adresse = this.getText().trim();

        if (adresse.equals("")) {
            adresseOK = false;
        } else {
            Pattern p = Pattern.compile("^(\\p{Alpha})+([ ]\\p{Alpha}+){0,}");
            Matcher m = p.matcher(adresse);
            adresseOK = m.matches();
        }

        return adresseOK;
    }

    public Boolean verifInfoComp() {
        Boolean infoCompOK;
        String infoComp = this.getText().trim();

        if (infoComp.equals("")) {
            infoCompOK = false;
        } else {
            Pattern p = Pattern.compile("^(\\p{Alnum})+([ ]\\p{Alnum}+){0,}");
            Matcher m = p.matcher(infoComp);
            infoCompOK = m.matches();
        }

        return infoCompOK;
    }
}
