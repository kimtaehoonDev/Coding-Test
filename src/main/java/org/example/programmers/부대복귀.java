package org.example.programmers;

// 성공/20분

// 다익스트라 문제인데 모두 거리가 일정해서 BFS로도 풀 수 있는 문제
// 다익스트라 공식이 떠오르지 않아 BFS로 풀었다

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 부대복귀 {
    class Solution {
        static List<List<Integer>> graph = new ArrayList<>();
        static int[] distances;

        public int[] solution(int n, int[][] roads, int[] sources, int destination) {
            // make graph
            for(int i=0; i<n+1; i++) {
                graph.add(new ArrayList<>());
            }
            for(int[] road: roads) {
                graph.get(road[0]).add(road[1]);
                graph.get(road[1]).add(road[0]);
            }

//         for(List<Integer> each: graph) {
//             System.out.println(each.toString());
//         }
            distances = new int[n+1];
            Arrays.fill(distances, -1);
            bfs(destination);

            // System.out.println(Arrays.toString(distances));
            int[] answer = new int[sources.length];
            int idx = 0;
            for(int person: sources) {
                answer[idx++] = distances[person];
            }

            return answer;
        }

        public void bfs(int start) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            distances[start] = 0;

            while(!queue.isEmpty()) {
                Integer target = queue.poll();
                List<Integer> connects = graph.get(target);
                for(int connect: connects) {
                    if (distances[connect] == -1) {
                        distances[connect] = distances[target] + 1;
                        queue.add(connect);
                    }
                }
            }
        }
    }
}
