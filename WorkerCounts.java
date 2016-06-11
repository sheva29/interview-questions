import java.util.*;

public class WorkerCounts {

    public static void main(String[] args) {
        Map<Integer, Integer> entries = new HashMap<Integer, Integer>();

        entries.put(3, 7);
        entries.put(5, 7);
        entries.put(8, 13);
        entries.put(9, 12);
        entries.put(13, 16);
        entries.put(7, 17);

        Map<Integer, Integer> counts = getWorkerCountsByTime(entries);
        printMap(counts);
    }

    public static void printMap(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static Map<Integer, Integer> getWorkerCountsByTime(
            Map<Integer, Integer> clockTimes) {
        Map<Integer, Integer> timestampsToDelta = new TreeMap<Integer, Integer>();

        for (Map.Entry<Integer, Integer> entry : clockTimes.entrySet()) {
            if (!timestampsToDelta.containsKey(entry.getKey())) {
                timestampsToDelta.put(entry.getKey(), 1);
            } else {
                Integer delta = timestampsToDelta.get(entry.getKey());
                timestampsToDelta.put(entry.getKey(), ++delta);
            }

            if (!timestampsToDelta.containsKey(entry.getValue())) {
                timestampsToDelta.put(entry.getValue(), -1);
            } else {
                Integer delta = timestampsToDelta.get(entry.getValue());
                timestampsToDelta.put(entry.getValue(), --delta);
            }
        }

        Map<Integer, Integer> workerCountsByTime = new TreeMap<Integer, Integer>();
        int activeWorkerCount = 0;
        for (Map.Entry<Integer, Integer> entry : timestampsToDelta.entrySet()) {
            activeWorkerCount += entry.getValue();
            workerCountsByTime.put(entry.getKey(), activeWorkerCount);
        }

        return workerCountsByTime;
    }
}
