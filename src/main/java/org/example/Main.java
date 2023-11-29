package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int truckCnt;
    static Bridge bridge;
    static int[] trucks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        truckCnt = Integer.parseInt(st.nextToken());
        bridge = new Bridge(Integer.parseInt(st.nextToken()));
        bridge.maxWeight = Integer.parseInt(st.nextToken()); // 부하를 버틸 수 있는 최대 무게

        trucks = new int[truckCnt];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < truckCnt; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }
//        System.out.println(Arrays.toString(trucks));
        int time = 0;
        for (int truck : trucks) {
            while(true) {
                if (bridge.canMoveOut(time)) {
                    bridge.moveOut();
                }

                // 새로운 트럭을 넣어본다
                if (bridge.canLocated(truck)) {
                    bridge.put(time++, truck);
                    break;
                } else { // 트럭을 넣을 수 없으면 다음 트럭이 빠져나가기 전까지 ~~.
//                    int takenTime = bridge.calculateTimeToMoveEnd(time);
                    time ++;
                }
            }
//            System.out.println(truck + " 무게가 들어왔을 때 시간 " + time);

        }

        System.out.println(time + bridge.length);
    }

    static class Bridge {
        int length;
        int maxWeight;
        int status; // 지금 다리에 위치한 트럭들의 무게 합
        Queue<TruckInBridge> queue = new LinkedList<>();

        public Bridge(int length) {
            this.length = length;
            this.status = 0;
        }

        // 이번 time에 나갈 게 있으면 true, 없으면 false 반환
        public boolean canMoveOut(int time) {
            TruckInBridge top = queue.peek();
            if (top == null) {
                return false;
            }
            return time >= top.inputTime + length; // 1초에 들어왔고 다리 길이 2
        }

        public TruckInBridge moveOut() {
            TruckInBridge target = queue.poll();
            this.status -= target.weight;
            return target;
        }

        public boolean canLocated(int truck) {
//            System.out.println(this.status  + truck + "비교 " + maxWeight);
            return this.status + truck <= maxWeight;
        }

        public void put(int time, int truck) {
            this.status += truck;
            queue.add(new TruckInBridge(time, truck));
        }

        public int calculateTimeToMoveEnd(int time) {
            TruckInBridge top = queue.peek();
            return bridge.length - (time - top.inputTime);
        }
    }

    static class TruckInBridge {
        int inputTime;
        int weight;

        public TruckInBridge(int inputTime, int weight) {
            this.inputTime = inputTime;
            this.weight = weight;
        }
    }
}

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class Main {
//    static int truckCnt;
//    static Bridge bridge;
//    static int[] trucks;
//
//
//    public static void main(String[] args) throws IOException {
////        System.out.println("Hello world!");
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        truckCnt = Integer.parseInt(st.nextToken());
//        bridge = new Bridge(Integer.parseInt(st.nextToken()));
//        bridge.maxWeight = Integer.parseInt(st.nextToken()); // 부하를 버틸 수 있는 최대 무게
//        trucks = new int[truckCnt];
//
//        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < truckCnt; i++) {
//            trucks[i] = Integer.parseInt(st.nextToken());
//        }
//
//        int time = 0; // long?
//        for (int i = 0; i < truckCnt; i++) {
////            System.out.println("지금까지 흘러간 시간 " + time);
////            System.out.println(trucks[i] + "무게를 올리면 " + bridge.canCross(trucks[i]));
//            if (bridge.canCross(trucks[i])) {
//                bridge.move(trucks[i]);
//                time++;
//                continue;
//            }
//
//            time += bridge.popIfPossible(trucks[i]) + 1;
//            bridge.move(trucks[i]);
//        }
//
////        System.out.println("흘러간 시간 " + time);
//        time += bridge.bridges.length;
//        System.out.println(time);
//    }
//
//    static class Bridge {
//        int[] bridges;
//        int maxWeight;
//        int startIdx;
//        int total;
//
//        public Bridge(int length) {
//            this.bridges = new int[length];
//            this.startIdx = 0;
//        }
//
//        public void move(int truckWeight) {
//            total -= bridges[startIdx];
//            bridges[startIdx] = truckWeight;
//            total += truckWeight;
//
//            startIdx = (startIdx + 1) % bridges.length;
//
////            System.out.println(Arrays.toString(bridges));
////            System.out.println(truckWeight);
//        }
//
//        public boolean canCross(int truckWeight) {
//            if ((total - bridges[startIdx] + truckWeight) > maxWeight) {
//                return false;
//            }
//            return true;
//        }
//
//        public int popIfPossible(int truckWeight) {
//            int lastIdx = startIdx;
//            int takenTime = 0;
//            while(true) {
//                lastIdx = (lastIdx + bridges.length + 1) % bridges.length;
//                total -= bridges[lastIdx];
//                takenTime ++;
//                bridges[lastIdx] = 0;
//                if (total + truckWeight <= maxWeight) {
//                    this.startIdx = lastIdx;
//                    return takenTime;
//                }
//
//            }
//        }
//    }
//}