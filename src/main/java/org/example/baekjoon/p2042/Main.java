package org.example.baekjoon.p2042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// TODO 다시풀기. 오래걸렸다
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 숫자의 개수
        int M1 = Integer.parseInt(st.nextToken()); // 변경 횟수
        int M2 = Integer.parseInt(st.nextToken()); // 구간합 계산 횟수

        int k = findK(N);
        long[] ary = new long[(int) Math.pow(2,(k + 1))];
        for(int i=0;i<N;i++) {
            ary[i + (int) Math.pow(2,k)] = Long.parseLong(br.readLine());
        }

        // 부모값을 채워넣는다, i==1은 부모노드니까 계산 하지 않음
        for(int i=ary.length-1;i>1;i--) {
            ary[i/2] += ary[i];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M1+M2;i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken()); // 1 | 2
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            if (command == 1) {
                change(a,b, ary, k);
            } else {
                long result = calculateSum(a, b, ary, k);
                sb.append(result + "\n");
            }
        }
        System.out.println(sb);

    }

    private static long calculateSum(int a, long b, long[] ary, int k) {
        List<Integer> selected = new LinkedList<>();
        int startIdx = a + (int) Math.pow(2, k) - 1;
        int endIdx = (int) b + (int) Math.pow(2, k) - 1;

        // print
        while (startIdx <= endIdx) {
            if (startIdx % 2 == 1) {
                selected.add(startIdx);
                startIdx = (startIdx + 1) / 2;
            } else {
                startIdx /= 2;
            }
            if (endIdx % 2 == 0) {
                selected.add(endIdx);
                endIdx = (endIdx - 1) / 2;
            } else {
                endIdx /= 2;
            }
        }
        long answer = 0;
        for (Integer each : selected) {
            answer += ary[each];
        }
        return answer;
    }

    private static void change(int a, long b, long[] ary, int k) {
        // a 인덱스의 숫자를 b로 바꿔라
        int idx = a + (int) Math.pow(2, k) - 1;
        long old = ary[idx];
        ary[idx] = b;
        long gap = b - old;
        while (idx >= 1) {
            idx /= 2;
            ary[idx] += gap;
        }
    }

    private static int findK(int n) {
        int k = 0;
        long value = 1;
        while (true) {
            if (value >= n) {
                return k;
            }
            value = value * 2;
            k++;
        }
    }
}
