package org.example.baekjoon.p3020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 실패. 투포인터가 도저히 그려지지 않음
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] up = new int[N/2];
        int[] down = new int[N/2];
        for(int i = 0; i<N; i++) {
            if (i % 2 == 0) {
                down[i / 2] = Integer.parseInt(br.readLine());
            } else {
                up[i/2] = Integer.parseInt(br.readLine());
            }
        }
        Arrays.sort(up);
        Arrays.sort(down);
//        System.out.println(Arrays.toString(up));
//        System.out.println(Arrays.toString(down));

        // 0 <= < 높이 H 반복
        int left = 0;
        int right = up.length;
        for(int height = 0; height< H; height++) {
            int count = 0;
            for (int i = left; i<down.length; i++) {
                if (down[i] > height) {
                    count += (i - left);
                    left = i;
                    break;
                }
            }
//            System.out.println("레프트 " + left +", 카운트 : " + count);
            int ccount = down.length;
            for (int i = right - 1; i>=0; i--) {
                if (up[i] < (H - height)) {
                    System.out.println("i" + i);
                    ccount -= (right - i - 1);
                    right = i;
                    break;
                }
            }
            System.out.println("라이트 " + right +", 카운트 : " + ccount);
        }
        int count = down.length - left;
//        System.out.println("레프트 " + left +", 카운트 : " + count);
    }
}
