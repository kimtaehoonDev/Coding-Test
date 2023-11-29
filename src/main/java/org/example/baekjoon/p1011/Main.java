package org.example.baekjoon.p1011;

import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.StringJoiner;
    import java.util.StringTokenizer;

public class Main {
    public static long start;
    public static long end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        List<Integer> answers = new ArrayList<>();
        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int answer = solve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            answers.add(answer);
        }
        StringJoiner sj = new StringJoiner("\n");
        for (Integer answer : answers) {
            sj.add(String.valueOf(answer));
        }
        System.out.println(sj);
    }

    public static int solve(int s, int e) {
        int distance = e - s;
        int max = (int) Math.sqrt(distance);
        if (max == Math.sqrt(distance)) {
            return max * 2 - 1;
        } else if (distance <= max * max + max) {
            return max * 2;
        } else {
            return max * 2 + 1;
        }
    }

}
