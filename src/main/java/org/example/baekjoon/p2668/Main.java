package org.example.baekjoon.p2668;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

// 성공 / 34분 / 그래프
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] values = new int[N+1];
        for(int i = 1 ; i<=N; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }
//        System.out.println("입력값 " + Arrays.toString(values));

        boolean[] canAnswer = new boolean[N+1];
        for(int i = 1; i<=N; i++) {
            if (canAnswer[i]) {
                continue;
            }
            Set<Integer> circle = findCircle(i, values);
//            System.out.println(i + "번째 -> " + (circle == null ? "널" : circle));
            if (circle == null) {
                continue;
            }
            for(int each : circle) {
                canAnswer[each] = true;
            }
        }
//        System.out.println(Arrays.toString(canAnswer));
        List<Integer> answers = new ArrayList<>();
        int cnt = 0;
        for(int i = 1; i<= N; i++) {
            if (canAnswer[i]) {
                answers.add(i);
                cnt++;
            }
        }
        StringJoiner sj = new StringJoiner("\n");
        sj.add(String.valueOf(cnt));
        for (Integer answer : answers) {
            sj.add(String.valueOf(answer));
        }
        System.out.println(sj);
    }

    static Set<Integer> findCircle(int x, int[] values) {
        List<int[]> temp = new ArrayList<>();
        boolean[] select = new boolean[values.length];
        while(true) {
            if (x <= 0 || x >= values.length) {
                return null;
            }
            if (select[x]) {
                // 순환이 만들어졌다
                break;
            }
            int next = values[x];
            select[x] = true;
            temp.add(new int[] {x, next});
            x = next;
        }

        Set<Integer> set = new HashSet<>();
        for(int[] each : temp) {
            set.add(each[1]);
        }
        if (set.size() != temp.size()) {
            return null; // 불가능
        }
        for(int[] each : temp) {
            if (!set.contains(each[0])) {
                return null;
            }
        }
        return set;
    }

}
