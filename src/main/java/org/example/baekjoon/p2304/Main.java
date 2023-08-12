package org.example.baekjoon.p2304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 아이디어를 잘못 떠올림. 왼쪽 -> 오른쪽은 잘 생각했으나 오른쪽 -> 왼쪽 아이디어를 잘못 떠올림
// 오른쪽 -> 왼쪽도 크거나 같은 애를 만나면 갱신되는 구조. 그러나 그걸 생각 못함
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Node> ary = new ArrayList<>();
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            ary.add(new Node(L, H));
        }
        Collections.sort(ary);

        int prev = 0;
        int start = 0;
        int total = 0; // 정답누적
        Node tall = null;
        for(Node each: ary) {
           if (prev <= each.height) {
               total += (each.left - start) * prev;
               start = each.left;
               tall = each;
               prev = each.height;
           }
        }

        prev = 0; // 높이
        start = ary.get(ary.size() - 1).left + 1;
        // 역순으로 보면서 가장 큰 애가 나올때까지...
        for(int i=ary.size() - 1; i>=0;i--) {
            Node each = ary.get(i);
            if (prev <= each.height) {
                total += (start - each.left - 1) * prev;
                start = each.left+1;
                prev = each.height;
            }
            if (each.left == tall.left) {
                break;
            }
        }

        total += tall.height;
        System.out.println(total);
        // 가장 큰 애만 더해주면 됨
    }

    public static class Node implements Comparable<Node> {
        int left;
        int height;

        public Node(int left, int height) {
            this.left = left;
            this.height = height;
        }

        public int compareTo(Node n) {
            return this.left - n.left;
        }
    }
}
