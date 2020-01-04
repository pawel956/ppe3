/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.components.importexport;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author pawel
 */
public class ImportExportXML extends ImportExport {

    public ImportExportXML(String cheminFichier, List<Integer> id_utilisateurs, List<Integer> id_cvs) {
        this.cheminFichier = cheminFichier;
        this.id_utilisateurs = id_utilisateurs;
        this.id_cvs = id_cvs;
    }

    public ImportExportXML(String cheminFichier, String donneesAImporter) {
        this.cheminFichier = cheminFichier;
        this.donneesAImporter = donneesAImporter;
    }

    @Override
    public Boolean exporterFichier() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        if (this.cheminFichier != null && (this.id_utilisateurs != null || this.id_cvs != null)) {
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();

                Document doc;
                Element racine;

                DOMSource source;
                StreamResult resultat;

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                if (this.id_utilisateurs != null && this.id_utilisateurs.size() > 0) {
                    // élément de racine
                    doc = docBuilder.newDocument();
                    racine = doc.createElement("utilisateurs");
                    doc.appendChild(racine);

                    for (Integer i = 0; i < this.id_utilisateurs.size(); i++) {
                        this.getUtilisateur(this.id_utilisateurs.get(i));

                        if (this.Utilisateur != null && this.Utilisateur.size() == 1) {
                            Map<String, Object> utilisateurData = this.Utilisateur.get(0);

                            // l'élément utilisateur
                            Element etudiant = doc.createElement("utilisateur_" + (i + 1));
                            racine.appendChild(etudiant);

                            // attribut de l'élément utilisateur
                            etudiant.setAttribute("id", (String) utilisateurData.get("id").toString());

                            // l'identifiant
                            Element identifiant = doc.createElement("identifiant");
                            identifiant.appendChild(doc.createTextNode((String) utilisateurData.get("identifiant")));
                            etudiant.appendChild(identifiant);

                            // le nom
                            Element nom = doc.createElement("nom");
                            nom.appendChild(doc.createTextNode((String) utilisateurData.get("nom_user")));
                            etudiant.appendChild(nom);

                            // le prénom
                            Element prenom = doc.createElement("prenom");
                            prenom.appendChild(doc.createTextNode((String) utilisateurData.get("prenom")));
                            etudiant.appendChild(prenom);

                            // la date de naissance
                            String[] dateNaissance_split = utilisateurData.get("date_de_naissance").toString().split("-");
                            String dateNaissance = dateNaissance_split[2] + "/" + dateNaissance_split[1] + "/" + dateNaissance_split[0];

                            Element date_de_naissance = doc.createElement("dateNaissance");
                            date_de_naissance.appendChild(doc.createTextNode(dateNaissance));
                            etudiant.appendChild(date_de_naissance);

                            // le numéro de rue
                            Element num_rue = doc.createElement("numeroRue");
                            num_rue.appendChild(doc.createTextNode((String) utilisateurData.get("num_rue").toString()));
                            etudiant.appendChild(num_rue);

                            // l'adresse
                            Element adresse = doc.createElement("rue");
                            adresse.appendChild(doc.createTextNode((String) utilisateurData.get("adresse")));
                            etudiant.appendChild(adresse);

                            // les infos complémentaires
                            Element info_complementaire = doc.createElement("infoComplementaire");
                            info_complementaire.appendChild(doc.createTextNode((String) utilisateurData.get("info_complementaire")));
                            etudiant.appendChild(info_complementaire);

                            // le code postal
                            Element code_postal = doc.createElement("codePostal");
                            code_postal.appendChild(doc.createTextNode((String) utilisateurData.get("code_postal").toString()));
                            etudiant.appendChild(code_postal);

                            // la ville
                            Element ville = doc.createElement("ville");
                            ville.appendChild(doc.createTextNode((String) utilisateurData.get("nom_ville")));
                            etudiant.appendChild(ville);

                            // le pays
                            Element pays = doc.createElement("pays");
                            pays.appendChild(doc.createTextNode((String) utilisateurData.get("nom_pays")));
                            etudiant.appendChild(pays);

                            // le courriel
                            Element courriel = doc.createElement("courriel");
                            courriel.appendChild(doc.createTextNode((String) utilisateurData.get("courriel")));
                            etudiant.appendChild(courriel);

                            // l'élément numéro de téléphone
                            Element numeroTelephone = doc.createElement("numeroTelephone");
                            etudiant.appendChild(numeroTelephone);

                            // le numéro de téléphone 1
                            Element num_telephone = doc.createElement("un");
                            num_telephone.appendChild(doc.createTextNode((String) utilisateurData.get("num_telephone")));
                            numeroTelephone.appendChild(num_telephone);

                            // le numéro de téléphone 2
                            Element num_telephone_deux = doc.createElement("deux");
                            num_telephone_deux.appendChild(doc.createTextNode((String) utilisateurData.get("num_telephone_deux")));
                            numeroTelephone.appendChild(num_telephone_deux);

                            // le site web
                            Element site_web = doc.createElement("siteWeb");
                            site_web.appendChild(doc.createTextNode((String) utilisateurData.get("site_web")));
                            etudiant.appendChild(site_web);
                        } else {
                            return null;
                        }
                    }

                    source = new DOMSource(doc);
                    resultat = new StreamResult(new File(this.cheminFichier + "_compte.xml"));

                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                    transformer.transform(source, resultat);
                }

                if (this.id_cvs != null && this.id_cvs.size() > 0) {
                    // élément de racine
                    doc = docBuilder.newDocument();
                    racine = doc.createElement("cvs");
                    doc.appendChild(racine);

                    for (Integer i = 0; i < this.id_cvs.size(); i++) {
                        this.getCV(this.id_cvs.get(i));

                        if (this.Cv != null && this.Cv.size() == 1) {
                            Map<String, Object> cvData = this.Cv.get(0);

                            // l'élément cv
                            Element cv = doc.createElement("cv_" + (i + 1));
                            racine.appendChild(cv);

                            // attribut de l'élément cv
                            cv.setAttribute("id", (String) cvData.get("id").toString());

                            // l'identifiant utilisateur
                            Element id_utilisateur = doc.createElement("id_utilisateur");
                            id_utilisateur.appendChild(doc.createTextNode((String) cvData.get("id_utilisateur").toString()));
                            cv.appendChild(id_utilisateur);

                            // le titre
                            Element titre = doc.createElement("titre");
                            titre.appendChild(doc.createTextNode((String) cvData.get("titre")));
                            cv.appendChild(titre);

                            // la description
                            Element description = doc.createElement("description");
                            description.appendChild(doc.createTextNode((String) cvData.get("description")));
                            cv.appendChild(description);

                            // l'élément maitrise
                            Element maitrise = doc.createElement("maitrise");
                            cv.appendChild(maitrise);

                            // le nom maitrise
                            Element nom_maitrise = doc.createElement("nom");
                            nom_maitrise.appendChild(doc.createTextNode((String) cvData.get("nom_maitrise")));
                            maitrise.appendChild(nom_maitrise);

                            // la valeur maitrise
                            Element valeur_maitrise = doc.createElement("valeur");
                            valeur_maitrise.appendChild(doc.createTextNode((String) cvData.get("maitrise")));
                            maitrise.appendChild(valeur_maitrise);

                            this.getExperiencePro();
                            if (this.ExperiencePro != null && this.ExperiencePro.size() > 0) {
                                // l'élément experiences pro
                                Element experiences_pro = doc.createElement("experiences_pro");
                                cv.appendChild(experiences_pro);

                                for (Integer j = 0; j < this.ExperiencePro.size(); j++) {
                                    Map<String, Object> experienceProData = this.ExperiencePro.get(j);

                                    // l'élément expérience pro
                                    Element experience_pro = doc.createElement("experience_pro_" + (j + 1));
                                    experiences_pro.appendChild(experience_pro);

                                    // attribut de l'élément expérience pro
                                    experience_pro.setAttribute("id", (String) experienceProData.get("id").toString());

                                    // le nom de l'entreprise
                                    Element entreprise = doc.createElement("entreprise");
                                    entreprise.appendChild(doc.createTextNode((String) experienceProData.get("entreprise")));
                                    experience_pro.appendChild(entreprise);

                                    // l'adresse de l'entreprise
                                    Element lieu_entreprise = doc.createElement("lieu");
                                    lieu_entreprise.appendChild(doc.createTextNode((String) experienceProData.get("adresse")));
                                    experience_pro.appendChild(lieu_entreprise);

                                    // la description de l'expérience pro
                                    Element description_experience_pro = doc.createElement("description");
                                    description_experience_pro.appendChild(doc.createTextNode((String) experienceProData.get("description")));
                                    experience_pro.appendChild(description_experience_pro);

                                    // l'élément annees
                                    Element annees = doc.createElement("annees");
                                    experience_pro.appendChild(annees);

                                    // l'année de début
                                    String[] dateNaissance_split = experienceProData.get("annee_debut").toString().split("-");
                                    String dateNaissance = dateNaissance_split[2] + "/" + dateNaissance_split[1] + "/" + dateNaissance_split[0];

                                    Element annee_debut = doc.createElement("annee_debut");
                                    annee_debut.appendChild(doc.createTextNode(dateNaissance));
                                    annees.appendChild(annee_debut);

                                    // l'année de fin
                                    dateNaissance_split = experienceProData.get("annee_fin").toString().split("-");
                                    dateNaissance = dateNaissance_split[2] + "/" + dateNaissance_split[1] + "/" + dateNaissance_split[0];

                                    Element annee_fin = doc.createElement("annee_fin");
                                    annee_fin.appendChild(doc.createTextNode(dateNaissance));
                                    annees.appendChild(annee_fin);
                                }
                            }

                            this.getFormation();
                            if (this.Formation != null && this.Formation.size() > 0) {
                                // l'élément formations
                                Element formations = doc.createElement("formations");
                                cv.appendChild(formations);

                                for (Integer j = 0; j < this.Formation.size(); j++) {
                                    Map<String, Object> formationData = this.Formation.get(j);

                                    // l'élément formation
                                    Element formation = doc.createElement("formation_" + (j + 1));
                                    formations.appendChild(formation);

                                    // attribut de l'élément formation
                                    formation.setAttribute("id", (String) formationData.get("id").toString());

                                    // le nom de formation
                                    Element nom_formation = doc.createElement("nom");
                                    nom_formation.appendChild(doc.createTextNode((String) formationData.get("nom")));
                                    formation.appendChild(nom_formation);

                                    // le lieu de formation
                                    Element lieu_formation = doc.createElement("lieu");
                                    lieu_formation.appendChild(doc.createTextNode((String) formationData.get("lieu")));
                                    formation.appendChild(lieu_formation);

                                    // la description de formation
                                    Element description_formation = doc.createElement("description");
                                    description_formation.appendChild(doc.createTextNode((String) formationData.get("description")));
                                    formation.appendChild(description_formation);

                                    // l'élément annees
                                    Element annees = doc.createElement("annees");
                                    formation.appendChild(annees);

                                    // l'année de début
                                    String[] dateNaissance_split = formationData.get("annee_debut").toString().split("-");
                                    String dateNaissance = dateNaissance_split[2] + "/" + dateNaissance_split[1] + "/" + dateNaissance_split[0];

                                    Element annee_debut = doc.createElement("annee_debut");
                                    annee_debut.appendChild(doc.createTextNode(dateNaissance));
                                    annees.appendChild(annee_debut);

                                    // l'année de fin
                                    dateNaissance_split = formationData.get("annee_fin").toString().split("-");
                                    dateNaissance = dateNaissance_split[2] + "/" + dateNaissance_split[1] + "/" + dateNaissance_split[0];

                                    Element annee_fin = doc.createElement("annee_fin");
                                    annee_fin.appendChild(doc.createTextNode(dateNaissance));
                                    annees.appendChild(annee_fin);
                                }
                            }

                            this.getInformationsComp();
                            if (this.InformationsComp != null && this.InformationsComp.size() > 0) {
                                // l'élément infos comp
                                Element infos_comp = doc.createElement("informations_comp");
                                cv.appendChild(infos_comp);

                                for (Integer j = 0; j < this.InformationsComp.size(); j++) {
                                    Map<String, Object> infoCompData = this.InformationsComp.get(j);

                                    // l'élément info comp
                                    Element info_comp = doc.createElement("information_comp_" + (j + 1));
                                    infos_comp.appendChild(info_comp);

                                    // attribut de l'élément info comp
                                    info_comp.setAttribute("id", (String) infoCompData.get("id").toString());

                                    // l'intitulé de l'info comp
                                    Element intitule_infoComp = doc.createElement("intitule");
                                    intitule_infoComp.appendChild(doc.createTextNode((String) infoCompData.get("intitule")));
                                    info_comp.appendChild(intitule_infoComp);

                                    // la description de l'info comp
                                    Element description_infoComp = doc.createElement("description");
                                    description_infoComp.appendChild(doc.createTextNode((String) infoCompData.get("description")));
                                    info_comp.appendChild(description_infoComp);
                                }
                            }
                        } else {
                            return null;
                        }
                    }

                    source = new DOMSource(doc);
                    resultat = new StreamResult(new File(this.cheminFichier + "_cv.xml"));

                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                    transformer.transform(source, resultat);
                }

                return true;
            } catch (ParserConfigurationException | TransformerConfigurationException ex) {
                Logger.getLogger(ImportExportXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException | SQLException ex) {
                Logger.getLogger(ImportExportXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    @Override
    public Boolean importerFichier() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        if (this.cheminFichier != null) {
            try {
                File fichier = new File(cheminFichier);

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();

                Document doc;
                try {
                    doc = docBuilder.parse(fichier);
                    doc.getDocumentElement().normalize();
                } catch (SAXParseException ex) {
                    return null;
                }

                // comptes
                if (this.donneesAImporter.equals("compte")) {
                    if (doc.getDocumentElement().getNodeName().equals("utilisateurs")) {
                        NodeList nList = doc.getElementsByTagName("utilisateur");

                        if (nList.getLength() > 0) {
                            String[][] lesEtudiants = new String[nList.getLength()][15];

                            for (int i = 0; i < nList.getLength(); i++) {
                                Node nNode = nList.item(i);

                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElement = (Element) nNode;
                                    // utilisateur_1

                                    if (eElement.getAttribute("id").equals("") || eElement.getElementsByTagName("identifiant").item(0) == null || eElement.getElementsByTagName("nom").item(0) == null || eElement.getElementsByTagName("prenom").item(0) == null || eElement.getElementsByTagName("dateNaissance").item(0) == null || eElement.getElementsByTagName("numeroRue").item(0) == null || eElement.getElementsByTagName("rue").item(0) == null || eElement.getElementsByTagName("infoComplementaire").item(0) == null || eElement.getElementsByTagName("codePostal").item(0) == null || eElement.getElementsByTagName("ville").item(0) == null || eElement.getElementsByTagName("pays").item(0) == null || eElement.getElementsByTagName("courriel").item(0) == null || eElement.getElementsByTagName("siteWeb").item(0) == null) {
                                        return null;
                                    }

                                    lesEtudiants[i][0] = eElement.getAttribute("id");
                                    lesEtudiants[i][1] = eElement.getElementsByTagName("identifiant").item(0).getTextContent();
                                    lesEtudiants[i][2] = eElement.getElementsByTagName("nom").item(0).getTextContent();
                                    lesEtudiants[i][3] = eElement.getElementsByTagName("prenom").item(0).getTextContent();
                                    lesEtudiants[i][4] = eElement.getElementsByTagName("dateNaissance").item(0).getTextContent();
                                    lesEtudiants[i][5] = eElement.getElementsByTagName("numeroRue").item(0).getTextContent();
                                    lesEtudiants[i][6] = eElement.getElementsByTagName("rue").item(0).getTextContent();
                                    lesEtudiants[i][7] = eElement.getElementsByTagName("infoComplementaire").item(0).getTextContent();
                                    lesEtudiants[i][8] = eElement.getElementsByTagName("codePostal").item(0).getTextContent();
                                    lesEtudiants[i][9] = eElement.getElementsByTagName("ville").item(0).getTextContent();
                                    lesEtudiants[i][10] = eElement.getElementsByTagName("pays").item(0).getTextContent();
                                    lesEtudiants[i][11] = eElement.getElementsByTagName("courriel").item(0).getTextContent();

                                    Node nNodeBis = eElement.getElementsByTagName("numeroTelephone").item(0);

                                    if (nNodeBis != null && nNodeBis.getNodeType() == Node.ELEMENT_NODE) {
                                        Element eElementBis = (Element) nNodeBis;

                                        if (eElementBis.getElementsByTagName("un").item(0) == null || eElementBis.getElementsByTagName("deux").item(0) == null) {
                                            return null;
                                        }

                                        lesEtudiants[i][12] = eElementBis.getElementsByTagName("un").item(0).getTextContent();
                                        lesEtudiants[i][13] = eElementBis.getElementsByTagName("deux").item(0).getTextContent();
                                    } else {
                                        return null;
                                    }

                                    lesEtudiants[i][14] = eElement.getElementsByTagName("siteWeb").item(0).getTextContent();
                                } else {
                                    return null;
                                }
                            }

//                            this.DonneesImporte = lesEtudiants;
                            return true;
                        }
                    }

                    return null;
                }
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                Logger.getLogger(ImportExportXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

}
