package hachage;

import java.math.BigInteger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMac {

	//////////////////////////     Algorithme de HMAC avec SHA     ////////////////////////////////////
	//																								 //
	//		L'algorithme de HMAC est un algorithme d'authentification prenant en paramètres			 //
	//		un message (value) et une clé d'authentification (key). L'algorithme utilise une		 //
	//		fonction de hachage comme SHA ou MD5 pour pouvoir calculer le HMAC correspondant.		 //
	//																								 //
	//		1) On choisit la fonction de hachage SHA suivant l'entier passé en paramètre			 //
	//		2) On convertit la clé de caractères en en tableau de bytes, pour pouvoir ensuite 		 //
	//		la rendre secrète avec la classe SecretKeySpec											 //
	//		3) On instance et on initialise le MAC suivant la méthode et la clé choisie				 //
	//		4) On récupère le HMAC du message en caractère, puis on le convertit en hexadécimal		 //
	//																								 //
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static String hmacShaXXX(String value, String key, int XXX) {
	    try {
	    	
	    	String method = "HmacSHA" + XXX;
	        byte[] keyBytes = key.getBytes();           
	        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, method);

	        Mac mac = Mac.getInstance(method);
	        mac.init(signingKey);

	        byte[] Hmac = mac.doFinal(value.getBytes("UTF-8"));
	        BigInteger bi = new BigInteger(1, Hmac); 

	        return bi.toString(16);
	        
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
