package org.example.swacademy;

import java.io.*;
import java.util.*;

// 1회 실패 후 성공 / 1시간 5분 -> 1시간 38분
// A와 B가 동일 BC에 걸쳤을 때 로직을 잘못 작성함.
// 원래 우선순위 큐를 사용해서 A와 B 중 가장 큰 값 -> 그 다음 값을 구하려고 했음.
// 그런데 몇 가지 케이스가 틀렸다고 뜨길래 그냥 모든 A와 B가 사용할 BC를 순회를 통해 찾아줌.
// 아래 주석 처리된 부분이 원래 로직
public class 무선충전 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        List<Integer> answers = new ArrayList<>();
        for(int i = 0; i<t; i++) {
            answers.add(solve(br));
        }
//        System.out.println(answers);
        StringJoiner sj = new StringJoiner("\n");
        int idx = 1;
        for(int each : answers) {
            sj.add("#" + idx++ + " " + each);
        }
        System.out.println(sj.toString());
    }

    static int[] aLocation = new int[]{0, 0};
    static int[] bLocation = new int[]{9, 9};

    static int[] dx = new int[] {0, -1, 0, 1, 0}; // 이동X, 상, 우, 하, 좌
    static int[] dy = new int[] {0, 0, 1, 0, -1}; // (0,0) (-1,0) (0,1) (1,0) (0,-1)

    static int TIME_LIMIT; // M: 총 이동 시간
    static int BC_CNT; // A : BC의 개수
    static int[] userA; // userA가 회전하는 걸 기록해둠
    static int[] userB;
    static List<BC> bcList = new ArrayList<>();
    static List<List<Set<BC>>> graph = new ArrayList<>(); // 각 위치에 있는 BC들은 power가 쎈 애들부터 정렬된다.

    public static int solve(BufferedReader br) throws Exception {
        int acc = 0;
        input(br);
        bcList.sort((o1, o2) -> o2.power - o1.power);

        // 각 BC를 돌면서 범위에 해당하는 곳에 값을 추가한다.
        for(int i = 0; i<10; i++) {
            for(int j = 0; j<10; j++) {
                // i,j가 BC의 구역인지 확인한다.
                for(BC each : bcList) {
                    int distance = Math.abs(i - each.x) + Math.abs(j - each.y);
                    if (each.range >= distance) {
                        graph.get(i).get(j).add(each);
                    }
                }
            }
        }
//        for(List<Queue<BC>> dd : graph) {
//            for(Queue<BC> zz : dd) {
//                System.out.print(zz + "  ");
//            }
//            System.out.println("\n");
//        } // graph 안에서 BC들이 잘 정렬되어있는지 확인한다.

        // 현 상태에서 충전한다 -> A와 B를 움직인다.
        for(int i = 0; i <= TIME_LIMIT; i++) {
            List<BC> temp = new ArrayList<>();
            boolean usedA = false;
            boolean usedB = false;
            int usedCnt = 0;
            for(BC bc : bcList) {
                Set<BC> bcListCanUsingA = graph.get(aLocation[0]).get(aLocation[1]);
                Set<BC> bcListCanUsingB = graph.get(bLocation[0]).get(bLocation[1]);
                // a 또는 b가 사용할 수 있니?
                if (bcListCanUsingA.contains(bc) && bcListCanUsingB.contains(bc)) {
                    //a, b 둘 다 사용할 수 있으면
                    temp.add(bc);
                    usedCnt++;
                } else if (!usedB && bcListCanUsingB.contains(bc)) {
                    // b만 사용할 수 있으면
                    temp.add(bc);
                    usedB = true;
                    usedCnt++;
                } else if (!usedA && bcListCanUsingA.contains(bc)) {
                    temp.add(bc);
                    usedA = true;
                    usedCnt++;
                }
                if (usedCnt == 2) {
                    break;
                }
            }
            for (BC bc : temp) {
                acc += bc.power;
            }

            if (i == TIME_LIMIT) {
                break;
            }
            // 이동
            int aDir = userA[i];
            aLocation[0] += dx[aDir];
            aLocation[1] += dy[aDir];

            int bDir = userB[i];
            bLocation[0] += dx[bDir];
            bLocation[1] += dy[bDir];
        }

        return acc;
    }

    // 입력 처리 로직
    static void input(BufferedReader br) throws Exception {
        init();

        // 10 * 10 graph에 List<BC>를 넣는다.
        for(int i = 0; i<10; i++) {
            List<Set<BC>> temp = new ArrayList<>();
            for(int j = 0; j<10; j++) {
                temp.add(new HashSet<>());
            }
            graph.add(temp);
        }
//        System.out.println(graph);

        StringTokenizer st = new StringTokenizer(br.readLine());
        TIME_LIMIT = Integer.parseInt(st.nextToken());
        BC_CNT = Integer.parseInt(st.nextToken());
        userA = new int[TIME_LIMIT];
        userB = new int[TIME_LIMIT];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<TIME_LIMIT; i++) {
            userA[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<TIME_LIMIT; i++) {
            userB[i] = Integer.parseInt(st.nextToken());
        }
//        System.out.println("A: " + Arrays.toString(userA));
//        System.out.println("B: " + Arrays.toString(userB));

        for(int i = 0; i<BC_CNT; i++) {
            //좌표x y 충전범위 처리량
            st = new StringTokenizer(br.readLine());
            bcList.add(new BC(i, st));
//            System.out.println(x + ", " + y + ", " + range + ", " + power);
        }
    }

    static void init() {
        aLocation = new int[]{0, 0};
        bLocation = new int[]{9, 9};
        bcList.clear();
        bcList = new ArrayList<>();
        graph.clear();
        graph = new ArrayList<>(); // 각 위치에 있는 BC들은 power가 쎈 애들부터 정렬된다.
    }

    static class BC {

        int idx;
        int x;
        int y;
        int range;
        int power;

        public BC(int idx, StringTokenizer st) {
            this.idx = idx;
            this.y = Integer.parseInt(st.nextToken()) - 1;
            this.x = Integer.parseInt(st.nextToken()) - 1;
            this.range = Integer.parseInt(st.nextToken());
            this.power = Integer.parseInt(st.nextToken());
        }

        public String toString() {
            return "" + power;
        }
    }
}

////for(int i = 0; i <= TIME_LIMIT; i++) {
////            // a야 충전해, b야 충전해,
////            Queue<BC> bcListUsingA = graph.get(aLocation[0]).get(aLocation[1]);
////            Queue<BC> bcListUsingB = graph.get(bLocation[0]).get(bLocation[1]);
////            // a가 충전가능한 지점
////            // b가 충전가능한 지점
////            if (bcListUsingA.isEmpty() && !bcListUsingB.isEmpty()) {
////                // B만 충전 가능
////                BC bcUsingB = bcListUsingB.peek();
////                acc += bcUsingB.power;
////            } else if (!bcListUsingA.isEmpty() && bcListUsingB.isEmpty()) {
////                // A만 충전 가능
////                BC bcUsingA = bcListUsingA.peek();
////                acc += bcUsingA.power;
////            } else if (!bcListUsingA.isEmpty() && !bcListUsingB.isEmpty()) {
////                // 둘 다 충전 가능
////                // 만약 동일하다면..?
////                BC bcUsingA = bcListUsingA.peek();
////                BC bcUsingB = bcListUsingB.peek();
////                if (bcUsingA == bcUsingB) {
////                    // 하나를 다른거 꺼내야함.
////                    bcListUsingA.poll();
////                    bcListUsingB.poll();
////                    BC nextA = bcListUsingA.peek();
////                    BC nextB = bcListUsingB.peek();
////                    if (nextA == null && nextB == null) {
////                        acc += bcUsingA.power;
////                    } else if (nextA == null) {
////                        // A는 bcUsingA, B는 nextB로 충전
////                        acc += bcUsingA.power;
////                        acc += nextB.power;
////                    } else if (nextB == null) {
////                        acc += bcUsingB.power;
////                        acc += nextA.power;
////                    } else {
////                        // 둘 중 더 큰 놈으로 충전
////                        acc += bcUsingA.power;
////                        acc += Math.max(nextA.power, nextB.power);
////                    }
////                    bcListUsingA.add(bcUsingA);
////                    bcListUsingB.add(bcUsingB);
////                } else {
////                    acc += bcUsingA.power;
////                    acc += bcUsingB.power;
////                }
////            }
////
////            if (i == TIME_LIMIT) {
////                break;
////            }
////            // 이동
////            int aDir = userA[i];
////            aLocation[0] += dx[aDir];
////            aLocation[1] += dy[aDir];
////
////            int bDir = userB[i];
////            bLocation[0] += dx[bDir];
////            bLocation[1] += dy[bDir];
////        }