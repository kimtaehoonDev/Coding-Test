package org.example.baekjoon.p2580;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 아이디어를 잘못 냄.
// 가능한 경우의 수가 작은거 순서대로 채워나가면 될 거라 생각했는데 불가능한 케이스가 생김

// 아래 예시에서 불가능함
//0 4 0 0 0 0 2 0 0
//0 6 0 0 0 5 0 0 0
//2 0 5 0 8 0 0 0 7
//0 0 6 0 0 0 0 0 0
//5 0 7 0 0 1 9 0 0
//0 0 0 0 4 0 0 1 0
//0 0 0 3 0 0 0 0 8
//0 2 0 0 0 0 0 0 0
//9 0 1 0 0 4 7 0 0


public class Main {
    static int[][] graph = new int[9][9];
    static List<Node> empties = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 0) {
                    empties.add(new Node(i,j));
                }
            }
        }
        // 그래프 초기화 잘 되었는가 확인한다
//        for(int i=0; i<9; i++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }

        while(empties.size() != 0) {
            // 그래프에서 공백을 찾아 안되는 값들을 확인한다
            PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.cases.size() - o2.cases.size(); // 가능한 경우가 더 적으면 우선순위를 갖는다
                }
            });

            for(Node empty: empties) {
                List<Integer> cases = findNums(empty);
                empty.setCases(cases);
                queue.add(empty);
            }

            Node target = queue.poll();

            graph[target.x][target.y] = target.cases.get(0);

            empties.remove(target);
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                sb.append(graph[i][j]);
                if (j != 8) {
                    sb.append(" ");
                } else {
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    static List<Integer> findNums(Node node) {
        List<Integer> result = new ArrayList<>();
        // 가로, 세로, 대각선 모두 가능한 걸 찾는다
        for(int i=1; i<=9; i++) {
            boolean canResult = validateNum(node, i);
            if (canResult) {
                result.add(i);
            }
        }
//        System.out.println(node.x + "/" + node.y + "->" + result); // 특정 위치에 들어갈 수 있는 값
        return result;
    }

    // node자리에 i가 들어갈 수 있는가?
    static boolean validateNum(Node node, int i) {

        for(int j=0; j<9; j++) {
            // 가로세로 확인
            if (graph[node.x][j] == i) {
                return false;
            }
            if (graph[j][node.y] == i) {
                return false;
            }
        }

        // 대각선 확인
        for(int a = node.x/3 * 3; a< node.x/3 * 3 + 3; a++) {
            for(int b = node.y/3 * 3; b< node.y/3 * 3 + 3; b++) {
                if (graph[a][b] == i) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Node {
        int x;
        int y;
        List<Integer> cases;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setCases(List<Integer> cases) {
            this.cases = cases;
        }
    }
}
