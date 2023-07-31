package org.example.leetcode;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/number-of-provinces/description/?envType=study-plan-v2&envId=graph-theory
public class p547 {
    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int cnt = 0;
        for(int i=0; i<isConnected.length;i++) {
            for(int j=0; j<isConnected.length; j++) {
                if (i == j) {
                    continue;
                }
                if (!visited[i] && isConnected[i][j] == 1) {
                    bfs(i, isConnected, visited);
                    cnt++;
                }
            }
        }

        for(int i=0; i<isConnected.length;i++) {
            if (!visited[i]) {
                cnt++;
            }
        }

        return cnt;
    }

    public void bfs(int x, int[][] isConnected, boolean[] visited) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        visited[x] = true;

        while(!queue.isEmpty()) {
            x = queue.poll();
            //연결된 지점을 다 방문
            for(int i=0; i<isConnected.length; i++) {
                if (!visited[i] && isConnected[i][x] == 1) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}

// https://www.acmicpc.net/problem/4963 이 문제와 유사함
// 차이점은 노드를 기반으로 그래프를 만들었냐, 엣지를 기준으로 만들었냐임
// 한 번 틀렸는데,
// 그래프가 엣지를 기준으로 나왔는데 이미 아는 문제라고 생각해 방심했다 틀림



// 문제에서 조심해야 할 건 i==j일 경우 연결이 안되어있는데 배열 상에는 1(연결됨) 이 들어감. 해당 부분에 유의하기