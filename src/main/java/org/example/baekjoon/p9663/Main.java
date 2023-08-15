package org.example.baekjoon.p9663;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// 시간초과 -> 메모리초과(dp테이블 사용) -> 실패
public class Main {
    public static int answer = 0;
    public static Boolean[][] dp;
    public static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new Boolean[N*N][N*N];

        comb(N*N, N, 0, 0, new ArrayList<>());
        System.out.println(answer);
    }

    public static void comb(int n, int r, int depth, int start, List<Spot> queens) {
        if (depth == r) {
            answer++;
            return;
        }

        for(int i=start; i<n;i++) {
            int x = i/N;
            int y = i%N;
            boolean canAttack = checkAttack(x, y, queens);
            if (canAttack) {
                continue;
            }
            queens.add(Spot.get(x,y));
            comb(n, r, depth + 1, i + 1, queens);
            queens.remove(queens.size() - 1);
        }
    }

    public static boolean checkAttack(int x, int y, List<Spot> queens) {
        for (Spot queen : queens) {
            if (dp[queen.x * N + queen.y][x*N + y] != null) {
                if  (dp[queen.x * N + queen.y][x*N + y]) {
                    return true;// 공격가능
                }
                continue;
            }

            if (x == queen.x) {
                dp[queen.x * N + queen.y][x*N + y] = true; // 공격가능
                return true;
            }
            if (y == queen.y) {
                dp[queen.x * N + queen.y][x*N + y] = true;
                return true;
            }
            if (Math.abs(queen.x - x) == Math.abs(queen.y - y)) {
                dp[queen.x * N + queen.y][x*N + y] = true;
                return true;
            }
            dp[queen.x * N + queen.y][x*N + y] = false;
        }
        return false;
    }

    public static class Spot {
        static Map<Integer, Spot> store = new HashMap<>();

        int x;
        int y;
        private Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static {
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    store.put(N*i + j, new Spot(i, j));
                }
            }
        }

        public static Spot get(int x, int y) {
            return store.get(N * x + y);
        }
    }
}
