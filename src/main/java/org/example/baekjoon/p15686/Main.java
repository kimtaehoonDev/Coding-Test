package org.example.baekjoon.p15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 성공/24분
public class Main {

    public static int answer = 1000000000;

    public static final int EMPTY = 0;
    public static final int HOUSE = 1;
    public static final int CHICKEN = 2;

    public static List<int[]> cases = new ArrayList<>();
    public static List<Spot> chickens = new ArrayList<>();
    public static List<Spot> houses = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N+2][N+2];
        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == CHICKEN) {
                    chickens.add(new Spot(i,j));
                } else if (graph[i][j] == HOUSE) {
                    houses.add(new Spot(i,j));
                }
            }
        }

        comb(chickens.size(), M, 0,0, new int[M]);

        for(int[] each: cases) {
            // each안에는 선택된 치킨집들의 idx가 들어간다
            int sum = 0;
            for(Spot house: houses) {
                //각 집들에서 최소 치킨집까지 거리를 찾는다
                int min = 100000000;
                for(int idx: each) {
                    Spot selectedChicken = chickens.get(idx);
                    min = Math.min(min, Math.abs(house.x - selectedChicken.x) + Math.abs(house.y - selectedChicken.y));
                }
                sum += min;
            }
            answer = Math.min(answer, sum);
        }

        System.out.println(answer);
    }

    public static void comb(int n, int r, int depth, int start, int[] output) {
        if (r == depth) {
            cases.add(Arrays.copyOfRange(output, 0, r));
            return;
        }
        for(int i=start; i<n; i++) {
            output[depth] = i;
            comb(n, r, depth +1, i+ 1, output);
        }
    }


    public static class Spot {
        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
