
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.File;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordFilesListMap;
    
    
    public WordsInFiles() {
        wordFilesListMap = new HashMap();
    }
    
    private void addWordsFromFile(File f) {
        //  If a word is not in the map, then you must create a new ArrayList of type String with this word, 
        // and have the word map to this ArrayList. If a word is already in the map, then add the current filename to its ArrayList,
        // unless the filename is already in the ArrayList. You can use the File method getName to get the filename of a file.
        FileResource fr = new FileResource(f);
        String curFilename = f.getName();
        
        for (String word : fr.words()) {
            if (wordFilesListMap.containsKey(word)) {
                ArrayList<String> curFileList = wordFilesListMap.get(word);
                if (!curFileList.contains(curFilename)) {
                    curFileList.add(curFilename);
                }
            } else {
                ArrayList<String> newFileList = new ArrayList();
                newFileList.add(curFilename);
                wordFilesListMap.put(word, newFileList);
            }
        }
    }
    
    private void buildWordFileMap() {
        wordFilesListMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
        
        System.out.println("words count: " + wordFilesListMap.size());
        // printMap();
    }
    
    private void printMap() {
        for (String word : wordFilesListMap.keySet()) {
            ArrayList<String> filenames = wordFilesListMap.get(word);
            System.out.println(word + " has in these files: ");
            for (String filename : filenames) {
                System.out.println("\t" + filename + "\n");
            }
        }
    }
    
    private int maxNumber() {
        int max = -1;
        for (String key : wordFilesListMap.keySet()) {
            ArrayList curList = wordFilesListMap.get(key);
            int curSize = curList.size();
            if (curSize > max) {
                max = curSize;
            }
        }
        
        return max;
    }
    
    private ArrayList wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList();
         for (String key : wordFilesListMap.keySet()) {
            ArrayList curList = wordFilesListMap.get(key);
            if (curList.size() == number) {
                words.add(key);
            }
        }
        
        return words;
    }
    
    private void printFilesIn(String word) {
        if (wordFilesListMap.containsKey(word)) {
            System.out.println(word + " has in these files in this map: ");
            ArrayList<String> filenames = wordFilesListMap.get(word);
            for (String filename : filenames) {
                System.out.println("\t" + filename);
            }
        } else {
            System.out.println(word + "doesn't in this map.");
        }
    }
    
    public void tester() {
        buildWordFileMap();

        int fileNum = 4;
        ArrayList<String> words = wordsInNumFiles(fileNum);
        System.out.println(words.size() + " words has occur " + fileNum + " files.");
        
        fileNum = 7;
        words = wordsInNumFiles(fileNum);
        System.out.println(words.size() + " words has occur " + fileNum + " files.");
        // for (String word : words) {
         //   System.out.println("\t" + word);
        //}
        
        String seeWord = "tree";
        printFilesIn(seeWord);
        
        seeWord = "laid";
        printFilesIn(seeWord);
        
        int max = maxNumber();
        
        System.out.println("max:\t" + max);
    }
}
