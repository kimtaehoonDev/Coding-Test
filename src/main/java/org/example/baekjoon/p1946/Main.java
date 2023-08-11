package org.example.baekjoon.p1946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] answers = new int[T];
        for(int i=0; i<T; i++) {
            answers[i] = solve(br);
        }

        StringJoiner sj = new StringJoiner("\n");
        for(int answer: answers) {
            sj.add(String.valueOf(answer));
        }
        System.out.println(sj.toString());
    }

    public static int solve(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine()); //지원자 수
        List<Rank> ranks = new ArrayList<>();
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int firstRank = Integer.parseInt(st.nextToken());
            int secondRank = Integer.parseInt(st.nextToken());
            ranks.add(new Rank(firstRank, secondRank));
        }

        ranks.sort(new Comparator<>() {
            public int compare(Rank o1, Rank o2) {
                return o1.first - o2.first;
            }
        });

        int minGrade = 1000000;
        int cnt = 0;
        for(Rank rank: ranks) {
            if (minGrade > rank.second) {
                minGrade = rank.second;
                cnt++;
            }
        }

        return cnt;
    }

    static class Rank {
        int first;
        int second;

        public Rank(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
