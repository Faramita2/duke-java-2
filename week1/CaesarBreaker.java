
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Arrays;
import edu.duke.FileResource;
public class CaesarBreaker {
    public String halfOfString(String message, int start) {
        String s = "";
        for (int i = start; i < message.length(); i+=2) {
            s += message.charAt(i);
        }
        
        return s;
    }
    
    public void testHalfOfString() {
        String s = "Qbkm Zgis";
        int key = 0;
        System.out.println("half string: " + halfOfString(s, key));
        
        key = 1;
        System.out.println("half string: " + halfOfString(s, key));
    }
    
    public int getKey(String s) {
        int[] counts = countLetters(s);
        int index = maxIndex(counts);
        if (index - 4 < 0) {
            return 26 - (4 - index);
        }
        
        return index - 4;
    }
    
    public void testGetKey() {
        String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(message, 23);
        
        System.out.println("key: " + getKey(encrypted));
    }
    
    public String decryptTwoKeys(String encrypted) {
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        System.out.println("First: " + firstHalf);
        System.out.println("Second: " + secondHalf);
        int firstKey = getKey(firstHalf);
        int secondKey = getKey(secondHalf);
        System.out.println("First key: " + firstKey);
        System.out.println("Second key: " + secondKey);
        CaesarCipher cc = new CaesarCipher();
        
        return cc.encryptTwoKeys(encrypted, 26-firstKey, 26-secondKey);
    }
    
    public String decryptTwoKeys(String encrypted, int key1, int key2) {
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        System.out.println("First: " + firstHalf);
        System.out.println("Second: " + secondHalf);
        
        CaesarCipher cc = new CaesarCipher();
        
        return cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
    }
    
    public void testDecrypTwoKeys() {
        CaesarCipher cc = new CaesarCipher();
        String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        int key1 = 8;
        int key2 = 21;
        String encrypted = cc.encryptTwoKeys(message, key1, key2);
        System.out.println("Encrypted: " + encrypted);
        String res = decryptTwoKeys(encrypted);
        System.out.println("Decrypted: " + res);
        
        encrypted = "Top ncmy qkff vi vguv vbg ycpx";
        res = decryptTwoKeys(encrypted, 2, 20);
        System.out.println("Decrypted: " + res);
        
        encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        res = decryptTwoKeys(encrypted);
        System.out.println("Decrypted: " + res);
       
    }
    
    public void testDecryptTwoKeysFile() {
        CaesarCipher cc = new CaesarCipher();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println("Eecrypted: " + encrypted);
        String res = decryptTwoKeys(encrypted);
        System.out.println("Decrypted: " + res);
       
    }
    
    public int[] countLetters(String s) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (char ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                int index = alphabet.indexOf(Character.toLowerCase(ch));
                counts[index]++;
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
    
    public String decrypt() {
        CaesarCipher cc = new CaesarCipher();
        int key = 2;
        String encrypted = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        String message = cc.encrypt(encrypted, 26 - key);
        System.out.println("Encrpted message: " + message);
        
        // get letter counts
        int[] counts = countLetters(message);

        int maxIndex = maxIndex(counts);

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int eIndex = alphabet.indexOf('e');
        
        int dKey;
        if ((maxIndex - eIndex) < 0) {
            dKey = 26 - (eIndex - maxIndex);
        } else {
            dKey = maxIndex - eIndex;
        }
        System.out.println("Dkey: " + dKey);


        System.out.println("Primary: " + cc.encrypt(message, 26 - dKey));
        
        
        return "";
    }
    
    public String decrypt(String s) {
        CaesarCipher cc = new CaesarCipher();
        
        // get letter counts
        int[] counts = countLetters(s);

        int maxIndex = maxIndex(counts);

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int eIndex = alphabet.indexOf('e');
        
        int dKey;
        if ((maxIndex - eIndex) < 0) {
            dKey = 26 - (eIndex - maxIndex);
        } else {
            dKey = maxIndex - eIndex;
        }

        return cc.encrypt(s, 26 - dKey);
    }
    
    public void testDecrypt() {
        decrypt();
    }
}
