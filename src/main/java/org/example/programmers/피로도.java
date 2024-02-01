package org.example.programmers;

// 22분 / 1회 실패 후 성공
// 29번째줄 continue 써야 하는데 return을 해버림... 피로한 상태에서 풀었더니 헤롱탄듯
class 피로도 {

    static int N;
    static int R;
    static int[][] dungeons;
    static int answer = 0;

    public int solution(int k, int[][] dungeons) {
        N = dungeons.length;
        R = N;
        this.dungeons = dungeons;

        perm(0,0, k);

        return answer;
    }

    public void perm(int depth, int flag, int pirodo) {
        // System.out.println(depth +"에서 " + flag + "피로도는 " + pirodo);
        for(int i = 0; i<N; i++) {
            if ((flag & 1<<i) != 0)
                continue; // i번째 원소는 뽑은적 있다
            int[] selected = dungeons[i];
            if (pirodo < selected[0]) {
                continue;
            }
            // i번째 원소 뽑기 성공,
            answer = Math.max(depth + 1, answer);
            perm(depth + 1, flag | 1<<i, pirodo - selected[1]);
        }
    }
}
