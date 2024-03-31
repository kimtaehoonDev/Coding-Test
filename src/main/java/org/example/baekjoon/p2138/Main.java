package org.example.baekjoon.p2138;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 아이디어 참조 후 풀이 / 1시간 고민했는데 풀이를 떠올리지 못함
// 개인적으로 어려운 문제. x버튼을 눌렀을 때, 문제에서는 x-1,x,x+1이 뒤집힌다고 이야기함.
// 이걸 첫번째 버튼 누른상황/누르지 않은 상황 분기하고 x, x+1, x+2 가 뒤집히는 거로 바꿔서 생각해야 하는데
// 나중에 다시 마주쳐도 아이디어를 떠올리기는 쉽지 않을 거로 예상
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 2이상
        char[] input = br.readLine().toCharArray();
        boolean[] init1 = new boolean[input.length];
        boolean[] init2 = new boolean[input.length];
        for(int i = 0; i<input.length; i++) {
            if (input[i] == '0') {
                init1[i] = false;
                init2[i] = false;
            } else {
                init1[i] = true;
                init2[i] = true;
            }
        }
        init2[0] = !init2[0];
        init2[1] = !init2[1];

        char[] input2 = br.readLine().toCharArray();
        boolean[] target = new boolean[input2.length];
        for(int i = 0; i<input2.length; i++) {
            if (input2[i] == '0') {
                target[i] = false;
            } else {
                target[i] = true;
            }
        }

//        System.out.println(Arrays.toString(init1));
//        System.out.println(Arrays.toString(init2));
//        System.out.println(Arrays.toString(target));

        int clicked = 0;
        int answer = 1000000000;
        for(int i = 0; i<N-1; i++) {
            if (init1[i] != target[i]) {
                // 버튼을 클릭한다
                clicked++;
                init1[i] = !init1[i];
                init1[i + 1] = !init1[i + 1];
                if (i + 2 < N) {
                    init1[i + 2] = !init1[i + 2];
                }
            }
        }
        if (init1[N - 1] == target[N - 1]) {
            answer = Math.min(answer, clicked);
        }
        // 0번째 스위치를 누른 경우
        clicked = 1;
        for(int i = 0; i<N-1; i++) {
            if (init2[i] != target[i]) {
                // 버튼을 클릭한다
                clicked++;
                init2[i] = !init2[i];
                init2[i + 1] = !init2[i + 1];
                if (i + 2 < N) {
                    init2[i + 2] = !init2[i + 2];
                }
            }
        }
        if (init2[N - 1] == target[N - 1]) {
            answer = Math.min(answer, clicked);
        }

        if (answer == 1000000000) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
