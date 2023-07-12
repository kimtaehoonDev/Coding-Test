package org.example.baekjoon.p9020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] primes = getPrimeNums();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] inputs = new int[T];
        Map<Integer, V> map = new HashMap<>();
        for (int i=0; i<T;i++) {
            inputs[i] = Integer.parseInt(br.readLine());
            map.put(inputs[i], V.EMPTY);
        }
        // primes: 소수가 들어있음
        // inputs: 입력값들이 들어있음
        // map

        for (int i = 0; i < primes.length; i++) {
            for (int j=0; j<primes.length; j++) {
                int sum = primes[i] + primes[j];
                if (map.containsKey(sum)) {
                    V old = map.get(sum);
                    if (Objects.equals(old,V.EMPTY)) {
                        map.put(sum, new V(primes[i],primes[j]));
                    } else {
                        // 비교
                        if(Math.abs(old.v1 - old.v2) > Math.abs(primes[i] - primes[j])) {
                            map.put(sum, new V(primes[i], primes[j]));
                        }
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int input: inputs) {
            V v = map.get(input);
            sb.append(v.getString()+"\n");
        }
        System.out.println(sb);

    }

    private static int[] getPrimeNums() {
        boolean[] visited = new boolean[10001];
        visited[1] = true;

        int primeCnt = 0;
        for(int i=2; i<= 10000; i++) {
            if (!visited[i]) {
                for(int j=i; j<=10000;j= j+i) {
                    if (j == i) {
                        continue;
                    }
                    visited[j] = true;
                    primeCnt += 1;
                }
            }
        }
        int[] primes = new int[primeCnt];
        int idx = 0;
        for (int i = 1; i <= 10000; i++) { // 소수 1229개
            if (!visited[i]) {
                primes[idx++] = i;
            }
        }
        return primes;
    }

    static class V {
        public static V EMPTY;
        static {
            EMPTY = new V(-1, -1);
        }

        Integer v1;
        Integer v2;

        public V(Integer v1, Integer v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        public String getString() {
            if (v1 < v2) {
                return v1 + " " + v2;
            }
            return v2 + " " + v1;
        }

    }
}
