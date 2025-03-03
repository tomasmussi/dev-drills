package leetcode.backtracking;

public class NQueens {

    public static void tests() {
        NQueens nQueens = new NQueens();
        System.out.println(nQueens.solveNQueens(9));
        System.out.println(nQueens.solveNQueens(4));
    }

    private int solveNQueens(int n) {
        boolean[][] matrix = new boolean[n][n];
        return checkQueens(matrix, 0, n, 0);
    }


    private int checkQueens(boolean[][] matrix, int col, int n, int total) {
        if (col == n) {
            return total + 1;
        }
        for (int row = 0; row < n; row++) {
            if (checkValid(matrix, row, col, n)) {
                matrix[row][col] = true;
                total = checkQueens(matrix, col + 1, n, total);
                matrix[row][col] = false;
            }
        }
        return total;
    }

    private boolean checkValid(boolean[][] matrix, int row, int col, int n) {
        for (int i = 0; i < n; i++) {
            if (matrix[i][col] || matrix[row][i])
                return false;
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (matrix[i][j])
                return false;
        }

        for (int i = row, j = col; i < n && j < n; i++, j++) {
            if (matrix[i][j])
                return false;
        }

        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (matrix[i][j])
                return false;
        }

        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (matrix[i][j])
                return false;
        }
        return true;
    }

    /**
    public int totalNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        return totalNQueens(n, board, 0, 0);
    }

    private int totalNQueens(int n, boolean[][] board, int col, int total) {
        if (col == n) {
            System.out.println(total);
            return total + 1;
        }
        for (int row = 0; row < n; row++) {
            if (checkValid(board, row, col, n)) {
                // place queen
                board[col][row] = true;
                // backtrack
                total = totalNQueens(n, board, col + 1, total);
                // remove queen
                board[col][row] = false;
            }
        }
        return total;
    }

    private boolean canPlaceQueen(boolean[][] board, int row, int col, int n) {
        // check if there's a queen in the same row or column
        for (int i = 0; i < n; i++) {
            if (board[i][col] || board[row][i]) {
                return false;
            }
        }

        // check if there's a queen in the up-left diagonal
        for (int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c]) {
                return false;
            }
        }

        // check if there's a queen in the down-right diagonal
        for (int r = row, c = col; r < n && c < n; r++, c++) {
            if (board[r][c]) {
                return false;
            }
        }

        // check if there's a queen in the down-left diagonal
        for (int r = row, c = col; r < n && c >= 0; r++, c--) {
            if (board[r][c]) {
                return false;
            }
        }

        // check if there's a queen in the up-right diagonal
        for (int r = row, c = col; r >= 0 && c < n; r--, c++) {
            if (board[r][c]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkValid(boolean[][] matrix, int row, int col, int n) {
        for (int i = 0; i < n; i++) {
            if (matrix[i][col] || matrix[row][i])
                return false;
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (matrix[i][j])
                return false;
        }

        for (int i = row, j = col; i < n && j < n; i++, j++) {
            if (matrix[i][j])
                return false;
        }

        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (matrix[i][j])
                return false;
        }

        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (matrix[i][j])
                return false;
        }
        return true;
    }
     */
}
