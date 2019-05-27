package Hashing;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by effyfeng 10:44 AM 5/27/19
 * 基础bfs当时竟然还是没有掌握牢的，并不懂bfs 每个边被generate 1次和因此每个node 被expand 1次这个道理
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
                int[] cur = q.poll();
                //poll出来是同一个1是没有关系的 只会被写当前层， 只要不进入到下一层就行
                //-- 上面的评论是错的， 同一个node 1只会被expand一次，因为所有指向这个1的边只有一条被generate了
                // 所以不会有同一个1被多次poll出来的情况！！！！

                /**为什么可以直接写dis这个dis肯定最小
                 * 因为bfs最先碰到的就是最小距离 **/
                if (matrix[cur[0]][cur[1]] == 1 ) {
                    matrix[cur[0]][cur[1]] = dis;
                }
                for (int[] dir : directions) {
                    int nexti = dir[0] + cur[0];
                    int nextj = dir[1] + cur[1];
                    if (nexti >= 0 && nexti < matrix.length && nextj >= 0 && nextj < matrix[0].length
                            && matrix[nexti][nextj] == 1 && !visited[nexti][nextj]) {
                        //同一个1保证只被generate1次 只会被放到queue一次
                        /**如果没有被generate（visited）过的话 才去generate以及在generate时去mark该点**/
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
