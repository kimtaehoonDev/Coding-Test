package org.example.baekjoon.p21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// N : 가로, 세로 (<= 20)
// friends : (N+1) * 4 배열 / friends[x] : x가 좋아하는 학생 4명
// classroom : N * N 배열 /각 학생이 앉아있는 자리

// 1회 실패 후 성공 / 조건 하나를 누락함 / 59분
// 단순 구현 문제
public class Main {

    public static final int EMPTY = 0;

    public static int[] dx = new int[] {1,0,-1,0};
    public static int[] dy = new int[] {0,1,0,-1};


    public static int N;
    public static int studentCnt;

    public static int[][] friends;
    public static int[][] classroom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        studentCnt = N * N;
        friends = new int[N*N+1][4];
        classroom = new int[N][N];
        List<Integer> inputs = new ArrayList<>();

        for(int i = 0 ; i< studentCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            inputs.add(num);
            friends[num][0] = Integer.parseInt(st.nextToken());
            friends[num][1] = Integer.parseInt(st.nextToken());
            friends[num][2] = Integer.parseInt(st.nextToken());
            friends[num][3] = Integer.parseInt(st.nextToken());
        }
        for (int input : inputs) {
            // 자리에 앉히다
            sit(input);
        }
//        for (int[] students : classroom) {
//            System.out.println(Arrays.toString(students));
//        }

        // 점수 계산
        int answer = 0;
        for(int i = 0; i<N; i++) {
            for(int j = 0 ; j < N; j++) {
                int studentIdx = classroom[i][j];
                int cnt = 0;
                for(int d = 0; d<4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                        continue; // 범위 벗어남
                    }
                    if (classroom[nx][ny] == EMPTY) {
                        continue; // 비어있을때
                    }
                    // 학생이 앉아있는 경우
                    if (isFriend(friends[studentIdx], classroom[nx][ny])) {
                        cnt++;
                    }
                }
                if (cnt == 0) {
                    continue;
                }
                answer += Math.pow(10, cnt - 1);
            }
        }
        System.out.println(answer);

    }

    public static void sit(int student) {
        int[] myFriends = friends[student];

        int max = 0;

        // 비어있는 자리를 확인한다
        Map<Spot, Integer> adjasts = new HashMap<>();
        for(int i = 0; i<N; i++) {
            for(int j = 0 ; j < N; j++) {
                if (classroom[i][j] != EMPTY) {
                    continue;
                }
                // 인접한 애들 중 좋아하는 애들의 숫자를 센다
                int cnt = 0;
                for(int d = 0; d<4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                        continue; // 범위 벗어남
                    }
                    if (classroom[nx][ny] == EMPTY) {
                        continue; // 비어있을때
                    }
                    // 학생이 앉아있는 경우
                    if (isFriend(myFriends, classroom[nx][ny])) {
                        cnt++;
                    }
                }
                adjasts.put(Spot.get(i, j), cnt);
                max = Math.max(max, cnt);
            }
        }

        // 좋아하는 학생들이 가장 많이 붙어있는 빈자리들을 찾는다.
        Set<Spot> spots = new HashSet<>();
        for(Spot spot : adjasts.keySet()) {
            if (adjasts.get(spot) == max) {
                spots.add(spot);
            }
        }

        // 각 자리에 인접한 빈자리의 개수들을 구한다.
        Map<Spot, Integer> emptyCnts = new HashMap<>();
        max = 0;
        for (Spot spot : spots) {
            int emptyCnt = 0;
            for (int d = 0; d < 4; d++) {
                int nx = spot.x + dx[d];
                int ny = spot.y + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue; // 범위 벗어남
                }
                if (classroom[nx][ny] == EMPTY) {
                    emptyCnt++;
                }
            }
            emptyCnts.put(spot, emptyCnt);
            max = Math.max(max, emptyCnt);
        }

        // 가장 인접한 빈자리가 많은 자리들을 찾는다.
        spots = new HashSet<>();
        for(Spot spot : emptyCnts.keySet()) {
            if (emptyCnts.get(spot) == max) {
                spots.add(spot);
            }
        }

        Queue<Spot> temp = new PriorityQueue<>((s1, s2) -> {
            if (s1.x != s2.x) {
                return s1.x - s2.x;
            }
            return s1.y - s2.y;
        });

        // 찾아낸 자리들 중 행 렬을 기준으로 가장 왼쪽 상단에 있는 녀석을 반환한다
        for (Spot spot : emptyCnts.keySet()) {
            int empty = emptyCnts.get(spot);
            if (empty == max) {
                temp.add(spot);
            }
        }
        // 남은값중 행과 렬이 가장 작은 애를 반환한다
        Spot target = temp.poll();
        classroom[target.x][target.y] = student;
    }

    public static boolean isFriend(int[] friends, int idx) {
        for (int friend : friends) {
            if (idx == friend) {
                return true;
            }
        }
        return false;
    }

    static class Spot {

        static Spot[][] spots = new Spot[N][N];

        static {
            for(int i = 0; i<N; i++) {
                for(int j = 0; j<N; j++) {
                    spots[i][j] = new Spot(i, j);
                }
            }
        }

        static Spot get(int i, int j) {
            // 범위 검사?
            return spots[i][j];
        }

        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Spot spot = (Spot) o;
            return x == spot.x && y == spot.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public String toString() {
            return "{" + x + ", " + y + "}";
        }
    }
}
