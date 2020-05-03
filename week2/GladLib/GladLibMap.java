 

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private HashMap<String, ArrayList<String>> usedMap;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        ArrayList<String> sourceList = new ArrayList(
            Arrays.asList("adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit")
        );
        
        myMap = new HashMap();
        for (String sourceName : sourceList) {
            myMap.put(sourceName, readIt(source + "/" + sourceName + ".txt"));
        }

        usedMap = new HashMap();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (myMap.containsKey(label)) {
            return randomFrom(myMap.get(label));
        }

        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String label = w.substring(first+1,last);
        String sub = getSubstitute(label);
        
        if (usedMap.containsKey(label)) {
            while (usedMap.get(label).contains(sub)) {
                sub = getSubstitute(label);
            }
            usedMap.get(label).add(sub);
        } else {
            usedMap.put(label, new ArrayList<String>());
        }
       
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap() {
        int total = 0;
        for (String key : myMap.keySet()) {
            ArrayList<String> used = myMap.get(key);
            for (String word : myMap.get(key)) {
                if (!used.contains(word)) {
                    total++;
                }
            }
        }
        return total;
    }
    
    private int totalWordsConsidered() {
        int total = 0;
        for (String key : usedMap.keySet()) {
            ArrayList<String> used = usedMap.get(key);
            total += used.size();
        }
        return total;
    }
    
    public void makeStory(){
        usedMap.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nremains total: " + totalWordsInMap());
        System.out.println("\nused total: " + totalWordsConsidered());
    }
    


}
