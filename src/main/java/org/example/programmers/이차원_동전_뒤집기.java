package org.example.programmers;

import java.util.*;

// 성공 / 60분 / 그리디
// 맞기야 맞았는데, 풀이에 확신을 가지고 풀지는 못한 문제.
// 한 30분 고민하다가 -> 이 아이디어 아니면 틀렸다고 생각하자 하고 푼 문제
// 어떻게 풀었다는 설명할 수 있는데, 왜 이게 정답이 되는지 명확하게 이야기는 못하겠다.

// 풀이 :
// beginning, target을 비교해서 값이 같은거와 다른걸 비교해서 boolean[][] graph 생성
// 목표는 graph의 원소를 모두 true로 만드는 것
// 모든 선들을 확인해보고, false가 가장 많은 선분을 뒤집는다.

// 확실하게 이야기는 못하겠지만 몇 가지 케이스를 만들어 확인해보았다.
// false가 많은걸 뒤집어 true로 만든다는 건 정답에 가까워지는거라 생각하여 문제를 풀었다.

// 뒤집기를 가로 + 세로 횟수만큼 반복했는데 답이 안나왔다면 만들 수 없다고 생각해 -1을 출력했다.

// 가로 + 세로 횟수라는 건 모든 지점을 한 번씩 뒤집었을 때 횟수이다.
// 만약 가로 + 세로 횟수보다 많이 뒤집는다는 건 뒤집은 선분을 또 뒤집었다는 이야기가 된다.
// 이미 뒤집었던 선분을 뒤집는다는 건 안뒤집는다는 거와 동일하기 때문에, 그런 케이스는 존재할 수 없다.
class 이차원_동전_뒤집기 {
        static int width;
        static int height;
        static boolean[][] graph;

        public int solution(int[][] beginning, int[][] target) {
            height = beginning.length;
            width = beginning[0].length;
            graph = new boolean[height][width];

            for(int i = 0; i<height; i++) {
                for(int j = 0; j<width; j++) {
                    graph[i][j] = beginning[i][j] == target[i][j];
                }
            }
            // 전부 동일하면 얼리리턴
            boolean isEnd = check(); // 그래프가 전부 true인지 확인한다
            if (isEnd) {
                return 0;
            }

            for(int i = 0; i<width + height; i++) {
                // false의 개수를 센다. -> 가장 false 많은거 뒤집는다
                int max = 0;
                boolean isSero = true;
                int idx = 0;
                for(int j = 0; j<width; j++) {
                    int falseCnt = 0;
                    for(int k = 0; k< height; k++) {
                        if (!graph[k][j]) {
                            falseCnt++;
                        }
                    }
                    // 큰 값 갱신
                    if (max < falseCnt) {
                        max = falseCnt;
                        isSero = true;
                        idx = j;
                    }
                }
                for(int j = 0; j < height; j++) {
                    int falseCnt = 0;
                    for(int k = 0; k< width; k++) {
                        if (!graph[j][k]) {
                            falseCnt++;
                        }
                    }
                    // 갱신
                    if (max < falseCnt) {
                        max = falseCnt;
                        isSero = false;
                        idx = j;
                    }
                }
                // System.out.println(isSero?"세로":"가로");
                // System.out.println("-> " + idx + "를 뒤집는다. false 개수" + max);
                if (isSero) {
                    // 세로 뒤집기
                    for(int j = 0; j<height; j++) {
                        graph[j][idx] = !graph[j][idx];
                    }
                } else {
                    // 가로 뒤집기
                    for(int j = 0; j<width; j++) {
                        graph[idx][j] = !graph[idx][j];
                    }
                }
                if (check()) {
                    return i +1;
                }
            }

            // 정답이 없다는 의미
            return -1;
        }

        // 전부 다 확인해보고 하나라도 False 있으면 false 반환, 모두 True면 true 반환
        static boolean check() {
            // for(int i = 0; i<height; i++) {
            //     System.out.println(Arrays.toString(graph[i]));
            // }
            // System.out.println("===");
            for(int i = 0; i<height; i++) {
                for(int j = 0; j<width; j++) {
                    if (!graph[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
