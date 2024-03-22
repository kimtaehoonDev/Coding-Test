package org.example.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringJoiner;

// 2시간 10분 / 성공 / 구현 && BFS(이게 Lv3???)
// bfs를 쓰는 것까지는 쉬움. 그런데 퍼즐이 비어있는 공간에 들어갈 수 있는지 확인하는 과정이 어려움
// 나는 퍼즐과 비어있는 공간을 자료구조로 만듬. 그리고 내부적으로 6*6 배열을 사용함(모든 도형과 비어있는 공간은 해당 배열 내에서 표현 가능)
// 그리고 만들어진 퍼즐과 비어있는 공간을 왼쪽 상단을 기준으로 정렬함
// 1. 도형이 만들어진다.
// 2. 도형의 크기와 일치하는 빈공간을 찾는다.
// 3. 둘의 모양이 일치하면 도형을 끼워넣는다
// 3-1. 만약 일치하지 않으면 오른쪽으로 90도 회전을 시킨다. && 다시 왼쪽 상단 기준으로 정렬한다.
// 4. 총 4번 돌려보며 해당 도형을 끼울 수 있는 공간이 있는지 찾는다.
public class 퍼즐_조각_채우기 {

    public static void main(String[] args) {
        퍼즐_조각_채우기 x = new 퍼즐_조각_채우기();
        int answer1 = x.solution(
            new int[][]{
                new int[]{1, 1, 0, 0, 1, 0}, new int[]{0, 0, 1, 0, 1, 0},
                new int[]{0, 1, 1, 0, 0, 1}, new int[]{1, 1, 0, 1, 1, 1},
                new int[]{1, 0, 0, 0, 1, 0}, new int[]{0, 1, 1, 1, 0, 0}
            },
            new int[][]{
                new int[]{1, 0, 0, 1, 1, 0}, new int[]{1, 0, 1, 0, 1, 0},
                new int[]{0, 1, 1, 0, 1, 1}, new int[]{0, 0, 1, 0, 0, 0},
                new int[]{1, 1, 0, 1, 1, 0}, new int[]{0, 1, 0, 0, 0, 0}
            });
        System.out.println(answer1);

        int answer2 = x.solution(
            new int[][]{
                new int[]{0,0,0},
                new int[]{1,1,0},
                new int[]{1,1,1}
            },
            new int[][]{
                new int[]{1,1,1},
                new int[]{1,0,0},
                new int[]{0,0,0}
            });
        System.out.println(answer2);
    }

    static List<Info> places = new ArrayList<>();

    public int solution(int[][] board, int[][] table) {
        int answer = 0;

        /**
         board에서 비어있는 공간들을 찾아내어 places에 저장한다.
         **/
        int[][] visited = new int[board.length][board[0].length];
        int groupIdx = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 0) {
                    // already filled
                    continue;
                }
                if (visited[i][j] != 0) {
                    // already visit
                    continue;
                }
                Info info = bfs(board, visited, i, j, groupIdx++);
                places.add(info);
            }
        }
//        System.out.println(places);

        // table에서 도형들을 찾아낸다.
        visited = new int[table.length][table[0].length];
        groupIdx = 1;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j] == 0) {
                    // isEmpty
                    continue;
                }
                if (visited[i][j] != 0) {
                    // already visit
                    continue;
                }
                Puzzle puzzle = bfs2(table, visited, i, j, groupIdx++);
//                System.out.println("----");
//                System.out.println(puzzle);
//                System.out.println("original");
                // places를 순회한다
                // 도형의 크기가 일치하는지 확인한다
                // 일치하면 네 방향으로 돌려보며 들어갈 수 있는지 확인한다
                // 들어가는게 가능하면 answer에 info 길이만큼 추가하고 && places에서 제거한다(how?)
                boolean findAnswer =false;
                for (Info place : places) {
                    if (findAnswer) {
                        break;
                    }
                    if (place.filled) {
                        // 이미 처리된 도형
                        continue;
                    }
                    if (place.size != puzzle.size) {
                        continue;
                    }
                    // 들어갈 수 있는지 확인한다.
                    for (int dir = 0; dir < 4; dir++) {
                        rotate(puzzle);
//                        System.out.println(puzzle);
                        if (check(place, puzzle)) {
                            // 방문 처리한다.
                            place.filled = true;
                            findAnswer = true;
                            answer += place.size;
                            break;
                        }
                    }

                }
            }
        }

        return answer;
    }

    //        rotate만큼 Puzzle의 원소들을 돌린다(어차피 6*6)
    void rotate(Puzzle puzzle) {
        boolean[][] temp = new boolean[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                temp[j][5 - i] = puzzle.values[i][j];
            }
        }
        int minX = 100000000;
        int minY = 100000000;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (temp[i][j]) {
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, j);
                }
            }
        }
        boolean[][] temp2 = new boolean[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i - minX >= 0 && j - minY >= 0) {
                    temp2[i - minX][j - minY] = temp[i][j];
                }
            }
        }

//        System.out.println(puzzle.toString());
        puzzle.change(temp2);
//        System.out.println(puzzle.toString());
//        System.out.println("===");
    }

    boolean check(Info place, Puzzle puzzle) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (place.values[i][j] != puzzle.values[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    Puzzle bfs2(int[][] table, int[][] visited, int x, int y, int groupIdx) {
        // board에서 비어있는 장소들을 찾는다
        List<int[]> spots = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();

        visited[x][y] = groupIdx;
        queue.offer(new int[]{x, y});
        spots.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] removed = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = removed[0] + dx[dir];
                int ny = removed[1] + dy[dir];
                if (nx < 0 || ny < 0 || nx >= table.length || ny >= table[0].length) {
                    continue;
                }
                if (visited[nx][ny] != 0) {
                    // already visited
                    continue;
                }
                if (table[nx][ny] == 0) {
                    // empty place
                    continue;
                }
                visited[nx][ny] = groupIdx;
                queue.offer(new int[]{nx, ny});
                spots.add(new int[]{nx, ny});
            }
        }
        return new Puzzle(spots);
    }

    Info bfs(int[][] board, int[][] visited, int x, int y, int groupIdx) {
        // board에서 비어있는 장소들을 찾는다
        List<int[]> spots = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();

        visited[x][y] = groupIdx;
        queue.offer(new int[]{x, y});
        spots.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] removed = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = removed[0] + dx[dir];
                int ny = removed[1] + dy[dir];
                if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length) {
                    continue;
                }
                if (visited[nx][ny] != 0) {
                    // already visited
                    continue;
                }
                if (board[nx][ny] != 0) {
                    // already blocked
                    continue;
                }
                visited[nx][ny] = groupIdx;
                queue.offer(new int[]{nx, ny});
                spots.add(new int[]{nx, ny});
            }
        }
        return new Info(spots, groupIdx);
    }

    static class Puzzle {

        int size;
        boolean[][] values = new boolean[6][6];

        public Puzzle(List<int[]> spots) {
            this.size = spots.size();
            int minX = 100000000;
            int minY = 100000000;
            for (int[] spot : spots) {
                if (spot[0] < minX) {
                    minX = spot[0];
                }
                if (spot[1] < minY) {
                    minY = spot[1];
                }
            }
            for (int[] spot : spots) {
                values[spot[0] - minX][spot[1] - minY] = true;
            }
        }

        public void change(boolean[][] temp) {
            this.values = temp;
        }

        @Override
        public String toString() {
            StringJoiner sj = new StringJoiner("\n");
            for (boolean[] value : values) {
                sj.add(Arrays.toString(value));
            }
            return "PUZZLE{\n" +
                sj.toString() +
                "\n";
        }
    }

    static class Info {

        int size;
        int groupIdx;
        boolean[][] values = new boolean[6][6];
        boolean filled;

        public Info(List<int[]> spots, int groupIdx) {
            this.size = spots.size();
            this.groupIdx = groupIdx;
            filled = false;

            int minX = 100000000;
            int minY = 100000000;
            for (int[] spot : spots) {
                if (spot[0] < minX) {
                    minX = spot[0];
                }
                if (spot[1] < minY) {
                    minY = spot[1];
                }
            }
            for (int[] spot : spots) {
                values[spot[0] - minX][spot[1] - minY] = true;
            }
        }

        @Override
        public String toString() {
            StringJoiner sj = new StringJoiner("\n");
            for (boolean[] value : values) {
                sj.add(Arrays.toString(value));
            }
            return "Info{\n" +
                sj.toString() +
                "\n";
        }
    }
}
