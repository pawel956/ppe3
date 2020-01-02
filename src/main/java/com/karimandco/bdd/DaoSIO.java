/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karimandco.bdd;

/**
 *
 * @author c.nadal
 */
import com.karimandco.auth.Utilisateur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// constants
import com.pradyna.constants.Constants;

/**
 * Classe d'accès aux données contenant des membres statiques afin de manipuler
 * la BDD ON implémente ici le Design Pattern Singleton
 *
 * @author nc
 */
public class DaoSIO {

    /**
     * Membres static (de classe)
     *
     */
    private static String nomServeur = Constants.NOM_SERVEUR;
    private static String port = Constants.PORT;
    private static String nomBdd = Constants.NOM_BDD;
    private static String nomUtilisateur = Constants.NOM_UTILISATEUR;
    private static String motDePasse = Constants.MDP_UTILISATEUR;

    private static String chaineConnexion;

    //propriété non statique
    private Connection connexion;

    private static DaoSIO monDao = null;

    public Connection getConnexion() {
        return connexion;
    }

    /**
     * Constructeur privé ! pour construire un objet, il faut utiliser la
     * méthode publique statique getDaoSIO
     */
    private DaoSIO() {
        try {
            //Définition de l'emplacement de la BDD
            DaoSIO.chaineConnexion = "jdbc:mysql://" + DaoSIO.nomServeur + "/" + DaoSIO.nomBdd;

            //Création de la connexion à la BDD
            this.connexion = (Connection) DriverManager.getConnection(DaoSIO.chaineConnexion, DaoSIO.nomUtilisateur, DaoSIO.motDePasse);

        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permet d'obtenir l'objet instancié
     *
     * @return un Objet DaoSIO avec connexion active ... pour une certaine durée
     */
    public static DaoSIO getInstance() {
        if (DaoSIO.monDao == null) {
            DaoSIO.monDao = new DaoSIO();
        } else {
            if (!DaoSIO.monDao.connexionActive()) {
                DaoSIO.monDao = new DaoSIO();
            }
        }
        return DaoSIO.monDao;
    }

    /**
     * Cette méthode vérifie si il y a déjà une connexion active
     *
     * @return Boolean true or false
     */
    public Boolean connexionActive() {
        Boolean connexionActive = true;
        try {
            if (this.connexion != null && !this.connexion.isValid(0)) {
                connexionActive = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connexionActive;
    }

    /**
     * Cette méthode permet d'exécuter une requête SQL de type SELECT
     *
     * @param sql, comportera un ordre selec
     * @return ResultSet résultat de la requête SQL
     */
    public ResultSet requeteSelection(String sql) {
        try {
            if (this.connexion != null) {
                Statement requete = this.connexion.createStatement();
                return requete.executeQuery(sql);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Cette méthode permet d'exécuter une requête SQL de type INSERT, UPDATE,
     * DELETE, ...
     *
     * @param sql, comportera un ordre insert, update, select, alter, etc.
     * @return Integer le nombre de lignes impactées par la requête action
     */
    public Integer requeteAction(String sql) {
        try {
            if (this.connexion != null) {
                Statement requete = this.connexion.createStatement();
                return requete.executeUpdate(sql);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     *
     * @param table
     * @param args
     * @return
     * @throws java.sql.SQLException
     */
    public Integer getLastID(String table, String... args) throws SQLException {

        ResultSet result = this.requeteSelection("SELECT " + (!args.equals("") ? String.join(",", args) : "*")
                + " FROM " + table + " ORDER BY id DESC LIMIT 0, 1");

        if (result.next()) {
            return Integer.parseInt(result.getString("id"));
        }

        return null;
    }

    public List<Integer> getIdCV() {
        ResultSet lesResultats = DaoSIO.getInstance().requeteSelection("SELECT id FROM cv WHERE id_utilisateur='" + Utilisateur.getInstance().getId() + "'");
        List<Integer> idCVs = new ArrayList<>();

        try {
            while (lesResultats.next()) {
                idCVs.add(lesResultats.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idCVs;
    }
}
