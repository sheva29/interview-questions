/**
 * A very hungry rabbit is placed in the center of of a garden,
 * represented by a rectangular N x M 2D matrix.
 *
 * The values of the matrix will represent numbers of carrots
 * available to the rabbit in each square of the garden. If the garden
 * does not have an exact center, the rabbit should start in the
 * square closest to the center with the highest carrot count.
 *
 * On a given turn, the rabbit will eat the carrots available on the
 * square that it is on, and then move up, down, left, or right,
 * choosing the the square that has the most carrots. If there are no
 * carrots left on any of the adjacent squares, the rabbit will go to
 * sleep. You may assume that the rabbit will never have to choose
 * between two squares with the same number of carrots.
 *
 * Write a function which takes a garden matrix and returns the number
 * of carrots the rabbit eats eg.
 *
 * [[5, 7, 8, 6, 3],
 *  [0, 0, 7, 0, 4],
 *  [4, 6, 3, 4, 9],
 *  [3, 1, 0, 5, 8]]
 *
 * Should return
 *     27
 */

public class Garden {
    private final int mColumns;
    private final int mRows;
    private final int[][] mFoodQuantities;

    public Garden(int[][] foodQuantities) {
        mFoodQuantities = foodQuantities;
    }

    public int getFoodAtPosition(Position position) {
        return mFoodQuantities[position.getRow()][position.getColumn()];
    }

    public int eatFoodAtPosition(Position position) {
        int foodCount = getFoodAtPosition(position);
        mFoodQuantities[position.getRow()][position.getColumn()] = 0;
        return foodCount;
    }

    /**
     * Calculates the amount of carrots that a rabbit would consume.
     * Note that this will modify the array, leaving 0s wherever the rabbit at the food.
     * Note also that this assumes that negative carrot quantities are the same as 0.
     * @param foodQuantities a 2-D array mapping locations in a
     *     garden to a quantity of carrots available in that location.
     * @return The amount of food that a greedy rabbit starting in the center
     *     of the garden that gives up as soon as it is no longer next to food
     *     would consume.
     */
    public int calculateFoodConsumption() {
        int foodConsumed = 0;

        int rows = mFoodQuantities.length;
        if (rows == 0) {
            return foodConsumed;
        }
        int columns = foodQuantities[0].length;

        // find the possible starting indices
        List<Integer> possibleCenterRows = calculatePossibleCenterIndices(rows);
        List<Integer> possibleCenterColumns = calculatePossibleCenterIndices(columns);

        // Start from the center, continue until there is no food.
        Position position = chooseBestMove(
                enumerateMoves(possibleCenterRows, possibleCenterColumns));
        while (position != null) {
            foodConsumed += eatFoodAtPosition(position);

            position = calculateNext(foodQuantities, position);
        }

        return foodConsumed;
    }

    private List<Integer> calculatePossibleCenterIndices(int size) {
        return size % 2 == 0
                ? Arrays.asList(size / 2) : Arrays.asList(size / 2, size / 2 + 1);
    }

    private Position calculateNext(Position currentPosition) {
        int rowPosition = currentPosition.getRow();
        int columnPosition = currentPosition.getColumn();

        List<Position> possibleMoves = enumeratePositions(Arrays.asList(rowPosition),
                Arrays.asList(columnPosition + 1, columnPosition - 1));
        possibleMoves.addAll(enumerateMoves(Arrays.asList(rowPosition + 1, rowPosition - 1),
                        Arrays.asList(columnPosition)));

        return chooseBestMove(possibleMoves);
    }

    /**
     * Chooses the move that nets the rabbit the most carrots. Assumes that all Positions in
     * possible moves are valid, since they should be calculated by enumeratePositions.
     */
    private Position chooseBestMove(List<Position> possibleMoves) {
        Position bestMove = null;
        int mostFood = 0;
        for (Position nextPosition : possibleMoves) {
            int foodCount = getFoodAtPosition(nextPosition);
            if (foodCount > mostFood) {
                mostFood = foodCount;
                bestMove = nextPosition;
            }
        }

        return bestMove;
    }

    /**
     * Checks all possible positions from combining elements of
     * possibleRowIndices and possibleColumnIndices and chooses the one
     * that contains the most carrots.
     */
    private List<Position> enumeratePositions(List<Integer> possibleRowIndices,
            List<Integer> possibleColumnIndices) {
        List<Position> positions = new LinkedList<Position>();

        for (Integer row : possibleRowIndices) {
            for (Integer column : possibleColumnIndices) {
                if (row < foodQuantities.length
                    && column < foodQuantities[row].length) {
                    positions.add(new Position(row, column));
                }
            }
        }

        return positions;
    }

    public class Position {
        public final int mRow;
        public final int mColumn;

        public Position(int row, int column) {
            mRow = row;
            mY = column;
        }

        public int getX() {
            return mRow;
        }

        public int getY() {
            return mY;
        }
    }
}
