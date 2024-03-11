package org.example.baekjoon.p2003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  18분 / 2회 실패 후 성공
 *  첫 번째 실패 : 순수 로직 오류(중간 검사 하지 않아서 발생)
 *  두 번째 실패 : 예상하지 못함. 틀린 이후에 틀린 이유를 생각하다가 알게됨
 *  인사이트 -> 투포인터를 한다면... right가 끝난 이후 left 역시 끝까지 움직여 봐야한다
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 10000개
        int M = Integer.parseInt(st.nextToken()); // 타겟. 0<= <= 3억
        int[] ary = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int total = 0;
        int answer = 0;

        while(right < N) {
            if (total < M) {
                // total을 더 키운다 -> Right를 움직인다
                total += ary[right];
                right++;
            } else {
                total -= ary[left];
                left++;
            }
//            System.out.println(left + "/" + (right-1) + "-> " + total);
            if (total == M) {
                answer++;
            }
        }
        while (left < right) {
            total -= ary[left];
            left++;
            if (total == M) {
                answer++;
            }
//            System.out.println(left + "/" + (right-1) + "-> " + total);
        }
        System.out.println(answer);
    }

}
