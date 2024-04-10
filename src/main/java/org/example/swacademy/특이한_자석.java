package org.example.swacademy;

import java.util.*;
import java.io.*;

// 성공 / 52분 / 구현
public class 특이한_자석 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        List<Integer> answers = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            answers.add(solve(br));
        }
//      System.out.println(answers);
        StringJoiner sj = new StringJoiner("\n");
        for(int i = 0; i<answers.size(); i++) {
            sj.add("#" + (i + 1) + " " + answers.get(i));
        }
        System.out.println(sj.toString());
    }

    private static int solve(BufferedReader br) throws IOException {
        int K = Integer.parseInt(br.readLine());
        // 4개 톱니바퀴 입력
        List<Wheel> wheels = new ArrayList<>();
        for (int j = 0; j < 4; j++) {
            wheels.add(new Wheel(br.readLine().split(" ")));
        }
//      System.out.println(wheels);
        // 회전 정보 입력

        for (int j = 0; j < K; j++) {
            String[] input = br.readLine().split(" ");
            int wheelIdx = Integer.parseInt(input[0]) - 1;
            int direction = Integer.parseInt(input[1]);
            rotateAll(wheels, wheelIdx, direction);
        }

        // 결과 연산
        int answer = 0;
        for(int i = 0; i < wheels.size(); i++) {
            if (wheels.get(i).top()) {
                answer += Math.pow(2, i);
            }
        }
        return answer;
    }

    private static void rotateAll(List<Wheel> wheels, int wheelIdx, int direction) {
        // 움직일 바퀴들의 idx를 moves에, 회전 방향을 directions에 저장한다.
        List<Integer> moves = new ArrayList<>();
        List<Integer> directions = new ArrayList<>();
        findMovingWheels(wheels, wheelIdx, direction, moves, directions);

        for(int i = 0; i < moves.size(); i++) {
            int target = moves.get(i);
            wheels.get(target).rotate(directions.get(i));
        }
    }

    private static void findMovingWheels(List<Wheel> wheels, int wheelIdx, int direction, List<Integer> moves,
        List<Integer> directions) {
        moves.add(wheelIdx);
        directions.add(direction); // wheelIdx를 direction방향으로 회전

        int tempDirection = direction;
        Wheel prev = wheels.get(wheelIdx);
        for(int i = wheelIdx - 1; i>=0; i--) {
            Wheel target = wheels.get(i);
            if (prev.left() == target.right()) {
                // 회전할일이 없음
                break;
            }
            tempDirection = -tempDirection;
            moves.add(i);
            directions.add(tempDirection);
            prev = wheels.get(i);
        }

        tempDirection = direction;
        prev = wheels.get(wheelIdx);
        for(int i = wheelIdx + 1; i<wheels.size(); i++) {
            Wheel target = wheels.get(i);
            if(prev.right() == target.left()) {
                break;
            }
            tempDirection = -tempDirection;
            moves.add(i);
            directions.add(tempDirection);
            prev = wheels.get(i);
        }

//      System.out.println("움직일 바퀴들, 움직이는 방향");
//      System.out.println(moves);
//      System.out.println(directions);
//      System.out.println("---");
    }

    static class Wheel {
        boolean[] values = new boolean[8];
        int top = 0; // 가장 꼭대기 IDX를 가리킨다.

        public Wheel(String[] input) {
            for (int i = 0; i < input.length; i++) {
                if (input[i].equals("0")) {
                    values[i] = false;
                } else {
                    values[i] = true;
                }
            }
        }

        public String toString() {
            return "{ " + Arrays.toString(values)  +"}";
        }

        // 1 : 시계, -1 : 반시계
        public void rotate(int dir) {
            top = (top + 8 - dir) % 8;
        }

        public boolean right() {
            return values[(top + 2) % 8];
        }

        public boolean left() {
            return values[(top + 6) % 8];
        }

        public boolean top() {
            return values[top];
        }
    }
}