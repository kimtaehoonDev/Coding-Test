package org.example.baekjoon.p2608;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// 답안 참조 후 풀이(실패) / 45분 / 문자열, 그리디
// 로마문자를 만들 때, 동전문제 아이디어를 사용하면 간단하게 풀 수 있음.
// 다만 이 방법을 떠올리지 못해서 분기가 복잡해졌고, 그 과정에서 생각이 꼬임
public class Main {

    static Map<String, Integer> nums = new HashMap<>();
    static Map<Integer, String> temp = new HashMap<>();
    static List<Integer> sortedNums = new ArrayList<>();

    static String input1;
    static String input2;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input1 = sc.nextLine();
        input2 = sc.nextLine();
        init();
        int num1 = calcNum(input1);
        int num2 = calcNum(input2);
        int answerArabic = num1 + num2;
        String answerRom = convertRom(answerArabic);
        System.out.println(answerArabic);
        System.out.println(answerRom);
    }

    private static String convertRom(int arabic) {
        StringBuilder sb = new StringBuilder();
        while(arabic > 0) {
            for(int num : sortedNums) {
                if (arabic >= num) {
                    arabic -= num;
                    String str = temp.get(num);
                    sb.append(str);
                    break;
                }
            }
        }

        return sb.toString();
    }

    static int calcNum(String inputStr) {
        int pointer = 0;
        char[] input = inputStr.toCharArray();
        int prev = 10000000;
        int result = 0;
        while(pointer < input.length) {
            int now = nums.get(Character.toString(input[pointer++]));
            if (prev < now) {
                result += (now - prev * 2);
                prev = now - prev;
                continue;
            }
            result += now;
            prev = now;
        }
//        System.out.println(result);
        return result;
    }

    static void init() {
        nums.put("I", 1);
        nums.put("IV", 4);
        nums.put("V", 5);
        nums.put("IX", 9);
        nums.put("X", 10);
        nums.put("XL", 40);
        nums.put("L", 50);
        nums.put("XC", 90);
        nums.put("C", 100);
        nums.put("CD", 400);
        nums.put("D", 500);
        nums.put("CM", 900);
        nums.put("M", 1000);

        for (String key : nums.keySet()) {
            Integer value = nums.get(key);
            sortedNums.add(value);
            temp.put(value, key);
        }
        Collections.sort(sortedNums);
        Collections.reverse(sortedNums);
    }

}
