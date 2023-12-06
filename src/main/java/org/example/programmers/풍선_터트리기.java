package org.example.programmers;

// 성공, 25분, 규칙 생각하면서 그림 그려보면 쉽게 풀 수 있음
class 풍선_터트리기 {
    public int solution(int[] a) {
        int minVal = 2_000_000_000;
        int minIdx = -1;
        for(int i = 0; i<a.length; i++) {
            if (minVal > a[i]) {
                minVal = a[i];
                minIdx = i;
            }
        }
        // System.out.println(minVal + " " + minIdx);
        int answer = 1;
        minVal = 2_000_000_000;
        for(int i = 0; i<minIdx; i++) {
            if (minVal > a[i]) {
                minVal = a[i];
                answer++;
                // System.out.println(a[i]+"는 정답이 될 수 있습니다");
            }
        }

        minVal = 2_000_000_000;
        for(int i = a.length - 1; i > minIdx; i--) {
            if (minVal > a[i]) {
                minVal = a[i];
                answer++;
                // System.out.println(a[i]+"는 정답이 될 수 있습니다");
            }
        }
        return answer;
    }
}