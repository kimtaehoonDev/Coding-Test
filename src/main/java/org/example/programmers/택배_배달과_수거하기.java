package org.example.programmers;

import java.util.*;

class 택배_배달과_수거하기 {
    static int cap;
    static int[] deliveries;
    static int[] pickups;

    static int deliveryPointer;
    static int pickupPointer;

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        this.cap = cap;
        this.deliveries = deliveries;
        this.pickups = pickups;

        deliveryPointer = deliveries.length - 1;
        pickupPointer = deliveries.length - 1;

        long answer = 0;

        while(true) {
            int deliveryDistance = moveDelivery();
            int pickupDistance = movePickups();
            // System.out.println(deliveryDistance + " | " + pickupDistance);
            if (deliveryDistance == 0 && pickupDistance == 0) {
                break;
            }
            answer += 2 * Math.max(pickupDistance, deliveryDistance);
        }

        return answer;
    }

    // 움직인 거리를 반환
    static int moveDelivery() {
        int truckAcc = 0; // 트럭에 쌓인 택배의 개수

        boolean isChanged = false;
        for(int i = deliveryPointer; i>=0; i--) {
            if (deliveries[i] != 0) {
                isChanged = true;
                deliveryPointer = i;
                break;
            }
        }
        if (!isChanged) {
            deliveryPointer = -1;
        }

        int startPoint = deliveryPointer;
        for(int i=deliveryPointer;i>=0;i--) {
            if (truckAcc + deliveries[i] <= cap) {
                truckAcc += deliveries[i]; // 트럭에 pointer 집의 택배를 다 싣는다
                deliveries[i] = 0;
            } else {
                int temp = cap - truckAcc; // 트럭에 가득차서 temp만큼만 짐을 실어
                truckAcc += temp;
                deliveries[i] -= temp;
                deliveryPointer = i; // i위치에 있는 집부터 다시 택배를 보낼거
                break;
            }
        }

        return startPoint + 1;
    }


    static int movePickups() {
        int truckAcc = 0; // 트럭에 쌓인 택배의 개수

        boolean isChanged = false;
        for(int i = pickupPointer; i>=0; i--) {
            if (pickups[i] != 0) {
                isChanged = true;
                pickupPointer = i;
                break;
            }
        }
        if (!isChanged) {
            pickupPointer = -1;
        }

        int startPoint = pickupPointer;
        for(int i=pickupPointer;i>=0;i--) {
            if (truckAcc + pickups[i] <= cap) {
                truckAcc += pickups[i]; // 트럭에 pointer 집의 택배를 다 싣는다
                pickups[i] = 0;
            } else {
                int temp = cap - truckAcc; // 트럭에 가득차서 temp만큼만 짐을 실어
                truckAcc += temp;
                pickups[i] -= temp;
                pickupPointer = i; // i위치에 있는 집부터 다시 택배를 보낼거
                break;
            }
        }

        return startPoint + 1;
    }
}