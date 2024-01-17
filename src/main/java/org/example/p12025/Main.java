package org.example.p12025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;


// 여러번 실패 후 성공 -> 원래 풀이는 39분 걸림
// 아이디어는 일치했고 풀이 역시 일치했는데 한 가지 실수를 함
// 범위가 Long인데 "1L << ary.size() < K" 대신 "1 << ary.size() < K" 을 사용해버림
// 후자의 방법을 사용하면 범위가 int로 제한되면서 스택오버플로우 발생

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] inputs = br.readLine().toCharArray();
        long K = Long.parseLong(br.readLine());
        int[] passwords = new int[inputs.length];
        Stack<Integer> ary = new Stack<>();
        for (int i = 0; i < inputs.length; i++) {
            passwords[i] = inputs[i] - '0';
            if (List.of(1, 6).contains(passwords[i])) {
                passwords[i] = 1;
                ary.push(i);
            } else if (List.of(2, 7).contains(passwords[i])) {
                passwords[i] = 2;
                ary.push(i);
            }
        }
//        System.out.println(Arrays.toString(passwords));
//        System.out.println(ary);
//        System.out.println("==");
        if (1L << ary.size() < K || 1L << ary.size() <= 0L) {
            System.out.println(-1);
            return;
        }

        K--;
        while (K > 0) {
            Integer idx = ary.pop();
            if (K % 2 == 1) {
                if (passwords[idx] == 1) {
                    passwords[idx] = 6;
                } else {
                    passwords[idx] = 7;
                }
            }
            K /= 2;
        }
//        System.out.println(Arrays.toString(passwords));
        StringBuilder sb = new StringBuilder();
        for (int password : passwords) {
            sb.append(password);
        }
        System.out.println(sb.toString());
    }

}