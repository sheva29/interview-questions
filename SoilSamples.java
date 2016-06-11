import java.util.*;

public class SoilSamples {
    private static final int SKIP_THRESHOLD = 5;

    public static void main(String[] args) {
        Output outputValue = validateSamples(args);

        System.out.println(outputValue.isIntegers);
        System.out.println(listToString(outputValue.missing));
        System.out.println(listToString(outputValue.duplicates));
    }

    public static String listToString(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (Integer value : list) {
            stringBuilder.append(value).append(" ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static class Output {
        public boolean isIntegers;
        public List<Integer> missing;
        public List<Integer> duplicates;

        public Output(boolean isIntegers,
                      List<Integer> missing, List<Integer> duplicates) {
            this.isIntegers = isIntegers;
            this.missing = missing;
            this.duplicates = duplicates;
        }
    }

    public static Output validateSamples(String[] samples) {
        List<Integer> integerSamples = new ArrayList<Integer>(samples.length);

        for (String sample : samples) {
            try {
                integerSamples.add(Integer.parseInt(sample));
            } catch (NumberFormatException e) {
                return new Output(false,
                        new ArrayList<Integer>(), new ArrayList<Integer>());
            }
        }

        Collections.sort(integerSamples);

        List<Integer> missing = new LinkedList<Integer>();
        List<Integer> duplicates = new LinkedList<Integer>();

        for (int i = 1; i < integerSamples.size(); ++i) {
            Integer previous = integerSamples.get(i - 1);
            Integer current = integerSamples.get(i);

            int difference = current - previous;

            if (current.equals(previous)) {
                duplicates.add(current);
            } else if (difference > 1 && difference < SKIP_THRESHOLD) {
                for (int missingInteger = previous + 1;
                         missingInteger < current; ++missingInteger) {
                    missing.add(missingInteger);
                }
            }
        }

        return new Output(true, missing, duplicates);
    }

}
