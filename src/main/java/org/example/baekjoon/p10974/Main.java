package org.example.baekjoon.p10974;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// 성공 / 25분 -> ex. 011(3) & 010 연산결과로 010(2)와 같은 정수가 나오는데, 1(비트있음) or 0(비트없음)이 나온다고 착각해 오래 걸림

// nums : 크기가 N인 공간. 원소들을 저장한다.
// store : 크기가 R인 공간. 선택한 원소를 저장하는 중간 저장소(마지막에 Arrays.copyOfRange 해줌)
public class Main {

    static int N;
    static int R;
    static int[] store;
    static int[] nums;
    static List<int[]> answers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N, nums 초기화
        N = sc.nextInt();
        nums = new int[N];
        for(int i = 0; i<N; i++) {
            nums[i] = i + 1;
        }

        R = N; // 여기서는 그렇다
        store = new int[R];

        perm(0,0);

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (int[] answer : answers) {
            idx++;
            for (int each : answer) {
                sb.append(each);
                sb.append(" ");
            }
            if (idx != answers.size()) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static void perm(int flag, int depth) {
        if (depth == store.length) {
            // 정답을 복사한다
            answers.add(Arrays.copyOfRange(store, 0, store.length));
            return;
        }
        for(int i = 0; i<N; i++) {
            if ((flag & 1<<i) != 0) {
                continue;
            }
            store[depth] = nums[i];
            perm(flag | 1<<i, depth + 1);
        }
    }
}
