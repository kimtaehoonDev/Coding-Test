package org.example.programmers;

import java.util.LinkedList;
import java.util.Queue;

// 성공 / BFS / 29분
public class 전력망을_둘로_나누기 {
    static boolean[][] graph;
    static int[][] wires;

    public int solution(int n, int[][] wires) {
        this.wires = wires;
        this.graph = new boolean[n+1][n+1];
        for(int[] wire: wires) {
            graph[wire[0]][wire[1]] = true;
            graph[wire[1]][wire[0]] = true;
        }

        int answer = 1000000000;
        for(int i = 0; i<wires.length; i++) {
            int s1 = bfs(wires[i]);
            int s2 = n - s1;
            answer = Math.min(answer, Math.abs(s1 - s2));
        }
        return answer;
    }

    // inactive : 비활성화 와이어(엣지)
    public int bfs(int[] inactivate) {
        // System.out.println(Arrays.toString(inactivate) + " 비활성화");
        int result = 0;
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1); // 1번 송전탑에 방문하겠다.
        visited[1] = true;
        while(!queue.isEmpty()) {
            // System.out.println("상태 " + queue);
            result++;
            int now = queue.poll();
            boolean[] connected = graph[now];
            for(int i = 1; i<connected.length; i++) {
                if (i == now || visited[i]) {
                    continue;
                }

                // now와 i가 연결되어있니?
                if ((now == inactivate[0] && i == inactivate[1]) ||
                    (now == inactivate[1] && i == inactivate[0])) {
                    continue;
                }
                if (connected[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }

        return result;
    }
}
