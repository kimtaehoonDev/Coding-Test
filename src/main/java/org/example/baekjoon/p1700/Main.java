package org.example.baekjoon.p1700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// N : 멀티탭 구멍 개수
// K : 전기용품 총 사용 횟수
public class Main {
    public static final int INF = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] orders = new int[K];
        int[] nexts = new int[K];
        // 초기값으로 초기화
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            orders[i] = Integer.parseInt(st.nextToken());
            nexts[i] = INF;
        }
        for (int i = 0; i < K; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (orders[i] == orders[j]) {
                    nexts[j] = i;
                    break;
                }
            }
        }
//        System.out.println(Arrays.toString(orders));
//        System.out.println(Arrays.toString(nexts));
        int answer = 0;
        Map<Integer, Integer> multitab = new HashMap<>();
        for (int i = 0; i < K; i++) {
            System.out.println(multitab);
            int input = orders[i];
            // 이미 꽃혀있음
            if (multitab.containsKey(input)) {
                multitab.put(input, nexts[i]);
                continue;
            }
            // 처음들어옴 && 멀티탭에 빈자리 있으면
            if (multitab.size() < N) {
                multitab.put(input, nexts[i]);
                continue;
            }
            // 처음들어옴 && 멀티탭에 자리 없으면 -> 한놈 빼야함
            answer++;
            // 멀티탭 순회하면서 가장 Value(next)가 큰 값 제거 && 새로운 애 추가
            int max = -1;
            int maxId = -1;
            for (Integer connectedId : multitab.keySet()) {
//                Integer nextAppearOrder = multitab.get(connected); //다음번에 나타날 순서
                if (max < multitab.get(connectedId)) {
                    max = multitab.get(connectedId);
                    maxId = connectedId;
                }
            }
            // maxId 멀티탭에서 빼고 새로운애 꽃아
//            System.out.println("제거되는 놈은 " + maxId);
//            System.out.println("새로운 값은 " + input + ", next == " + nexts[i]);
            multitab.remove(maxId);
            multitab.put(input, nexts[i]);
        }

//        System.out.println(multitab);
//        System.out.println("==");
        System.out.println(answer);
    }

}
