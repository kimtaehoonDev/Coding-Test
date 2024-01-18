package org.example.programmers;

import java.util.ArrayList;
import java.util.List;

class 조이스틱 {
    public int solution(String name) {
        List<Integer> moves = new ArrayList<>(name.length());
        char[] chars = name.toCharArray();
        int distance = 0;
        for(char each: chars) {
            distance = (int) each - (int) 'A';
            if (distance > 13) {
                distance = 26 - distance;
            }
            moves.add(distance);
        }

        int answer = 0;
        for(Integer each: moves) {
            answer += each;
        }
        answer += calculateMinimumMoving(moves);
        return answer;
    }

    public int calculateMinimumMoving(List<Integer> moves) {


        int min = 1000;
        int size = moves.size();

        // 전부다 A면 return 0;
        boolean allA = true;
        for(int i=0; i<size; i++) {
            if (moves.get(i) != 0) {
                allA = false;
            }
        }
        if (allA) {
            return 0;
        }

        // -> 방향으로만 이동
        for(int i=size-1; i>0;i--) {
            if (moves.get(i) == 0) {
                min = Math.min(min,i);
                System.out.println("-> 방향으로 min" + i);
                break;
            }
        }

        // <- 방향
        for(int i=1; i<size;i++) {
            System.out.println(moves);
            System.out.println(moves.get(i));
            if (moves.get(i) == 0) {
                min = Math.min(min, size - i);
                System.out.println("<- 방향으로 min" + i);
                break;
            }
        }

        // -> 갔다가 <- 방향 움직이기
        // moves가 짝수라고 가정하자
        // moves/2 - 1, moves/2
        int rightPoint = 0; //오른쪽으로 움직여서 만난 포인터
        int leftPoint = 0;
        if (size %2 != 0) {
            for(int i = size/2; i>0; i--) {
                if (moves.get(i) != 0) {
                    rightPoint = i;
                    break;
                }
            }
            for(int i= size/2 + 1; i<size; i++) {
                if (moves.get(i) != 0) {
                    leftPoint = i;
                    break;
                }
            }
        } else {
            for(int i = size/2-1; i>0; i--) {
                if (moves.get(i) != 0) {
                    rightPoint = i;
                    break;
                }
            }
            for(int i= size/2 + 1; i<size; i++) {
                if (moves.get(i) != 0) {
                    leftPoint = i;
                    break;
                }
            }
            if (moves.get(size/2) != 0) {
                // left랑 right 중 더 가까이 있는 애로 바꿔치기
                if ((size/2 - rightPoint) < (leftPoint - size/2)) {
                    leftPoint = size/2;
                } else {
                    rightPoint = size/2;
                }
            }
        }

        // leftPoint랑 rightPoint를 가지고 min 거리를 계산한다

        int temp = Math.min(rightPoint, size - 1 - leftPoint) * 2 +
            Math.max(rightPoint, size - 1 - leftPoint);
        min = Math.min(min,temp);

        System.out.println("최소거리 : " + min);
        return min;
    }
}