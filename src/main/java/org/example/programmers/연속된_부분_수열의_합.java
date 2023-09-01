package org.example.programmers;

// 투포인터라는 생각을 못했다
// [1,1,1,2,3,6] 이라는 값이 있다고 하자.
// 투포인터 방식으로 풀면 1,2,3이 선택된다.
// 나는 여기서 1,2,3을 선택한 뒤 어떻게 더 로직을 이어가야 할지 생각을 못해 투포인터는 아니라고 생각했다
// 그러나, 단순하게도 정답이 나오고 나서 계속 진행을 하면 된다. left를 옮기건, right를 옮기건 둘 다 옮기건 아무튼 옮겨서 계속 로직을 이어가면 된다...

public class 연속된_부분_수열의_합 {
    class Solution {
        public int[] solution(int[] sequence, int k) {
            int start = 0;
            int end = 0;
            int sum = sequence[start];
            if (sum == k) {
                return new int[] {0,0};
            }

            int[] answer = new int[2];
            answer[0] = -1000000000;
            answer[1] = 1000000000;


            while(start <= end && end < sequence.length) {
                // start와 end 사이 합을 구한다
                if (sum < k) {
                    end++;
                    if (end >= sequence.length) {
                        break;
                    }
                    sum += sequence[end];
                    continue;
                }
                if (sum > k) {
                    sum -= sequence[start];
                    start++;
                    continue;
                }
                // sum == k

                // 새 값이 더 짧으면
                if ((answer[1] - answer[0]) > (end - start)) {
                    answer[0] = start;
                    answer[1] = end;
                    if (start == end) {
                        break;
                    }
                }
                end++;
                if (end >= sequence.length) {
                    continue;
                }

                sum = sum + sequence[end] - sequence[start];
                start++;
            }
            return answer;
        }
    }
}
