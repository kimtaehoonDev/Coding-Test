package org.example.programmers;
import java.util.*;
import java.math.*;

class 가장_먼_노드 {
    int[] visited;
    List<List<Integer>> graph;

    public int solution(int n, int[][] edge) {
        visited = new int[n+1];
        graph = new ArrayList<>(n+1);
        for(int i=0; i<n+1;i++) {
            visited[i] = -1; //하는 김에 같이 초기화
            graph.add(new ArrayList<>());
        }
        for(int[] each: edge) {
            graph.get(each[0]).add(each[1]);
            graph.get(each[1]).add(each[0]);
        }

        bfs();
        int max = 0;
        for(Integer e : visited) {
            max = Math.max(max, e);
        }

        if (max == 0) {
            return 0;
        }
        int answer = 0;
        for(Integer e: visited) {
            if (max == e) {
                answer += 1;
            }
        }
        return answer;
    }

    public void bfs() {
        visited[1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        while(!queue.isEmpty()) {
            int x = queue.poll();
            List<Integer> connects = graph.get(x);
            for(Integer connect: connects) {
                if (visited[connect] != -1) {
                    continue;
                }
                visited[connect] = visited[x] + 1;
                queue.add(connect);
            }
        }
    }
}