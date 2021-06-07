
// Asked during AWS internal transfer June 4, 2021.
class Print2DArrayInSpiral {
    /**
     * Input:
     * r = 4, c = 4
     * matrix[][] = {{1, 2, 3, 4},
     *               {5, 6, 7, 8},
     *               {9, 10, 11, 12},
     *               {13, 14, 15,16}}
     * Output:
     * 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
    */
    public void printSpiral(int[][] matrix) {
        if (matrix.length == 0)
            return;
        int r = matrix.length;;
        int c = matrix[0].length;

        // 1. Iterate through row up to maxCol
        // 2. When hit end of row, decrement maxCol, iterate DOWN the column
        // 3. When hit maxRow, increment maxRow, iterate DOWN the row
        // 4. When hit minCol, increment minCol, iterate UP the column
        // 5. When hit minRow, decrement minRow, iterate RIGHT row
        // Terminate when maxCol = middle - 1;
        boolean completed = false;
        int i = 0, j = 0;
        int minCol = 0, maxCol = c;
        int minRow = 0, maxRow = r;
        bolean iterateRightRow = true, iterateDownCol = false;
        boolean iterateLeftRow = flase, iterateUpCol = false;
        while (maxCol > Math.ceil(r / 2)) { // Can update this condition to be more clean later
            if (iterateRightRow) {
                printRow(matrix, i, minCol, maxCol, true);
                j = maxCol; // 4
                iterateRightRow = false;
                iterateDownCol = true;
                minRow++; // 1
            } else if (iterateDownCol) {
                printDownCol(matrix, j - 1, minRow, maxRow, true);
                i = maxRow; // 4
                iterateDownCol = false;
                iterateLeftRow = true;
                maxCol--; // 3
            } else if (iterateLeftRow) {
                printLeftRow(matrix, i - 1, minCol, maxCol - 1, false);
                j = minCol; // 0
                iterateDownCol = false;
                iterateUpCol = true;
                maxRow--; // 3
            } else if (iterateUpCol) {
                printCol(matrix, j, minRow, maxRow - 1, false);
                iterateUpCol = false;
                iterateRightRow = true;
                minCol++;
            }
            // Check terminating condition -> Update completed
        }
    }

    private void printRow(int[][] matrix, int p, int minCol, int maxCol, int right) {
        if (right) {
            for (minCol; minCol < maxCol - 1; minCol++) {
                System.out.print(matrix[p][minCol] + " ");
            }
        } else {
            for (maxCol; maxCol > minCol; maxColl--) {
                System.out.print(matrix[p][maxCol] + " ");
            }
        }
    }

    private void printCol(int[][] matrix, int p, int minRow, int maxRow, int down) {
        if (down) {
            for (minRow; minRow < maxRow - 1; minRow++) {
                System.out.print(matrix[minRow][p] + " ");
            }
        } else {
            for (maxRow; maxRow > minRow; maxRow--) {
                System.out.print(matrix[maxRow][p] + " ");
            }
        }
    }
}
