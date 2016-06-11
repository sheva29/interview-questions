
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WordFrequency {
    public List<String> getKMostFrequentWords(String words, int k) {
        Map<String, Integer> counts = new HashMap<String, Integer>();

        String[] wordArray = words.split(" ");
        for (String word : wordArray) {
            if (counts.containsKey(word)) {
                Integer value = counts.get(word);
                value++;
            } else {
                counts.put(word, 1);
            }
        }

        List<LinkedList<String>> countArray =
            new ArrayList<LinkedList<String>>(wordArray.length + 1);
        for (String word : counts.keySet()) {
            if (countArray[counts.get(word)] == null) {
                countArray[counts.get(word)] = new LinkedList<String>();
            }
            countArray[counts.get(word)].add(word);
        }

        List<String> mostFrequentWords = new LinkedList<String>();
        for (int i = counts.keySet().size() - 1; i >= 0
                 && mostFrequentWords.size() < k; --i) {
            LinkedList<String> wordsAtCount = countArray[i];
            if (wordsAtCount != null) {
                while (mostFrequentWords.size() < k) {
                    mostFrequentWords.add(wordsAtCount.poll());
                }
            }
        }

    }


    public static int main(String[] args) {

    }
}
