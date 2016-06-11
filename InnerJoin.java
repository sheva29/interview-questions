import java.util.*;

public class InnerJoin {

    public static void main(String[] args) {
        List<List<Integer>> table1 = new LinkedList<List<Integer>>();
        List<List<Integer>> table2 = new LinkedList<List<Integer>>();

        table1.add(Arrays.asList(2, 1, 0, 3));
        table1.add(Arrays.asList(5, 1, 0, 3));
        table1.add(Arrays.asList(5, 8, 13, 3));
        table1.add(Arrays.asList(5, 16, -1, 8));
        table1.add(Arrays.asList(2, 12, 4, 3));
        table1.add(Arrays.asList(0, 1, 0, 3));
        table1.add(Arrays.asList(1, 0, 0, 3));

        table2.add(Arrays.asList(3, 0, 45, 3));
        table2.add(Arrays.asList(2, 13, 45, 8));
        table2.add(Arrays.asList(2, 13, 2, 8));
        table2.add(Arrays.asList(5, 13, 2, 8));

        // [ [2, 1, 0, 3, 13, 45, 8] ]
        List<List<Integer>> innerJoined = innerJoin(table1, table2);

        StringBuilder output = new StringBuilder();
        output.append("[ ");
        for (List<Integer> row : innerJoined) {
            output.append("[");
            for (Integer value : row) {
                output.append(value);
                output.append(",");
            }
            output.append("],\n");
        }

        output.append(" ]");

        System.out.println(output.toString());
    }

    public static List<List<Integer>> innerJoin(List<List<Integer>> table1,
                                                List<List<Integer>> table2) {
        Map<Integer, List<List<Integer>>> firstElementTable =
            new HashMap<Integer, List<List<Integer>>>();

        for (List<Integer> row : table1) {
            if (!firstElementTable.containsKey(row.get(0))) {
                firstElementTable.put(row.get(0), new LinkedList<List<Integer>>());
            }

            firstElementTable.get(row.get(0)).add(row);
        }


        List<List<Integer>> joinedTables = new LinkedList<List<Integer>>();
        for (List<Integer> row : table2) {
            Integer first = row.get(0);
            if (firstElementTable.containsKey(first)) {
                for (List<Integer> rowsToConcatenate : firstElementTable.get(first)) {
                    List<Integer> concatenated = new LinkedList<Integer>();
                    concatenated.addAll(rowsToConcatenate);
                    concatenated.addAll(row.subList(1, row.size()));
                    joinedTables.add(concatenated);
                }
            }
        }

        return joinedTables;
    }
}
