package org.example.baekjoon.p22866;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

// 1시간 20분 / 풀이를 생각하는게 쉽지 않았음
public class Main {
    public static void main(String[] args) throws Exception {
        // 값이 작은 순서대로, 값이 같다면 오른쪽 인덱스부터 정렬
        Queue<Node> sortedRight = new PriorityQueue<>((n1, n2) -> {
            if (n1.val != n2.val) {
                return n1.val - n2.val;
            }
            return n2.idx - n1.idx;
        });
        // 값이 작은 순서대로, 값이 같다면 왼쪽 인덱스부터 정렬
        Queue<Node> sortedLeft = new PriorityQueue<>((n1, n2) -> {
            if (n1.val != n2.val) {
                return n1.val - n2.val;
            }
            return n1.idx - n2.idx;
        });
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] heights = new int[N];
        int[] rights = new int[N];
        int[] lefts = new int[N];
        int[] nears = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
            Node node = new Node(i, heights[i]);
            sortedRight.add(node);
            sortedLeft.add(node);
        }

        Map<Integer, List<Integer>> listenerOnRight = new HashMap<>();
        // 오른쪽으로 바라봤을 떄 보이는 애들을 찾는다
        while (!sortedRight.isEmpty()) {
            Node target = sortedRight.poll();
            for (int i = target.idx + 1; i < N; i++) {
                if (target.val < heights[i]) {
                    // 구독해놓고 종료
                    if (!listenerOnRight.containsKey(i)) {
                        listenerOnRight.put(i, new ArrayList<>());
                    }
                    listenerOnRight.get(i).add(target.idx); // target.idx는 i를 바라본다
                    break;
                }
            }
        }
//        for (Integer idx : listenerOnRight.keySet()) {
//            System.out.println(idx);
//            System.out.println(listenerOnRight.get(idx));
//        }
//        System.out.println(Arrays.toString(heights));
        for (int i = N - 1; i >= 0; i--) {
            if (listenerOnRight.containsKey(i)) {
                List<Integer> subscribers = listenerOnRight.get(i);
                for (Integer subscriber : subscribers) {
                    rights[subscriber] = rights[i] + 1;
                    nears[subscriber] = i + 1;
                }
            }
        }

        Map<Integer, List<Integer>> listenerOnLeft = new HashMap<>();
//         왼쪽으로 바라봤을 떄 보이는 애들을 찾는다
        while (!sortedLeft.isEmpty()) {
            Node target = sortedLeft.poll();
            for (int i = target.idx - 1; i >=0 ; i--) {
                if (target.val < heights[i]) {
                    // 구독해놓고 종료
                    if (!listenerOnLeft.containsKey(i)) {
                        listenerOnLeft.put(i, new ArrayList<>());
                    }
                    listenerOnLeft.get(i).add(target.idx); // target.idx는 i를 바라본다
                    break;
                }
            }
        }
//        for (Integer idx : listenerOnLeft.keySet()) {
//            System.out.println(idx);
//            System.out.println(listenerOnLeft.get(idx));
//        }
        for (int i = 0; i < N; i++) {
            if (listenerOnLeft.containsKey(i)) {
                List<Integer> subscribers = listenerOnLeft.get(i);
                for (Integer subscriber : subscribers) {
                    lefts[subscriber] = lefts[i] + 1;

                    // subscriber +1(subscriber의 건물번호)와 nears[subscriber](오른쪽인접건물번호) 거리를 구한다
                    // 지금 거리가 더 가깝거나 같으면 갱신한다
                    if (Math.abs(subscriber - i) <= Math.abs(subscriber + 1 - nears[subscriber])) {
                        nears[subscriber] = i + 1;
                    }
                }
            }
        }

        // 검증
//        System.out.println("오른" + Arrays.toString(rights));
//        System.out.println("왼" + Arrays.toString(lefts));
//        System.out.println("가까운" + Arrays.toString(nears));

        // Rights랑 Lefts를 더하면 몇 개 건물 바라보는지 확인이 가능
        // 가장 가까운 건물을 어떻게 찾냐?
        StringJoiner sj = new StringJoiner("\n");
        for(int i = 0; i<N; i++) {
            int cnt = lefts[i] + rights[i];
            if (cnt == 0) {
                sj.add("0");
                continue;
            }
            String temp = cnt + " " + nears[i];
            sj.add(temp);
        }
        System.out.println(sj.toString());
    }

    static class Node {
        int idx;
        int val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}
