/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.components.importexport;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.pradyna.components.Utilitaire;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pawel
 */
public class ImportExportJSON extends ImportExport {

    public ImportExportJSON(String cheminFichier, List<Integer> id_utilisateurs, List<Integer> id_cvs) {
        this.cheminFichier = cheminFichier;
        this.id_utilisateurs = id_utilisateurs;
        this.id_cvs = id_cvs;
    }

    @Override
    public Boolean exporterFichier() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        if (this.cheminFichier != null && (this.id_utilisateurs != null || this.id_cvs != null)) {
            JsonArray resultat = new JsonArray();

            if (this.id_utilisateurs != null && this.id_utilisateurs.size() > 0) {
                JsonArray etudiants = new JsonArray();

                for (Integer i = 0; i < this.id_utilisateurs.size(); i++) {
                    try {
                        this.getUtilisateur(this.id_utilisateurs.get(i));

                        if (this.Utilisateur != null && this.Utilisateur.size() == 1) {
                            Map<String, Object> utilisateurData = this.Utilisateur.get(0);

                            JsonObject etudiant = new JsonObject();

                            etudiant.addProperty("id", (String) utilisateurData.get("id").toString());
                            etudiant.addProperty("identifiant", (String) utilisateurData.get("identifiant"));
                            etudiant.addProperty("nom", (String) utilisateurData.get("nom_user"));
                            etudiant.addProperty("prenom", (String) utilisateurData.get("prenom"));

                            String[] dateNaissance_split = utilisateurData.get("date_de_naissance").toString().split("-");
                            String dateNaissance = dateNaissance_split[2] + "/" + dateNaissance_split[1] + "/" + dateNaissance_split[0];
                            etudiant.addProperty("dateNaissance", dateNaissance);

                            etudiant.addProperty("numeroRue", (String) utilisateurData.get("num_rue").toString());
                            etudiant.addProperty("rue", (String) utilisateurData.get("adresse"));
                            etudiant.addProperty("infoComplementaire", (String) utilisateurData.get("info_complementaire"));
                            etudiant.addProperty("codePostal", (String) utilisateurData.get("code_postal").toString());
                            etudiant.addProperty("ville", (String) utilisateurData.get("nom_ville"));
                            etudiant.addProperty("pays", (String) utilisateurData.get("nom_pays"));
                            etudiant.addProperty("courriel", (String) utilisateurData.get("courriel"));

                            JsonObject numeroTelephoneObjet = new JsonObject();
                            numeroTelephoneObjet.addProperty("un", (String) utilisateurData.get("num_telephone"));
                            numeroTelephoneObjet.addProperty("deux", (String) utilisateurData.get("num_telephone_deux"));

                            etudiant.add("numeroTelephone", numeroTelephoneObjet);

                            etudiant.addProperty("siteWeb", (String) utilisateurData.get("site_web"));

                            etudiants.add(etudiant);
                        } else {
                            return null;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ImportExportJSON.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    }
                }

                resultat.add(etudiants);
            }

            if (this.id_cvs != null && this.id_cvs.size() > 0) {
                JsonArray cvs = new JsonArray();

                for (Integer i = 0; i < this.id_cvs.size(); i++) {
                    try {
                        this.getCV(this.id_cvs.get(i));

                        if (this.Cv != null && this.Cv.size() == 1) {
                            Map<String, Object> cvData = this.Cv.get(0);

                            JsonObject cv = new JsonObject();

                            cv.addProperty("id", (String) cvData.get("id").toString());
                            cv.addProperty("id_utilisateur", (String) cvData.get("id_utilisateur").toString());
                            cv.addProperty("titre", (String) cvData.get("titre"));
                            cv.addProperty("description", (String) cvData.get("description"));

                            JsonObject maitriseObjet = new JsonObject();
                            maitriseObjet.addProperty("nom", (String) cvData.get("nom_maitrise"));
                            maitriseObjet.addProperty("valeur", (String) cvData.get("maitrise"));

                            cv.add("maitrise", maitriseObjet);

                            this.getExperiencePro();
                            if (this.ExperiencePro != null && this.ExperiencePro.size() > 0) {
                                JsonObject experiencesProObjet = new JsonObject();

                                for (Integer j = 0; j < this.ExperiencePro.size(); j++) {
                                    Map<String, Object> experienceProData = this.ExperiencePro.get(j);

                                    JsonObject experienceProObjet = new JsonObject();

                                    experienceProObjet.addProperty("id", (String) experienceProData.get("id").toString());
                                    experienceProObjet.addProperty("entreprise", (String) experienceProData.get("entreprise").toString());
                                    experienceProObjet.addProperty("lieu", (String) experienceProData.get("adresse"));
                                    experienceProObjet.addProperty("description", (String) experienceProData.get("description"));

                                    JsonObject anneesObjet = new JsonObject();

                                    String[] dateNaissance_split = experienceProData.get("annee_debut").toString().split("-");
                                    String dateNaissance = dateNaissance_split[2] + "/" + dateNaissance_split[1] + "/" + dateNaissance_split[0];

                                    anneesObjet.addProperty("annee_debut", dateNaissance);

                                    dateNaissance_split = experienceProData.get("annee_fin").toString().split("-");
                                    dateNaissance = dateNaissance_split[2] + "/" + dateNaissance_split[1] + "/" + dateNaissance_split[0];

                                    anneesObjet.addProperty("annee_fin", dateNaissance);

                                    experienceProObjet.add("annees", anneesObjet);

                                    experiencesProObjet.add("experience_pro_" + (j + 1), experienceProObjet);
                                }

                                cv.add("experiences_pro", experiencesProObjet);
                            }

                            this.getFormation();
                            if (this.Formation != null && this.Formation.size() > 0) {
                                JsonObject formationsObjet = new JsonObject();

                                for (Integer j = 0; j < this.Formation.size(); j++) {
                                    Map<String, Object> formationData = this.Formation.get(j);

                                    JsonObject formationObjet = new JsonObject();

                                    formationObjet.addProperty("id", (String) formationData.get("id").toString());
                                    formationObjet.addProperty("nom", (String) formationData.get("nom").toString());
                                    formationObjet.addProperty("lieu", (String) formationData.get("lieu"));
                                    formationObjet.addProperty("description", (String) formationData.get("description"));

                                    JsonObject anneesObjet = new JsonObject();

                                    String[] dateNaissance_split = formationData.get("annee_debut").toString().split("-");
                                    String dateNaissance = dateNaissance_split[2] + "/" + dateNaissance_split[1] + "/" + dateNaissance_split[0];

                                    anneesObjet.addProperty("annee_debut", dateNaissance);

                                    dateNaissance_split = formationData.get("annee_fin").toString().split("-");
                                    dateNaissance = dateNaissance_split[2] + "/" + dateNaissance_split[1] + "/" + dateNaissance_split[0];

                                    anneesObjet.addProperty("annee_fin", dateNaissance);

                                    formationObjet.add("annees", anneesObjet);

                                    formationsObjet.add("formation_" + (j + 1), formationObjet);
                                }

                                cv.add("formations", formationsObjet);
                            }

                            this.getInformationsComp();
                            if (this.InformationsComp != null && this.InformationsComp.size() > 0) {
                                JsonObject infosCompObjet = new JsonObject();

                                for (Integer j = 0; j < this.InformationsComp.size(); j++) {
                                    Map<String, Object> infoCompData = this.InformationsComp.get(j);

                                    JsonObject infoCompObjet = new JsonObject();

                                    infoCompObjet.addProperty("id", (String) infoCompData.get("id").toString());
                                    infoCompObjet.addProperty("intitule", (String) infoCompData.get("intitule").toString());
                                    infoCompObjet.addProperty("description", (String) infoCompData.get("description"));

                                    infosCompObjet.add("information_comp_" + (j + 1), infoCompObjet);
                                }

                                cv.add("informations_comp", infosCompObjet);
                            }

                            cvs.add(cv);
                        } else {
                            return null;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ImportExportJSON.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    }
                }

                resultat.add(cvs);
            }

            return Utilitaire.setFileString(this.cheminFichier, resultat.toString());
        }

        return null;
    }

    @Override
    public Boolean importerFichier(String donneesAImporter) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

//        if (this.cheminFichier != null) {
//            String contenuFichier = Utilitaire.getFileString(this.cheminFichier);
//
//            if (contenuFichier != null) {
//                JsonElement JsonElement;
//                try {
//                    JsonElement = JsonParser.parseString(contenuFichier);
//                } catch (JsonSyntaxException ex) {
//                    return null;
//                }
//
//                if (JsonElement.isJsonArray()) {
//                    JsonArray etudiants = JsonElement.getAsJsonArray();
//
//                    String[][] lesEtudiants = new String[etudiants.size()][15];
//                    for (int i = 0; i < etudiants.size(); i++) {
//                        JsonElement JsonElementBis = etudiants.get(i);
//
//                        if (JsonElementBis.isJsonObject()) {
//                            JsonObject etudiant = JsonElementBis.getAsJsonObject();
//
//                            if (etudiant.get("id") == null || etudiant.get("identifiant") == null || etudiant.get("nom") == null || etudiant.get("prenom") == null || etudiant.get("dateNaissance") == null || etudiant.get("numeroRue") == null || etudiant.get("rue") == null || etudiant.get("infoComplementaire") == null || etudiant.get("codePostal") == null || etudiant.get("ville") == null || etudiant.get("pays") == null || etudiant.get("courriel") == null || etudiant.get("siteWeb") == null) {
//                                return null;
//                            }
//
//                            lesEtudiants[i][0] = etudiant.get("id").getAsString();
//                            lesEtudiants[i][1] = etudiant.get("identifiant").getAsString();
//                            lesEtudiants[i][2] = etudiant.get("nom").getAsString();
//                            lesEtudiants[i][3] = etudiant.get("prenom").getAsString();
//                            lesEtudiants[i][4] = etudiant.get("dateNaissance").getAsString();
//                            lesEtudiants[i][5] = etudiant.get("numeroRue").getAsString();
//                            lesEtudiants[i][6] = etudiant.get("rue").getAsString();
//                            lesEtudiants[i][7] = etudiant.get("infoComplementaire").getAsString();
//                            lesEtudiants[i][8] = etudiant.get("codePostal").getAsString();
//                            lesEtudiants[i][9] = etudiant.get("ville").getAsString();
//                            lesEtudiants[i][10] = etudiant.get("pays").getAsString();
//                            lesEtudiants[i][11] = etudiant.get("courriel").getAsString();
//
//                            if (etudiant.get("numeroTelephone") != null && etudiant.get("numeroTelephone").isJsonObject()) {
//                                JsonObject etudiantNumeroTelephone = etudiant.get("numeroTelephone").getAsJsonObject();
//
//                                if (etudiantNumeroTelephone.get("un") == null || etudiantNumeroTelephone.get("deux") == null) {
//                                    return null;
//                                }
//                                lesEtudiants[i][12] = etudiantNumeroTelephone.get("un").getAsString();
//                                lesEtudiants[i][13] = etudiantNumeroTelephone.get("deux").getAsString();
//                            } else {
//                                return null;
//                            }
//
//                            lesEtudiants[i][14] = etudiant.get("siteWeb").getAsString();
//                        } else {
//                            return null;
//                        }
//                    }
//
//                    this.lesDonnees = lesEtudiants;
//                    return true;
//                }
//            }
//        }
//
        return null;
    }

}
