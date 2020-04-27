
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        mainKey = key;
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }
    
    public String encrypt(String input) {
        
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
        // change every normal index character to changed index character
            char c = input.charAt(i);
            if (Character.isAlphabetic(c)) {
                int index = alphabet.indexOf(Character.toLowerCase(c));
                char changedChar = shiftedAlphabet.charAt(index);
                
                if (Character.isUpperCase(c)) {
                    changedChar = Character.toUpperCase(changedChar);
                } else {
                    changedChar = Character.toLowerCase(changedChar);
                }
                
                sb.setCharAt(i, changedChar);
            }
        }
        
        return sb.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        
        return cc.encrypt(input);
    }
    
    public void testEncrypt() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("message: " + message);
        String encrypted = encrypt(message);
        System.out.println("encrypted: " + encrypted);
        String decrypted = decrypt(encrypted);
        System.out.println("decrypted: " + decrypted);
    }
}
