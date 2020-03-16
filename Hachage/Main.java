package hachage;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.*;

//////////////////////////
//
// Réponses aux questions
//
// Mot testé : "bonjour"
// Taille digest = 6 : 6 essais avant 1ère collision (abaca)
// Taille digest = 7 : 6 essais avant 1ère collision (abaca)
// Taille digest = 8 : 22 essais avant 1ère collision (abaissantes)
// Taille digest = 9 : 22 essais avant 1ère collision (abaissantes)
// Taille digest = 10 : 22 essais avant 1ère collision (abaissantes)
// 
// Propriété cassée de hachage : résistance aux collisions
//
// Mot testé : "poisson"
// Taille digest = 5 : 6 essais avant 1ère collision (acaba)
// Taille digest = 6 : 6 essais avant 1ère collision (abaca)
// Taille digest = 7 : 6 essais avant 1ère collision (abaca)
// Taille digest = 8 : 6 essais avant 1ère collision (abaca)
// Taille digest = 9 : 6 essais avant 1ère collision (acaba)
// Taille digest = 10 : 6 essais avant 1ère collision (acaba)
//
// En SHA512, le HMAC est beaucoup plus grand qu'avec le SHA1, d'où sa plus grande fiabialité
//
//////////////////////////

public class Main {

	public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
		String message = "bonjour";
		int longueurDigest = 5;
		String sha = Sha1.sha1ToString(message);
		
		System.out.println(sha);
		System.out.println(Sha1.sha1Bit(message, longueurDigest));
		
		Sha1.trouverCollision(message,longueurDigest);
		
		String texte = "Ceci est mon premier HMAC SHA1.";
		String cle = "Exercise2";
		
		System.out.println(HMac.hmacShaXXX(texte, cle,1));
		System.out.println(HMac.hmacShaXXX(texte, cle,512));

	}

}
