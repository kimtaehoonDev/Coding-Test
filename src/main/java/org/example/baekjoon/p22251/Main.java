package org.example.baekjoon.p22251;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

// 다른 사람 풀이 참조 / 50분 + 16분 / 구현, 비스마스킹
// 50분 고민한 뒤, 다른 분 설명 들으면서 문제를 잘못 해석했다는 걸 알게 됨.
// "전체를 기준으로" LED를 반전시켜야 한다는 걸 깨달음. 나는 각각의 숫자마다 LED를 반전시킨다고 생각함...
// 이후 다시 처음부터 풀었음 -> 다시 풀었을 때 걸린 시간 16분
public class Main {

    static List<Integer> bits = new ArrayList<>(); // 0<= <10까지 디스플레이 비트들을 저장

    static {
        List<String> temp = new ArrayList<>();
        temp.add("1110111"); // 0
        temp.add("0010010"); // 1
        temp.add("1011101"); // 2
        temp.add("1011011"); // 3
        temp.add("0111010"); // 4
        temp.add("1101011"); // 5
        temp.add("1101111"); // 6
        temp.add("1010010"); // 7
        temp.add("1111111"); // 8
        temp.add("1111011"); // 9
        for(String each :temp) {
            char[] chars = each.toCharArray();
            int bit = 0;
            for(int i = 0; i<chars.length; i++) {
                if (chars[i] == '1') {
                    bit += (int) Math.pow(2,i);
                }
            }
            bits.add(bit);
        }
//        System.out.println(bits); // 0 1 2 검증완료
    }

    static int N;
    static int K;
    static int P;
    static int X;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        N = Integer.parseInt(st.nextToken()); // 최상층
        K = Integer.parseInt(st.nextToken()); // 디스플레이 자릿수
        P = Integer.parseInt(st.nextToken()); // 호석이가 제어 가능한 개수
        X = Integer.parseInt(st.nextToken()); // 현재 엘레베이터 위치

        // X를 자릿수로 변경한다. 0을 포함한다.
        Display now = new Display(X, K);
        int answer = 0;

        // 자리수별로 만들 수 있는 숫자를 만든다.
        for(int i =1; i<= Math.pow(10, K); i++) {
            if (i > N) {
                // N층까지만 확인하고 넘어가면 확인할 필요 없다
                break;
            }
            if (i == X) {
                continue;
            }
//            System.out.println("반복 " + i);

            // i 와 now를 비교한다
            Display display = new Display(i, K);
            int cnt = now.calculateGap(display);
//            System.out.println("cnt is " + cnt );
            if (cnt <= P) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    static class Display {
        List<Integer> values;

        public Display(int x, int k) {
            List<Integer> values = new ArrayList<>(); // 현재층
            for(int i = 0; i<k; i++) {
                if (x == 0) {
                    values.add(0);
                    continue;
                }
                values.add(x % 10);
                x /= 10;
            }
            Collections.reverse(values);
            this.values = values;
        }

        public int calculateGap(Display display) {
            int gap = 0;
            for(int i = 0; i< values.size(); i++) {
                int my = bits.get(this.values.get(i));
                int other = bits.get(display.values.get(i));
                gap += Integer.bitCount(my ^ other);
            }
            return gap;
        }
    }
}
