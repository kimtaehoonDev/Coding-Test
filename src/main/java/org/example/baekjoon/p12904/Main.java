package org.example.baekjoon.p12904;

import java.io.IOException;
import java.util.*;

// 성공 / 34분
// 문자열을 계속 만들기에는 무리가 있을 거 같아, S와 T 문자열을 sStr, str이라는 char 배열로 만들어두고
// 시작 문자열과 끝 문자열을 기록해서 문제를 품

// a -> 끝 문자열을 하나 제거
// b -> 끝 문자열을 하나 제거 & reversed 스위칭

// 그런데, 굳이 투포인터 아니고 계속 StringBuilder 사용해서 문자열 만들어줘도 성능상 문제는 없다..
public class Main {
    static String s;
    static char[] sStr;
    static char[] str;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        s = sc.next();
        sStr = s.toCharArray();
        String t = sc.next();

        str = t.toCharArray();
        if (find(0, str.length - 1, false)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static boolean find(int start, int end, boolean reversed) {
//        System.out.println("입력된 값은 " + start + ", " + end + ", " + reversed);
        // 만들어야 하는 문자 s와 현재 확인하는 문자열이 길이가 같다면
        if (s.length() == end - start + 1) {
//            System.out.println("확인" + start + ", " + end + ", " + reversed);
            // 체크하고
            if (reversed) {
                int temp = end;
                for(int i = 0; i<s.length();i++) {
                    if (sStr[i] != str[temp]) {
                        return false;
                    }
                    temp--;
                }
            } else {
                int temp = start;
                for(int i = 0; i<s.length();i++) {
                    if (sStr[i] != str[temp]) {
                        return false;
                    }
                    temp++;
                }
            }
            return true;
        }

        if (s.length() > end - start + 1) {
            return false;
        }


        if (!reversed) {
            if (str[end] == 'A') {
                return find(start, end - 1, false);
            }
            return find(start, end - 1, true);
        }
        // 뒤집어져있을때
        if (str[start] == 'A') {
            return find(start + 1, end, true);
        }
        return find(start + 1, end, false);
    }
}

// 이런 기법이 있구나
//    private static String reverseStr(String tmp) {
//        return new StringBuilder(tmp).reverse().toString();
//    }
//
//    private static String removeLastIndex(String t) {
//        return t.substring(0, t.length() - 1);
//    }
