package org.example.baekjoon.p3568;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

// 성공 / 구현 / 50분
public class Main {
    static String primitiveType = null;
    static String additional = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        char[] ary = input.toCharArray();

        int left = 0;
        int right = -1;
        while(true) {
            right++;
            if (primitiveType == null && List.of('*', '&', '[', ']').contains(ary[right])) {
                primitiveType = new String(Arrays.copyOfRange(ary, left, right));
                left = right;
            }
            if (ary[right] == ' ') {
                if (primitiveType == null) {
                    primitiveType = new String(Arrays.copyOfRange(ary, left, right));
                } else {
                    additional = new String(Arrays.copyOfRange(ary, left, right));
                }
                break;
            }
        }
//        System.out.println(primitiveType);
//        System.out.println(additional);
        String variablesStr = new String(Arrays.copyOfRange(ary, right + 1, ary.length - 1));
        String[] variables = variablesStr.split(", ");

        // 각 variable을 한줄한줄 만들어낸다
        StringJoiner sj = new StringJoiner("\n");
        for (String variable : variables) {
            sj.add(makeLine(variable));
        }
        System.out.println(sj);
    }

    static String makeLine(String variable) {
        StringBuilder sb = new StringBuilder();
        sb.append(primitiveType);
        if (additional != null) {
            sb.append(additional);
        }

        char[] charArray = variable.toCharArray();
        for (int i = charArray.length - 1; i >= 0; i--) {
            if (charArray[i] == '*' || charArray[i] == '&') {
                sb.append(charArray[i]);
                continue;
            }
            if (charArray[i] == ']') {
                sb.append("[]");
                i--;
                continue;
            }
            sb.append(' ');
            // 문자
            sb.append(Arrays.copyOfRange(charArray, 0, i + 1));
            break;
        }
        sb.append(';');
        // 시작할 때 primitive붙이고, 만약에 추가타입 있으면 추가타입 이어서 붙인다.
        return sb.toString();

        // 마지막에는 ;를 붙인다
    }
}
