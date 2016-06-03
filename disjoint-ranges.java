/**
 * A simple utility class with a min and a max to describe an inclusive range.
 */
public class Range {
    private int mMin;
    private int mMax;

    public Range(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Range is invalid");
        }
        mMin = min;
        mMax = max;
    }

    public int getMin() {
        return mMin;
    }

    public int getMax() {
        return mMax;
    }

    public int getMiddle() {
        return (mMin + mMax) / 2;
    }

    public void setMax(int newMax) {
        mMax = newMax;
    }

    public void setMin(int newMin) {
        mMin = newMin;
    }

    /**
     * Given a list of sorted, increasing, disjoint ranges, this
     * function adds the new range of integers into the list, merging
     * any to maintain the properties of sorted, increasing, and
     * disjoint.
     *
     * e.g., given the list of ranges
     *     [(-3, 5), (7, 8), (200, 500), (1000, 1001)]
     * and the new range
     *     (4, 7),
     * the function would output
     *     [(-3, 8), (200, 500), (1000, 1001)]
     *
     * Note that this function provides no guarantee of working if ranges does
     * not already satisfy the sorted, increasing, and disjoint properties.  if
     * necessary, the conditions could be validated beforehand using {@code
     * isSortedDisjointRanges}
     *
     * This function has complexity O(n)
     *
     * @param newRange the range to merge in.
     * @param ranges the sorted, increasing, disjoint list of ranges.
     * @return ranges after newRange is added.
     */
    public static List<Range> addNewRange(Range newRange, List<Range> ranges) {
        // startIndex points to last location where newRange.getMin() >
        // ranges.get(i).getMin(). O(log n) operation.
        int startIndex = findBeginIndex(newRange, ranges);
        // endIndex points to first location where newRange.getMax() <
        // ranges.get(i).getMax(). O(log n) operation.
        int endIndex = findEndIndex(newRange, ranges);

        // O(1) operation
        if (endIndex == -1) {
            // No such element means we can just subsume the whole end of the
            // list without adjusting newRange.
            endIndex = ranges.size() - 1;
        } else if (newRange.getMin() > ranges.get(endIndex).getMin()) {
            newRange.setMax(ranges.get(endIndex).getMax());
        } else {
            --endIndex;
        }

        // O(1) operation
        if (startIndex == -1) {
            // No such element means we can just subsume the whole beginning of
            // the list without adjusting newRange.
            startIndex = 0;
        } if (newRange.getMax() < ranges.get(startIndex).getMax()) {
            newRange.setMin(ranges.get(startIndex).getMin());
        } else {
            ++startIndex;
        }

        // O(n) operation
        if (startIndex <= endIndex) {
            ranges.remove(startIndex, endIndex + 1);
        }

        // O(1) operation.
        ranges.add(startIndex, newRange);
        return ranges;
    }

    /**
     * Validates the monotonically increasing, disjoint properties on ranges.
     * @param ranges the list of ranges to validate.
     */
    public static boolean isSortedDisjointRanges(List<Range> ranges) {
        for (int i = 1; i < ranges.size(); ++i) {
            if (ranges.get(i).getMin() < ranges.get(i - 1).getMax()) {
                return false;
            }
        }

        return true;
    }

    /**
     * A binary search implementation of finding the begin index. This function
     * has complexity O(log n)
     * @param newRange the relevant range that we want to find a position for
     * @param ranges the list of disjoint, sorted range that we want to find a
     *     position for newRange in.
     * @return the last location for which newRange.getMin() >
     *     ranges.get(i).getMin(), or -1 if there is none.
     */
    private static int findBeginIndex(Range newRange, List<Range> ranges) {
        return findIndex(newRange, ranges, 0, ranges.size() - 1, true);
    }

    /**
     * A simple, loop based implementation of finding the begin index. This
     * function has complexity O(n)
     * @param newRange the relevant range that we want to find a position for
     * @param ranges the list of disjoint, sorted range that we want to find a
     *     position for newRange in.
     * @return the last location for which newRange.getMin() >
     *     ranges.get(i).getMin(), or -1 if there is none.
     */
    private static int findBeginIndexIterative(Range newRange, List<Range> ranges) {
        int bestIndex = 0;
        for (int i = 0; i < ranges.size(); ++i) {
            if (ranges.get(i).getMin() < newRange.getMin()) {
                bestIndex = i;
            } else {
                break;
            }
        }

        return bestIndex;
    }

    /**
     * A binary search implementation of finding the end index. This function
     * has complexity O(log n)
     * @param newRange the relevant range that we want to find a position for
     * @param ranges the list of disjoint, sorted range that we want to find a
     *     position for newRange in.
     * @return the first location for which newRange.getMax() <
     *     ranges.get(i).getMax(), or -1 if there is none.
     */
    private static int findEndIndex(Range newRange, List<Range> ranges) {
        return findIndex(newRange, ranges, 0, ranges.size() - 1, false);
    }

    /**
     * A simple, loop-based implementation of finding the end index. This
     * function has complexity O(n)
     * @param newRange the relevant range that we want to find a position for
     * @param ranges the list of disjoint, sorted range that we want to find a
     *     position for newRange in.
     * @return the first location for which newRange.getMax() <
     *     ranges.get(i).getMax(), or -1 if there is none.
     */
    private static int findEndIndexIterative(Range newRange, List<Range> ranges) {
        int bestIndex = ranges.size() - 1;
        for (int i = ranges.size() - 1; i >= 0; --i) {
            if (ranges.get(i).getMax() > newRange.getMax()) {
                bestIndex = i;
            } else {
                break;
            }
        }

        return bestIndex;
    }

    /**
     * The work function of binary-search based index finding.
     * @param newRange the range we're trying to determine where to place.
     * @param ranges the set of disjoint, sorted, increasing ranges that we're
     *     putting newRange into.
     * @param leftBound the index to begin binary search from.
     * @param rightBound the index to end binary search at.
     * @param useMin
     */
    private static int findIndex(Range newRange, List<Range> ranges,
                                 int leftBound, int rightBound, boolean useMin) {
        if (leftBound < 0 || rightBound >= ranges.size()) {
            return -1;
        }

        int middleIndex = (leftBound + rightBound) / 2;
        Range middleRange = ranges.get(middleIndex);
        boolean isValidPosition = (useMin && newRange.getMin() > middleRange.getMin())
                || (useMax && newRange.getMax() < middleRange.getMax());

        if (leftBound == rightBound) {
            // if we have converged, there is still the possibility that no such
            // index existed.
            return isValidPosition ? leftBound : -1;
        }

        if (isValidPosition) {
            // If the position is valid, we need to make sure it's the last
            // valid position.  For min, this means finding the rightmost; for
            // max, it means finding the leftmost.
            nextIndex = findIndex(newRange, ranges, useMin ? middleIndex : leftBound,
                                  useMin ? rightBound : middleIndex, useMin);
            return nextIndex == -1 ? middleIndex : nextIndex;
        } else {
            // If the position is invalid, we need to search for a valid position. For
            // min, this means going left; for max, this means going right.
            return findIndex(newRange, ranges, useMin ? leftBound : middleIndex,
                             useMin ? middleIndex : rightBound, useMin);
        }
    }
}
