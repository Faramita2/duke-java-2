
/**
 * Write a description of CaesarCrackerTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCrackerTest {
    public void tester() {
        CaesarCracker cc = new CaesarCracker();
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("message: " + message);
        String decrypted = cc.decrypt(message);
        System.out.println("decrypted: " + decrypted);
        int key = cc.getKey(message);
        System.out.println("key: " + key);
        
        CaesarCracker cca = new CaesarCracker('a');
        FileResource frp = new FileResource();
        String messageP = frp.asString();
        System.out.println("message: " + messageP);
        String decryptedP = cc.decrypt(messageP);
        System.out.println("decrypted: " + decryptedP);
        key = cca.getKey(messageP);
        System.out.println("key: " + key);
    }
}
