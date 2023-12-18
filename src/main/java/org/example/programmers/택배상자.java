package org.example.programmers;

import java.util.*;

// 아이디어 자체가 동일함. 사람들이 올려둔 반례를 다 통과해서
// 코드를 버리고 새롭게 짜야 하는데 개인적으로 너무 아쉴움... 시간이 지나고 그냥 다시 풀기
class 택배상자 {
    public int solution(int[] order) {
        int answer = 0;

        // orders로 Queue 만듬
        Queue<Integer> inputs = new LinkedList<>();
        for(int each : order) {
            inputs.add(each);
        }

        Stack<Integer> subContainer = new Stack<>();
        int now = 1; // 지금 확인하고 있는 상자의 번호
        while(!inputs.isEmpty()) {
            Integer selected = inputs.peek();
//             System.out.println("now = " + now + ", selected = " + selected);
//             System.out.println("메인 " + inputs);
//             System.out.println("서브 " + subContainer);

            if (now <= selected) {
                // -> 아직 고른적이 없다
                // subContainer에 -> now<= x < selected 넣는다
                for(int i = now; i < selected; i++) {
                    subContainer.push(i);
                }
                // inputs에서 빼고, now를 갱신하고 answer를 더해준다
                inputs.poll();
                now = selected + 1;
                answer++;
            } else if ((now > selected) && subContainer.peek() == selected) {
                // subContainer에서 하나 빼고, answer를 더해준다. now는 그대로 유지한다
                inputs.poll();
                subContainer.pop();
                answer++;
            } else {
                break;
            }

        }
        return answer;
    }
}