/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.components.importexport;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import static com.itextpdf.text.html.WebColors.getRGBColor;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pawel
 */
public class ImportExportPDF extends ImportExport {

    public ImportExportPDF(String cheminFichier, List<Integer> id_utilisateurs, List<Integer> id_cvs) {
        this.cheminFichier = cheminFichier;
        this.id_utilisateurs = id_utilisateurs;
        this.id_cvs = id_cvs;
    }

    @Override
    public Boolean exporterFichier() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        if (this.cheminFichier != null && this.id_utilisateurs != null && this.id_cvs != null && this.updateAllList(id_utilisateurs.get(0), id_cvs.get(0)) && this.Utilisateur != null && this.Utilisateur.size() == 1 && this.Cv != null && this.Cv.size() == 1) {
            try {
                Document document = new Document(PageSize.A4, 0, 0, 0, 0);

                /**
                 * Création de différents styles de police
                 */
                FontSelector selector = new FontSelector();
                Font f1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
                selector.addFont(f1);
                Font f2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 41);
                selector.addFont(f2);
                Font f3 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
                f3.setColor(getRGBColor("#318CE7"));
                selector.addFont(f3);
                Font f4 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24);
                selector.addFont(f4);
                Font f5 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
                f5.setColor(getRGBColor("#318CE7"));
                selector.addFont(f5);
                Font f6 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 1);
                f6.setColor(getRGBColor("#318CE7"));
                selector.addFont(f6);
                Font f7 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
                f7.setColor(getRGBColor("#A9A9A9"));
                selector.addFont(f7);

//                Image photo = Image.getInstance("https://kodejava.org/wp-content/uploads/2017/01/kodejava.png");
                Image photo = Image.getInstance((byte[]) this.Utilisateur.get(0).get("photo"));

                PdfWriter.getInstance(document, new FileOutputStream(this.cheminFichier));
                document.open();
                float[] columnWidths = {10f, 10f, 10f, 10f, 800f};
                PdfPTable Table = new PdfPTable(columnWidths);
                PdfPCell Plein = new PdfPCell(new Phrase(""));
                Plein.setBorderColor(BaseColor.WHITE);
                Table.setWidthPercentage(100);
                Table.setSpacingBefore(0f);
                Table.setSpacingAfter(0f);

                /**
                 * Travail dans la Premier Cellulue de la table
                 */
                PdfPTable Bleu = new PdfPTable(1);
                Bleu.setWidthPercentage(100);
                Bleu.setSpacingBefore(0f);
                Bleu.setSpacingAfter(0f);
                PdfPCell Bleu1 = new PdfPCell(new Phrase(""));
                Bleu1.setBackgroundColor(WebColors.getRGBColor("#318CE7"));
                Bleu1.setBorderColor(BaseColor.WHITE);
                Bleu1.setFixedHeight(842);
                Table.addCell(Bleu1);

                PdfPTable White = new PdfPTable(1);
                White.setWidthPercentage(100);
                White.setSpacingBefore(0f);
                White.setSpacingAfter(0f);
                PdfPCell White1 = new PdfPCell(new Phrase(""));
                White1.setBackgroundColor(BaseColor.WHITE);
                White1.setBorderColor(BaseColor.WHITE);
                White1.setFixedHeight(840);
                Table.addCell(White1);

                PdfPTable Blue = new PdfPTable(1);
                Blue.setWidthPercentage(100);
                Blue.setSpacingBefore(0f);
                Blue.setSpacingAfter(0f);
                PdfPCell Blue1 = new PdfPCell(new Phrase(""));
                Blue1.setBackgroundColor(WebColors.getRGBColor("#318CE7"));
                Blue1.setBorderColor(BaseColor.WHITE);
                Blue1.setFixedHeight(840);
                Table.addCell(Blue1);

                PdfPTable White2 = new PdfPTable(1);
                White2.setWidthPercentage(100);
                White2.setSpacingBefore(0f);
                White2.setSpacingAfter(0f);
                PdfPCell White21 = new PdfPCell(new Phrase(""));
                White21.setBackgroundColor(BaseColor.WHITE);
                White21.setBorderColor(BaseColor.WHITE);
                White21.setFixedHeight(840);
                Table.addCell(White21);

                PdfPCell C1 = new PdfPCell(new Phrase("Gris"));
                C1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                C1.setFixedHeight(840);

                /**
                 * Travail dans la Seconde Cellule de la table
                 */
                /**
                 * Création de la table Principale
                 */
                PdfPTable Table2 = new PdfPTable(1);
                Table2.setWidthPercentage(100);
                Table2.setSpacingBefore(0f);
                Table2.setSpacingAfter(0f);
                Table2.setHorizontalAlignment(Element.ALIGN_RIGHT);

                /**
                 * Création de la table avec les info et la photo
                 */
                float[] columnWidths3 = {500f, 200f};
                PdfPTable Table3 = new PdfPTable(columnWidths3);
                Table3.setWidthPercentage(100);
                Table3.setSpacingBefore(0f);
                Table3.setSpacingAfter(0f);

                /**
                 * Création de la barre bleu qui sépare chaque catégorie
                 */
                PdfPCell TraitBleu = new PdfPCell(new Phrase(""));
                TraitBleu.setBorderColor(BaseColor.WHITE);
                TraitBleu.setBackgroundColor(WebColors.getRGBColor("#318CE7"));
                TraitBleu.setFixedHeight(1f);

                /**
                 * Création de la cellule contenant les informations de
                 * l'utilisateur et la photo
                 */
                PdfPCell cell_PresentationUtilisateurPhoto = new PdfPCell(new Phrase(""));
                cell_PresentationUtilisateurPhoto.setBorderColor(BaseColor.WHITE);
                cell_PresentationUtilisateurPhoto.addElement(Table3);

                /**
                 * Création de la cellule avec les informations de l'utilisateur
                 */
                PdfPCell cell_Utilisateur = new PdfPCell(new Phrase(""));
                cell_Utilisateur.setBorderColor(BaseColor.WHITE);

                Paragraph para_Utilisateur = new Paragraph("");
                para_Utilisateur.add(new Paragraph((String) this.Utilisateur.get(0).get("prenom") + " " + (String) this.Utilisateur.get(0).get("nom_user"), f2));
                para_Utilisateur.add(new Paragraph((String) this.Utilisateur.get(0).get("num_rue").toString() + " " + (String) this.Utilisateur.get(0).get("adresse") + " " + (String) this.Utilisateur.get(0).get("info_complementaire"), f1));
                para_Utilisateur.add(new Paragraph((String) this.Utilisateur.get(0).get("code_postal").toString() + " " + (String) this.Utilisateur.get(0).get("nom_ville"), f1));
                para_Utilisateur.add(new Paragraph((String) this.Utilisateur.get(0).get("nom_pays"), f1));
                para_Utilisateur.add(new Paragraph((String) this.Utilisateur.get(0).get("courriel"), f1));
                para_Utilisateur.add(new Paragraph((String) this.Utilisateur.get(0).get("num_telephone"), f1));
                para_Utilisateur.add(new Paragraph((String) this.Utilisateur.get(0).get("num_telephone_deux"), f1));
                para_Utilisateur.add(new Paragraph((String) this.Utilisateur.get(0).get("site_web"), f1));
                para_Utilisateur.add(new Paragraph((String) this.Cv.get(0).get("titre") + " " + (String) this.Cv.get(0).get("description"), f7));

                cell_Utilisateur.addElement(para_Utilisateur);

                /**
                 * Creation de la Cellule Image
                 */
                PdfPCell cell_Photo = new PdfPCell(new Phrase(""));
                cell_Photo.setBorderColor(BaseColor.WHITE);
                cell_Photo.addElement(photo);

                /**
                 * Création de la catégorie Expérience Pro
                 */
                PdfPCell cell_ExperienceProTitre = new PdfPCell(new Phrase(""));

                Paragraph para_ExperienceProTitre = new Paragraph("EXPERIENCE PROFESSIONNELLE", f5);
                para_ExperienceProTitre.add(new Paragraph(" ", f6));

                cell_ExperienceProTitre.setBorderColor(BaseColor.WHITE);
                cell_ExperienceProTitre.addElement(para_ExperienceProTitre);

                PdfPCell cell_ExperiencePro = new PdfPCell(new Phrase(""));
                cell_ExperiencePro.setBorderColor(BaseColor.WHITE);

                for (Integer i = 0; i < this.ExperiencePro.size(); i++) {
                    String[] date_debut_split = this.ExperiencePro.get(i).get("annee_debut").toString().split("-");
                    String date_debut = date_debut_split[2] + "/" + date_debut_split[1] + "/" + date_debut_split[0];

                    String[] date_fin_split = this.ExperiencePro.get(i).get("annee_fin").toString().split("-");
                    String date_fin = date_fin_split[2] + "/" + date_fin_split[1] + "/" + date_fin_split[0];

                    Paragraph para_ExperiencePro = new Paragraph(date_debut + " - " + date_fin, f3);
                    para_ExperiencePro.add(new Paragraph((String) this.ExperiencePro.get(i).get("entreprise") + " - " + (String) this.ExperiencePro.get(i).get("adresse"), f1));
                    para_ExperiencePro.add(new Paragraph((String) this.ExperiencePro.get(i).get("description"), f1));

                    cell_ExperiencePro.addElement(para_ExperiencePro);
                }

                /**
                 * Création de la catégorie Formation
                 */
                PdfPCell cell_FormationTitre = new PdfPCell(new Phrase(""));

                Paragraph para_FormationTitre = new Paragraph("FORMATION", f5);
                para_FormationTitre.add(new Paragraph(" ", f6));

                cell_FormationTitre.setBorderColor(BaseColor.WHITE);
                cell_FormationTitre.addElement(para_FormationTitre);

                PdfPCell cell_Formation = new PdfPCell(new Phrase(""));
                cell_Formation.setBorderColor(BaseColor.WHITE);

                for (Integer j = 0; j < this.Formation.size(); j++) {
                    String[] date_debut_split = this.Formation.get(j).get("annee_debut").toString().split("-");
                    String date_debut = date_debut_split[2] + "/" + date_debut_split[1] + "/" + date_debut_split[0];

                    String[] date_fin_split = this.Formation.get(j).get("annee_fin").toString().split("-");
                    String date_fin = date_fin_split[2] + "/" + date_fin_split[1] + "/" + date_fin_split[0];

                    Paragraph para_Formation = new Paragraph(date_debut + " - " + date_fin, f3);
                    para_Formation.add(new Paragraph((String) this.Formation.get(j).get("nom") + " - " + (String) this.Formation.get(j).get("lieu"), f1));
                    para_Formation.add(new Paragraph((String) this.Formation.get(j).get("description"), f1));

                    cell_Formation.addElement(para_Formation);
                }

                /**
                 * Rajout de toutes les cellules et tables secondaires dans la
                 * table principale
                 */
                Table3.addCell(cell_Utilisateur);
                Table3.addCell(cell_Photo);

                Table2.addCell(cell_PresentationUtilisateurPhoto);
                Table2.addCell(cell_ExperienceProTitre);
                Table2.addCell(TraitBleu);
                Table2.addCell(cell_ExperiencePro);
                Table2.addCell(cell_FormationTitre);
                Table2.addCell(TraitBleu);
                Table2.addCell(cell_Formation);

                /**
                 * Création de la catégorie Informations comp
                 */
                for (Integer j = 0; j < this.InformationsComp.size(); j++) {
                    PdfPCell cell_InformationsCompTitre = new PdfPCell(new Phrase(""));

                    String intitule = (String) this.InformationsComp.get(j).get("intitule");

                    Paragraph para_InformationsCompTitre = new Paragraph(intitule.toUpperCase(), f5);
                    para_InformationsCompTitre.add(new Paragraph(" ", f6));

                    cell_InformationsCompTitre.setBorderColor(BaseColor.WHITE);
                    cell_InformationsCompTitre.addElement(para_InformationsCompTitre);

                    PdfPCell cell_InformationsComp = new PdfPCell(new Phrase(""));
                    cell_InformationsComp.setBorderColor(BaseColor.WHITE);

                    Paragraph para_InformationsComp = new Paragraph(this.InformationsComp.get(j).get("description").toString(), f1);

                    cell_InformationsComp.addElement(para_InformationsComp);

                    Table2.addCell(cell_InformationsCompTitre);
                    Table2.addCell(TraitBleu);
                    Table2.addCell(cell_InformationsComp);
                }

                /**
                 * Fin de rajout de toutes les cellules et tables secondaires
                 * dans la table principale
                 */
                Plein.addElement(Table2);
                Table.addCell(Plein);

                document.add(Table);

                document.close();

                return true;
            } catch (DocumentException | IOException ex) {
                Logger.getLogger(ImportExportPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    @Override
    public Boolean importerFichier() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        return null;
    }

}
