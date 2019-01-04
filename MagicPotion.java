/* Problem Name is &&& Magic Potion &&& PLEASE DO NOT REMOVE THIS LINE. */

/*
Instructions to candidate.
  1) Run this code in the REPL to observe its behaviour. The
   execution entry point is main.
  2) Consider adding some additional tests in doTestsPass().
  3) Implement minimalSteps() correctly.
  4) If time permits, some possible follow-ups.

Question: 
Hermione is preparing a cheat-sheet for her final exam in Potions class. 
To create a potion, one must combine ingredients in a specific order, any of which may be repeated. 

As an example, consider the following potion which uses 4 distinct ingredients 
(A,B,C,D) in 11 steps: A, B, A, B, C, A, B, A, B, C, D

Hermione realizes she can save tremendous space on her cheat-sheet by introducing a 
special instruction, '*', which means "repeat from the beginning". 

Using these optimizations, Hermione is able to encode the potion above using only 6 characters: A,B,*,C,*,D - A,B,A,B,C,A,B,A,B,C,D

Your job is to write a function that takes as input an un-encoded potion and returns the 
minimum number of characters required to encode the potion on Hermione's Cheat Sheet.
*/

public class MagicPotion {

    static void testFindLengthOfMagicString() {
        findLengthOfMagicString("ABABCABABCD");
        findLengthOfMagicString("ABCDABCE");
        findLengthOfMagicString("ABCABCE");
        findLengthOfMagicString("ABABCD");
    }

    static void findLengthOfMagicString(String input) {
        char[] charInput = input.toCharArray();
        StringBuilder ans = new StringBuilder();
        ans.append(charInput[0]);
        int limit = 0;
        for (int i=1; i<charInput.length/2+1; i++) {
            String patternBefore = input.substring(0,i);
            String patternAfter = input.substring(i,i+i);
            if (patternBefore.equals(patternAfter)) {
                ans.append('*');
                i += patternAfter.length() - 1;
            } else {
                ans.append(charInput[i]);
            }
            limit = i;
        }
        ans.append(input.substring(limit+1, charInput.length));
    }
}