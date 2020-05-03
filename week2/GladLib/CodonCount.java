
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import edu.duke.FileResource;
public class CodonCount {
    private HashMap<String, Integer> codeonMap;
    private int starting;
    
    public CodonCount() {
        codeonMap = new HashMap<>();
    }
    
    private String getCodeon(String dna, int start) {

        if (start <= dna.length() - 3) {
            return dna.substring(start, start+3);
        }
        
        return "";
    }
    
    private void buildCodonMap(int start, String dna) {
        starting = start;
        codeonMap.clear();
        for (int i = start; i < dna.length(); i += 3) {
            String code = getCodeon(dna, i);
            if (code.trim().length() == 3) {
                if (codeonMap.containsKey(code)) {
                    int count = codeonMap.get(code);
                    codeonMap.put(code, count+1);
                } else {
                    codeonMap.put(code, 1);
                }
            }
            
        }

        // for (String key : codeonMap.keySet()) {
        //     System.out.println(key + "\t" + codeonMap.get(key));
        // }
    }
    
    private String getMostCommonCodon() {
        int max = 0;
        String maxKey = "";
        for (String key : codeonMap.keySet()) {
            int count = codeonMap.get(key);
            if (count > max) {
                max = count;
                maxKey = key;
            }
        }
        
        return maxKey;
    }
    
    public void printCodonCounts(int start, int end) {
        int size = codeonMap.size();
        System.out.println("Reading frame starting with " + starting + " results in " + size + " unique codons");
        String maxKey = getMostCommonCodon();
        int maxCount = codeonMap.get(maxKey);
        System.out.println("and most common codon is " + maxKey + " with count " + maxCount);
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are: ");
        for (String key : codeonMap.keySet()) {
            int count = codeonMap.get(key);
            if (count >= start && count <= end) {
                System.out.println(key + "\t" + count);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        System.out.println("dna: " + dna);
        int start = 0;
        
        buildCodonMap(start, dna);
        printCodonCounts(7, 7);
        
        start = 1;
        buildCodonMap(start, dna);
        printCodonCounts(1, 5);
        printCodonCounts(6, 6);
        
        start = 2;
        buildCodonMap(start, dna);
        printCodonCounts(1, 5);
    }
}
