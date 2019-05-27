package Hashing;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by effyfeng 10:44 AM 5/27/19
 **/
public class Test {
    // test

    //check out a branch

    /***Better***/
    public int[][] updateMatrixMGeneration(int[][] matrix) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int dis = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();//poll出来是同一个1是没有关系的 只会被写当前层， 只要不进入到下一层就行

                /**为什么可以直接写dis这个dis肯定最小
                 * 因为我mark visit保证每个1只被expand1次
                 * 不会再次在更长的距离上expand **/
                if (matrix[cur[0]][cur[1]] == 1) {
                    matrix[cur[0]][cur[1]] = dis;
                }
                for (int[] dir : directions) {
                    int nexti = dir[0] + cur[0];
                    int nextj = dir[1] + cur[1];
                    if (nexti >= 0 && nexti < matrix.length && nextj >= 0 && nextj < matrix[0].length
                            && matrix[nexti][nextj] == 1 && !visited[nexti][nextj]) {
                        /**如果没有被generat（visited）过的话 才去generate以及在generate时去mark改点**/
                        q.add(new int[]{nexti, nextj});
                        visited[nexti][nextj] = true;

                    }
                }
            }
            dis++;
        }
        return matrix;
    }
}
