package com.gamesUP.gamesUP;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderWriter {

    public static void main(String[] args) {
        // Chemin du dossier à parcourir
        String folderPath = "src/main/java/com/gamesUP/gamesUP";  // Remplacer par le chemin de ton dossier
        // Nom du fichier de sortie
        String outputFile = "file.txt";
        // Appel de la méthode pour lire et écrire le contenu des fichiers
        try {
            readAndWriteFiles(new File(folderPath), outputFile);
            System.out.println("Le contenu des fichiers a été écrit dans " + outputFile + " avec succès.");
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    // Méthode récursive pour lire les fichiers dans un dossier et ses sous-dossiers
    public static void readAndWriteFiles(File folder, String outputFile) throws IOException {
        // Créer ou ouvrir le fichier de sortie
        FileWriter writer = new FileWriter(outputFile, true);  // true pour ajouter au fichier existant

        // Récupérer tous les fichiers et dossiers du répertoire actuel
        File[] listOfFiles = folder.listFiles();

        // Vérifier que le dossier n'est pas vide
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                // Si c'est un fichier, on lit et écrit son contenu
                if (file.isFile()) {
                    writer.write("--- Contenu de " + file.getName() + " ---\n");

                    // Lire le contenu du fichier et l'écrire dans le fichier de sortie
                    List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
                    for (String line : lines) {
                        writer.write(line + "\n");
                    }
                    writer.write("\n\n");  // Séparation entre les fichiers
                }
                // Si c'est un dossier, on appelle la méthode récursive pour parcourir le sous-dossier
                else if (file.isDirectory()) {
                    writer.write("--- Entrée dans le dossier " + file.getName() + " ---\n");
                    readAndWriteFiles(file, outputFile);  // Récursion pour les sous-dossiers
                }
            }
        }
        writer.close();  // Fermer le FileWriter
    }
}