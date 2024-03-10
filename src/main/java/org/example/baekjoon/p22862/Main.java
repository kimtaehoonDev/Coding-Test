package org.example.baekjoon.p22862;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 30분 / 성공
// 투포인터라는 걸 알고 풀었더니 풀 수 있었음

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]); // 삭제 가능 최대 개수
        int[] ary = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = 0;
        int now = 0;
        int deleteElementCnt = 0;

        int answer = 0;
        while (r < ary.length) {
//            System.out.println(l + ", " + r + "=> 최대길이" + now + "삭제 : " + deleteElementCnt);
            if (K >= deleteElementCnt) { // right 포인터를 이동시킬 예정
                if (ary[r] % 2 == 1) {
                    deleteElementCnt++;
                } else {
                    now++;
                }
                r++;
            } else { // K < deletedElementCnt
                if (ary[l] % 2 == 1) {
                    deleteElementCnt--;
                } else {
                    now--;
                }
                l++;
            }
            answer = Math.max(answer, now);
        }
        System.out.println(answer);
    }
}
