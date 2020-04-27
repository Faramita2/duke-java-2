
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.FileResource;
import java.util.Arrays;
public class TestCaesarCipher {
    public int[] countLetters(String s) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (char ch : s.toCharArray()) {
            int index = alphabet.indexOf(Character.toLowerCase(ch));
            if (index != -1) {
                counts[index]++;
            }
        }
        
        return counts;
    }
    
    public int maxIndex(int[] counts) {
        int max = 0;
        for (int i = 0; i < counts.length; i++) {

            if (counts[i] > counts[max]) {
                max = i;
            }
        }
        
        return max;
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        int key = 18;
        CaesarCipher cc = new CaesarCipher(key);
        String message = fr.asString();
        System.out.println("Message: " + message);
        String encrypted = cc.encrypt(message);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        System.out.println("Decrypted: " + breakCaesarCipher(encrypted));
    }
    
    public String breakCaesarCipher(String input) {
        // find the encrypted key
        int[] counts = countLetters(input);
        System.out.println("counts: " + Arrays.toString(counts));
        int max = maxIndex(counts);
        System.out.println("max: " + max);
        int key;
        if (max - 4 < 0) {
            key = 26 - (4 - max);
        } else {
            key = max - 4;
        }
        
        System.out.println("key: " + key);
        
        CaesarCipher cc = new CaesarCipher(key);
        
        return cc.decrypt(input);
    }
    
    public void testBreakCaesarCipher() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println("Encrypted: " + encrypted);
        String decrypted = breakCaesarCipher(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
    
     public void testEncrypt() {
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key = 15;
        System.out.println("message: " + message);
        CaesarCipher cct = new CaesarCipher(key);
        String encrypted = cct.encrypt(message);
        System.out.println("encrypted: " + encrypted);
    }
}
