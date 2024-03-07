package org.example.baekjoon.p5525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 답지 보고 풀이 / DP라고 봐야할듯?
// https://girawhale.tistory.com/11 해당 블로그가 설명을 잘해줌
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[] S = br.readLine().toCharArray();
        int answer = 0;

        int[] memo = new int[S.length];
        for(int i = 1; i<S.length-1; i++) {
            if (S[i] == 'O' && S[i + 1] == 'I') {
                memo[i+1] = memo[i-1] + 1;
                if (memo[i + 1] >= N) {
                    if (S[i - 2 * N + 1] == 'I') {
                        answer++;
                    }
                }
            }
        }
        System.out.println(answer);
    }

}
