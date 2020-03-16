package hachage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.util.Scanner;

public class Sha1 {
	
	public static String sha1ToString(String chaine) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
		//////////////////////////     Algorithme de hachage SHA     ////////////////////////////////////
		
		// On choisit la fonction de hachage SHA, et on hache le message grâce à la méthode digest de la classe MessageDigest
		
		MessageDigest md = MessageDigest.getInstance("SHA"); 
        byte[] messageDigest = md.digest(chaine.getBytes()); 

        // Tableau de bytes en un BigInteger 
        
        BigInteger bi = new BigInteger(1, messageDigest); 

        // Passage à l'hexadecimal 
        
        String hash = bi.toString(16); 

        // Pour récupérer le hash sur 32 bits, on ajoute des 0 au début du hachage
        
        while (hash.length() < 32) { 
            hash = "0" + hash; 
        } 
        return hash; 
	}
	
	public static String sha1Bit(String chaine, int nbBitsAuDebut) throws NoSuchAlgorithmException, UnsupportedEncodingException {	
		
		/////////////////////     Conversion du hachage SHA en binaire    //////////////////////////////
		
		// On effectue le hachage du message
		
		String chaineSha = sha1ToString(chaine);

        // Conversion de la chaine hachée (String) en chaine binaire

        String binary = new BigInteger(chaineSha.getBytes()).toString(2);
        
        // On récupère en résultat que les n premiers bits

        String result ="";
        for (int j = 0;j < nbBitsAuDebut;j++) {
        	result += binary.charAt(j);
        }
        
        // Retourne le texte haché binaire
        
        return result; 
	}
	
	////////////     Algorithme de recherche de collisions dans un dictionnaire       /////////////////
	//																								 //
	//		Cet algorithme renvoie les collisions du hachage d'un mot avec celui d'autres mots  	 //
	//		d'un dictionnaire. A chaque fois qu'une collision est trouvée, on l'inscrit dans un		 //
	//		fichier output appelé "rechercheCollision.txt". L'algorithme prend donc en paramètre	 //
	//		un entier qui correspond à la taille de la collision, ainsi que le mot servant de		 //
	//		collision 																				 //
	//																								 //
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void trouverCollision(String chaine, int longueurBinaire) throws FileNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
		
		// On définit les fichiers sources et de destinations des résultats
		
		File file = new File("/home/freddy/eclipse-workspace/TP3-4/src/hachage/dico.txt");
		PrintWriter writer = new PrintWriter("src/rechercheCollision.txt", "UTF-8");
		int i = 0;
		
		System.out.println("En cours de calcul de collisions...");
		
		try (FileInputStream fis = new FileInputStream(file)){
			
			// Hachage du mot passé en paramètre
			
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(fis);
			String buff = "";
			String chaineSha = sha1Bit(chaine, longueurBinaire);
			
			while (scan.hasNextLine()) {
				buff = scan.nextLine();
				
				// Pour chaque mot du dictionnaire, on vérifie si son hachage correspond à celui du mot en paramètre
				// Si oui, on l'inscrit dans le fichier d'output
				
				String shaBuff = sha1Bit(buff, longueurBinaire);
				if (chaineSha.equals(shaBuff)) {
					writer.println(chaineSha + " --> " + buff + "       à l'essai n°  " + i);
				}
				i++;
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		writer.close();
		System.out.println("Fin recherche collision.");
	}
	
	
	
	
}
