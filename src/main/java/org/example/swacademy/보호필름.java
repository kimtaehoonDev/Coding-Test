package org.example.swacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

// 53분/성공/구현
public class 보호필름 {

    // D : 두께, W : 가로 길이, K : 합격 기준
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        List<Integer> answer = new ArrayList<>();
        for (int t = 0; t < T; t++) {
            String[] input = br.readLine().split(" ");
            int D = Integer.parseInt(input[0]);
            int W = Integer.parseInt(input[1]);
            int K = Integer.parseInt(input[2]);
            boolean[][] film = new boolean[D][W];
            for (int i = 0; i < D; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    film[i][j] = (x == 1);
                }
            }
            answer.add(solve(K, film));
        }
        StringJoiner sj = new StringJoiner("\n");
        for (int i = 1; i <= answer.size(); i++) {
            sj.add("#" + i + " " + answer.get(i - 1));
        }
        System.out.println(sj);
    }

    // K : 합격 기준
    // film이 K개라는 기준을 통과하려면 약품을 몇 번 주입해야 할까?
    static int solve(int K, boolean[][] film) {
//        for (boolean[] line : film) {
//            System.out.println(Arrays.toString(line));
//        }

        // 아무 약품도 주입하지 않고 통과되는지 확인한다.
        if (test(K, film)) {
            return 0;
        }

        // 약품을 i번 주입한다.
        int height = film.length;
        for (int i = 1; i < height; i++) {
            // 어떤막을 변경할건지 선택한다.
            List<int[]> cases = new ArrayList<>();
            comb(height, i, 0, 0, new int[i], cases);

            for (int[] eachCase : cases) {
                for (int bit = 0; bit < Math.pow(2, i); bit++) {
                    boolean[][] changedFilm = change(film, eachCase, bit);
                    if (test(K, changedFilm)) {
                        return i;
                    }
                }
            }
        }

        return height;
    }

    static boolean[][] change(boolean[][] film, int[] eachCase, int bit) {
        // 원래 모양 복사
        boolean[][] copy = new boolean[film.length][film[0].length];
        for (int i = 0; i < film.length; i++) {
            copy[i] = Arrays.copyOfRange(film[i], 0, film[i].length);
        }

        // eachCase[i]를 I번째 비트로 변경해라
        int idx = -1;
        for (int i : eachCase) {
            idx++;
            for (int j = 0; j < film[0].length; j++) {
                copy[i][j] = ((bit & (1 << idx)) == 0);
            }
        }
        return copy;
    }

    static void comb(int n, int k, int start, int depth, int[] store, List<int[]> cases) {
        if (k == depth) {
            cases.add(Arrays.copyOfRange(store, 0, store.length));
            return;
        }
        for (int i = start; i < n; i++) {
            store[depth] = i;
            comb(n, k, i + 1, depth + 1, store, cases);
        }
    }

    // 각 라인별로 통과하는지 검사한다. 모두 통과하면 test는 성공한다
    static boolean test(int K, boolean[][] film) {
        for (int j = 0; j < film[0].length; j++) {
            // 각 라인 연속 개수 센다
            boolean prev = film[0][j];
            int acc = 1;
            boolean isSuccess = false;
            for (int i = 1; i < film.length; i++) {
                if (prev != film[i][j]) {
                    prev = film[i][j];
                    acc = 1;
                } else {
                    acc++;
                }
                if (acc >= K) {
                    isSuccess = true;
                    break;
                }
            }
            if (!isSuccess) {
                return false;
            }
        }
        return true;
    }
}
