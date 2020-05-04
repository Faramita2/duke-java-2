
/**
 * Write a description of CaesarCipherTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipherTest {
    public void tester() {
        int key = 6;
        CaesarCipher cc = new CaesarCipher(key);
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("message: " + message);
        String encrypted = cc.encrypt(message);
        System.out.println("enctypted: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("decrypted: " + decrypted);
        
        char ch = 'z';
        System.out.println("letter: " + ch);
        char enCh = cc.encryptLetter(ch);
        System.out.println("enctypted: " + enCh);
        char deCh = cc.decryptLetter(enCh);
        System.out.println("decrypted: " + deCh);        
    }
}
