package org.example.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 문제의 핵심은 9보다 큰 숫자 A B C D E F를 어떻게 처리하느냐이다.
// 나는 이 문제를 처리하기 위해 Digit라는 구조체를 만들어두고 static한 변수들을 만들어 사용함
// 그런데 List<Character> digits = List.of('1','2',....,'D', 'E', 'F') 를 만들어두고 사용할 수 있다.
class N진수_게임 {
    static Digit[] ary;

    public String solution(int n, int t, int m, int p) {
        ary = new Digit[t * m];
        int idx = 0; // ary에 접근하기 위한 idx

        int num = -1;
        boolean isEnd = false;
        while(!isEnd) {
            num++;
            List<Digit> digits = makeDigits(num, n);

            for(Digit each : digits) {
                ary[idx++] = each;
                if (idx >= ary.length) {
                    isEnd = true;
                    break;
                }
                // idx 위치에 each를 넣는다. 그러고 idx 한 칸 up한다
                // 만약 idx가 ary 범위 벗어나면 isEnd = true; break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = p - 1; i<ary.length; i += m) { // p번째 순서부터 n개씩
            if (ary[i] == null) {
                break;
            }
            sb.append(ary[i].value);
        }

        return sb.toString();
    }

    public List<Digit> makeDigits(int num, int n) {
        if (num == 0) {
            return List.of(Digit.get(0));
        }
        List<Digit> result = new ArrayList<>();
        while(num != 0) {
            result.add(Digit.get(num % n));
            num /= n;
        }

        Collections.reverse(result);
        return result;
    }

    static class Digit {
        static Map<Integer, Digit> store = new HashMap<>();

        char value;

        private Digit(int num) {
            if (num <= 9) {
                this.value = (char) (num + '0');

                return;
            }
            if (num == 10) {
                this.value = 'A';
            }
            else if (num == 11) {
                this.value = 'B';
            }
            else if (num == 12) {
                this.value = 'C';
            }
            else if (num == 13) {
                this.value = 'D';
            }
            else if (num == 14) {
                this.value = 'E';
            } else if (num == 15) {
                this.value = 'F';
            }
        }

        public static Digit get(int num) {
            if (store.containsKey(num)) {
                return store.get(num);
            }
            store.put(num, new Digit(num));
            return store.get(num);
        }
    }
}