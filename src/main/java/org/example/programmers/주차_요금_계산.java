package org.example.programmers;

import java.util.*;

class 주차_요금_계산 {
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> car2time = calculateParkingTime(records);
        // System.out.println(car2time);
        Map<Integer, Integer> car2fee = calculateFee(car2time, fees);
        // System.out.println(car2fee);
        int[] answer = transformSortedFeeList(car2fee);
        return answer;
    }

    public int[] transformSortedFeeList(Map<Integer, Integer> car2fee) {
        List<Node> nodes = new ArrayList<>(car2fee.size());
        for(Integer carNum : car2fee.keySet()) {
            Integer fee = car2fee.get(carNum);
            nodes.add(new Node(carNum, fee));
        }
        Collections.sort(nodes, (n1, n2) -> n1.carNum - n2.carNum);
        int[] answer = new int[car2fee.size()];
        int idx = 0;
        for(Node node : nodes) {
            answer[idx++] = node.fee;
        }
        return answer;
    }

    static class Node {
        Integer carNum;
        Integer fee;

        public Node(Integer carNum, Integer fee) {
            this.carNum = carNum;
            this.fee = fee;
        }
    }

    // 차량번호 -> 비용
    // fees : [기본시간, 기본요금, 단위시간, 단위요금]
    public Map<Integer, Integer> calculateFee(Map<Integer, Integer> car2time, int[] fees) {
        int basicTime = fees[0];
        int basicCost = fees[1];
        int standardTime = fees[2];
        int standardFee = fees[3];

        Map<Integer, Integer> feesMap = new HashMap<>();
        for(Integer carNum : car2time.keySet()) {
            Integer parkingTime = car2time.get(carNum);
            if (parkingTime <= basicTime) {
                feesMap.put(carNum, basicCost);
                continue;
            }
            parkingTime -= basicTime;
            int cost = basicCost + ((parkingTime + standardTime - 1) / standardTime) * standardFee;
            feesMap.put(carNum, cost);
        }
        return feesMap;
    }

    public Map<Integer, Integer> calculateParkingTime(String[] records) {
        Map<Integer, Integer> car2time = new HashMap<>();
        Map<Integer, Integer> car2in = new HashMap<>();
        for(String record : records) {
            String[] temp = record.split(" ");
            int time = changeMinute2time(temp[0]);
            int carNum = Integer.parseInt(temp[1]);
            String type = temp[2];
            // System.out.println(time + " " + carNum + " " + type);
            if (type.equals("IN")) {
                car2in.put(carNum, time);
                continue;
            }
            if (type.equals("OUT")) {
                Integer entranceTime = car2in.get(carNum);
                car2in.remove(carNum);
                if (!car2time.containsKey(carNum)) {
                    car2time.put(carNum, 0);
                }
                Integer old = car2time.get(carNum);
                car2time.put(carNum, old + time - entranceTime);
            }

        }
        int time = changeMinute2time("23:59");
        for(Integer carNum: car2in.keySet()) {
            Integer entranceTime = car2in.get(carNum);
            if (!car2time.containsKey(carNum)) {
                car2time.put(carNum, 0);
            }
            Integer old = car2time.get(carNum);
            car2time.put(carNum, old + time - entranceTime);
        }

        return car2time;


    }

    public int changeMinute2time(String time) {
        String[] hourAndMinutes = time.split(":");
        return Integer.parseInt(hourAndMinutes[0]) * 60 + Integer.parseInt(hourAndMinutes[1]);
    }
}