package org.example.baekjoon.p17140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 성공 / 1시간 6분 / 구현
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1; // 편의를 위해 리인덱싱
        int k = Integer.parseInt(st.nextToken());
        int[][] A = new int[100][100];

        int rowCnt = 3;
        int colCnt = 3;

        for(int i = 0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while(time < 100) {
            if (A[r][c] == k) {
                break;
            }
            time++;
            if (rowCnt >= colCnt) {
                colCnt = operationR(A);
            } else {
                rowCnt = operationC(A);
            }
//            System.out.println("time" + time + " | row, col 길이" + rowCnt + "," + colCnt);
//            for(int i = 0; i<10; i++) {
//                System.out.println(Arrays.toString(Arrays.copyOfRange(A[i], 0, 11)));
//            }
//            System.out.println("@@@@");
        }

        if (A[r][c] == k) {
            System.out.println(time);
        } else {
            System.out.println(-1);
        }
    }

    static int operationC(int[][] A) {
        int max = -1;
        for(int j = 0; j<100; j++) {
//            if (A[0][j] == 0) {
//                break;
//            }
            Map<Integer, Integer> temp = new HashMap<>();
            for(int i = 0; i<100; i++) {
                if (A[i][j] == 0) {
//                    break;
                    continue;
                }
                temp.put(A[i][j], temp.getOrDefault(A[i][j], 0) + 1);
            }
            Queue<Node> queue = new PriorityQueue<>((n1, n2) -> {
                // 횟수 기준 오름차순
                if (n1.count != n2.count) {
                    return n1.count - n2.count;
                }
                // 횟수가 같다면 -> 숫자 기준 오름차순
                return n1.num - n2.num;
            });
            for(int key : temp.keySet()) {
                Integer value = temp.get(key);
                queue.offer(new Node(key, value));
            }
//            System.out.println(queue);
            int idx = 0;
            while(!queue.isEmpty()) {
                Node now = queue.poll();
                A[idx][j] = now.num; // 수 먼저
                A[idx+1][j] = now.count;// 등장횟수
                idx += 2;
                if (idx >= 100) {
                    break;
                }
            }
            max = Math.max(max, idx);
            for(int i = idx; i<100; i++) {
                A[i][j] = 0;
            }
        }
        return max;
    }

    static int operationR(int[][] A) {
        int max = -1;
        for(int i = 0; i<100; i++) {
//            if (A[i][0] == 0) {
//                break;
//            }
            Map<Integer, Integer> temp = new HashMap<>();
            for(int j = 0; j<100; j++) {
                if (A[i][j] == 0) {
//                    break;
                    continue;
                }
                temp.put(A[i][j], temp.getOrDefault(A[i][j], 0) + 1);
            }
            Queue<Node> queue = new PriorityQueue<>((n1, n2) -> {
                // 횟수 기준 오름차순
                if (n1.count != n2.count) {
                    return n1.count - n2.count;
                }
                // 횟수가 같다면 -> 숫자 기준 오름차순
                return n1.num - n2.num;
            });
            for(int key : temp.keySet()) {
                Integer value = temp.get(key);
                queue.offer(new Node(key, value));
            }
//            System.out.println(queue);
            int idx = 0;
            while(!queue.isEmpty()) {
                Node now = queue.poll();
                A[i][idx] = now.num; // 수 먼저
                A[i][idx + 1] = now.count;// 등장횟수
                idx += 2;
                if (idx >= 100) {
                    break;
                }
            }
            max = Math.max(max, idx);
            for(int j = idx; j<100; j++) {
                A[i][j] = 0;
            }
        }
        return max;
    }

    // num은 count번 나옴.
    static class Node {
        int num;
        int count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public String toString() {
            return "{num : " + num + ", count : " + count + "}";
        }
    }

}
