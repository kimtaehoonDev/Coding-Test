package org.example.baekjoon.p5525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[] S = br.readLine().toCharArray();
        int answer = 0;

        int left = 0;
        int right = -1;
        int now = -1;
        int iCnt = 0;
        int oCnt = 0;
        while (left < M && right < M) {
            now++;
            System.out.println("반복 -> " + left + "/ " + right + ", now = " + now);
            if (now >= S.length) {
                break;
            }
            if (S[now] == 'I') {
                if (iCnt == oCnt) {
                    right = now;
                    iCnt++;
                } else {
                    right = now;
                    left = right;
                    iCnt = 1;
                    oCnt = 0;
                    continue;
                }
            } else { // o가 나왔을 때
                if (iCnt == oCnt + 1) {
                    right = now;
                    oCnt++;
                } else {
                    left = now + 1;
                    right = now;
                    iCnt = 0;
                    oCnt = 0;
                    continue;
                }
            }

            if (iCnt == N + 1 && oCnt == N) {
                answer += 1;
                System.out.println(left + "/ " + right);
                if (now + 2 >= S.length) {
                    break;
                }
                if (!(S[now + 1] == 'O' && S[now + 2] == 'I')) {
                    // 새롭게 시작
                    left = now;
                    right = now - 1;
                    iCnt = 0;
                    oCnt = 0;
                } else {
                    left += 1;
                    right += 1;
                    now++;
                }
            }
        }

        // 만들기에 성공
        System.out.println(answer);
    }

}
