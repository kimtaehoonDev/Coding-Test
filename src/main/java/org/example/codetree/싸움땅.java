package org.example.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

// 성공 / 1시간 40분 / 구현

// N : 격자 크기(N * N)
// PLAYER_CNT(M) : 플레이어 수
// ROUND_LIMIT(K) : 라운드 수
public class 싸움땅 {

    static int EMPTY = -1;

    static int[] dx = new int[] {-1, 0, 1, 0}; // 상우하좌
    static int[] dy = new int[] {0, 1, 0, -1}; // (-1 0) (0 1) (1 0) (0 -1)

    static int N;
    static int PLAYER_CNT;
    static int ROUND_LIMIT;

    static List<Person> people = new ArrayList<>();
    static List<List<Queue<Integer>>> graph; // 각 우선순위 큐 : 총의 공격력이 높은 순서대로
    static int[][] located; // -1로 초기화. 만약 플레이어가 있다면 플레이어 번호를 입력하기(초기 위치 안겹침)

    public static void main(String[] args) throws IOException {
        input();
        int round = 0;
        while(round != ROUND_LIMIT) {
            round++;
            for(Person person : people) {
                int[] next = person.findNextLocation();
                int temp = located[next[0]][next[1]]; // 원래 next자리에 위치했던 값
                person.move(next);
//            System.out.println(person + "의 다음 위치 : " + Arrays.toString(spot));

                if (temp != EMPTY) { // 이동할 위치에 플레이어가 있다면
                    fight(person, people.get(temp));
                } else {
                    getGun(person);
                }
//                System.out.println(round + " 라운드 " + person.idx + " 사람 끝 @@@@");
//                System.out.println("사람들의 위치");
//                for(int[] row : located) {
//                    System.out.println(Arrays.toString(row));
//                }
//                System.out.println("각 사람들의 정보");
//                System.out.println(people);
//
//                System.out.println("맵에 위치한 총들의 정보");
//                System.out.println(graph);
//                System.out.println("@@@@@@");
            }

//            System.out.println(round + " 라운드 끝 @@@@");
//            System.out.println("사람들의 위치");
//            for(int[] row : located) {
//                System.out.println(Arrays.toString(row));
//            }
//            System.out.println("각 사람들의 정보");
//            System.out.println(people);
//
//            System.out.println("맵에 위치한 총들의 정보");
//            System.out.println(graph);
//            System.out.println("@@@@@@");
        }


        StringJoiner sj = new StringJoiner(" ");
        for(Person each : people) {
            sj.add(String.valueOf(each.score));
        }
        System.out.println(sj);

    }

    // person과 another는 싸운다.
    private static void fight(Person person, Person another) {
        Person winner = person.fight(another);
        Person loser = person;
        if (winner == person) {
            loser = another;
        }
        // 승자는 점수를 추가한다.
        winner.score += (winner.defaultPower + winner.gun - loser.defaultPower - loser.gun);

        // 루저는 총 내려놓는다 -> 그다음 이동한다. -> 총 있으면 획득한다.
        if (loser.gun != 0) {

            graph.get(loser.x).get(loser.y).add(loser.gun);
            loser.gun = 0;
        }
        // loser 이동.
        for(int i = 0; i<4; i++) {
            int dir = (loser.dir + i) % 4;
            int[] loserNext = loser.findLocation(dir);
            // 범위 벗어났거나 다른 사용자가 있으면
            if (loserNext[0] < 0 || loserNext[1] < 0 || loserNext[0] >= N || loserNext[1] >= N) {
                continue;
            }
            if (located[loserNext[0]][loserNext[1]] != EMPTY) {
                continue;
            }
            // loserNext로 옮긴다.
            // 총 획득
            loser.move(loserNext);
            getGun(loser);
            break;
        }

        // 승자는 가장 공격력 높은 총을 획득한다.
        getGun(winner);

        located[winner.x][winner.y] = winner.idx;
        located[loser.x][loser.y] = loser.idx;
    }

    private static void getGun(Person person) {
        Queue<Integer> guns = graph.get(person.x).get(person.y);
        if (person.gun != 0) {
            guns.offer(person.gun);
            person.gun = 0;
        }
        if (guns.isEmpty()) {
            return;
        }
        int poweredGun = guns.poll();
        person.gun = poweredGun;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        PLAYER_CNT = Integer.parseInt(st.nextToken());
        ROUND_LIMIT = Integer.parseInt(st.nextToken());
//        System.out.println(String.format("격자수 : %d, 플레이어수 : %d, 라운드 수 : %d", N, PLAYER_CNT, ROUND_LIMIT));
        graph = new ArrayList<>();
        for(int i = 0; i<N; i++) {
            List<Queue<Integer>> row = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                int gun = Integer.parseInt(st.nextToken());
                Queue<Integer> queue = new PriorityQueue<>((n1, n2) -> {
                    return - n1 + n2; // 총 공격력이 높은 게 우선순위를 가짐.
                });
                queue.offer(gun);
                row.add(queue);
            }
            graph.add(row);
        }
//        System.out.println("최초 총 배치");
//        for(List<Queue<Integer>> row : graph) {
//            System.out.println(row);
//        };
        located = new int[N][N];
        for(int[] row : located) {
            Arrays.fill(row, EMPTY);
        }
        for(int i = 0; i<PLAYER_CNT; i++) {
            st = new StringTokenizer(br.readLine());
            Person person = new Person(st, i);
            people.add(person);
            located[person.x][person.y] = i;
        }
//        System.out.println(people);
//        for(int[] row : located) {
//            System.out.println(Arrays.toString(row));
//        }
    }

    static class Person {
        int x;
        int y;
        int dir;
        int defaultPower;
        int gun;
        int score;
        int idx;

        public Person(StringTokenizer st, int idx) {
            this.x = Integer.parseInt(st.nextToken()) - 1;
            this.y = Integer.parseInt(st.nextToken()) - 1;
            this.dir = Integer.parseInt(st.nextToken());
            this.defaultPower = Integer.parseInt(st.nextToken());
            this.gun = 0; // 공격력 0 총 장착
            this.score = 0; // 초기 점수 0
            this.idx = idx;
        }

        public String toString() {
            return String.format("{(%d, %d) dir : %d,  초기힘 : %d, 들고있는 총 : %d, 점수 : %d}", x, y, dir, defaultPower, gun, score);
        }

        public int[] findNextLocation() {
            // x,y 에서 dir 방향으로 1 이동.
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            int ndir = dir;
            // 만약 out of range면 dir 뒤집어서 이동
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                nx = x - dx[dir];
                ny = y - dy[dir];
                ndir = (dir + 2) % 4;
            }
            return new int[] {nx,ny, ndir};
        }

        // 싸워서 이긴 사람을 반환한다.
        public Person fight(Person another) {
            int myPower = this.defaultPower + this.gun;
            int anotherPower = another.defaultPower + another.gun;
            if (myPower > anotherPower) {
                return this;
            } else if (myPower < anotherPower) {
                return another;
            }
            if (this.defaultPower < another.defaultPower) {
                return another;
            }
            return this;
        }

        public void move(int[] next) {
            located[x][y] = EMPTY;
            this.x = next[0];
            this.y = next[1];
            this.dir = next[2];
            located[x][y] = idx;
        }

        public int[] findLocation(int dir) {
            return new int[] {this.x + dx[dir], this.y + dy[dir], dir};
        }
    }

    // gun은 Integer 공격력만 있어도 되지 않나?
}
