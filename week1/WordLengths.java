
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.FileResource;
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        // for each words from fileResource
        for (String word : resource.words()) {
        // check first letter and last letter if they are non-letter,
            int len = word.length();
            
            // subtract length for these
            // set this word's length to lenth
            if (!Character.isLetter(word.charAt(0))) {
                len--;
            }
            
            if (len > 1 && !Character.isLetter(word.charAt(len-1))) {
                len--;
            }
        
            // counts[length] ++
            if (len < (counts.length - 1)) { 
                counts[len]++;
            } else {
                counts[counts.length-1]++;
            }
        }
    }
    
    public int indexOfMax(int[] values) {
        int max = Integer.MIN_VALUE;
        for (int v : values) {
            if (v > max) {
                max = v;
            }
        }
        
        return max;
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " length word counts: " + counts[i]);
        }
        
        System.out.println("The most common word length: " + indexOfMax(counts));
    }
}
