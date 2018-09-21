/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.rustem.hasher;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *Generationg hash values of given values with a given value
 * @author Rustem Azimov
 */
public class Function {
    static final String
                    MD5 = "MD5",
                    SHA1 = "SHA1",
                    SHA256 = "SHA256",
                    SHA512 = "SHA512"/*,
                    BCRYPT = "BCRYPT",
                    PBKDF2 = "PBKDF2"*/;

    private final static String problemResponse = "You need support\nContact with us";

    String hashText(String text, String methodName){
        switch(methodName){
            case MD5: return convertToMD5(text);
             case SHA1: return convertToSHA1(text);
              case SHA256: return convertToSHA256(text);
               case SHA512: return convertToSHA512(text);
                /*case BCRYPT: return convertToBCRYPT(text);
                 case PBKDF2: return convertToPBKDF2(text);*/
                    default: return problemResponse;
        }
    }

    
    /*Create a method for hasing with MD5*/
    private String convertToMD5(String text){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(text.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashedText = number.toString(16);
            while(hashedText.length() < 32){
                hashedText = "0" + hashedText;
            }
            return hashedText;
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("No such Algorithm in MD5");
        }
        /*If te program executes the following statement
                it means that Exception happened*/
        return problemResponse;
    }
   
    private String convertToSHA1(String text)
{
    String hashedText = "";
    try
    {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(text.getBytes("UTF-8"));
  hashedText = convertByteToHex(crypt.digest());//Convert from byte to HexaDecimal then Initialize to ^hashedText^
    }
    catch(NoSuchAlgorithmException | UnsupportedEncodingException e)
    {
        //If the jvp is here  Your program is already broken :)))
    }
    return hashedText;
}
  
  private String convertToSHA256(String base) {
    try{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(base.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    } catch(UnsupportedEncodingException | NoSuchAlgorithmException ex){
                       //If the jvp is here  Your program is already broken :)))
    }
        return problemResponse;
}

    private String convertToSHA512(String textToHash)
    {
        try {
            final MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            sha512.update(textToHash.getBytes());
            
            return convertByteToHex(sha512.digest());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
        }
        return problemResponse;
    }  
    /*private String convertToBCRYPT(String password_plaintext) {
		String salt = BCrypt.gensalt(12);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return(hashed_password);
	}

    private final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    // The following constants may be changed without breaking existing hashes.
    private final int SALT_BYTES = 24;
    private final int HASH_BYTES = 24;
    private final int PBKDF2_ITERATIONS = 1000;

    private final int ITERATION_INDEX = 0;
    private final int SALT_INDEX = 1;
    private final int PBKDF2_INDEX = 2;

    *//**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param   password    the password to hash
     * @return              a salted PBKDF2 hash of the password
     *//*
    public String convertToPBKDF2(String password)
    {
        return convertToPBKDF2(password.toCharArray());
    }

    *//**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param   password    the password to hash
     * @return              a salted PBKDF2 hash of the password
     *//*
    private String convertToPBKDF2(char[] password)
        
    {
        try {
            // Generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_BYTES];
            random.nextBytes(salt);
            
            // Hash the password
            byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTES);
            // format iterations:salt:hash
            return PBKDF2_ITERATIONS + ":" + convertByteToHex(salt) + ":" +  convertByteToHex(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                           //If the jvp is here  Your program is already broken :)))
        }
        return problemResponse;
    }
    private byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }*/
    private  String convertByteToHex(byte data[])
    {
        StringBuilder hexData = new StringBuilder();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++)
            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));

        return hexData.toString();
    }
}
