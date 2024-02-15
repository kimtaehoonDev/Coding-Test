package org.example.baekjoon.p20056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 2시간 3분 / 성공
// 전부 다 맞게 생각했는데 continue 하나 빠트려서 1시간을 넘게 디버깅함
// 구현 문제는 역시 코드짜면서 검증하는게 맞다는 생각이 든다.
public class Main {


    static int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

    static int N; // 지도 가로세로 길이
    static int M;// 파이어볼 개수
    static int K;// 명령 횟수
    static Graph graph;
    // m : 질량, s: 속력, D:방향

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new Graph();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph.addFireball(x, y, m, s, d);
        }
//        System.out.println(graph);
//        System.out.println("초기상태끝");
        for (int i = 0; i < K; i++) {
            graph.moveFireballs();
        }
//        System.out.println(graph);

        System.out.println(graph.calculateM());

    }


    static class Graph {

        List<int[]>[][] graph; // [질량,속력,방향]

        public Graph() {
            graph = new List[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = new ArrayList<>();
                }
            }
        }

        public void addFireball(int x, int y, int m, int s, int d) {
            graph[x - 1][y - 1].add(new int[]{m, s, d});
        }

        public void moveFireballs() {
            // 중간 테이블을 만든다
            List<int[]>[][] temp = new List[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    temp[i][j] = new ArrayList<>();
                }
            }
            // graph에 있는 모든 파이어볼은 이동 후 temp에 기록한다
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!isFireball(i,j)) {
                        continue; // 파이어볼 없는 공간
                    }

                    // 방향을 보고 속력만큼 이동시킨다
                    move(i, j, temp); // i,j에 위치한 k번째 파이어볼을 이동시킨다
                }
            }
            // 진짜 옮긴다
            changeFireballStatus(temp);
        }

        public void changeFireballStatus(List<int[]>[][] temp) {
            List<int[]>[][] nextGraph = new List[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    nextGraph[i][j] = new ArrayList<>();
                }
            }

            for(int i = 0; i<N; i++) {
                for(int j = 0; j<N; j++) {
                    List<int[]> fireballs = temp[i][j];
                    if (fireballs.isEmpty()) {
                        continue;
                    }
                    if (fireballs.size() == 1) {
                        nextGraph[i][j].add(fireballs.get(0));
                        continue; // 하.... 이거떄문에 한시간읋 ㄱ버렸네
                    }
                    int totalN = 0;
                    int totalS = 0;
                    boolean allOdd = true;
                    boolean allEven = true;
                    for(int[] each : fireballs) {
                        totalN += each[0];
                        totalS += each[1];
                        if (each[2] % 2 == 0) {
                            allOdd = false;
                        } else {
                            allEven = false;
                        }
                    }
                    if (totalN / 5 == 0) {
                        continue;
                    }
                    if (allOdd || allEven) {
                        nextGraph[i][j].add(new int[]{totalN / 5, totalS / fireballs.size(), 0});
                        nextGraph[i][j].add(new int[]{totalN / 5, totalS / fireballs.size(), 2});
                        nextGraph[i][j].add(new int[]{totalN / 5, totalS / fireballs.size(), 4});
                        nextGraph[i][j].add(new int[]{totalN / 5, totalS / fireballs.size(), 6});
                    } else {
                        nextGraph[i][j].add(new int[]{totalN / 5, totalS / fireballs.size(), 1});
                        nextGraph[i][j].add(new int[]{totalN / 5, totalS / fireballs.size(), 3});
                        nextGraph[i][j].add(new int[]{totalN / 5, totalS / fireballs.size(), 5});
                        nextGraph[i][j].add(new int[]{totalN / 5, totalS / fireballs.size(), 7});
                    }
                }
            }
            graph = nextGraph;
        }

        public void move(int x, int y, List<int[]>[][] temp) {
            List<int[]> fireballs = graph[x][y];
            for (int[] fireball : fireballs) {
                //            fireball[1] == 속력, fireball[2] == 방향
                int nx = (x + dx[fireball[2]] * fireball[1] + 1000 * N) % N;
                int ny = (y + dy[fireball[2]] * fireball[1] + 1000 * N) % N;

//            System.out.println(x + ", " + y + "->" + nx + ", " + ny);
                temp[nx][ny].add(fireball);
            }
        }

        public boolean isFireball(int x, int y) {
            return !graph[x][y].isEmpty();
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (List<int[]>[] lists : graph) {
                for (List<int[]> list : lists) {
                    sb.append("[");
                    for (int[] ints : list) {
                        sb.append(Arrays.toString(ints) + ", ");
                    }
                    sb.append("] ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        public int calculateM() {
            int total = 0;
            for (List<int[]>[] lists : graph) {
                for (List<int[]> list : lists) {
                    for (int[] fireball : list) {
                        total += fireball[0];
                    }
                }
            }
            return total;
        }
    }
}
