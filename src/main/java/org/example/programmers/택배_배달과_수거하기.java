package org.example.programmers;

import java.util.*;

class 택배_배달과_수거하기 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int deliveryTruck = 0;
        int pickupTruck = 0;
        long movingDistance = 0;
        for(int i = deliveries.length - 1; i>=0; i--) {
            // System.out.println(deliveries[i]);
            deliveryTruck += deliveries[i];
            pickupTruck += pickups[i];

            while(deliveryTruck > 0 || pickupTruck > 0) { // i위치로 배달하거나 가져올 택배가 있다면
                // System.out.println(i);
                deliveryTruck -= cap;
                pickupTruck -= cap;
                movingDistance += 2 * (i+1);
            }

        }

        return movingDistance;
    }
}