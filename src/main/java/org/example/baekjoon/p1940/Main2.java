package org.example.baekjoon.p1940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 성공 / 7분 / 투포인터
public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] ary = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ary);

        int answer = 0;
        int left = 0;
        int right = N - 1;
        while(left < right) {
            int sum = ary[left] + ary[right];
            if (sum > M) {
                right--;
            } else if (sum < M) {
                left++;
            } else {
                right--;
                left++;
                answer++;
            }
        }
        System.out.println(answer);
    }

}
