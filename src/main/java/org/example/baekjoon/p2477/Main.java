package org.example.baekjoon.p2477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 36분 / 성공 / 구현
// 더 깔끔하게 구현할 수 있겠지만 안전하고 최대한 빠르게 풀기 위해서는 이 방식이 최선이었음
public class Main {

    static int[] dx = new int[]{-100, 0, 0, 1, -1};
    static int[] dy = new int[]{-100, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int x = 0;
        int y = 0;
        int minX = 1000;
        int maxX = -1000;
        int minY = 1000;
        int maxY = -1000;

        int[][] spots = new int[6][2];

        for (int i = 0; i < 6; i++) {
            String[] input = br.readLine().split(" ");
            int dir = Integer.parseInt(input[0]);
            int range = Integer.parseInt(input[1]);
            x += dx[dir] * range;
            y += dy[dir] * range;
            maxX = Math.max(x, maxX);
            maxY = Math.max(y, maxY);
            minX = Math.min(x, minX);
            minY = Math.min(y, minY);
            spots[i][0] = x;
            spots[i][1] = y;
        }
//        System.out.println(maxX + ":" + minX);
//        System.out.println(minY + ":" + maxY);
        int big = Math.abs(maxX - minX) * Math.abs(maxY - minY);

        int middleX = 0;
        int middleY = 0;
        for(int[] spot : spots) {
            if (spot[0] != minX && spot[0] != maxX && spot[1] != minY && spot[1] != maxY) {
                // middle값
                middleX = spot[0];
                middleY = spot[1];
            }
        }
        // 특정 좌표가 있니?
        boolean isExist = false;
        for(int[] spot : spots) {
            if (spot[0] == minX && spot[1] == minY) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            // 그놈과 미들 간의 차이
            System.out.println((big - Math.abs(minX - middleX) * Math.abs(minY - middleY)) * K);
            return;
        }
        isExist = false;
        for(int[] spot : spots) {
            if (spot[0] == minX && spot[1] == maxY) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            // 그놈과 미들 간의 차이
            System.out.println((big - Math.abs(minX - middleX) * Math.abs(maxY - middleY)) * K);
            return;
        }
        isExist = false;
        for(int[] spot : spots) {
            if (spot[0] == maxX && spot[1] == minY) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            // 그놈과 미들 간의 차이
            System.out.println((big - Math.abs(maxX - middleX) * Math.abs(minY - middleY))*K);
            return;
        }
        isExist = false;
        for(int[] spot : spots) {
            if (spot[0] == maxX && spot[1] == maxY) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            // 그놈과 미들 간의 차이
            System.out.println((big - Math.abs(maxX - middleX) * Math.abs(maxY - middleY))*K);
        }
    }

}
