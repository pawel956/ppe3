/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karimandco.auth;

import com.karimandco.bdd.DaoSIO;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Damien F, Pawel R
 */
public class Utilisateur {

    private static Utilisateur monUtilisateur = null;

    private static String identifiant;

    private Boolean estConnecte = false;

    private Integer id;
    private Integer statut;

    private String mdp;

    private String nom;
    private String prenom;
    private String numeroTelephone;
    private String courriel;
    private String dateNaissance;

    private InputStream photo;

    private String numeroTelephoneDeux;
    private Integer numeroRue;
    private String adresse;
    private String infoComplementaire;
    private Integer codePostal;
    private String ville;
    private String pays;
    private String siteWeb;

    private Utilisateur() {
    }

    public static String getIdentifiant() {
        return identifiant;
    }

    public static void setIdentifiant(String identifiant) {
        Utilisateur.identifiant = identifiant;
    }

    /**
     * Cette méthode renvoie la propriété estConnecte.
     *
     * @return Boolean true or false
     */
    public Boolean getEstConnecte() {
        return estConnecte;
    }

    /**
     * Cette méthode permet de définir la propriété estConnecte.
     *
     * @param estConnecte Boolean true or false
     */
    public void setEstConnecte(Boolean estConnecte) {
        this.estConnecte = estConnecte;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatut() {
        return statut;
    }

    public void setStatut(Integer statut) {
        this.statut = statut;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public InputStream getPhoto() {
        return photo;
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }

    public String getNumeroTelephoneDeux() {
        return numeroTelephoneDeux;
    }

    public void setNumeroTelephoneDeux(String numeroTelephoneDeux) {
        this.numeroTelephoneDeux = numeroTelephoneDeux;
    }

    public Integer getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(Integer numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getInfoComplementaire() {
        return infoComplementaire;
    }

    public void setInfoComplementaire(String infoComplementaire) {
        this.infoComplementaire = infoComplementaire;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    /**
     * Permet d'obtenir l'objet instancié.
     *
     * @return un Objet Utilisateur
     */
    public static Utilisateur getInstance() {
        if (Utilisateur.monUtilisateur == null) {
            Utilisateur.monUtilisateur = new Utilisateur();
        }
        return Utilisateur.monUtilisateur;
    }

    /**
     * Cette méthode permet d'initialiser les propriétés contenant les
     * informations de l'utilisateur en fesant une requête à la bdd avec
     * l'identifiant de l'utilisateur et de mettre true à la propriété
     * estConnecte.
     */
    public void chargerInformationsUtilisateur() {
        if (Utilisateur.identifiant != null) {
            ResultSet lesResultats = DaoSIO.getInstance().requeteSelection("SELECT *, villes.nom AS nom_ville, pays.nom AS nom_pays FROM utilisateurs, villes, pays WHERE utilisateurs.id_ville = villes.id AND villes.id_pays = pays.id AND utilisateurs.identifiant = '" + Utilisateur.getIdentifiant() + "'");

            try {
                if (lesResultats.next()) {
                    this.id = lesResultats.getInt("id");
                    this.statut = lesResultats.getInt("statut");
                    Utilisateur.identifiant = lesResultats.getString("identifiant");
                    this.mdp = null;
                    this.nom = lesResultats.getString("nom");
                    this.prenom = lesResultats.getString("prenom");
                    this.numeroTelephone = lesResultats.getString("num_telephone");
                    this.courriel = lesResultats.getString("courriel");
                    String[] date_split = lesResultats.getString("date_de_naissance").split("-");
                    this.dateNaissance = date_split[2] + "/" + date_split[1] + "/" + date_split[0];

                    this.photo = this.getPhotoBDD();

                    this.numeroTelephoneDeux = lesResultats.getString("num_telephone_deux");
                    this.numeroRue = lesResultats.getInt("num_rue");
                    this.adresse = lesResultats.getString("adresse");
                    this.infoComplementaire = lesResultats.getString("info_complementaire");
                    this.codePostal = lesResultats.getInt("code_postal");
                    this.ville = lesResultats.getString("nom_ville");
                    this.pays = lesResultats.getString("nom_pays");
                    this.siteWeb = lesResultats.getString("site_web");

                    this.estConnecte = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Cette méthode permet de mettre à jour les informations de l'utilisateur
     * sur la bdd et renvoie un booléen si la maj a été réussie.
     *
     * @return Boolean true or false
     */
    public Boolean updateInformationsUtilisateurBDD() {
        if (Utilisateur.identifiant != null) {
            try {
                String[] date_split = this.getDateNaissance().split("/");
                String data_split_ok = date_split[2] + "-" + date_split[1] + "-" + date_split[0];

                PreparedStatement maRequete;
                if (this.getMdp() == null) {
                    maRequete = DaoSIO.getInstance().getConnexion().prepareStatement("UPDATE utilisateurs SET nom='" + this.getNom() + "', prenom='" + this.getPrenom() + "', num_telephone='" + this.getNumeroTelephone() + "', courriel='" + this.getCourriel() + "', date_de_naissance='" + data_split_ok + "', photo=?, num_telephone_deux='" + this.getNumeroTelephoneDeux() + "', num_rue='" + this.getNumeroRue() + "', adresse='" + this.getAdresse() + "', info_complementaire='" + this.getInfoComplementaire() + "', id_ville='" + this.getIdVille() + "', site_web='" + this.getSiteWeb() + "' WHERE identifiant='" + Utilisateur.getIdentifiant() + "'");
                } else {
                    maRequete = DaoSIO.getInstance().getConnexion().prepareStatement("UPDATE utilisateurs SET mot_de_passe='" + this.getMdp() + "', nom='" + this.getNom() + "', prenom='" + this.getPrenom() + "', num_telephone='" + this.getNumeroTelephone() + "', courriel='" + this.getCourriel() + "', date_de_naissance='" + data_split_ok + "', photo=?, num_telephone_deux='" + this.getNumeroTelephoneDeux() + "', num_rue='" + this.getNumeroRue() + "', adresse='" + this.getAdresse() + "', info_complementaire='" + this.getInfoComplementaire() + "', id_ville='" + this.getIdVille() + "', site_web='" + this.getSiteWeb() + "' WHERE identifiant='" + Utilisateur.getIdentifiant() + "'");
                }
                maRequete.setBinaryStream(1, this.getPhoto());

                if (maRequete.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * Cette méthode permet d'afficher toutes les propriétés contenant les
     * informations de l'utilisateur (utile pour débug).
     */
    public void getAll() {
        System.out.println();
        System.out.println("============ Informations de l'utilisateur ============");
        System.out.println("Id : " + this.getId());
        System.out.println("Statut : " + this.getStatut());
        System.out.println("Identifiant : " + Utilisateur.getIdentifiant());
        System.out.println("Nom : " + this.getNom());
        System.out.println("Prénom : " + this.getPrenom());
        System.out.println("Numéro de téléphone : " + this.getNumeroTelephone());
        System.out.println("Courriel : " + this.getCourriel());
        System.out.println("Date de naissance : " + this.getDateNaissance());
//        System.out.println("Photo : " + this.getPhoto());
        System.out.println();
        System.out.println("Numéro de téléphone 2 : " + this.getNumeroTelephoneDeux());
        System.out.println("Site web : " + this.getSiteWeb());
        System.out.println("Pays : " + this.getPays());
        System.out.println("Code postal : " + this.getCodePostal());
        System.out.println("Ville : " + this.getVille());
        System.out.println("Numéro de rue : " + this.getNumeroRue());
        System.out.println("Adresse : " + this.getAdresse());
        System.out.println("Info complémentaire : " + this.getInfoComplementaire());
        System.out.println("=======================================================");
        System.out.println();
    }

    private Integer getIdVille() {
        Integer id_ville = null;
        Integer id_pays = this.getIdPays();
        try {
            ResultSet resultat_bdd = DaoSIO.getInstance().requeteSelection("SELECT id FROM villes WHERE id_pays='" + id_pays + "' AND code_postal='" + this.getCodePostal() + "' AND nom='" + this.getVille() + "'");
            if (resultat_bdd.next()) {
                id_ville = resultat_bdd.getInt("id");
            } else {
                Integer compteur = DaoSIO.getInstance().requeteAction("INSERT INTO villes (id_pays, code_postal, nom) VALUES (" + id_pays + ", " + this.getCodePostal() + ", '" + this.getVille() + "')");
                if (compteur > 0) {
                    ResultSet resultat_bdd_bis = DaoSIO.getInstance().requeteSelection("SELECT id FROM villes WHERE id_pays='" + id_pays + "' AND code_postal='" + this.getCodePostal() + "' AND nom='" + this.getVille() + "'");
                    if (resultat_bdd_bis.next()) {
                        id_ville = resultat_bdd_bis.getInt("id");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id_ville;
    }

    private Integer getIdPays() {
        Integer id_pays = null;
        try {
            ResultSet resultat_bdd = DaoSIO.getInstance().requeteSelection("SELECT id FROM pays WHERE nom='" + Utilisateur.getInstance().getPays() + "'");
            if (resultat_bdd.next()) {
                id_pays = resultat_bdd.getInt("id");
            } else {
                Integer compteur = DaoSIO.getInstance().requeteAction("INSERT INTO pays (nom) VALUES ('" + Utilisateur.getInstance().getPays() + "')");
                if (compteur > 0) {
                    ResultSet resultat_bdd_bis = DaoSIO.getInstance().requeteSelection("SELECT id FROM pays WHERE nom='" + Utilisateur.getInstance().getPays() + "'");
                    if (resultat_bdd_bis.next()) {
                        id_pays = resultat_bdd_bis.getInt("id");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id_pays;
    }

    public InputStream getPhotoBDD() {
        try {
            ResultSet lesResultats = DaoSIO.getInstance().requeteSelection("SELECT photo FROM utilisateurs WHERE identifiant='" + Utilisateur.getIdentifiant() + "'");
            if (lesResultats.next()) {
                return lesResultats.getBinaryStream("photo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
