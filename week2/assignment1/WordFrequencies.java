
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import edu.duke.FileResource;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        
        // putting the unique words found into myWords
        // For each word in the kth position of myWords, 
        // it puts the count of how many times that word occurs from the selected file into the kth position of myFreqs,
        // as was demonstrated in the lesson
        for (String word : fr.words()) {
            word = word.toLowerCase();
            if (!myWords.contains(word)) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int dex = myWords.indexOf(word);
                int num = myFreqs.get(dex);
                myFreqs.set(dex, num+1);
            }
        }
    }
    
    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myFreqs.size());
        int maxIndex = findIndexOfMax();
        String maxWord = myWords.get(maxIndex);
        int maxCount = myFreqs.get(maxIndex);
        
        for (int i = 0; i < myWords.size(); i++) {    
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }

        System.out.println("The word that occurs most often and its count are: '" + maxWord + "' " + maxCount); 
    }
    
    public int findIndexOfMax() {
        int maxIndex = -1;
        int max = 0;
        for (int i = 0; i < myWords.size(); i++) {
            int count = myFreqs.get(i);
            if (count > max) {
                maxIndex = i;
                max = count;
            }
        }
        
        return maxIndex;
    }
}
