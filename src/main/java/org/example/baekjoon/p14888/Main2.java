package org.example.baekjoon.p14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// N : 숫자의 개수
// nums : 숫자들
// operators : 덧,뺄,곱,나 개수

// 성공 / 20분 / 구현

// 풀이
// 조건 : 연산자의 개수가 고정되어 있고 && 연산자 우선순위가 없음
// 모든 경우들을 탐색하며 최대와 최소를 구함.
// x번째까지의 연산 결과를 저장해두고 다음 원소를 연산하는 방식을 사용함
public class Main2 {

    static final int INF = 2000000000;

    static int N;
    static int[] nums;
    static int[] operators;

    static int max = -INF;
    static int min = INF;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        operators = new int[4];
        for(int i = 0; i<4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }
//        System.out.println(Arrays.toString(nums));
//        System.out.println(Arrays.toString(operators));

        // 연산
        calculate(nums[0],1);
        System.out.println(max);
        System.out.println(min);
    }

    public static void calculate(int acc, int now) {
        if (now == N) {
            // 종료, acc 사용해 최소 최대 결과를 갱신한다
            max = Math.max(max, acc);
            min = Math.min(min, acc);
            return;
        }
        for(int i = 0; i<4; i++) {
            if (operators[i] == 0) {
                continue;
            }
            operators[i]--;
            int temp = acc;
            if (i == 0) {
                // +
                temp += nums[now];
            } else if (i == 1) {
                // -
                temp -= nums[now];
            } else if (i == 2) {
                // *
                temp *= nums[now];
            } else {
                // 나누기
                if (temp < 0) { // 음수를 양수로 나눌때
                    temp = - temp;
                    temp /= nums[now];
                    temp = - temp;
                } else {
                    temp /= nums[now];
                }
            }
            now++;
            calculate(temp, now);
            now--;
            operators[i]++;
        }
    }
}
