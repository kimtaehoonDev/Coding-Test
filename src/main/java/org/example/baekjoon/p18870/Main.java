package org.example.baekjoon.p18870;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nodes[i] = new Node(i, Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.value > o2.value) {
                    return 1;
                } else if (o1.value < o2.value) {
                    return -1;
                }
                return 0;
            }
        });

        int smallerThanMe = 0;
        Node prev = null;
        int[] answer = new int[N];

        for(Node node: nodes) {
            // 시작
            if (prev == null) {
                answer[node.idx] = 0;
                prev = node;
                continue;
            }
            if (node.value == prev.value) {
                answer[node.idx] = smallerThanMe;
            } else {
                smallerThanMe += 1;
                answer[node.idx] = smallerThanMe;
            }
            prev = node;
        }

        StringJoiner sj = new StringJoiner(" ");
        for (Integer each : answer) {
            sj.add(String.valueOf(each));
        }
        System.out.println(sj);
    }

    static class Node {
        int idx;
        int value;

        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
}
