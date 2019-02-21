/*
As a company grows, employees come and go. You want to find the period of time where a company added the most number of people.
As input you're given H = [h1, h2, .., hn], a list of the number of employees working at the company on each day of the company's existence. Your job is to find the most number of employees added over some period of time. Each hi is a positive integer.

Example:

H = [2, 3, 10, 4, 6, 1, 7] 

Expected Answer: 8

Explanation:

From day 1 to day 3, the company added 8 employees, which is the most.

Example:

H = [5, 15, 1, 2, 12, 4]

Expected Answer: 11

Explanation:

Between days 3 and day 5, the company added 11 people. 

*/

public class Solution {

    // Complete the most_added function below.
    static int most_added(List<Integer> headcounts) {
        int max = 0, sum = 0;
        for(int i = 1; i < headcounts.size(); i++) {
            int curr = headcounts.get(i);
            int prev = headcounts.get(i-1);
            curr = curr - prev;
            if (curr > 0) {
                sum = curr + sum;
            } else {
                sum = 0;
            }
            if (sum > max) max = sum;
        }
        return max;
    }

	/*
		BOILER PLATE AND MAIN METHOD
	*/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int headcountsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> headcountsTemp = new ArrayList<>();

        IntStream.range(0, headcountsCount).forEach(i -> {
            try {
                headcountsTemp.add(bufferedReader.readLine().replaceAll("\\s+$", ""));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> headcounts = headcountsTemp.stream()
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int res = most_added(headcounts);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

