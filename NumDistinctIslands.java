package Hashing;

import java.util.*;

/**
 * Created by effyfeng 8:12 PM 7/20/18
 *
 *其实就是把上一个方向是哪里来的记录了下来 然后就拼成了一条路 路里面的字母刻画这条路的样子 像密码
 [[1,1,0],
 [0,1,1],
 [0,0,0],
 [1,1*,1],
 [0,1,0]]
 Output:1
 Expected:2
 not add e at the end of recursion call is WRONG:
 1: slul
 2: slul
 add e:
 1 s l u l e e e e
 2 s l u*e l e e e
 我们用e来标记和表示该层recursion结束了== 表示了岛屿状态



 **/
public class NumDistinctIslands {

    //backtracking，O(n^2)
    public int numDistinctIslandsI(int[][] grid) {
        Set<String> set=new HashSet();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]==1){
                    StringBuilder sb=new StringBuilder();
                    dfs(grid,i,j,sb,"s"); // start
                    // grid[i][j]=0;
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
    /**我们用e来标记/表示该层recursion结束了== 画出了岛屿边界 == 表示了岛屿状态 **/
    private void dfs(int[][] grid, int i, int j, StringBuilder sb, String dir){
        if(i<0 || i==grid.length || j<0 || j==grid[i].length || grid[i][j]==0)
            return;

        sb.append(dir);
        grid[i][j]=0;//访问过的陆地标记为无效

        dfs(grid,i-1,j,sb,"u");//up  记录从上一点走过来的方向，从下面一行走上来是up
        dfs(grid,i+1,j,sb,"d");//down
        dfs(grid,i,j-1,sb,"l");
        dfs(grid,i,j+1,sb,"r");
        sb.append("e"); // end from the origin => get rid of confusion
    }



    //没看懂下面这个
    private static int[][] delta = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

    public int numDistinctIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Set<List<List<Integer>>> shapes = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<List<Integer>> island = new ArrayList<>();
                //利用了list这个object的euqals， list中每个list<integer>(也就是点）的值一样 那么
                if (dfs(i, j, i, j, grid, m, n, island))
                    shapes.add(island);
            }
        }
        return shapes.size();
    }

    private boolean dfs(int i0, int j0, int i, int j, int[][] grid, int m, int n, List<List<Integer>> island) {
        if (i < 0 || m <= i || j < 0 || n <= j || grid[i][j] <= 0)
            return false;

        island.add(Arrays.asList(i - i0, j - j0));//normolize

        grid[i][j] *= -1;
        for (int d = 0; d < 4; d++) {
            dfs(i0, j0, i + delta[d][0], j + delta[d][1], grid, m, n, island);
        }
        return true;
    }
    public int numDistinctIslands2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        Set<String> res = new HashSet<>();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    List<Point> island = new ArrayList<>();
                    dfs(grid, i, j, island);
                    res.add(getUnique(island));//每次处理一个
                }
            }
        }

        return res.size();
    }

    private void dfs(int[][]grid, int x, int y, List<Point> island) {
        int m = grid.length, n = grid[0].length;

        island.add(new Point(x, y));
        grid[x][y] = 0;

        int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int _x = x + dirs[i], _y = y + dirs[i + 1];
            if (_x >= 0 && _x < m && _y >= 0 && _y < n && grid[_x][_y] == 1) {
                dfs(grid, _x, _y, island);
            }
        }
    }
//1.首先通过DFS寻找island，并在这个过程中通过一个ArrayList存DFS找到的一个island的所有坐标
//2.通过normalize(),得到当前island的shape key。normalize会将island的每个坐标转换成8种变化，然后通过排序得到取第一个作为表示该island形状的key，将key放入set中
//3.最后set的size就代表有多少个distinct island
   // https://protegejj.gitbooks.io/algorithm-practice/content/711-number-of-distinct-islands-ii.html
    private String getUnique(List<Point> island) {
        List<String> sameIslands = new ArrayList<>();

        int[][] trans={{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int i = 0; i < 4; ++i) {
            List<Point> l1 = new ArrayList<>(), l2 = new ArrayList<>();

            for (Point point : island) {
                int x = point.x, y = point.y;
                l1.add(new Point(x * trans[i][0], y * trans[i][1]));//每个点都有四中变型
                l2.add(new Point(y * trans[i][0], x * trans[i][1]));
            }
            sameIslands.add(getStr(l1));
            sameIslands.add(getStr(l2));
        }

        Collections.sort(sameIslands);
        return sameIslands.get(0);
    }

    private String getStr(List<Point> island) {

        Collections.sort(island, new Comparator<Point>() {
            public int compare(Point a, Point b) {
                if (a.x != b.x) {
                    return a.x - b.x;
                }
                return a.y - b.y;
            }
        });

        StringBuilder sb = new StringBuilder();
        int x = island.get(0).x, y = island.get(0).y;

        for (Point point : island) {
            sb.append((point.x - x) + " " + (point.y - y) + " ");
        }
        return sb.toString();
    }
    class Point {
        public int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
