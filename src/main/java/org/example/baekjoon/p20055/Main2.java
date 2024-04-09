package org.example.baekjoon.p20055;

import java.util.*;
import java.io.*;

// 50분 / 성공 / 이문제 풀때마다 어렵다. 특정 조건이 매번 풀때마다 눈에 안보임
public class Main2 {

    // 2 * N : 벨트 크기, K : 기준치, belts : 각 위치별 내구도
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 2 * N 크기의 벨트를 만든다.
        int K = Integer.parseInt(st.nextToken()); // 기준치

        int[] belts = new int[2 * N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<2 * N; i++) {
            belts[i] = Integer.parseInt(st.nextToken());
        }
//        System.out.println(Arrays.toString(belts));

        int entrance = 0;
        int out = N - 1;

//        System.out.println(entrance + ">" + out);
        LinkedList<Integer> robots = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int level = 0;
        int broken = 0;
        while(true) {
            level++;

            // 회전
            entrance = (entrance + belts.length - 1) % (belts.length);
            out = (out + belts.length - 1) % (belts.length);
//            System.out.println(entrance + ">" + out);
            if (set.contains(out)) {
                set.remove(out);
                robots.remove((Integer) out);
            }
            // 로봇들을 이동시킨다
            LinkedList<Integer> temp = new LinkedList<>();
            while(!robots.isEmpty()) {
                int robot = robots.removeLast();
                int next = (robot + 1) % belts.length;
                if (belts[next] == 0 || set.contains(next)) { // next 벨트 내구도가 0이거나, 그 자리에 로봇이 있거나
                    temp.addFirst(robot); // 움직이지 못함.
                } else { // robot이 이동한다.
                    set.remove(robot);
                    belts[next]--;
                    if (belts[next] == 0) {
                        broken++;
                    }
                    if (next != out) {
                        temp.addFirst(next);
                        set.add(next);
                    } // else : 로봇 뛰어내림

                }
            }
            robots.clear();
            robots = temp;

            // 만약 올리는 칸의 내구도가 0이 아니라면, 로봇을 올린다.
            if (belts[entrance] != 0) {
                // 내구도 1 감소, robots 머리에 로봇 인덱스 추가
                robots.addFirst(entrance);
                set.add(entrance);
                belts[entrance]--;
                if (belts[entrance] == 0) {
                    broken++;
                }
            }

//            System.out.println(Arrays.toString(belts));
//            System.out.println("robot is " + robots);
            // 내구도가 0인게 K개 이상이면 종료한다.
            if (broken >= K) {
                break;
            }

        }
        System.out.println(level);

    }

}
