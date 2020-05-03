
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.FileResource;
public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        characters = new ArrayList<>();
        counts = new ArrayList<>();
    }
    
    public void update(String person) {
        // This method should update the two ArrayLists
        // adding the character’s name if it is not already there
        // and counting this line as one speaking part for this person
        if (characters.contains(person)) {
            int dex = characters.indexOf(person);
            int count = counts.get(dex);
            counts.set(dex, count+1);
        } else {
            characters.add(person);
            counts.add(1);
        }
    }
    
    public void findAllCharacters() {
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int dex = line.indexOf('.');
            if (dex != -1) {
                String person = line.substring(0, dex);
                update(person);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        int total = 0;
        for (int i = 0; i < characters.size(); i++) {
            int count = counts.get(i);
            if (count >= num1 && count <= num2) {
                total++;
                // System.out.println(characters.get(i) + " " + counts.get(i));

            }
        }
        
        System.out.println(total + " character between " + num1 + " and " + num2);
    }
    
    public int findIndexOfMax() {
        int maxIndex = -1;
        int max = 0;
        for (int i = 0; i < counts.size(); i++) {
            int count = counts.get(i);
            if (count > max) {
                maxIndex = i;
                max = count;
            }
        }
        
        return maxIndex;
    }
    
    public int findIndexOfMax(ArrayList<Integer> skip) {
        int maxIndex = -1;
        int max = 0;
        for (int i = 0; i < counts.size(); i++) {
            int count = counts.get(i);
            if (count > max && skip.indexOf(i) == -1) {
                maxIndex = i;
                max = count;
            }
        }
        
        return maxIndex;
    }
    
    private void print() {
        for (int i = 0; i < counts.size(); i++) {
            System.out.println(characters.get(i) + "\t" + counts.get(i));
        }
    }
        
    public void tester() {
        findAllCharacters();
        int num1 = 1;
        int num2 = counts.size();
        charactersWithNumParts(num1, num2);
        int max = findIndexOfMax();
        System.out.println(characters.get(max) + " " + counts.get(max));
        ArrayList<Integer> skip = new ArrayList();
        skip.add(max);
        int second = findIndexOfMax(skip);
        System.out.println(characters.get(second) + " " + counts.get(second));
        skip.add(second);
        int third = findIndexOfMax(skip);
        System.out.println(characters.get(third) + " " + counts.get(third));
        
        num1 = 10;
        num2 = 15;
        charactersWithNumParts(num1, num2);
    }
}
