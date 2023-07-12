package org.example.baekjoon.p1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// TODO 내일 일어나서 우선순위 큐와 함께 풀어보기
public class Main {
    public static int INF = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[V + 1];

        int[] answer = new int[V + 1];
        for (int i = 2; i < V + 1; i++) {
            answer[i] = INF;
        }
        answer[K] = 0;

        List<List<Node>> graph = new ArrayList<>(V + 1);
        for (int i = 0; i < V + 1; i++) {
            graph.add(new LinkedList<>());
        }

        // 그래프를 초기화한다
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, cost));
        }

        // 가장 cost가 낮고 방문하지 않은 녀석을 넣는다
        for (int i = 0; i < V; i++) {
            visited[K] = true;
            List<Node> connected = graph.get(K);

            for (Node each : connected) {
                int oldCost = answer[each.getIdx()];
                int newCost = answer[K] + each.getCost();
                if (newCost < oldCost) {
                    answer[each.getIdx()] = newCost;
                }
            }

            int min = INF;
            Integer minIdx = null;
            for (int j = 0; j < answer.length; j++) {
                if (!visited[j] && answer[j] < min) {
                    min = answer[j];
                    minIdx = j;
                }
            }
            if (minIdx == null) {
                break;
            }
            K = minIdx;
        }


        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < answer.length; i++) {
            if (answer[i] >= INF) {
                sb.append("INF\n");
            } else {
                sb.append(answer[i] + "\n");
            }
        }
        System.out.println(sb);
    }

    static class Node {
        private int end;
        private int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        public int getIdx() {
            return end;
        }

        public int getCost() {
            return cost;
        }
    }
}
