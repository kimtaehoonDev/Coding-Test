package org.example.programmers;

// 오,,, 꽤나 어려웠다
// 나는 약수를 구할 때 제곱근을 이용한다는 걸 처음 알았음.
// 맨 처음에 X라는 숫자가 있으면 X의 절반까지 확인해서 시간복잡도에 걸림

import java.util.ArrayList;
import java.util.List;

// 다시풀면 당연히풀죠
class 숫자_블록 {
    public int[] solution(long beginLong, long endLong) {
        int begin = (int) beginLong;
        int end = (int) endLong;

        int[] answer = new int[end - begin + 1];
        int idx = -1; // max 5000
        for(int i = begin; i<=end; i++) {
            idx++;
            answer[idx] = getMaxDivisionNumExceptMe(i);
        }

        if (begin == 1) {// 엣지
            answer[0] = 0;
        }
        return answer;
    }

    static int getMaxDivisionNumExceptMe(int num) {
        List<Integer> divisions = new ArrayList<>();
        for(int i=2; i<= Math.sqrt(num);i++) {
            if (num % i == 0) {
                if (num/i <= 10_000_000) {
                    return num/i;
                }
                divisions.add(i);
            }
        }
        if (divisions.size() == 0) {
            return 1;
        }
        return divisions.get(divisions.size() - 1);

    }
}