package org.example.programmers;

// 성공 / 10분 / 슬라이딩 윈도우
public class 숫자의_표현 {
    public int solution(int n) {
        int acc = 0;
        int first = 1;
        int last = 1;
        int cnt = 0;
        while(!(last > n && first == n)) {
            if (acc <= n) {
                acc += last;
                last++;
            } else if (acc > n) {
                acc -= first;
                first++;
            }
            if(acc == n) {
                // System.out.println(String.format("%d ~ %d",first, last));
                cnt++;
            }
            // System.out.println(String.format("%d ~ %d 합 %d", first, last, acc));
        }

        return cnt;
    }
}
