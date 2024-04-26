package org.example.baekjoon.p1092;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 1회 실패 후 성공
// 아이디어는 동일. 메모리 초과 떠서 메모리 덜쓰고, 시간을 더쓰도록 만듬
// 일부러 메모리 아끼려했다가 틀려버린 문제. 시간복잡도 괜찮으면(딱1억나옴) 그냥 메모리 덜쓰면서 풀기.
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> crains = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            crains.add(Integer.parseInt(st.nextToken()));
        }
        crains.sort((n1, n2) -> {
            return -n1 + n2;
        });

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        List<Box> boxes = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            boxes.add(new Box(Integer.parseInt(st.nextToken())));
        }
        boxes.sort((n1, n2) -> {
            return -n1.value + n2.value;
        });
//        System.out.println(crains);
//        System.out.println(boxes);

        int time = 0;
        int prevBoxCnt = boxes.size();
        while (true) {
            time++;
//            System.out.println(time + " 반복");
            int idx = -1;
            for (int i = 0; i < crains.size(); i++) {
                // i번째 크레인은 물건을 집는다.
                for (int j = idx + 1; j < boxes.size(); j++) {
                    idx = j;
                    Box target = boxes.get(j);
                    if (target.picked) {
                        continue;
                    }
                    if (crains.get(i) >= target.value) {
                        // 집는다
                        target.picked = true;
                        break;
                    }
                }
            }
//            System.out.println(boxes);

            int aliveBoxCnt = 0;
            for (int i = 0; i < boxes.size(); i++) {
                if (boxes.get(i).picked) {
                    continue;
                }
                aliveBoxCnt++;
            }
            if (aliveBoxCnt == 0) {
                // 모든 박스가 picked됨
                break;
            }
            if (prevBoxCnt == aliveBoxCnt) {
                // 이번 차례에 한개도 pick을 못함
                time = -1;
                break;
            }
            prevBoxCnt = aliveBoxCnt;
        }
        System.out.println(time);
    }

    static class Box {

        int value;
        boolean picked;

        public Box(int value) {
            this.value = value;
            picked = false;
        }

        public String toString() {
            return String.format("{%d, %s}", value, picked);
        }
    }
}
