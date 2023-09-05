package org.example.programmers;

import java.util.*;

// 성공/31분
// 잘 생각했고 수도코드도 잘 써서 풀긴 함
// 수도코드를 만드는 능력이 매우 떨어져있음
class 뒤에_있는_큰_수_찾기 {
    public int[] solution(int[] numbers) {

        int[] answer = new int[numbers.length];
        int[] answerIdx = new int[numbers.length];
        Arrays.fill(answer, -1);

        for(int i=numbers.length-1; i>=0 ; i--) {
            if (i==numbers.length - 1) {
                answerIdx[i] = -1; // 여기까지 왔다는 건 뒷큰수가 없다는 거
                continue;
            }

            // 바로 뒤 값이 더 크면 -> i+1이 뒤큰수가 됨
            if (numbers[i] < numbers[i+1]) {
                answer[i] = numbers[i+1];
                answerIdx[i] = i+1;
                continue;
            }

            int tempIdx = i + 1;
            while (true) {
                if (answer[tempIdx] == -1) {
                    answer[i] = -1;
                    answerIdx[i] = -1; // 뒤큰수 없다
                    break;
                }
                if (answer[tempIdx] > numbers[i]) {
                    // answer[tempIdx]가 i의 뒤큰수가 됨
                    answer[i] = answer[tempIdx];
                    answerIdx[i] = tempIdx;
                    break;
                }

                tempIdx = answerIdx[tempIdx];
            }



        }

        return answer;
    }
}