package org.example.programmers.pccp.round1;

// 20분 / 1회 실패 후 성공 / 구현
// 실패 이유 : N==1일 때, 엣지 케이스를 제대로 걸러내지 못함. 실전이었다면 틀렸을 문제
public class Third {
    public String[] solution(int[][] queries) {
        String[] answer = new String[queries.length];
        int idx = 0;
        for(int[] query : queries) {
            int n = query[0];
            int p = query[1] - 1;
            if (n == 1) {
                answer[idx++] = "Rr";
                continue;
            }
            int[] digits = make4Digit(n, p);
            int temp = digits[0];
            for(int i = 1; i<digits.length; i++) {
                // temp와 digits[i] 관계 조율
                // digits[i];
                if (temp == 0 || temp == 3) { // 순종 자식은 고대로 나옴
                    continue;
                }
                temp = digits[i];
            }
            if (temp == 0) {
                answer[idx++] = "RR";
            } else if (temp == 3) {
                answer[idx++] = "rr";
            } else {
                answer[idx++] = "Rr";
            }
        }

        return answer;
    }

    // p를가지고 N자리수 만든다
    public int[] make4Digit(int n, int p) {
        int[] result = new int[n-1];
        int idx = result.length - 1;
        while(p > 0) {
            result[idx--] = p % 4;
            p/=4;
        }
        // System.out.println("입력값이 " + n + ", " + p);
        // System.out.println(Arrays.toString(result));
        // System.out.println("@@@@");
        return result;
    }
}
