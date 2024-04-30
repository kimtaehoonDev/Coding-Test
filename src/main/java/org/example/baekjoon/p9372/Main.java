package org.example.baekjoon.p9372;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

// 11분 / 성공 / 그래프
// Union Find를 사용해 최소 간선을 구하려함 -> 그런데 엣지 정보가 없음..!
// 문제에서 물어보는 건 "모든 노드를 이으려면 최소 몇 개의 엣지가 필요하냐?"와 동일함. 그렇다면 노드 - 1개가 정답이 될 수 밖에 없다.
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] answers = new int[T];
        for(int i = 0; i<T; i++) {
            String[] input1 = br.readLine().split(" ");
            int N = Integer.parseInt(input1[0]); // 국가 <= 1000
            int M = Integer.parseInt(input1[1]); // 비행기(엣지) <= 10,000

            for(int j = 0; j<M; j++) {
                String[] input2 = br.readLine().split(" ");
                int a = Integer.parseInt(input2[0]);
                int b = Integer.parseInt(input2[1]); // a는 1부터 n까지
            }
            answers[i] = N-1;
        }

        StringJoiner sj = new StringJoiner("\n");
        for(int answer : answers) {
            sj.add(String.valueOf(answer));
        }
        System.out.println(sj);
    }

}
