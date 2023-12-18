package org.example.programmers;

import java.util.*;

// 충격이다....
// 알고리즘 자체는 맞았음. 그런데 틀린 이유는 Integer 객체에 대해 == 비교를 했기 때문이다.

// Integer 객체 값이 -128에서 127 사이를 벗어나면, == 비교할 때 call by reference 비교를 한다.
// 이 문제가 틀렸던 이유는 그것때문...
// 와,,, 충격적이다. 그동안 래퍼객체의 동등성 비교가 어떻게 일어나는지 왜 관심을 갖지 않았을까
// 그동안 기계적으로 Objects.equals를 사용했던 걸까. 이제와서야 이 문제를 마주쳤다니 굉장히 충격적이고 자바 공부를 다시 해야겠다 생각이 든다.....
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
            } else if ((now > selected) && Objects.equals(subContainer.peek(), selected)) {
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