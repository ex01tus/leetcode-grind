package graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description: https://leetcode.com/problems/word-search
 * Difficulty: Medium
 * Time complexity: O(m * n * 3^word.length)
 * Space complexity: O(m * n)
 */
public class WordSearch {

    private int[][] directions;
    private int[][] visited;

    public boolean existRecursive(char[][] board, String word) {
        directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        visited = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean isExist = isExist(board, i, j, word, 0);
                    if (isExist) return true;
                }
            }
        }

        return false;
    }

    boolean isExist(char[][] board, int x, int y, String word, int index) {
        if (index == word.length() - 1) return true;

        visited[x][y] = 1;
        for (int[] dir : directions) {
            int x1 = x + dir[0];
            int y1 = y + dir[1];

            if (x1 >= 0 && x1 < board.length && y1 >= 0 && y1 < board[0].length
                    && visited[x1][y1] == 0
                    && word.charAt(index + 1) == board[x1][y1]) {
                if (isExist(board, x1, y1, word, index + 1)) {
                    return true;
                }
            }
        }
        visited[x][y] = 0;

        return false;
    }

    public boolean existIterative(char[][] board, String word) {
        directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean isExist = isExist(board, word, new int[]{i, j});
                    if (isExist) return true;
                }
            }
        }

        return false;
    }

    private boolean isExist(char[][] board, String word, int[] start) {
        int[][] visited = new int[board.length][board[0].length];
        int pointer = 0;

        Deque<int[]> stack = new LinkedList<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            if (pointer == word.length()) {
                return true;
            }

            int[] current = stack.pop();

            if (visited[current[0]][current[1]] == 0) {
                if (board[current[0]][current[1]] != word.charAt(pointer)) {
                    continue;
                }

                visited[current[0]][current[1]] = 1;
                stack.push(current);

                for (int[] dir : directions) {
                    int x = current[0] + dir[0];
                    int y = current[1] + dir[1];

                    if (x >= 0 && y >= 0 && x < board.length && y < board[0].length
                            && visited[x][y] == 0) {
                        stack.push(new int[]{x, y});
                    }
                }

                pointer++;
            } else if (visited[current[0]][current[1]] == 1) {
                visited[current[0]][current[1]] = 0;
                pointer--;
            }
        }

        return false;
    }
}
