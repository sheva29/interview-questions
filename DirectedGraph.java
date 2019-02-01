/* Given a directed graph such as
 *   A -> B -> C
 *   v
 *   D -> E -> F -> G
 *   ^              v
 *    <-------------
 * find whether there is a route between two given nodes. For example:
 *    B ? D : False
 *    C ? A : True, you can go from A to C
 *    D ? F : True, you can go either from D to F or from F to D
 *
 * Input is a 2D array containing source and target nodes for each edge,
 * for example, if the first two edges are
 *    A -> B,
 *    B -> C,
 * the array contains
 *    char[][] edges = { {'A', 'B'},
 *                       {'B', 'C'} };
 */

import java.io.*;
import java.util.*;

class Solution {
  public static void main(String[] args) {
    char[][] edges = {{'A', 'B'},  // A -> B
                      {'B', 'C'},  // B -> C
                      {'A', 'E'},  // A -> E
                      {'D', 'A'},  // D -> A
                      {'E', 'F'},  // E -> F
                      {'F', 'G'},  // F -> G
                      {'G', 'H'},  // G -> H
                      {'H', 'E'},  // H -> E
                      {'I', 'J'}}; // I -> J
    assert  existRoute('A', 'C', edges) : "Test 01";
    assert  existRoute('C', 'A', edges) : "Test 02";
    assert  existRoute('D', 'E', edges) : "Test 03";
    assert !existRoute('B', 'E', edges) : "Test 04";
    assert  existRoute('E', 'G', edges) : "Test 05";
    assert !existRoute('D', 'J', edges) : "Test 06";
    assert !existRoute('C', 'I', edges) : "Test 07";
    System.out.println("Passed!");
  }

  public static boolean existRoute(char start, char dest, char[][] edges) {
    // Your code
    boolean [] visited = new boolean[edges.length];
    for (int r = 0; r < edges.length;r++) {
        if (edges[r][0] == start) {
          visited[r] = true;
          return dfs(edges, r-1, dest, edges[r][1], visited) || dfs(edges, r+1, dest, edges[r][1], visited);
      }
    }
    return false;
  }
  
  private static boolean dfs(char [][] edges, int r, char dest, char curr, boolean [] visited) {
    if (r < 0 || r > edges.length) return false;    
    if (visited[r]) return false;
    if (edges[r][1] == dest) return true;  
    
    if (edges[r][0] == curr) { 
      visited[r] = true;
      curr = edges[r][1];
      for (int i = 0; i < edges.length; i++) {
          boolean yes = dfs(edges, i, dest, curr, visited);
          if (yes) return true;
      }
    }
    return false;  
  }
}