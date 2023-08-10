package org.example.leetcode;

// https://leetcode.com/problems/number-of-islands
public class p200 {
    public static int[] dx = new int[] {1,0,-1,0};
    public static int[] dy = new int[] {0,1,0,-1};

    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int cnt = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(visited, grid, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public void dfs(boolean[][] visited,char[][] grid, int i, int j) {
        visited[i][j] = true;
        for(int direction =0; direction<4;direction++) {
            int nx = i + dx[direction];
            int ny = j + dy[direction];
            if (nx < 0 || ny <0 ||
                visited.length <= nx || visited[0].length <= ny) {
                continue;
            }
            if (visited[nx][ny] || grid[nx][ny] == '0') {
                continue;
            }

            dfs(visited, grid, nx, ny);
        }
    }
}