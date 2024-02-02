package org.example.baekjoon.p14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 43분 / 2회 실패 후 성공 / 구현
// 구현 문제이다 보니 로직 자체가 굉장히 중요한 문제
// 놓친 부분 1 : a가 움직였을 때, a와 b가 원래! 맞닿아있던 부분을 기준으로 바퀴를 돌려야함. 그런데 나는 A를 돌린 이후 상태를 b와 비교함.
// 놓친 부분 2 : 맨 처음 바퀴를 기준으로 생각. 오른쪽에 있는 애들을 다 돌리고, 그 다음 왼쪽을 돌림.
// 이 때 왼쪽 바퀴를 돌리는 방향은 맨 처음 바퀴가 기준이 되어야 하는데 오른쪽 바퀴들에 영향을 받아서 왼쪽이 이상하게 돌아감.

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Wheel[] wheels = new Wheel[4];
        for (int i = 0; i < 4; i++) {
            char[] inputs = br.readLine().toCharArray();
            wheels[i] = new Wheel(inputs);
        }
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            String[] input = br.readLine().split(" ");
            int num = Integer.parseInt(input[0]) - 1;
            int dir = Integer.parseInt(input[1]);

            char left = wheels[num].left();
            char right = wheels[num].right();
            int originalDir = dir;
            wheels[num].move(dir);
            for (int j = num + 1; j < 4; j++) {
                if (right != wheels[j].left()) {
                    dir = -dir;
                    right = wheels[j].right();
                    wheels[j].move(dir);
                } else {
                    break;
                }
            }
            dir = originalDir;
            for (int j = num - 1; j >= 0; j--) {
                if (left != wheels[j].right()) {
                    dir = -dir;
                    left = wheels[j].left();
                    wheels[j].move(dir);
                } else {
                    break;
                }

            }
//            for (Wheel wheel : wheels) {
//                System.out.println(wheel.pointer);
//                System.out.println(Arrays.toString(wheel.values));
//            }
//            System.out.println("---");
        }
        int answer = 0;
        int idx = -1;
        for (Wheel wheel : wheels) {
            idx++;
//            System.out.println(wheel.pointer);
//            System.out.println(Arrays.toString(wheel.values));
            char value = wheel.values[wheel.pointer];
            if (value == '1') { // S극이면
                answer += (int) Math.pow(2, idx);
            }
        }
        System.out.println(answer);
    }

    static class Wheel {

        char[] values;
        int pointer = 0; // pointer가 가리키는 방향이 가장 위쪽이다

        public Wheel(char[] values) {
            this.values = values;
        }

        public void move(int dir) {
            pointer = (pointer + 8 - dir) % 8;
        }

        public char left() {
            return values[(pointer + 6) % 8];
        }

        public char right() {
            return values[(pointer + 2) % 8];
        }
    }

}