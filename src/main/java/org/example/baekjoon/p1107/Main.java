package org.example.baekjoon.p1107;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

//public class Main {
//    public static void main(String[] args) {
//
//    }
//}


public class Main {
    static int N;
    static boolean isPlusBroken;
    static boolean isMinusBroken;
    static List<Integer> brokenBtn;
    static boolean[] visited = new boolean[3000000];

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int brokenCnt = sc.nextInt();
        brokenBtn = new ArrayList<>();
        if (brokenCnt != 0) {
            for(int i=0; i<brokenCnt; i++) {
                String value = sc.next();
                if (value.equals("+")) {
                    isPlusBroken = true;
                } else if (value.equals("-")) {
                    isMinusBroken = true;
                } else {
                    brokenBtn.add(Integer.parseInt(value));
                }
            }
        }


//        System.out.println(brokenBtn); // 입력값이 잘 들어갔나 검증한다

        // 두 개의 큐를 만들고 peek으로 비교
        Queue<Node> heap1 = new PriorityQueue<>(); // 100부터 넣는다
        Queue<Node> heap2 = new PriorityQueue<>(); // N부터 넣는다
        heap1.add(new Node(100, 0));

        heap2.add(new Node(N, calcNumDigit(N)));
//        System.out.println("자릿수 " + calcNumDigit(N));
        System.out.println(bfs(heap1, heap2));

    }

    // 클릭한 횟수 cnt를 반환한다
    static int bfs(Queue<Node> heap1, Queue<Node> heap2) {

        boolean isHeap1First = false;
        while (!heap1.isEmpty() || !heap2.isEmpty()) {
            // 순서를 찾는다
            isHeap1First = findHeap1IsFirst(heap1, heap2);
            if (isHeap1First) {
                Node target = heap1.poll();
                if (target.value == N) {
                    return target.cnt;
                }

                if (target.value < N) {
                    heap1.add(new Node(target.value + 1, target.cnt + 1));
                } else {
                    heap1.add(new Node(target.value - 1, target.cnt + 1));
                }
            } else {
                Node target = heap2.poll();
                if (visited[target.value]) {
                    continue;
                }
//                System.out.println(target);

                visited[target.value] = true;
                if (canMake(target.value)) {
                    return target.cnt;
                }

                heap2.add(new Node(target.value + 1, calcNumDigit(target.value + 1) + Math.abs(target.value + 1 - N)));

                if (target.value > 0){
                    heap2.add(new Node(target.value - 1, calcNumDigit(target.value - 1) + Math.abs(target.value - 1 - N)));
                }

            }
        }

        return -1229;
    }

    static boolean canMake(int num) {
        if (num == 0) {
            if (brokenBtn.contains(0)) {
                return false;
            }
            return true;
        }
        int temp = num;
        int cnt = 1;
        while (temp > 0) {
            int digit = temp % 10;
            if (brokenBtn.contains(digit)) {
                return false;
            }
            temp /= 10;
            cnt++;
        }
        return true;
    }


    // 양수의 자릿수를 구한다
    static int calcNumDigit(int num) {
        int temp = num;
        int cnt = 1;
        while (true) {
            if (temp < 10) {
                return cnt;
            }
            temp /= 10;
            cnt++;
        }
    }


    static boolean findHeap1IsFirst(Queue<Node> heap1, Queue<Node> heap2) {
        if (heap1.isEmpty()) {
            return false;
        }
        if (heap2.isEmpty()) {
            return true;
        }
        // 둘 다 값이 있으면 우열을 비교 -> 더 버튼을 조금 누른 쪽이 우선순위를 갖는다
        if (heap1.peek().cnt < heap2.peek().cnt) {
            return true;
        }

        return false;
    }

    static class Node implements Comparable<Node> {
        int value;
        int cnt; // 리모콘을 누른 카운트

        public Node(int value, int cnt) {
            this.value = value;
            this.cnt = cnt;
        }

        // cnt가 작은 순서로 정렬
        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }

        @Override
        public String toString() {
            return "Node{" +
                "value=" + value +
                ", cnt=" + cnt +
                '}';
        }
    }
}