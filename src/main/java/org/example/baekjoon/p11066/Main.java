package org.example.baekjoon.p11066;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1시간 2분 / 성공
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            int pageCnt = Integer.parseInt(br.readLine());
            int[] pages = new int[pageCnt];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<pageCnt; j++) {
                pages[j] = Integer.parseInt(st.nextToken());
            }

            System.out.println(solve(pages));
        }
    }

    static int solve(int[] pages) {
        int pageCnt = pages.length;
//        System.out.println(Arrays.toString(pages));
        int[][] dp = new int[pageCnt][pageCnt];
        for(int [] line: dp) {
            Arrays.fill(line, -1);
        }
//        for(int [] line: dp) {
//            System.out.println(Arrays.toString(line));
//        }
        for(int i=0; i<pageCnt; i++) {
            dp[i][i] = 0;
        }
        int cnt = 0;
        while (cnt < pageCnt) {
            cnt++; // cnt 개수만큼 확인하겠다
            for(int i=0; i<pageCnt - cnt; i++) {
                int sum = 0;
                for(int j=i; j<=i+cnt; j++) {
                    sum += pages[j];
                }
//                System.out.println(sum);
                dp[i][i+cnt] = findMin(i, i+cnt, dp) + sum;
            }
        }

//        for (int[] each : dp) {
//            System.out.println(Arrays.toString(each));
//        }

        return dp[0][pageCnt - 1];
    }

    static int findMin(int start, int end, int[][] dp) {
        int min = 100000000;

        for(int mid = start; mid<end;mid++) {
            min = Math.min(min, dp[start][mid] + dp[mid + 1][end]);
        }
        return min;
    }
}

