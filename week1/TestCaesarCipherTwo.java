
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.FileResource;
import java.util.Arrays;
public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start) {
        String s = "";
        for (int i = start; i < message.length(); i+=2) {
            s += message.charAt(i);
        }
        
        return s;
    }
    
    public int[] countLetters(String s) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = Character.toLowerCase(s.charAt(i));
            int dex = alphabet.indexOf(ch);
            if (dex != -1) {
                counts[dex]++;
            }
        }
       
        
        return counts;
    }
    
    public int maxIndex(int[] counts) {
        int max =0;
        for (int i = 0; i < counts.length; i++) {

            if (counts[i] > counts[max]) {
                max = i;
            }
        }
        
        return max;
    }
    
    public void simpleTests() {
        int key1 = 17;
        int key2 = 3;
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = cct.encrypt(message);
        System.out.println("Message: " + message);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = cct.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        
        System.out.println("Decrypted: " + breakCaesarCipher(encrypted));
    }
    
    public String breakCaesarCipher(String input) {

        String firstHalf = halfOfString(input, 0);
        String secondHalf = halfOfString(input, 1);
        
        System.out.println("First: " + firstHalf);
        System.out.println("Second: " + secondHalf);
        
        int firstKey = getKey(firstHalf);
        int secondKey = getKey(secondHalf);
        
        System.out.println("First key: " + firstKey);
        System.out.println("Second key: " + secondKey);
        
        CaesarCipherTwo cct = new CaesarCipherTwo(firstKey, secondKey);
        
        return cct.decrypt(input);
    }
    
    public int getKey(String s) {
        int[] counts = countLetters(s);
        System.out.println("counts: " + Arrays.toString(counts));
        int max = maxIndex(counts);
        int dKey = max - 4;
        if (dKey < 0) {
            return 26 - dKey;
        }
        
        return dKey;
    }
    
    public void testBreakCaesarCipher() {
        String input = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println("input: " + input);
        System.out.println("Message: " + breakCaesarCipher(input));
    }
    
    public void testFileBreakCaesarCipher() {
        FileResource fr = new FileResource();
        String input = fr.asString();

        System.out.println("Message: " + breakCaesarCipher(input));
    
    }
    
    public void testEncrypt() {
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key1 = 21;
        int key2 = 8;
        System.out.println("message: " + message);
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        String encrypted = cct.encrypt(message);
        System.out.println("encrypted: " + encrypted);
    }
    
    public void testDecrypt() {
        
    }
}
