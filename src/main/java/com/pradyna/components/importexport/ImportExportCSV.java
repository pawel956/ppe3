/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.components.importexport;

import com.pradyna.components.Utilitaire;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author p.radyna
 */
public class ImportExportCSV extends ImportExport {

    public ImportExportCSV(String cheminFichier, List<Integer> id_utilisateurs, List<Integer> id_cvs) {
        this.cheminFichier = cheminFichier;
        this.id_utilisateurs = id_utilisateurs;
        this.id_cvs = id_cvs;
    }

    public ImportExportCSV(String cheminFichier, String donneesAImporter) {
        this.cheminFichier = cheminFichier;
        this.donneesAImporter = donneesAImporter;
    }

    @Override
    public Boolean exporterFichier() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        if (this.cheminFichier != null && this.id_utilisateurs != null && this.id_cvs != null) {
            if (this.id_utilisateurs.size() > 0) {
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.cheminFichier + "_compte.csv"))) {
                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.RFC4180
                            .withHeader("id", "identifiant", "nom", "prenom", "dateNaissance", "numeroRue", "rue", "infoComplementaire", "codePostal", "ville", "pays", "courriel", "numeroTelephone", "numeroTelephoneDeux", "siteWeb")
                    );

                    for (Integer i = 0; i < this.id_utilisateurs.size(); i++) {
                        this.getUtilisateur(this.id_utilisateurs.get(i));

                        if (this.Utilisateur != null && this.Utilisateur.size() == 1) {
                            Map<String, Object> utilisateurData = this.Utilisateur.get(0);

                            String[] utilisateur = new String[15];

                            String[] dateNaissance_split = utilisateurData.get("date_de_naissance").toString().split("-");
                            String dateNaissance = dateNaissance_split[2] + "/" + dateNaissance_split[1] + "/" + dateNaissance_split[0];

                            utilisateur[0] = (String) utilisateurData.get("id").toString();
                            utilisateur[1] = (String) utilisateurData.get("identifiant");
                            utilisateur[2] = (String) utilisateurData.get("nom_user");
                            utilisateur[3] = (String) utilisateurData.get("prenom");
                            utilisateur[4] = dateNaissance;
                            utilisateur[5] = (String) utilisateurData.get("num_rue").toString();
                            utilisateur[6] = (String) utilisateurData.get("adresse");
                            utilisateur[7] = (String) utilisateurData.get("info_complementaire");
                            utilisateur[8] = (String) utilisateurData.get("code_postal").toString();
                            utilisateur[9] = (String) utilisateurData.get("nom_ville");
                            utilisateur[10] = (String) utilisateurData.get("nom_pays");
                            utilisateur[11] = (String) utilisateurData.get("courriel");
                            utilisateur[12] = (String) utilisateurData.get("num_telephone");
                            utilisateur[13] = (String) utilisateurData.get("num_telephone_deux");
                            utilisateur[14] = (String) utilisateurData.get("site_web");

                            csvPrinter.printRecord(Arrays.asList(utilisateur));
                        } else {
                            return null;
                        }
                    }

                    csvPrinter.flush();
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(ImportExportCSV.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
            }

            if (this.id_cvs.size() > 0) {
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.cheminFichier + "_cv.csv"))) {
                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.RFC4180
                            .withHeader("id", "id_utilisateur", "titre", "description", "nom_maitrise", "maitrise", "experiences_pro", "formations", "informations_comp")
                    );

                    for (Integer i = 0; i < this.id_cvs.size(); i++) {
                        this.getCV(this.id_cvs.get(i));

                        if (this.Cv != null && this.Cv.size() == 1) {
                            Map<String, Object> cvData = this.Cv.get(0);

                            String[] cv = new String[9];

                            cv[0] = (String) cvData.get("id").toString();
                            cv[1] = (String) cvData.get("id_utilisateur").toString();
                            cv[2] = (String) cvData.get("titre");
                            cv[3] = (String) cvData.get("description");
                            cv[4] = (String) cvData.get("nom_maitrise");
                            cv[5] = (String) cvData.get("maitrise");

                            this.getExperiencePro();
                            if (this.ExperiencePro != null && this.ExperiencePro.size() > 0) {
                                String[] experiencesPro = new String[this.ExperiencePro.size()];

                                for (Integer j = 0; j < this.ExperiencePro.size(); j++) {
                                    Map<String, Object> experienceProData = this.ExperiencePro.get(j);

                                    experiencesPro[j] = experienceProData.toString();
                                }

                                cv[6] = Arrays.toString(experiencesPro);
                            }

                            this.getFormation();
                            if (this.Formation != null && this.Formation.size() > 0) {
                                String[] formations = new String[this.Formation.size()];

                                for (Integer j = 0; j < this.Formation.size(); j++) {
                                    Map<String, Object> formationData = this.Formation.get(j);

                                    formations[j] = formationData.toString();
                                }

                                cv[7] = Arrays.toString(formations);
                            }

                            this.getInformationsComp();
                            if (this.InformationsComp != null && this.InformationsComp.size() > 0) {
                                String[] informationsComp = new String[this.InformationsComp.size()];

                                for (Integer j = 0; j < this.InformationsComp.size(); j++) {
                                    Map<String, Object> informationsCompData = this.InformationsComp.get(j);

                                    informationsComp[j] = informationsCompData.toString();
                                }

                                cv[8] = Arrays.toString(informationsComp);
                            }

                            csvPrinter.printRecord(Arrays.asList(cv));
                        } else {
                            return null;
                        }
                    }

                    csvPrinter.flush();
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(ImportExportCSV.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
            }

            return true;
        }

        return null;
    }

    @Override
    public Boolean importerFichier() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

//        if (this.cheminFichier != null) {
//            try (Reader reader = Files.newBufferedReader(Paths.get(this.cheminFichier))) {
//                CSVParser csvParser = new CSVParser(reader, CSVFormat.RFC4180
//                        .withFirstRecordAsHeader()
//                        .withIgnoreHeaderCase()
//                        .withTrim()
//                );
//                String[][] lesEtudiants = new String[Utilitaire.countLines(this.cheminFichier) - 1][15];
//
//                for (CSVRecord csvRecord : csvParser) {
//                    if (csvRecord.size() == 15) {
//                        try {
//                            Integer compteurLigneTab = Math.toIntExact(csvRecord.getRecordNumber()) - 1;
//
//                            lesEtudiants[compteurLigneTab][0] = csvRecord.get("id");
//                            lesEtudiants[compteurLigneTab][1] = csvRecord.get("identifiant");
//                            lesEtudiants[compteurLigneTab][2] = csvRecord.get("nom");
//                            lesEtudiants[compteurLigneTab][3] = csvRecord.get("prenom");
//                            lesEtudiants[compteurLigneTab][4] = csvRecord.get("dateNaissance");
//                            lesEtudiants[compteurLigneTab][5] = csvRecord.get("numeroRue");
//                            lesEtudiants[compteurLigneTab][6] = csvRecord.get("rue");
//                            lesEtudiants[compteurLigneTab][7] = csvRecord.get("infoComplementaire");
//                            lesEtudiants[compteurLigneTab][8] = csvRecord.get("codePostal");
//                            lesEtudiants[compteurLigneTab][9] = csvRecord.get("ville");
//                            lesEtudiants[compteurLigneTab][10] = csvRecord.get("pays");
//                            lesEtudiants[compteurLigneTab][11] = csvRecord.get("courriel");
//                            lesEtudiants[compteurLigneTab][12] = csvRecord.get("numeroTelephone");
//                            lesEtudiants[compteurLigneTab][13] = csvRecord.get("numeroTelephoneDeux");
//                            lesEtudiants[compteurLigneTab][14] = csvRecord.get("siteWeb");
//
//                        } catch (Exception ex) {
//                            if (ex.toString().contains("java.lang.IllegalArgumentException")) {
//                                return null;
//                            } else {
//                                Logger.getLogger(ImportExportCSV.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        }
//                    } else {
//                        return null;
//                    }
//                }
//
//                this.lesDonnees = lesEtudiants;
//                return true;
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(ImportExportCSV.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(ImportExportCSV.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return null;
    }

}
