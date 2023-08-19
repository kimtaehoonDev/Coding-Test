package org.example.baekjoon.p2580;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 아이디어를 잘못 냄 -> 실패
// 백트래킹으로 아이디어 변경 후 풀이 -> 성공

// 처음에 백트래킹을 생각했다. 하지만 더 효율적인 방법을 생각하다가 잘못된 아이디어를 생각해 틀린 문제

public class Main {
    static int[][] graph = new int[9][9];
    static List<Node> empties = new ArrayList<>();
    static boolean checkAnswer = false;


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

        sudoku(0);

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

    public static void sudoku(int idx) {
        if (idx == empties.size()) {
            // 종료조건
            checkAnswer = true;
            return;
        }
        Node target = empties.get(idx);

        for(int num=1; num<=9; num++) {
            boolean canAnswer = validateNum(target, num);
            if (!canAnswer) {
                continue;
            }

            graph[target.x][target.y] = num;
            sudoku(idx + 1);
            if (checkAnswer) {
                return;
            }
            graph[target.x][target.y] = 0;
        }
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



