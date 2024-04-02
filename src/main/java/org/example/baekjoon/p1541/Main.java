package org.example.baekjoon.p1541;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 1실패 후 성공 / 31분 / 모든 케이스를 꼼꼼하게 검사하지 않음
// + 연산자가 나옴 && 괄호로 감싸지지 않음 -> 해당 케이스 처리를 빼먹어서 틀림
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] input = sc.nextLine().toCharArray();
        List<Character> operators = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        int prev = 0;
        for (int i = 0; i < input.length; i++) {
            if (List.of('+', '-').contains(input[i])) {
                operators.add(input[i]);
                nums.add(Integer.parseInt(String.copyValueOf(input, prev, i - prev)));
                prev = i + 1;
                continue;
            }
        }
        nums.add(Integer.parseInt(String.copyValueOf(input, prev, input.length - prev)));
//        System.out.println(operators);
//        System.out.println(nums);

        int answer = nums.get(0);
        int temp = 0;
        boolean isInGwalho = false;
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i) == '-') {
                if (isInGwalho) { // - 연속으로 나온 상황
                    answer -= temp;
                    temp = 0;
                }
                isInGwalho = true;
                temp = nums.get(i + 1);
            } else { // +일때
                if (isInGwalho) {
                    temp += nums.get(i + 1);
                } else {
                    answer += nums.get(i+1);
                }
            }
//            System.out.println(i + 1 + "번째 순서에서 결과 = " + answer + ", 누적 : " + temp);
        }
        if (isInGwalho) {
            answer -= temp;
        } else {
            answer += temp;
        }
        System.out.println(answer);
    }

}
