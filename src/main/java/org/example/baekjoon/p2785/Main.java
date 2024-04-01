package org.example.baekjoon.p2785;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// 성공 / 27분 / 그리디
// 예제를 해석하는 과정(문제 이해 과정)에서 시간이 오래 걸림
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] chains = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            chains[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(chains); // 오름차순
//        if (chains.length <= 2) {
//            System.out.println(1);
//            return;
//        }

        Deque<Integer> list = new LinkedList<>();
        for(int i = 0; i<N; i++) {
            list.add(chains[i]);
        }

        int answer = 0;
        while(list.size() > 1) {
            int smallest = list.removeFirst();
            answer++;
            list.removeLast(); // 가장 큰거랑 두번째로 큰거를 묶는다. (굳이 덧셈 연산 필요 없어서 버림. 가장큰거 + 두번째큰거 더하면 가장 큰값이 나오니까)
            if (smallest != 1) { // 고리가 1개짜리면 -> 자기자신 + 체인 2개를 하나로 묶을 수 있다.
                list.addFirst(smallest - 1);
            }
        }
        System.out.println(answer);
    }

}
