package org.example.programmers;

import java.util.Arrays;

class 삼각_달팽이 {
    static int[] dx = new int[] {1, 0, -1};
    static int[] dy = new int[] {0, 1, -1};

    static int dalpang = 1;
    static int dir = -1;
    static int x = -1;
    static int y = 0;

    public int[] solution(int n) {
        int[][] graph = new int[n][n];
        int lineLength = n;
        while(lineLength != 0) {
            dir = (dir + 1) % 3;
            for(int i =0; i<lineLength; i++) {
                x = x + dx[dir];
                y = y + dy[dir];
                graph[x][y] = dalpang++;
            }
            lineLength--;
        }
        for(int[] each: graph) {
            System.out.println(Arrays.toString(each));
        }

        int[] answer = new int[dalpang - 1];
        int tempX = 0;
        int tempY = 0;
        for(int i=0; i<dalpang - 1; i++) {
            answer[i] = graph[tempX][tempY];
            tempY++;
            if (tempY == n || graph[tempX][tempY] == 0) {
                tempX++;
                tempY = 0;
            }
        }
        return answer;
    }
}