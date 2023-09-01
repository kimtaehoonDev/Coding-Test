package org.example.programmers;

// 실패: 풀이시간 43분
public class 연속된_부분_수열의_합 {
    class Solution {
        public int[] solution(int[] sequence, int k) {
            int[] answer = {};
            int start = 0;
            int end = sequence.length; // 백만개

            // 정답을 만들 수 있는 가능성이 있는 가장 작은 원소 개수는? -> start
            while(start < end) {
                int mid = (start + end)/2;
                int temp = 0;
                for (int i= sequence.length - mid; i< sequence.length; i++) {
                    temp += sequence[i];
                }
                if (temp < k) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
                // end -1 부터 end - mid 까지 선택해 -> 그녀석들의 합 < k: mid개 뽑아서 정답이 될 수 없음
            }

            // start부터 비교하며 답을 찾아나간다
            for(int i = start; i<=sequence.length; i++) {
                // i개를 뽑을겁니다. 슬라이딩 윈도우로 합을 구해나갑니다
                int temp = 0;
                for(int j=0; j<start;j++) {
                    temp += sequence[j];
                }
                // System.out.println(start+"개 선택 -> 최소합 " + temp);
                if (temp == k) {
                    // 정답 리턴
                    return new int[] {0, start - 1};
                } else if (temp < k) {
                    // 가장 앞에 한개 빼고, 뒤에 한개 넣고
                    int idx = 0;
                    while(idx+start < sequence.length) {
                        temp = temp - sequence[idx] + sequence[idx + start];
                        idx++;
                        if (temp == k) {
                            // 정답 리턴
                            return new int[] {idx, idx + start - 1};
                        }
                    }
                } else {
                    // i개 뽑아서는 답이 나올 수 없음. 다음 i 경우를 확인
                    continue;

                }
            }
            return answer;
        }
    }
}
