
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.FileResource;
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        mainKey1 = key1;
        mainKey2 = key2;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.alphabet = alphabet;
        
        this.shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        this.shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }
    
    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        
        for (int i = 0; i < input.length(); i++) {
            char ch = sb.charAt(i);
            char shiftedCh;
            int index = this.alphabet.indexOf(Character.toLowerCase(ch));

            if (index != -1) {
                
                if (i % 2 == 0) {
                    shiftedCh = this.shiftedAlphabet1.charAt(index);
                } else {
                    shiftedCh = this.shiftedAlphabet2.charAt(index);
                }
                
                if (Character.isLowerCase(ch)) {
                    sb.setCharAt(i, shiftedCh);
                } else {
                    sb.setCharAt(i, Character.toUpperCase(shiftedCh));
                }

            }
        }

        return sb.toString();
    }
    
    public String decrypt(String encrypted) {
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        
        return cct.encrypt(encrypted);
    }
}
