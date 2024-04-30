package org.example.baekjoon.p9017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// 성공 / 30분 / 구현
// 쉽다고 문제 대충 읽었다가 다시 풀게 됨. 이 과정에서 시간이 꽤나 소모..
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] players = new int[N + 1];
            Map<Integer, Integer> teamCount = new HashMap<>();
            for (int j = 1; j <= N; j++) {
                players[j] = Integer.parseInt(st.nextToken());
                teamCount.put(players[j], teamCount.getOrDefault(players[j],0) + 1);
            }

            List<Integer> ranks = new ArrayList<>();
            ranks.add(-1);

            for (int j = 1; j <= N; j++) {
                if (teamCount.get(players[j]) < 6) {
                    continue;
                }
                ranks.add(players[j]);
            }
//            System.out.println(ranks);
            Map<Integer, Team> teams = new HashMap<>();
            for(int j = 1; j<ranks.size(); j++) {
                // ranks.get(j)는 j등
                Team team = teams.getOrDefault(ranks.get(j), new Team(ranks.get(j)));
                teams.put(ranks.get(j), team);
                team.add(j);
            }

            // 모든 팀의 점수 계산 끝남
            Team winner = null;
            for(Team team : teams.values()) {
                if (winner == null) {
                    winner = team;
                    continue;
                }
                if (team.score < winner.score) {
                    winner = team;
                }else if (team.score == winner.score) {
                    if (team.players.get(4) < winner.players.get(4)) {
                        winner = team;
                    }
                }
            }
            System.out.println(winner.idx);
        }
    }

    static class Team {

        List<Integer> players = new ArrayList<>();
        int idx;
        int score;

        public Team(int idx) {
            this.idx = idx;
        }

        public void add(int playerRank) {
            players.add(playerRank);
            if (players.size() <= 4) {
                score += playerRank;
            }
        }
    }
}
