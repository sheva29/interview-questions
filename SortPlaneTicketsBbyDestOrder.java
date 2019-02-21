import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

/*
Given a list of scrambled plane tickets, each with a starting city and end city, put together the path of the trip.

Input: [['Chennai', 'Banglore'], ['Bombay', 'Delhi'], ['Goa', 'Chennai'], ['Delhi', 'Goa'], ['Banglore', 'Beijing']]


Output: [['Bombay', 'Delhi'], ['Delhi', 'Goa'], ['Goa', 'Chennai'], ['Chennai', 'Banglore'], ['Banglore', 'Beijing']]
*/

class Solution {
  public static void main(String[] args) {
    
    String[][] input = {{"Chennai", "Banglore"}, {"Bombay", "Delhi"}, {"Goa", "Chennai"}, {"Delhi", "Goa"}, {"Banglore", "Beijing"}};
    String[][] result = collectFlights(input);
    
    assert result[0][0] == "Bombay"; 
    assert result[result.length-1][1] == "Beijing";
  }

  
  private static String[][] collectFlights (String[][] input) {
    Set<String> dest = new HashSet<>();
    Map<String,String> flights = new HashMap<>();
    for(String [] flight: input) {
      flights.putIfAbsent(flight[0], flight[1]);
      dest.add(flight[1]);
    }
    
    String origin = null;
    for(Map.Entry<String,String> entry: flights.entrySet()) {
      if (!dest.contains(entry.getKey())) {
        origin = entry.getKey();
      }
    }
    
    input[0][0] = origin;
    input[0][1] = flights.get(origin);
    
    for(int i = 1; i < input.length; i++) {
      String previous = input[i-1][1];
      input[i][0] = previous;
      input[i][1] = flights.get(previous);      
    }    
    return input;  
  }    
}
