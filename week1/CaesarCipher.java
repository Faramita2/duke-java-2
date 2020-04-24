
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;
public class CaesarCipher {
    public String encrypt(String input, int key) {
        // normal alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // get changed by key alphabet
        String changed = alphabet.substring(key) + alphabet.substring(0, key);
        
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
        // change every normal index character to changed index character
            char c = input.charAt(i);
            if (Character.isAlphabetic(c)) {
                int index = alphabet.indexOf(Character.toUpperCase(c));
                char changedChar = changed.charAt(index);
                if (Character.isUpperCase(c)) {
                    sb.setCharAt(i, Character.toUpperCase(changedChar));
                } else {
                    sb.setCharAt(i, Character.toLowerCase(changedChar));
                }

            }
        }
        
        return sb.toString();
    }
    
    public void testEncrypt() {
        String input = "FIRST LEGION ATTACK EAST FLANK!";
        int key = 23;
        String expected = "CFOPQ IBDFLK XQQXZH BXPQ CIXKH!";
        System.out.println("input: " + input + ", expected: " + expected + ", encrypted: " + encrypt(input, key));
        
        input = "First Legion";
        key = 23;
        expected = "Cfopq Ibdflk";
        System.out.println("input: " + input + ", expected: " + expected + ", encrypted: " + encrypt(input, key));
        
        input = "First Legion";
        key = 17;
        expected = "Wzijk Cvxzfe";
        System.out.println("input: " + input + ", expected: " + expected + ", encrypted: " + encrypt(input, key));        
    }
    
    public void testCaesar() {
        
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key = 15;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        // key1 is used to encrypt every other character with the Caesar Cipher algorithm
        // , starting with the first character
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                sb.setCharAt(i, encrypt(Character.toString(input.charAt(i)), key1).charAt(0));
            } else {
                sb.setCharAt(i, encrypt(Character.toString(input.charAt(i)), key2).charAt(0));
            }
        }
        // key2 is used to encrypt every other character
        // , starting with the second character
        return sb.toString();
    }
    
    public void testExcryptTwoKeys() {
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key1 = 8;
        int key2 = 21;
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);
    }
}
