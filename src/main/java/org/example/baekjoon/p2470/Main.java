package org.example.baekjoon.p2470;

import java.util.Arrays;
import java.util.Scanner;

// 성공 / 21분 / 투포인터
// 투포인터가 확실한가 근거를 찾느라 오래걸림
// left가 가리키는 값과 right가 가리키는 값을 '더한 값의 절대값'이 최소가 되어야함
// left를 옮겼을 때와, right를 옮겼을 때 결과를 비교해 더 결과가 작은 쪽으로 이동
// (여기가 중요) 만약 left를 인덱스 0 -> 1 로 옮겼다면, ary[0]을 사용해서 정답을 만들 수 없다는 가정이 필요 => 그림 그려서 몇 가지 케이스 확인 후 불가능하다 결론냄
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
            if (Math.abs(ary[left + 1] + ary[right]) < Math.abs(ary[left] + ary[right - 1])) {
                // 레프트 옮기는게 절대값이 더 작아짐
                left++;
            } else {
                right--;
            }
        }
        System.out.println(answer[0] + " " + answer[1]);
    }

}
