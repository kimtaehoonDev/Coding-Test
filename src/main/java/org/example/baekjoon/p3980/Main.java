package org.example.baekjoon.p3980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    static List<Set<Player>> playersOfPosition;
    static int maxScore;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        List<Integer> answers = new ArrayList<>();
        for(int i=0; i<T; i++) {
            answers.add(solve(br));
        }
        StringJoiner sj = new StringJoiner("\n");
        for (Integer answer : answers) {
            sj.add(String.valueOf(answer));
        }
        System.out.println(sj);
    }

    // 최대 1100
    static int solve(BufferedReader br) throws IOException {
        // 초기화
        maxScore = 0;
        Set<Integer> selectedPlayer = new HashSet<>();
        // 포지션으로 조회를 하면 해당 포지션에서 뛸 수 있는 선수들을 반환한다
        playersOfPosition = new ArrayList<>();
        for(int i=0; i<11; i++) {
            playersOfPosition.add(new HashSet<>());
        }

        for(int i=0; i<11; i++) { // 선수
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<11; j++) {
                Set<Player> players = playersOfPosition.get(j);
                int grade = Integer.parseInt(st.nextToken());
                if (grade == 0) {
                    continue;
                }
                players.add(new Player(i, grade));
            }
        }

        // 백트래킹
        select(0, 0, selectedPlayer);
        return maxScore;
    }

    static void select(int position, int total, Set<Integer> selectedPlayer) {
        // 점수를 갱신한다
        if (position == 11) {
            maxScore = Math.max(maxScore, total);
            return;
        }

        // 선택된 position에 선수들을 하나씩 배치한다. 백트래킹이 끝나면 원상복구시켜놓는다.
        Set<Player> players = playersOfPosition.get(position);
        for (Player player : players) {
            if (selectedPlayer.contains(player.num)) {
                continue;
            }
            selectedPlayer.add(player.num);
            total += player.grade;
            select(position + 1, total, selectedPlayer);
            total -= player.grade;
            selectedPlayer.remove(player.num);
        }

    }

    static class Player {
        int num;
        int grade;

        public Player(int num, int grade) {
            this.num = num;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Player{" +
                "num=" + num +
                ", grade=" + grade +
                '}';
        }

        public boolean equals(Object other) {
            return this.num == ((Player) other).num;
        }
    }
}
