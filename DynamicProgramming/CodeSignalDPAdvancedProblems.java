package DynamicProgramming;

/**
 * Created by Matthieu J. Capuano on 31/05/2021.
 */
class CodeSignalDPAdvancedProblems {

    public static void main(String[] args) {

    }

    /**
     * You have a 2D binary matrix that's filled with 0s and 1s. In the matrix,
     * find the largest square that contains only 1s and return its area.
     *
     * Example
     * For
     * matrix = [
     *      ['1', '0', '1', '1', '1'],
     *      ['1', '0', '1', '1', '1'],
     *      ['1', '1', '1', '1', '1'],
     *      ['1', '0', '0', '1', '0'],
     *      ['1', '0', '0', '1', '0']
     * ]
     * the output should be
     * maximalSquare(matrix) = 9.
     *
     * Input/Output
     * - [execution time limit] 3 seconds (java)
     * - [input] array.array.char matrix
     *      Guaranteed constraints:
     *      0 ≤ matrix.length ≤ 100,
     *      1 ≤ matrix[i].length ≤ 100,
     *      0 ≤ matrix[i][j] ≤ 1.
     *  - [output] integer
     *      An integer that represents the area of the largest square in the
    *       given matrix that is composed only of 1s.
     */
    class maximalSquare {

        /**
         * MY SOLUTION: The problem with what I did is that I'm worrying about
         * the square size, as if it were an extra variable in the DP problem
         * in addition to the i, j coordinates at which the square starts.
         * In the top solution you'll see that we just iterate through the matrix
         * once, and count the biggest area for the any square that ends (bottom-right)
         * at that index.
         */
        // Let i be the row index, j be the column index, and d be the square size. Then the recurrence relation is:
        //      s(i, j, d) = matrixNotZeroAt(i, j) && s(i+1, j, d-1) && s(i, j+1, d-1) && s(i, j, d-1)
        boolean evaluateRecurrence(int i, int j, int d, char[][] matrix, boolean[][] subProbs) {
            return (matrix[i + d - 1][j + d - 1] != '0')
                    && subProbs[i + 1][j] && subProbs[i][j + 1] && subProbs[i][j];
        }

        int maximalSquare(char[][] matrix) {
            int numRows = matrix.length;
            if (numRows == 0) return 0;
            int numCols = matrix[0].length;
            if (numCols == 0) return 0;

            int biggestSquareSize = 0;
            Map<Integer, boolean[][]> subProblems = new HashMap<>();

            int minDim = numRows < numCols ? numRows : numCols;
            for (int d = 1; d <= minDim; d++) {
                boolean[][] smallerSquareSubs = subProblems.get(d - 1);

                int rowSize = numRows - d + 1, colSize = numCols - d + 1;
                boolean[][] currSubproblems= new boolean[rowSize][colSize];
                subProblems.put(d, currSubproblems);

                for (int i = 0; i < rowSize; i++) {
                    for (int j = 0; j < colSize; j++) {
                        currSubproblems[i][j] = (d == 1 && matrix[i][j] == '1')
                                                || (d != 1 && evaluateRecurrence(i, j, d, matrix, smallerSquareSubs));

                        if (currSubproblems[i][j])
                            biggestSquareSize = d;
                    }
                }
            }

            return biggestSquareSize * biggestSquareSize;
        }


        /**
         * Top java solution, no concern about the square size. Basically he
         * iterates through the matrix, and the value he sets at that point
         * (dp[i][j]) is the biggest area from above, left, or above-left
         * and then he adds one if the current is not '0'.
         * So basically at every index you have the bigest area formed by the
         * square of which this is the bottom right
         */
         int maximalSquareTopJavaSolution(char[][] matrix) {
            if(matrix.length == 0) return 0;
            int[][] dp = new int[matrix.length][matrix[0].length];
            int ret = 0;
            for(int i = 0; i < dp.length; i++) {
                for(int j = 0; j < dp[i].length; j++) {
                    if(matrix[i][j] == '0') continue;
                    int curr = Integer.MAX_VALUE;
                    if(i > 0) curr = Math.min(curr, dp[i-1][j]); else curr = 0;
                    if(j > 0) curr = Math.min(curr, dp[i][j-1]); else curr = 0;
                    if(i > 0 && j > 0) curr = Math.min(curr, dp[i-1][j-1]); else curr = 0;
                    dp[i][j] = curr + 1;
                    ret = Math.max(ret, dp[i][j]);
                }
            }
            return ret * ret;
        }

    }

    /**
     * This problem is explained very poorly, I have a basic solution that passes
     * 29/31 tests and I don't wanna pay to know the last ones, not sure why
     * this isn't just a trivial indexing problem.
     */
    class regularExpressionMatching {

    }
}
