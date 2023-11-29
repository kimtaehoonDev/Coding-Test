package org.example.programmers;

import java.util.ArrayList;
import java.util.List;

class 교점에_별_만들기 {
    static int INF = 2000000000;
    static int MAX_X = -INF;
    static int MAX_Y = -INF;
    static int MIN_X = INF;
    static int MIN_Y = INF;

    public String[] solution(int[][] line) {
        List<Spot> spots = new ArrayList<>();
        for(int i = 0; i<line.length; i++) {
            for(int j = 0; j<line.length; j++) {
                if (i >= j) {
                    continue;
                }
                // 연립방정식
                int[] l1 = line[i];
                int[] l2 = line[j];
                long[] tempL1 = new long[3];
                long[] tempL2 = new long[3];
                for(int k = 0; k<3; k++) {
                    tempL1[k] = (long) l2[0] * l1[k];
                    tempL2[k] = (long) l1[0] * l2[k];
                }
                long yGap = tempL1[1] - tempL2[1];
                long cGap = - tempL1[2] + tempL2[2];
                if (yGap == 0 || cGap % yGap != 0) {
                    continue;
                }
                int yValue = (int) (cGap / yGap);
                int xValue;
                if (l1[0] != 0) {
                    xValue = (- yValue) * l1[1] - l1[2];
                    if (xValue % l1[0] != 0) {
                        continue;
                    }
                    xValue /= l1[0];
                } else {
                    xValue = (- yValue) * l2[1] - l2[2];
                    if (xValue % l2[0] != 0) {
                        continue;
                    }
                    xValue /= l2[0];
                }

                spots.add(new Spot(xValue, yValue));
                MAX_X = Math.max(MAX_X, xValue);
                MAX_Y = Math.max(MAX_Y, yValue);
                MIN_X = Math.min(MIN_X, xValue);
                MIN_Y = Math.min(MIN_Y, yValue);
            }
        }

        char[][] graph = new char[MAX_Y - MIN_Y+1][MAX_X - MIN_X+1];
        // 초기값을 . 으로 만든다
        for(int i =0; i<graph.length; i++) {
            for(int j = 0; j<graph[0].length; j++) {
                graph[i][j] = '.';
            }
        }
        for(Spot spot: spots) {
            int tempX = spot.x - MIN_X;
            int tempY = MAX_Y - spot.y;
            // System.out.println(spot.x+"|"+spot.y + "-> "+ tempX+"|"+tempY);
            graph[tempY][tempX] = '*';
        }

        String[] answer = new String[graph.length];
        int idx = 0;
        for(char[] each: graph) {
            answer[idx++] = new String(each);
        }

        return answer;
    }

    static class Spot {
        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}