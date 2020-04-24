
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        switch (Character.toUpperCase(ch)) {
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                return true;
            default:
                return false;
        }
    }
    
    public void testIsVowel() {
        char ch = 'F';
        System.out.println(ch + " is vowel: " + isVowel(ch));
        
        ch = 'a';
        System.out.println(ch + " is vowel: " + isVowel(ch));
        
        ch = 'E';
        System.out.println(ch + " is vowel: " + isVowel(ch));
    }
    
    public String replaceVowels(String phrase, char ch) {
        // new StringBuilder for new String
        StringBuilder sb = new StringBuilder(phrase);
        
        // for each character c in phrase
        for (int i = 0; i < phrase.length(); i++) {
            // if c is vowel, char at c index will be replaced ch
            if (isVowel(phrase.charAt(i))) {
                sb.setCharAt(i, ch);
            }
        }

        // return sb toString
        return sb.toString();
    }
    
    public void testReplaceVowels() {
        String s = "Hello";
        char ch = '*';
        System.out.println(s + " replace vowels to " + ch + ": " + replaceVowels(s, ch));
    }
    
    public String emphasize(String phrase, char ch) {
        // new StringBuilder for phrase
        StringBuilder sb = new StringBuilder(phrase);
        char oddCh = '*';
        char evenCh = '+';
        // for each location for phrase
        for (int i = 0; i < phrase.length(); i++) {
            // if index is even number, set sb specific location char to oddCh
            if (phrase.charAt(i) == ch) {
                if (i % 2 == 0) {
                    sb.setCharAt(i, oddCh);
                } else {
                    sb.setCharAt(i, evenCh);
                }
            }
        }

        // else evenCh
        // return sb.toString()
        return sb.toString();
    }
    
    public void testEmphasize() {
        String s = "dna ctgaaactga";
        char ch = 'a';
        System.out.println(emphasize(s, ch));
        
        s = "Mary Bella Abracadabra";
        System.out.println(emphasize(s, ch));
    }
}
