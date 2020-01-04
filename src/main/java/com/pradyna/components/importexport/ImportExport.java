/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.components.importexport;

import com.karimandco.bdd.DaoSIO;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author c.nadal
 */
public abstract class ImportExport implements IfaceImportExport {

    String cheminFichier;
    String donneesAImporter;

    List<Integer> id_utilisateurs;
    List<Integer> id_cvs;

    List<Map<String, Object>> Utilisateur;
    List<Map<String, Object>> Cv;

    List<Map<String, Object>> Formation;
    List<Map<String, Object>> ExperiencePro;
    List<Map<String, Object>> InformationsComp;

    List<Map<String, Object>> DonneesImporte_Utilisateur;
    List<Map<String, Object>> DonneesImporte_Cv;
    List<Map<String, Object>> DonneesImporte_Formation;
    List<Map<String, Object>> DonneesImporte_ExperiencePro;
    List<Map<String, Object>> DonneesImporte_InformationsComp;

    // Accesseurs ou mutateurs pour la base de donnée
    /**
     * Permet de récuperer toute les informations d'un utilisateur de puis la
     * base de données grace à l'id de l'utilisateur
     *
     * @param id_utilisateur
     * @throws SQLException
     */
    public void getUtilisateur(Integer id_utilisateur) throws SQLException {
        if (id_utilisateur != null && DaoSIO.getInstance().connexionActive()) {
            ResultSet res = DaoSIO.getInstance().requeteSelection("SELECT utilisateurs.id, statut, identifiant, utilisateurs.nom AS nom_user, prenom, num_telephone, courriel, date_de_naissance, photo, num_telephone_deux, site_web, num_rue, adresse, info_complementaire, code_postal, villes.nom AS nom_ville, pays.nom AS nom_pays FROM utilisateurs, villes, pays WHERE utilisateurs.id_ville = villes.id AND villes.id_pays = pays.id AND utilisateurs.id = '" + id_utilisateur + "'");

            if (res != null && res.isBeforeFirst()) {
                this.Utilisateur = resultSetToList(res);
            }
        }
    }

    /**
     * Permet de récuperer toute les informations d'un cv de puis la base de
     * donnée grace à l'id de l'utilisateur
     *
     * @param id_cv
     * @throws SQLException
     */
    public void getCV(Integer id_cv) throws SQLException {
        if (id_cv != null && DaoSIO.getInstance().connexionActive()) {
            ResultSet res = DaoSIO.getInstance().requeteSelection("SELECT * FROM cv WHERE id = " + id_cv);

            if (res != null && res.isBeforeFirst()) {
                this.Cv = resultSetToList(res);
            }
        }
    }

    /**
     * Permet de récuperer toute les informations d'une formation de puis la
     * base de donnée grace à l'id de l'utilisateur
     *
     * @throws SQLException
     */
    public void getFormation() throws SQLException {
        if (this.Cv != null && DaoSIO.getInstance().connexionActive()) {
            ResultSet res = DaoSIO.getInstance().requeteSelection("SELECT * FROM formation WHERE id_cv = " + this.Cv.get(0).get("id"));

            if (res != null && res.isBeforeFirst()) {
                this.Formation = resultSetToList(res);
            }
        }
    }

    /**
     * Permet de récuperer toute les informations d'une Expérience pro de puis
     * la base de donnée grace à l'id de l'utilisateur
     *
     * @throws SQLException
     */
    public void getExperiencePro() throws SQLException {
        if (this.Cv != null && DaoSIO.getInstance().connexionActive()) {
            ResultSet res = DaoSIO.getInstance().requeteSelection("SELECT * FROM experience_pro WHERE id_cv = " + this.Cv.get(0).get("id"));

            if (res != null && res.isBeforeFirst()) {
                this.ExperiencePro = resultSetToList(res);
            }
        }
    }

    /**
     * Permet de récuperer toutes les informations comp depuis la base de
     * données grâce à l'id de l'utilisateur
     *
     * @throws SQLException
     */
    public void getInformationsComp() throws SQLException {
        if (this.Cv != null && DaoSIO.getInstance().connexionActive()) {
            ResultSet res = DaoSIO.getInstance().requeteSelection("SELECT * FROM info_comp WHERE id_cv = " + this.Cv.get(0).get("id"));

            if (res != null && res.isBeforeFirst()) {
                this.InformationsComp = resultSetToList(res);
            }
        }
    }

    /**
     * Convertir le ResultSet en une liste de cartes, où chaque carte représente
     * une ligne avec columnNames et columValues
     *
     * @param res
     * @return
     * @throws SQLException
     */
    private List<Map<String, Object>> resultSetToList(ResultSet res) throws SQLException {
        ResultSetMetaData md = res.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        while (res.next()) {
            Map<String, Object> row = new HashMap<String, Object>(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnLabel(i), res.getObject(i));
            }
            rows.add(row);
        }
        return rows;
    }

    protected Boolean updateAllList(Integer id_utilisateur, Integer id_cv) {
        try {
            this.getUtilisateur(id_utilisateur);
            this.getCV(id_cv);
            this.getFormation();
            this.getExperiencePro();
            this.getInformationsComp();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ImportExport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Map<String, Object>> getDonneesImporte_Utilisateur() {
        return DonneesImporte_Utilisateur;
    }

    public List<Map<String, Object>> getDonneesImporte_Cv() {
        return DonneesImporte_Cv;
    }

    public List<Map<String, Object>> getDonneesImporte_Formation() {
        return DonneesImporte_Formation;
    }

    public List<Map<String, Object>> getDonneesImporte_ExperiencePro() {
        return DonneesImporte_ExperiencePro;
    }

    public List<Map<String, Object>> getDonneesImporte_InformationsComp() {
        return DonneesImporte_InformationsComp;
    }

    // et les deux méthodes qui sont héritées de IfaceImportExport
}
