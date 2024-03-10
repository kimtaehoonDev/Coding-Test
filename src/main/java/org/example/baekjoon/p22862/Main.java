package org.example.baekjoon.p22862;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 30분 / 성공
// 투포인터라는 걸 알고 풀었더니 풀 수 있었음

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int limit = Integer.parseInt(split[1]); // 삭제 가능 최대 개수
        int[] ary = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * 초기 상태를 세팅한다. 포인터 두개는 인덱스 0을 가리킨다.
         * now : 연속한 부분 수열의 길이
         * deletedCnt : 연속한 부분 수열을 만들기 위해 삭제한 원소들
         */
        int left = 0;
        int right = -1;
        int now = 0;
        int deletedCnt = 0;

        /**
         * 1. '연속 수열을 만들기 위해 삭제된 원소'들의 개수가 K보다 작거나 같은지 확인한다.
         * 2. right 포인터를 이동시킨다.
         *  - 1. 이동할 위치가 홀수면 해당 원소를 삭제한다(연속한 수열을 만들기 위해서) -> deletedCnt++
         *  - 2. 이동할 위치가 짝수면 연속 수열의 길이를 갱신한다 -> now++
         * 1-1. 1번 단계에서 삭제한 원소들이 limit를 넘겨버렸다면 left 값을 조정해야 한다.
         *      (연속 수열을 만들기 위해 삭제한 원소들이 K보다 작거나 같아질 때까지 left가 가리키는 원소를 연속수열에서 없앤다)
         *  - 1. left 포인터가 가리키는 값이 홀수면 deletedCnt 를 1 빼준다.
         *  - 2. left 포인터가 가리키는 값이 짝수면 연속수열 길이가 1 줄어든다(now--)
         * 3. answer에는 가장 길이가 길었던 연속수열의 길이를 저장한다.
         */
        int answer = 0;
        while (right + 1 < ary.length) {
            if (limit >= deletedCnt) { // 1
                if (isOdd(ary[right + 1])) {
                    deletedCnt++;
                } else {
                    now++;
                }
                right++;
            } else { // 1-1
                if (isOdd(ary[left])) {
                    deletedCnt--;
                } else {
                    now--;
                }
                left++;
            }
            answer = Math.max(answer, now); // 3
        }

        System.out.println(answer);
    }

    private static boolean isOdd(int ary) {
        return ary % 2 == 1;
    }
}
