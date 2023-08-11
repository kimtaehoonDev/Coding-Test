package org.example.baekjoon.p1328;

import java.util.Scanner;

// 어렵다. 시간 초과 날거 알았는데 다른 방법이 떠오르지 않음

public class Main {
    public static int N;
    public static int L;
    public static int R;
    public static boolean[] visited;
    public static int[] output;
    public static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();

        visited = new boolean[N+1];
        output = new int[N];

        solve(N, -1, 0, 0);
        System.out.println(answer);
    }

    public static void solve(int n, int prevTop, int leftSee, int depth) {
        // 종료조건 시 left, right 개수를 확인한다
        if (depth == n) {

            if (leftSee != L) {
                return;
            }
            // n개를 뽑았다.
            // output을 확인하며 right 개수
            int rightTop = -1;
            int rightSee = 0;
            for(int i=n-1; i>=0 ;i--) {
                if (output[i] > rightTop) {
                    rightTop = output[i];
                    rightSee++;
                }
            }
            if (rightSee == R) {
                // 정답추가
                answer = (answer + 1) % 1000000007;
            }
            return;
        }

        for(int i=1; i<=n; i++) {
            if (!visited[i]) {
                // 기존 가장 높은 건물보다 높으면 보이게 된다
                if (prevTop < i) {
                    int temp = prevTop;
                    prevTop = i;
                    leftSee++;

                    // 종료조건
                    if (leftSee > L) {
                        leftSee--;
                        prevTop = temp;
                        continue;
                    }
                    visited[i] = true;
                    output[depth] = i;
                    solve(n, prevTop, leftSee, depth+1);
                    visited[i] = false;
                    output[depth] = 0;
                    leftSee--;
                    prevTop = temp;
                } else {
                    visited[i] = true;
                    output[depth] = i;
                    solve(n, prevTop, leftSee, depth + 1);
                    visited[i] = false;
                    output[depth] = 0;
                }
            }
        }
    }
}
