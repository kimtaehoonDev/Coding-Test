package org.example.baekjoon.p22862;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 실패 / 40분 고민
// 처음에 DP라고 생각함 -> 이후 구현으로 문제를 품 (아이디어 자체가 틀린듯)
// 투포인터 문제. 이 유형 많이 풀어봐야겠음. 최소한 이 문제에서는 투포인터라는 걸 알아도 감을 못잡음
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]); // 삭제 가능 최대 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Node> nodes = new ArrayList<>();
        int conn = 0;
        boolean prevOdd = false;
        for(int i = 0; i<N; i++) {
            int element = Integer.parseInt(st.nextToken());
            if (i == 0) {
                // 짝수 홀수 따라 값을 넣는다.
                if (element % 2 != 0) {
                    prevOdd = true;
                }
                conn++;
                continue;
            }

            if (element % 2 == 0) {
                if (!prevOdd) {
                    conn++;
                } else {
                    nodes.add(new Node(prevOdd, conn));
                    conn = 1;
                    prevOdd = false;
                }
            } else { // 홀수
                if (prevOdd) {
                    conn++;
                } else {
                    nodes.add(new Node(prevOdd, conn));
                    conn = 1;
                    prevOdd = true;
                }
            }
        }
//        System.out.println(nodes);

        int[] A = new int[nodes.size()];
        int[] B = new int[nodes.size()];
        int idx = -1;
        int prev = 0;
        for(Node node : nodes) {
            idx++;
            if (!node.isOdd) {
                B[idx] = prev + node.connectedCnt;
                prev = B[idx];

            } else {
                B[idx] = B[idx - 1];
            }
        }
    }

    static class Node {
        boolean isOdd;
        int connectedCnt;

        public Node(boolean isOdd, int connectedCnt) {
            this.isOdd = isOdd;
            this.connectedCnt = connectedCnt;
        }

        @Override
        public String toString() {
            return "Node{" +
                "isOdd=" + isOdd +
                ", connectedCnt=" + connectedCnt +
                '}';
        }
    }

}
