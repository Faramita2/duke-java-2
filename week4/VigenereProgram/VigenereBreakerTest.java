
/**
 * Write a description of VigenereBreakerTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class VigenereBreakerTest {
    private VigenereBreaker vb;
    
    public VigenereBreakerTest() {
        vb = new VigenereBreaker();
    }
    
    private void testSliceStringHelper(String str, int from, int length, String expected) {
        String res = vb.sliceString(str, from, length);
        boolean isExcepted = res.equals(expected);
        
        System.out.print(str + " slice from index of [" + from + "] for [" + length + "] length is: '" + res + "' ");
        System.out.println("expected: '" + expected + "'\t" + isExcepted);
    }
    
    public void testSliceString() {
        testSliceStringHelper("abcdefghijklm", 0, 3, "adgjm");
        testSliceStringHelper("abcdefghijklm", 1, 3, "behk");
        testSliceStringHelper("abcdefghijklm", 2, 3, "cfil");
        testSliceStringHelper("abcdefghijklm", 0, 4, "aeim");
        testSliceStringHelper("abcdefghijklm", 1, 4, "bfj");
        testSliceStringHelper("abcdefghijklm", 2, 4, "cgk");
        testSliceStringHelper("abcdefghijklm", 3, 4, "dhl");
        testSliceStringHelper("abcdefghijklm", 0, 5, "afk");
        testSliceStringHelper("abcdefghijklm", 1, 5, "bgl");
        testSliceStringHelper("abcdefghijklm", 2, 5, "chm");
        testSliceStringHelper("abcdefghijklm", 3, 5, "di");
        testSliceStringHelper("abcdefghijklm", 4, 5, "ej");
        
    }
    
    public void testTryKeyLength() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println("encrypted: " + encrypted);
        int[] keys = vb.tryKeyLength(encrypted, 4, 'e');
        System.out.println("result: " + Arrays.toString(keys));
        // int[] exceptedKeys = new int[]{5, 11, 20, 19, 4};
        // System.out.println("expected: " + Arrays.toString(exceptedKeys));
    }
    
    public void testBreakVigenere() {
        vb.breakVigenere();
    }
    
    public void testReadDictionary() {
        FileResource fr = new FileResource();
        HashSet<String> dict = vb.readDictionary(fr);
        System.out.println("dict: " + dict);
    }
    
    public void testCountWords() {
        FileResource fr = new FileResource();
        HashSet<String> dict = vb.readDictionary(fr);

        String message = "I love you";
        int total = vb.countWords(message, dict);
        System.out.println("\"" + message + "\"" + " has " + total + " valid words.");
    }
    
    public void testBreakForLanguage() {
        FileResource fr = new FileResource();
        HashSet<String> dict = vb.readDictionary(fr);

        FileResource frm = new FileResource();
        //String message = frm.asString();
        // System.out.println("message: " + message);
        // VigenereCipher vc = new VigenereCipher(keys);
        String encrypted = frm.asString();
        System.out.println("encrypted: " + encrypted.split("\n")[0]);
        
        String decrypted = vb.breakForLanguage(encrypted, dict);
        System.out.println("decrypted: " + decrypted.split("\n")[0]);
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int keyLength = 38;
        int[] keys = vb.tryKeyLength(encrypted, keyLength, 'e');
        VigenereCipher vc = new VigenereCipher(keys);
        String decrypted = vc.decrypt(encrypted);
        // and count how many of the “words” in it are real words in English,
        // based on the dictionary passed in (use the countWords method you just wrote).
        FileResource frd = new FileResource();
        HashSet<String> dict = vb.readDictionary(frd);

        int count = vb.countWords(decrypted, dict);
        
        System.out.println("count: " + count);
    }
    
    public void testMostCommonCharIn() {
        FileResource frd = new FileResource();
        HashSet<String> dict = vb.readDictionary(frd);
        char commonChar = vb.mostCommonCharIn(dict);
        System.out.println("common key: " + commonChar);
    }
}
