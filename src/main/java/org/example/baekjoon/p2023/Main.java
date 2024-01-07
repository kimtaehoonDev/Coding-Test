package org.example.baekjoon.p2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

// 성공, 백트래킹 / 40분
// 맨 처음에 에라스토텔레스의 체를 사용하려 했는데 공간복잡도를 해결할 수 없었음
// 이후, 신기한 소수가 되려면 -> 현재 숫자가 소수이며 && 10으로 나누어 떨어진 몫이 신기한 소수어야 한다는 조건을 발견
// 백트래킹으로 문제 해결
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer> prevDigitPrimes = new ArrayList<>();
        prevDigitPrimes.addAll(List.of(2, 3, 5, 7));
        for (int i = 2; i <= N; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int baseNum : prevDigitPrimes) {
                for (int target = baseNum * 10; target < baseNum * 10 + 10; target++) {
                    if (isPrime(target)) {
                        temp.add(target);
                    }
                }
            }
            prevDigitPrimes = temp;
        }

//        System.out.println(prevDigitPrimes);
        StringJoiner sj = new StringJoiner("\n");
        for (Integer each : prevDigitPrimes) {
            sj.add(each.toString());
        }
        System.out.println(sj);
    }

    private static boolean isPrime(int target) {
        if (target < 2) {
            return false;
        }
        for(int i =2; i<=Math.sqrt(target); i++) {
            if (target % i == 0) {
                return false;
            }
        }
        return true;
    }

}
