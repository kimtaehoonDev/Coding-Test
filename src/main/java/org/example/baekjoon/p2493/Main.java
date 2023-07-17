package org.example.baekjoon.p2493;

import java.io.*;
import java.util.*;

public class Main {
    static int[] buildings;
    static int NOT_FOUND = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in)); // 한줄에 여러개있으면 버퍼리더랑 성능차이 있나? 알아보기
        int N = Integer.parseInt(br.readLine());
        buildings = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> answers = solve();
        StringJoiner sj = new StringJoiner(" ");
        for(Integer answer: answers) {
            sj.add(String.valueOf(answer));
        }
        System.out.println(sj);
    }

    static List<Integer> solve() {
        List<Integer> answers = new ArrayList<>(buildings.length);
        Stack<Building> stack = new Stack<>();

        for(int i = 0; i< buildings.length; i++) {
            int target = buildings[i];

            while(!stack.isEmpty()) {
                Building building = stack.peek();

                if (building.value < target) {
                    stack.pop();
                } else {
                    answers.add(building.idx);
                    stack.add(new Building(i+1, target));
                    break;
                }
            }
            if (stack.isEmpty()) {
                stack.push(new Building(i+1, target));
                answers.add(NOT_FOUND);
            }
        }
        return answers;
    }

    static class Building {
        int idx;
        int value;

        public Building(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
}
