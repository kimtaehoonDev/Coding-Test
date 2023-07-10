package org.example.baekjoon.p1940;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt(); // 갑옷을 만드는 숫자

        // 입력
        int[] ary = new int[N];
        for(int i =0; i<N; i++) {
            ary[i] = sc.nextInt();
        }

        // 정렬
        Arrays.sort(ary);

        int left = 0;
        int right = ary.length - 1;
        int answer = 0;

        while(left < right) {
            int result = ary[left] + ary[right];
            if (result == M) {
                answer += 1;
                left += 1;
                right -= 1;
            } else if (result > M) {
                right -= 1;
            } else {
                left += 1;
            }
        }
        System.out.println(answer);
    }
}
