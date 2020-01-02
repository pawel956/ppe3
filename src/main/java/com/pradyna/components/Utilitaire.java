/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.components;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pawel
 */
public class Utilitaire {

    public static String getApiString(String siteWeb) {
        try {
            URL url = new URL(siteWeb);
            String contenuApi = new String();

            try (Scanner scan = new Scanner(url.openStream(), "UTF-8")) {
//            try (Scanner scan = new Scanner(url.openStream(), StandardCharsets.UTF_8)) {
                while (scan.hasNext()) {
                    contenuApi += scan.nextLine();
                }
            }

            return contenuApi;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Utilitaire.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            if (!ex.toString().contains("java.io.FileNotFoundException: http://api.zippopotam.us/")) {
                Logger.getLogger(Utilitaire.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public static String getFileString(String cheminFichier) {
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String line;
            String contenuFichier = null;

            while ((line = br.readLine()) != null) {
                if (contenuFichier == null) {
                    contenuFichier = line + "\n";
                } else {
                    contenuFichier += line + "\n";
                }
            }
            br.close();

            return contenuFichier;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utilitaire.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Utilitaire.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Boolean setFileString(String cheminFichier, String contenuFichier) {
        try (FileWriter file = new FileWriter(cheminFichier)) {
            file.write(contenuFichier);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Utilitaire.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static int countLines(String filename) throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                // bail out if nothing to read
                return 0;
            }

            // make it easy for the optimizer to tune this loop
            int count = 0;
            while (readChars == 1024) {
                for (int i = 0; i < 1024;) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            // count remaining characters
            while (readChars != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            return count == 0 ? 1 : count;
        }
    }

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

}
