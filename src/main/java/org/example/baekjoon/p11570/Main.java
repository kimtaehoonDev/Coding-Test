package org.example.baekjoon.p11570;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 실패 / DP / 90분 고민 / 답지 참조 후 풀이
// 점화식까지는 찾아냈는데 해당 점화식을 구현하는데 어려움을 겪음
// A나 B가 원소를 선택 안한 경우를 제대로 처리하지 못했기 때문
// https://sookr5416.tistory.com/274?category=594631
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] val = new int[N+1];
        for (int i = 1; i <= N; i++) {
            val[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[N+1][N+1];
        if (N < 3) {
            System.out.println(0);
            return;
        }
        for (int[] ints : dp) {
            Arrays.fill(ints, 2100000000);
        }
        dp[0][1] = 0;
        dp[1][0] = 0;
//        Queue<int[]> queue = new LinkedList<>();
//        queue.add(new int[] {0, 1});
//        queue.add(new int[] {1, 0});
//        while (!queue.isEmpty()) {
//            int[] target = queue.poll();
        for(int i = 0; i<=N; i++) {
            for(int j = 0; j<=N; j++) {
                if (i == j) {
                    continue;
                }
                int next = Math.max(i,j) + 1;
                if (next > N) {
                    continue;
                }
                if (i == 0 || j == 0) { // i가 0이라는 건 A는 아직 노래부르지 않았다는 의미. 그러니까 val[next] - val[i]를 0으로 만들어줌
                    val[0] = val[next];
                }

                dp[i][next] =
                    Math.min(dp[i][next], dp[i][j] + Math.abs(val[next] - val[j]));
                dp[next][j] =
                    Math.min(dp[next][j], dp[i][j] + Math.abs(val[next] - val[i]));
            }
        }

        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
