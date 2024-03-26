package org.example.baekjoon.p2470;

import java.util.Arrays;
import java.util.Scanner;

// 성공 / 21분 / 투포인터 -> 답안 참조 후 풀이 수정(포인터 옮기는 로직 변경)
// 투포인터인걸 어떻게 알 수 있냐. 두 개의 숫자를 합해서 특정 값(0)을 만들어야 함
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());

        String[] input = sc.nextLine().split(" ");
        int[] ary = new int[N];
        int idx = 0;
        for (String s : input) {
            ary[idx++] = Integer.parseInt(s);
        } // -10억 ~ 10억
        Arrays.sort(ary);
//        System.out.println(Arrays.toString(ary));
        int gap = 2_100_000_000; // -21억
        int left = 0;
        int[] answer = new int[]{-1, -1};
        int right = N - 1;
        while (left < right) {
            if (gap > Math.abs(ary[left] + ary[right])) {
                gap = Math.abs(ary[left] + ary[right]);
                answer[0] = ary[left];
                answer[1] = ary[right];
            }

            // 합이 음수일때 0에 가깝게 만드려면 지금보다 더 큰 수를 더해야 함(left이동
            if (ary[left] + ary[right] < 0) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(answer[0] + " " + answer[1]);
    }

}
