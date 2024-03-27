package org.example.baekjoon.p12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 실패 -> 시간초과 / 1시간 10분 고민
// 가지치기로 풀려 했으나 실패함. 분명 배웠던 내용인데 기억이 안남
public class Main {

    static int WEIGHT = 0;
    static int VALUE = 1;

    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        int[][] products = new int[N][2];
        for(int i =0; i<N; i++) {
            input = br.readLine().split(" ");
            products[i][WEIGHT] = Integer.parseInt(input[0]);
            products[i][VALUE] = Integer.parseInt(input[1]);
        }

        Arrays.sort(products, (a1, a2) -> {
            if (a1[WEIGHT] != a2[WEIGHT]) {
                return a1[WEIGHT] - a2[WEIGHT]; // 무게가 낮은 순서로 정렬
            }
            return a2[VALUE] - a1[VALUE]; // 무게가 같으면 가치가 높은게 우선순위 O
        });
//        for (int[] product : products) {
//            System.out.println(Arrays.toString(product));
//        }

        int answer = calcMax(0, 0, 0, products);
        System.out.println(answer);
    }

    static int calcMax(int depth, int weightInBackpack, int valueInBackpack, int[][] products) {
//        System.out.println(String.format("현재 깊이 : %d, 무게 : %d, 가치 : %d", depth, weightInBackpack, valueInBackpack));
        int max = valueInBackpack;
        for(int i =depth; i<N; i++) {
            if (weightInBackpack + products[i][WEIGHT] > K) {
                break;
            }
            int temp = calcMax(i + 1, weightInBackpack + products[i][WEIGHT], valueInBackpack + products[i][VALUE], products);
            max = Math.max(max, temp);
        }
        return max;
    }

}
