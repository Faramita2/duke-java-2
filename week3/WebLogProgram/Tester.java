
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("short-test_log");
        analyzer.printAll();
    }
    
    public void testCountUniqueIPs() {
        // complete method
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        int ipNum = analyzer.countUniqueIPs();
        System.out.println("ips: " + ipNum);
    }
    
    public void testPrintAllHigherThanNum() {
        // complete method
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");
        int statusCode = 200;
        analyzer.printAllHigherThanNum(statusCode);
        
        System.out.println("----\n");
        statusCode = 300;
        analyzer.printAllHigherThanNum(statusCode);
        
        System.out.println("----\n");
        statusCode = 302;
        analyzer.printAllHigherThanNum(statusCode);
        
        System.out.println("----\n");
        statusCode = 400;
        analyzer.printAllHigherThanNum(statusCode);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        String someday = "Sep 24";
        ArrayList<String> ips = analyzer.uniqueIPVisitsOnDay(someday);
        System.out.println("num: " + ips.size());
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        int low = 200;
        int high = 299;
        System.out.println("size: " + analyzer.countUniqueIPsInRange(low, high));
       
    }
    
    public void testCountVisitsPerIP() {
        
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog-short_log");
        System.out.println(analyzer.countVisitsPerIP());
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        HashMap<String, Integer> ips = analyzer.countVisitsPerIP();
        System.out.println("max: " + analyzer.mostNumberVisitsByIP(ips));
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        HashMap<String, Integer> ips = analyzer.countVisitsPerIP();
        System.out.println(analyzer.iPsMostVisits(ips));
    }
    
    public void testIPsForDays() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> map = analyzer.iPsForDays();
        
        for (String key : map.keySet()) {
            System.out.println(key + "\t" + map.get(key));
            
        }
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = analyzer.iPsForDays();
        System.out.println("max:\t" + analyzer.dayWithMostIPVisits(map));
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = analyzer.iPsForDays();
        String date = "Sep 29";
        System.out.println(analyzer.iPsWithMostVisitsOnDay(map, date));
    }
}
