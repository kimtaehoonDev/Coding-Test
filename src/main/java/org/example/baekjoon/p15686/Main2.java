package org.example.baekjoon.p15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {
    static int HOUSE = 1;
    static int CHICKEN = 2;

    static int N;
    static int M;
    static int[][] graph;
    static List<Spot> chickens;
    static List<Spot> houses;
    static int answer = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        chickens = new ArrayList<>();
        houses = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == CHICKEN) {
                    chickens.add(new Spot(i, j));
                } else if (graph[i][j] == HOUSE) {
                    houses.add(new Spot(i, j));
                }
            }
        }

        List<List<Spot>> cases = findAllSelectedCases();
        for(List<Spot> eachCase: cases) {
            int dist = calcChickenDistance(eachCase);
            answer = Math.min(dist, answer);
        }
        System.out.println(answer);
    }

    // selectedChickens가 선택되었을 때 치킨 거리를 계싼한다
    static int calcChickenDistance(List<Spot> selectedChickens) {
        int total = 0;
        for (Spot house : houses) {
            total += calcMinDistance(house, selectedChickens);
        }
        return total;
    }

    // house에서 가장 짧은 치킨 거리를 구한다
    static int calcMinDistance(Spot house, List<Spot> selectedChickens) {
        int min = 1000000000;
        for (Spot selectedChicken : selectedChickens) {
            int distance = Math.abs(house.x - selectedChicken.x) + Math.abs(house.y - selectedChicken.y);
            min = Math.min(distance, min);
        }
        return min;
    }

    static List<List<Spot>> findAllSelectedCases() {
        int n = chickens.size();
        int r = M;
        List<List<Spot>> cases = new ArrayList<>();
        comb(n, r, 0, 0, new ArrayList<>(), cases);
//        for(List<Spot> each : cases) {
//            System.out.println(each);
//        }
        return cases;
    }

    static void comb(int n, int r, int start, int depth, List<Spot> tempAnswer, List<List<Spot>> cases) {
        if (r == depth) {
            cases.add(new ArrayList<>(tempAnswer));
            return;
        }

        for(int i = start; i<n; i++) {
            Spot selected = chickens.get(i);
            tempAnswer.add(selected);
            comb(n, r, i + 1, depth + 1, tempAnswer, cases);
            tempAnswer.remove(selected);
        }
    }

    static class Spot {
        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
