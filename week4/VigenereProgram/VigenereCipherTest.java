
/**
 * Write a description of VigenereCipherTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class VigenereCipherTest {
    public void tester() {
        int[] keys = new int[]{17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(keys);
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("message: " + message);
        String encrypted = vc.encrypt(message);
        System.out.println("enctypted: " + encrypted);
        String decrypted = vc.decrypt(encrypted);
        System.out.println("decrypted: " + decrypted);
    }
}
