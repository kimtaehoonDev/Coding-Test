package org.example.programmers.pccp.round2;

import java.util.LinkedList;
import java.util.Queue;

// 다시 풀이 후 성공 / 구현 / 22분
// 뭐가 문제였을까.. 아무래도 큐 하나 안쓰고 괜히 머리쓴다고 설쳤다가 틀린 거 같다
// 쉬운?? 문제인데 틀린게 아쉽다.
class Num3 {

    public int solution(int[] menu, int[] order, int k) {
        Queue<Integer> completes = new LinkedList<>(); // 손님이 만들어달라 요청한 음료 완성 시간들

        Queue<Customer> customers = new LinkedList<>(); // 손님들이 들어오는 초
        for (int i = 0; i < order.length; i++) {
            customers.add(new Customer(k * i, order[i]));
        }
        Queue<Customer> waitings = new LinkedList<>();

        // System.out.println(customers);
        int time = -1;
        int barister = 0;
        int peopleCnt = 0;
        int answer = 0;
        while (true) {
            time++;

            // 나갈 손님 나간다
            if (!completes.isEmpty() && completes.peek() == time) {
                peopleCnt--;
                completes.poll();
            }

            // 들어올 손님 들어온다
            if (!customers.isEmpty() && customers.peek().time == time) {
                peopleCnt++;
                // 주문한다.
                Customer entered = customers.poll();
                waitings.add(entered);
            }

            // 음료 만들 수 있으면 음료 만든다.
            if (!waitings.isEmpty() && barister <= time) {
                Customer customer = waitings.poll();
                int makingTime = menu[customer.productNum];
                barister = time + makingTime;
                completes.add(barister);
            }

            // System.out.println(time + "초 사람숫자-> " + peopleCnt);
            answer = Math.max(peopleCnt, answer);

            // 들어올 손님 없고, 나갈 손님 없으면 끝
            if (customers.isEmpty() && completes.isEmpty()) {
                break;
            }
        }

        return answer;
    }

    static class Customer {

        int time;
        int productNum;

        public Customer(int time, int productNum) {
            this.time = time;
            this.productNum = productNum;
        }
    }

}