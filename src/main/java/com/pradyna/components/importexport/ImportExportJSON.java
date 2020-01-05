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
import java.util.ArrayList;
import java.util.HashMap;
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

    public ImportExportJSON(String cheminFichier, String donneesAImporter) {
        this.cheminFichier = cheminFichier;
        this.donneesAImporter = donneesAImporter;
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
    public Boolean importerFichier() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        if (this.cheminFichier != null) {
            String contenuFichier = Utilitaire.getFileString(this.cheminFichier);

            if (contenuFichier != null) {
                JsonElement JsonElement_racine;
                try {
                    JsonElement_racine = JsonParser.parseString(contenuFichier);
                } catch (JsonSyntaxException ex) {
                    return null;
                }

                if (JsonElement_racine.isJsonArray()) {
                    JsonArray JsonArray_racine = JsonElement_racine.getAsJsonArray();

                    // comptes
                    if (this.donneesAImporter.equals("compte")) {
                        JsonElement JsonElement_comptes = JsonArray_racine.get(0);

                        if (JsonElement_comptes.isJsonArray()) {
                            JsonArray JsonArray_comptes = JsonElement_comptes.getAsJsonArray();

                            List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();

                            for (int i = 0; i < JsonArray_comptes.size(); i++) {
                                JsonElement JsonElement_compte = JsonArray_comptes.get(i);

                                if (JsonElement_compte.isJsonObject()) {
                                    JsonObject JsonObject_compte = JsonElement_compte.getAsJsonObject();

                                    Map<String, Object> row = new HashMap<String, Object>(16);

                                    if (JsonObject_compte.get("id") == null || JsonObject_compte.get("identifiant") == null || JsonObject_compte.get("nom") == null || JsonObject_compte.get("prenom") == null || JsonObject_compte.get("dateNaissance") == null || JsonObject_compte.get("numeroRue") == null || JsonObject_compte.get("rue") == null || JsonObject_compte.get("infoComplementaire") == null || JsonObject_compte.get("codePostal") == null || JsonObject_compte.get("ville") == null || JsonObject_compte.get("pays") == null || JsonObject_compte.get("courriel") == null || JsonObject_compte.get("siteWeb") == null) {
                                        return null;
                                    }

                                    row.put("id", JsonObject_compte.get("id").getAsString());
                                    row.put("identifiant", JsonObject_compte.get("identifiant").getAsString());
                                    row.put("nom", JsonObject_compte.get("nom").getAsString());
                                    row.put("prenom", JsonObject_compte.get("prenom").getAsString());
                                    row.put("dateNaissance", JsonObject_compte.get("dateNaissance").getAsString());
                                    row.put("numeroRue", JsonObject_compte.get("numeroRue").getAsString());
                                    row.put("rue", JsonObject_compte.get("rue").getAsString());
                                    row.put("infoComplementaire", JsonObject_compte.get("infoComplementaire").getAsString());
                                    row.put("codePostal", JsonObject_compte.get("codePostal").getAsString());
                                    row.put("ville", JsonObject_compte.get("ville").getAsString());
                                    row.put("pays", JsonObject_compte.get("pays").getAsString());
                                    row.put("courriel", JsonObject_compte.get("courriel").getAsString());

                                    if (JsonObject_compte.get("numeroTelephone") != null && JsonObject_compte.get("numeroTelephone").isJsonObject()) {
                                        JsonObject JsonObject_compte_numeroTelephone = JsonObject_compte.get("numeroTelephone").getAsJsonObject();

                                        if (JsonObject_compte_numeroTelephone.get("un") == null || JsonObject_compte_numeroTelephone.get("deux") == null) {
                                            return null;
                                        }

                                        row.put("numeroTelephoneUn", JsonObject_compte_numeroTelephone.get("un").getAsString());
                                        row.put("numeroTelephoneDeux", JsonObject_compte_numeroTelephone.get("deux").getAsString());
                                    } else {
                                        return null;
                                    }

                                    row.put("siteWeb", JsonObject_compte.get("siteWeb").getAsString());

                                    rows.add(row);
                                }
                            }

                            this.DonneesImporte_Utilisateur = rows;
                            return true;
                        }
                    }

                    // cvs
                    if (this.donneesAImporter.equals("cv")) {
                        JsonElement JsonElement_cvs = null;

                        if (JsonArray_racine.size() == 1) {
                            JsonElement_cvs = JsonArray_racine.get(0);
                        } else if (JsonArray_racine.size() == 2) {
                            JsonElement_cvs = JsonArray_racine.get(1);
                        }

                        if (JsonElement_cvs != null && JsonElement_cvs.isJsonArray()) {
                            JsonArray JsonArray_cvs = JsonElement_cvs.getAsJsonArray();

                            List<Map<String, Object>> rows_cv = new ArrayList<Map<String, Object>>();
                            List<Map<String, Object>> rows_experiences_pro = new ArrayList<Map<String, Object>>();
                            List<Map<String, Object>> rows_formations = new ArrayList<Map<String, Object>>();
                            List<Map<String, Object>> rows_informations_comp = new ArrayList<Map<String, Object>>();

                            JsonElement JsonElement_cv = JsonArray_cvs.get(0);

                            if (JsonElement_cv.isJsonObject()) {
                                JsonObject JsonObject_cv = JsonElement_cv.getAsJsonObject();

                                Map<String, Object> row_cv = new HashMap<String, Object>(7);

                                if (JsonObject_cv.get("id") == null || JsonObject_cv.get("id_utilisateur") == null || JsonObject_cv.get("titre") == null || JsonObject_cv.get("description") == null) {
                                    return null;
                                }

                                row_cv.put("id", JsonObject_cv.get("id").getAsString());
                                row_cv.put("id_utilisateur", JsonObject_cv.get("id_utilisateur").getAsString());
                                row_cv.put("titre", JsonObject_cv.get("titre").getAsString());
                                row_cv.put("description", JsonObject_cv.get("description").getAsString());

                                if (JsonObject_cv.get("maitrise") != null && JsonObject_cv.get("maitrise").isJsonObject()) {
                                    JsonObject JsonObject_cv_maitrise = JsonObject_cv.get("maitrise").getAsJsonObject();

                                    if (JsonObject_cv_maitrise.get("nom") == null || JsonObject_cv_maitrise.get("valeur") == null) {
                                        return null;
                                    }

                                    row_cv.put("maitriseNom", JsonObject_cv_maitrise.get("nom").getAsString());
                                    row_cv.put("maitriseValeur", JsonObject_cv_maitrise.get("valeur").getAsString());
                                } else {
                                    return null;
                                }

                                // experiences pro
                                if (JsonObject_cv.get("experiences_pro") != null && JsonObject_cv.get("experiences_pro").isJsonObject()) {
                                    JsonObject JsonObject_cv_experiences_pro = JsonObject_cv.get("experiences_pro").getAsJsonObject();

                                    for (int j = 0; j < JsonObject_cv_experiences_pro.size(); j++) {
                                        JsonElement JsonElement_cv_experience_pro = JsonObject_cv_experiences_pro.get("experience_pro_" + (j + 1));

                                        if (JsonElement_cv_experience_pro.isJsonObject()) {
                                            JsonObject JsonObject_cv_experience_pro = JsonElement_cv_experience_pro.getAsJsonObject();

                                            if (JsonObject_cv_experience_pro.get("id") == null || JsonObject_cv_experience_pro.get("entreprise") == null || JsonObject_cv_experience_pro.get("lieu") == null || JsonObject_cv_experience_pro.get("description") == null) {
                                                return null;
                                            }

                                            Map<String, Object> row_experiences_pro = new HashMap<String, Object>(7);

                                            row_experiences_pro.put("id", JsonObject_cv_experience_pro.get("id").getAsString());
                                            row_experiences_pro.put("entreprise", JsonObject_cv_experience_pro.get("entreprise").getAsString());
                                            row_experiences_pro.put("lieu", JsonObject_cv_experience_pro.get("lieu").getAsString());
                                            row_experiences_pro.put("description", JsonObject_cv_experience_pro.get("description").getAsString());

                                            if (JsonObject_cv_experience_pro.get("annees") != null && JsonObject_cv_experience_pro.get("annees").isJsonObject()) {
                                                JsonObject JsonObject_cv_experience_pro_annees = JsonObject_cv_experience_pro.get("annees").getAsJsonObject();

                                                if (JsonObject_cv_experience_pro_annees.get("annee_debut") == null || JsonObject_cv_experience_pro_annees.get("annee_fin") == null) {
                                                    return null;
                                                }

                                                row_experiences_pro.put("annee_debut", JsonObject_cv_experience_pro_annees.get("annee_debut").getAsString());
                                                row_experiences_pro.put("annee_fin", JsonObject_cv_experience_pro_annees.get("annee_fin").getAsString());
                                            } else {
                                                return null;
                                            }

                                            rows_experiences_pro.add(row_experiences_pro);
                                        } else {
                                            return null;
                                        }
                                    }
                                } else {
                                    return null;
                                }

                                // formations
                                if (JsonObject_cv.get("formations") != null && JsonObject_cv.get("formations").isJsonObject()) {
                                    JsonObject JsonObject_cv_formations = JsonObject_cv.get("formations").getAsJsonObject();

                                    for (int j = 0; j < JsonObject_cv_formations.size(); j++) {
                                        JsonElement JsonElement_cv_formation = JsonObject_cv_formations.get("formation_" + (j + 1));

                                        if (JsonElement_cv_formation.isJsonObject()) {
                                            JsonObject JsonObject_cv_formation = JsonElement_cv_formation.getAsJsonObject();

                                            if (JsonObject_cv_formation.get("id") == null || JsonObject_cv_formation.get("nom") == null || JsonObject_cv_formation.get("lieu") == null || JsonObject_cv_formation.get("description") == null) {
                                                return null;
                                            }

                                            Map<String, Object> row_formations = new HashMap<String, Object>(7);

                                            row_formations.put("id", JsonObject_cv_formation.get("id").getAsString());
                                            row_formations.put("nom", JsonObject_cv_formation.get("nom").getAsString());
                                            row_formations.put("lieu", JsonObject_cv_formation.get("lieu").getAsString());
                                            row_formations.put("description", JsonObject_cv_formation.get("description").getAsString());

                                            if (JsonObject_cv_formation.get("annees") != null && JsonObject_cv_formation.get("annees").isJsonObject()) {
                                                JsonObject JsonObject_cv_formation_annees = JsonObject_cv_formation.get("annees").getAsJsonObject();

                                                if (JsonObject_cv_formation_annees.get("annee_debut") == null || JsonObject_cv_formation_annees.get("annee_fin") == null) {
                                                    return null;
                                                }

                                                row_formations.put("annee_debut", JsonObject_cv_formation_annees.get("annee_debut").getAsString());
                                                row_formations.put("annee_fin", JsonObject_cv_formation_annees.get("annee_fin").getAsString());
                                            } else {
                                                return null;
                                            }

                                            rows_formations.add(row_formations);
                                        } else {
                                            return null;
                                        }
                                    }
                                } else {
                                    return null;
                                }

                                // informations_comp
                                if (JsonObject_cv.get("informations_comp") != null && JsonObject_cv.get("informations_comp").isJsonObject()) {
                                    JsonObject JsonObject_cv_informations_comp = JsonObject_cv.get("informations_comp").getAsJsonObject();

                                    for (int j = 0; j < JsonObject_cv_informations_comp.size(); j++) {
                                        JsonElement JsonElement_cv_informations_comp = JsonObject_cv_informations_comp.get("information_comp_" + (j + 1));

                                        if (JsonElement_cv_informations_comp.isJsonObject()) {
                                            JsonObject JsonObject_cv_information_comp = JsonElement_cv_informations_comp.getAsJsonObject();

                                            if (JsonObject_cv_information_comp.get("id") == null || JsonObject_cv_information_comp.get("intitule") == null || JsonObject_cv_information_comp.get("description") == null) {
                                                return null;
                                            }

                                            Map<String, Object> row_informations_comp = new HashMap<String, Object>(4);

                                            row_informations_comp.put("id", JsonObject_cv_information_comp.get("id").getAsString());
                                            row_informations_comp.put("intitule", JsonObject_cv_information_comp.get("intitule").getAsString());
                                            row_informations_comp.put("description", JsonObject_cv_information_comp.get("description").getAsString());

                                            rows_informations_comp.add(row_informations_comp);
                                        } else {
                                            return null;
                                        }
                                    }
                                } else {
                                    return null;
                                }

                                rows_cv.add(row_cv);
                            }

                            this.DonneesImporte_Cv = rows_cv;
                            this.DonneesImporte_ExperiencePro = rows_experiences_pro;
                            this.DonneesImporte_Formation = rows_formations;
                            this.DonneesImporte_InformationsComp = rows_informations_comp;
                            return true;
                        }
                    }
                }
            }
        }

        return null;
    }

}
