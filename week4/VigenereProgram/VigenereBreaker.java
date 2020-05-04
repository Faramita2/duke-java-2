import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        
        // This method returns a String consisting of every totalSlices-th character from message, 
        // starting at the whichSlice-th character
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i+=totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE 
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String str = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(str);
        }
        
        // System.out.println("key: " + Arrays.toString(key));
        return key;
    }

    public void breakVigenere () {
        // In particular, you should make a HashMap mapping Strings to a HashSet of Strings 
        // that will map each language name to the set of words in its dictionary. 
        HashMap<String, HashSet<String>> lanDictMap = new HashMap();
        // Then, you will want to read (using your readDictionary method) each of the dictionaries 
        // that we have provided (Danish, Dutch, English, French, German, Italian, Portuguese, and Spanish) 
        // and store the words in the HashMap you made. Reading all the dictionaries may take a little while,
        // so you might add some print statements to reassure you that your program is making progress. 
        String[] languages = new String[]{"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for (String language : languages) {
            System.out.println("Select the " + language + "'s dictionary file.");
            FileResource fr = new FileResource();
            HashSet<String> dict = readDictionary(fr);
            lanDictMap.put(language, dict);
        }
        
        for (String language : languages) {
            HashSet<String> d = lanDictMap.get(language);
            int i = 0;
            System.out.print(language);
            for (String word : d) {
                if (i == 9) {
                    break;
                }
                i++;
                System.out.print("\t" + word);
            }
            System.out.println();
        }
        // Once you have made that change, you will want to call breakForAllLangs, 
        // passing in the message (the code to read in the message is unchanged from before), 
        // and the HashMap you just created.
        
        //WRITE YOUR CODE HERE
        System.out.println("Selected encrypted file.");
        // Create a new FileResource using its default constructor (which displays a dialog for you to select a file to decrypt).
        FileResource fr = new FileResource();
        // Use the asString method to read the entire contents of the file into a String.
        String encrypted = fr.asString();
        // You should make a new FileResource to read from the English dictionary file that we have provided. 
        // Use the tryKeyLength method that you just wrote to find the key for the message you read in.
        // For now, you should just pass ‘e’ for mostCommon.
        // You should use your readDictionary method to read the contents of that file into a HashSet of Strings.
        // [You should create a new VigenereCipher, passing in the key that tryKeyLength found for you.]
        
        // You should use the breakForLanguage method that you just wrote to decrypt the encrypted message.
        // You should use the VigenereCipher’s decrypt method to decrypt the encrypted message.
        String decrypted = breakForAllLangs(encrypted, lanDictMap);
        
        // Finally, you should print out the decrypted message!
        System.out.println("decrypted: " + decrypted);    
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        // This method should first make a new HashSet of Strings, 
        HashSet<String> dict = new HashSet();
        // then read each line in fr (which should contain exactly one word per line), 
        for (String line : fr.lines()) {
        // convert that line to lowercase, and put that line into the HashSet that you created. 
            dict.add(line.toLowerCase());
        }

        // The method should then return the HashSet representing the words in a dictionary. 
        // All the dictionary files, including the English dictionary file, 
        // are included in the starter program you download. 
        // They are inside the folder called ‘dictionaries’.

        return dict;
    }
    
    public int countWords(String message, HashSet<String> dict) {
        int total = 0;
        // This method should split the message into words (use .split(“\\W+”), which returns a String array),
        for (String word : message.split("\\W+")) {
            if (dict.contains(word.toLowerCase())) {
                total++;
            }
        }
        //  iterate over those words, and see how many of them are “real words”—that is, how many appear in the dictionary. 
        // Recall that the words in dictionary are lowercase.
        
        // This method should return the integer count of how many valid words it found.
        return total;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dict) {
        ArrayList<Integer> counts = new ArrayList();
        // This method should try all key lengths from 1 to 100 
        // (use your tryKeyLength method to try one particular key length) to obtain the best decryption for each key length in that range.
        // For each key length, your method should decrypt the message (using VigenereCipher’s decrypt method as before),
        char commonKey = mostCommonCharIn(dict);
        System.out.println("common char: " + commonKey);
        for (int keyLength = 1; keyLength <= 100; keyLength++) {
            int[] keys = tryKeyLength(encrypted, keyLength, commonKey);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            // and count how many of the “words” in it are real words in English,
            // based on the dictionary passed in (use the countWords method you just wrote). 
            int count = countWords(decrypted, dict);
            counts.add(count);
        }
        

        // This method should figure out which decryption gives the largest count of real words,
        // and return that String decryption. 
        int max = 0;

        for (int c : counts) {
            if (c > max) {
                max = c;
            }
        }

        System.out.println("max words: " + max);
        int keyLen = counts.indexOf(max) + 1;
        System.out.println("key length: " + keyLen);

        int[] keys = tryKeyLength(encrypted, keyLen, commonKey);
        System.out.println("keys: " + Arrays.toString(keys));
        VigenereCipher vc = new VigenereCipher(keys);
        String res = vc.decrypt(encrypted);
        
        return res;
        // Note that there is nothing special about 100;
        // we will just give you messages with key lengths in the range 1–100. 
        // If you did not have this information, you could iterate all the way to encrypted.length(). 
        // Your program would just take a bit longer to run
    }
    
    public char mostCommonCharIn(HashSet<String> dict) {
        // This method should find out which character, of the letters in the English alphabet,
        // appears most often in the words in dictionary. It should return this most commonly occurring character.
        // Remember that you can iterate over a HashSet of Strings with a for-each style for loop.
        HashMap<Character, Integer> charMap = new HashMap();
        for (String word : dict) {
            for (char ch : word.toCharArray()) {
                if (charMap.containsKey(ch)) {
                    charMap.put(ch, charMap.get(ch) + 1);
                } else {
                    charMap.put(ch, 1);
                }
            }
        }
        
        int max = 0;
        char commonChar = 'e';
        
        for (char ch : charMap.keySet()) {
            int count = charMap.get(ch);
            if (count > max) {
                max = count;
                commonChar = ch;
            }
        }
        
        return commonChar;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        // try breaking the encryption for each language, and see which gives the best results!
        // Remember that you can iterate over the languages.keySet() to get the name of each language, 
        // and then you can use .get() to look up the corresponding dictionary for that language.
        // You will want to use the breakForLanguage and countWords methods 
        // that you already wrote to do most of the work (it is slightly inefficient to re-count the words here, 
        // but it is simpler, and the inefficiency is not significant). 
        // You will want to print out the decrypted message as well as the language that you identified for the message.
        HashMap<String, Integer> countsMap = new HashMap();
        for (String language : languages.keySet()) {
            System.out.println("cur language: " + language);
            HashSet<String> d = languages.get(language);
            int i = 0;
            for (String word : d) {
                if (i == 9) {
                    break;
                }
                i++;
                System.out.print("\t" + word);
            }
            System.out.println();
            
            String decrypted = breakForLanguage(encrypted, d); 
            int count = countWords(decrypted, d);
            countsMap.put(language, count);
        }
        
        System.out.println("countsMap: " + countsMap);
        int max = 0;
        String targetLanguage = null;
        for (String language : countsMap.keySet()) {
            int cur = countsMap.get(language);
            if (cur > max) {
                max = cur;
                targetLanguage = language;
            }
        }
        
        System.out.println("language: " + targetLanguage);
        
        HashSet<String> targetDict = languages.get(targetLanguage);
        
        return breakForLanguage(encrypted, targetDict);
    }
}
