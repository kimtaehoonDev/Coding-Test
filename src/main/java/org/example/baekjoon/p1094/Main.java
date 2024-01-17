package org.example.baekjoon.p1094;

import java.util.Scanner;


// 성공 / 23분 / 비트마스킹
// 비트마스킹으로 풀려고 하다보니 시간이 조금 걸림
// 만드는 과정에 집중을 했는데, 사실 N이라는 숫자를 이진수로 만들었을 때, 1이 몇 개있는지만 확인하면 되는 간단한 문제
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(Integer.bitCount(N));
    }
}
