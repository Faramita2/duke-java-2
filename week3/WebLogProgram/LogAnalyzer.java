
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             records.add(WebLogParser.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (!uniqueIPs.contains(ip)) {
                 uniqueIPs.add(ip);
             }
         }
         
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             if (statusCode > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> ips = new ArrayList();
         for (LogEntry le : records) {
             String curDate = le.getAccessTime().toString();
             String ip = le.getIpAddress();
             if (curDate.indexOf(someday) != -1 && !ips.contains(ip)) {
                 ips.add(ip); 
             }
         }
         
         return ips;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> ips = new ArrayList();
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             String ip = le.getIpAddress();
             if ((statusCode >= low && statusCode <= high)
                    && !ips.contains(ip)) {
                 ips.add(ip); 
             }
         }
         
         return ips.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> ips = new HashMap();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (!ips.containsKey(ip)) {
                 ips.put(ip, 1); 
             } else {
                 ips.put(ip, ips.get(ip) + 1);
             }
         }
         
         return ips;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> ips) {
        int max = 0;
        for (String ip : ips.keySet()) {
            int count = ips.get(ip);
            if (count > max) {
                max = count;
            }
            
        }
        return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ips) {
         int max = mostNumberVisitsByIP(ips);
         ArrayList<String> ipList = new ArrayList();
         for (String ip : ips.keySet()) {
             int count = ips.get(ip);   
             if (count == max && !ipList.contains(ip)) {
                 ipList.add(ip);
             }
         }
         return ipList;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> dayIpMap = new HashMap();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             String date = le.getAccessTime().toString();
             System.out.println("date: " + date);
             String key = date.substring(4, 10);
             ArrayList<String> curList;
             if (!dayIpMap.containsKey(key)) {
                 curList = new ArrayList();
                 curList.add(ip);
                 dayIpMap.put(key, curList);
             } else {
                 curList = dayIpMap.get(key);
                 curList.add(ip);
             }
         }

         return dayIpMap;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
         int max = 0;
         String maxDay = null;
         for (String day : map.keySet()) {
             int cur = map.get(day).size();
             if (cur > max) {
                 max = cur;
                 maxDay = day;
             }
             
         }
         
         return maxDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) {
         ArrayList<String> list = new ArrayList();
         
         // if map has the day's data, analyze data
         if (map.containsKey(day)) {
             // get all ip on that day
             ArrayList<String> ips = map.get(day);
             // count every ip's visit and save them to a map
             HashMap<String, Integer> ipVisitMap = new HashMap();
             
             for (String ip : ips) {
                 if (!ipVisitMap.containsKey(ip)) {
                     ipVisitMap.put(ip, 1);
                 } else {
                     ipVisitMap.put(ip, ipVisitMap.get(ip) + 1);
                 }
             }
             
             // find the max visit times save to (max)
             int max = 0;
             for (String ip : ipVisitMap.keySet()) {
                 int cur = ipVisitMap.get(ip);
                 if (cur > max) {
                     max = cur;
                 }
             }
             
             // iterate all ipVisitMap, find ip that visit time equals to max
             // add it to list
             for (String ip : ipVisitMap.keySet()) {
                 if (ipVisitMap.get(ip) == max) {
                     list.add(ip);
                 }
             }
             
             
         }
         // else empty list 
         return list;
     }
}
