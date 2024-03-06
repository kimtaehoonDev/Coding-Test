package org.example.baekjoon.p2304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 31분 / 성공 / 구현
// 23년8월에는 풀이를 떠올리다 실패한 문제. 이번에는 스무쓰
public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[1001];
        int max = -1;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            heights[idx] = height;
            if (max < height) {
                max = height;
            }
        }
//        System.out.println(Arrays.toString(heights));

        // 좌측영역
        int total = 0;
        int left = 0;
        int prevTop = -1;
        while (left < heights.length) {
            if (heights[left] == max) {
                break;
            }
            if (heights[left] < prevTop) {
                total += prevTop;
                left++;
            } else {
                prevTop = heights[left];
                total += heights[left];
                left++;
            }
        }
//        System.out.println("왼" + total);
//        System.out.println(left);

        // 우측영역
        int right = heights.length - 1;
        prevTop = -1;
        while (right >= left) {
            if (heights[right] == max) {
                break;
            }
            if (heights[right] < prevTop) {
                total += prevTop;
                right--;
            } else {
                prevTop = heights[right];
                total += heights[right];
                right--;
            }
        }
//        System.out.println("오른 왼 합 " + total);
//        System.out.println(right);
        total += ((right - left + 1) * max);

        System.out.println(total);
    }

}
