package org.example.programmers.pccp.round2;

import java.util.*;

// 실패
class Num3 {
    public int solution(int[] menu, int[] order, int k) {
        Queue<Integer> waitings = new LinkedList<>();

        int barister = 0;
        int answer = 0;
        int time = -1;
        int orderIdx = 0;
        int peopleCnt = 0;

        // 모든 손님이 주문 끝나고 나가면
        while(true) {
            time++;

            // 나갈 손님은 나간다(음료를 다 만들었다)
            if (barister <= time && barister != 0) {
                peopleCnt--;
            }

            // 새로 들어온 손님은 주문한다.
            if (time % k == 0 && (time / k) < order.length) { // 일정한 시간에 손님이 들어와서 주문을 한다.
                waitings.add(order[time / k]);
                peopleCnt++;
            }

            // 주문 밀려있으면 바리스타는 바로 음료를 제작한다.
            if (barister <= time && !waitings.isEmpty()) {
                int makingMenu = waitings.poll();
                int makingTime = menu[makingMenu];
                barister += makingTime;
            }

            // 사람 숫자를 센다
            answer = Math.max(peopleCnt, answer);
            // System.out.println(time + "초에 총 사람" + peopleCnt);
            // System.out.println("바리스타는 일하는중 몇초까지? -> " + barister);

            // 모든 음료 제작에 끝나면(바리스타 쉬고 있고, 손님은 다 받았고)
            if (barister <= time && (time / k) > order.length - 1) {
                break;
            }
        }

        return answer;
    }
}