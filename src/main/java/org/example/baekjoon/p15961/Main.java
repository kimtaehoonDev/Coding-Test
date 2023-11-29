package org.example.baekjoon.p15961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static List<Integer> DISHES = new ArrayList<>();
    static int DISH_CNT;
    static int CHOBAB_TYPE_CNT;
    static int K;
    static int COUPON_DISH; // 쿠폰에 적혀있는 번호
    static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        DISH_CNT = Integer.parseInt(st.nextToken());
        CHOBAB_TYPE_CNT = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        COUPON_DISH = Integer.parseInt(st.nextToken());

        for (int i = 0; i < DISH_CNT; i++) {
            DISHES.add(Integer.parseInt(br.readLine()));
        }

        Accumulator acc = new Accumulator();
        ANSWER = Math.max(ANSWER, acc.calculateCnt());
        for(int i = 1; i<DISH_CNT; i++) {
            acc.move(i-1);
            ANSWER = Math.max(ANSWER, acc.calculateCnt());
        }
        System.out.println(ANSWER);
    }

    static class Accumulator {
        // 접시 번호 & 개수
        Map<Integer, Integer> hash = new HashMap<>();
        int typeCnt;

        public Accumulator() {
            this.typeCnt = 0;

            // 모든 초밥이 0개씩 있다고 초기화한다
            for (int i = 1; i <= CHOBAB_TYPE_CNT; i++) {
                hash.put(i, 0);
            }

            // 0번부터 K번까지 초밥을 먹는다
            for(int i = 0; i<K; i++) {
                Integer dishNum = DISHES.get(i);// 접시번호
                hash.put(dishNum, hash.get(dishNum) + 1);
                if (hash.get(dishNum) == 1) {
                    typeCnt++;
                }
            }
        }

        // 쿠폰이 있는 초밥 COUPON_DISH를 아직 안먹었으면 주방장이 새로 만들어서 준다.
        public int calculateCnt() {
            if (hash.get(COUPON_DISH) == 0) {
                return typeCnt + 1;
            }
            return typeCnt;
        }

        // 해당 dish를 한 개 빼고, idx + K 위치에 있는 애를 더한다
        public void move(int idx) {
            // idx를 통해 접시를 찾는다
            Integer removed = DISHES.get(idx);
            hash.put(removed, hash.get(removed) - 1);
            if (hash.get(removed) == 0) { // 기존에 있었는데 없어지면
                typeCnt--;
            }

            int addedIdx = (idx + K) % DISH_CNT;
            Integer added = DISHES.get(addedIdx);
            hash.put(added, hash.get(added) + 1);
            if (hash.get(added) == 1) {
                typeCnt++;
            }

        }
    }
}


//    static int DISH_CNT; // 접시개수
//    static List<Integer> DISHES = new ArrayList<>();
//    static int DISH_NUMS; // 초밥 가짓수. 1<= <=DISH_NUMS
//    static int K; // 연속해서 먹는 접시 개수
//    static int COUPON_DISH; // 쿠폰 번호수. 초밥 접시 번호가 적힘
//    static int ANSWER = 0;
//
//    // 무조건 할인 받는다고 전제하기
//    public static void main(String[] args) throws IOException {
//        boolean findAnswer = false;
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        DISH_CNT = Integer.parseInt(st.nextToken());
//        DISH_NUMS = Integer.parseInt(st.nextToken());
//        K = Integer.parseInt(st.nextToken());
//        COUPON_DISH = Integer.parseInt(st.nextToken());
//
//        for(int i=0; i<DISH_CNT; i++) {
//            DISHES.add(Integer.parseInt(br.readLine()));
//        }
////        System.out.println(DISHES);
//
//        Accumulator acc = new Accumulator();
//        ANSWER = acc.getCnt();
//
//        for(int i=1; i<DISH_CNT; i++) {
//            acc.move(i-1);
//            ANSWER = Math.max(ANSWER, acc.getCnt());
//        }
//
//        System.out.println(ANSWER);
//    }
//
//
//    static class Accumulator {
//        Map<Integer, Integer> hash = new HashMap<>();
//        Set<Integer> duplicates = new HashSet<>();
//
//        public Accumulator() {
//            for(int i=1; i<=DISH_NUMS; i++) {
//                hash.put(i, 0);
//            }
//            for(int i=0; i<K; i++) {
//                add(DISHES.get(i));
//            }
//        }
//
//        // dishNum을 한 개 추가한다.
//        // 만약 중복된 접시가 있으면 duplicates에 추가한다
//        public void add(int dishNum) {
//            Integer temp = hash.get(dishNum);
//            if (temp == 1) {
//                duplicates.add(dishNum);
//            }
//            hash.put(dishNum, temp + 1);
//        }
//
//        public boolean contains(int couponDish) {
//            return hash.get(couponDish) != 0;
//        }
//
//        public void move(int idx) {
//            Integer dishNum = DISHES.get(idx);
//            // dishNum을 remove하고
//            Integer cnt = hash.get(dishNum);
//            hash.put(dishNum, cnt - 1);
//            if (cnt == 2) { // 접시가 0개가 되면
//                duplicates.remove(dishNum);
//            }
//            // idx + DISH_CNT를 추가한ㄷ
//            int newIdx = (idx + K) % DISH_CNT;
//            dishNum = DISHES.get(newIdx);
//            add(dishNum);
////            System.out.println(hash);
////            System.out.println(duplicates);
////            System.out.println("---");
//        }
//
//        public int getCnt() {
//            int total = 0;
//            // 접시 총 개수 - dup들 총 개수
//
//            for (Integer each : duplicates) {
//                total += (hash.get(each) - 1);
//            }
//            int answer = K - total;
//            if (!contains(COUPON_DISH)) {
//                answer++;
//            }
//            return answer;
//        }
//    }