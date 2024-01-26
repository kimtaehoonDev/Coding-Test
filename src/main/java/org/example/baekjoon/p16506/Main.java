package org.example.baekjoon.p16506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// 성공 / 40분 / 구현
public class Main {

    static Map<String, int[]> codes = new HashMap<>();

    static {
        codes.put("ADD", new int[] {0, 0, 0, 0, 0});
        codes.put("ADDC", new int[] {0, 0, 0, 0, 1});
        codes.put("SUB", new int[] {0, 0, 0, 1, 0});
        codes.put("SUBC", new int[] {0, 0, 0, 1, 1});
        codes.put("MOV", new int[] {0, 0, 1, 0, 0});
        codes.put("MOVC", new int[] {0, 0, 1, 0, 1});
        codes.put("AND", new int[] {0, 0, 1, 1, 0});
        codes.put("ANDC", new int[] {0, 0, 1, 1, 1});
        codes.put("OR", new int[] {0, 1, 0, 0, 0});
        codes.put("ORC", new int[] {0, 1, 0, 0, 1});
        codes.put("NOT", new int[] {0, 1, 0, 1, 0});
        codes.put("MULT", new int[] {0, 1, 1, 0, 0});
        codes.put("MULTC", new int[] {0, 1, 1, 0, 1});
        codes.put("LSFTL", new int[] {0, 1, 1, 1, 0});
        codes.put("LSFTLC", new int[] {0, 1, 1, 1, 1});
        codes.put("LSFTR", new int[] {1, 0, 0, 0, 0});
        codes.put("LSFTRC", new int[] {1, 0, 0, 0, 1});
        codes.put("ASFTR", new int[] {1, 0, 0, 1, 0});
        codes.put("ASFTRC", new int[] {1, 0, 0, 1, 1});
        codes.put("RL", new int[] {1, 0, 1, 0, 0});
        codes.put("RLC", new int[] {1, 0, 1, 0, 1});
        codes.put("RR", new int[] {1, 0, 1, 1, 0});
        codes.put("RRC", new int[] {1, 0, 1, 1, 1});
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int[] result = new int[16];
            String[] input = br.readLine().split(" ");
            int[] opcode = validateOpcode(input[0]);
            for (int j = 0; j < 5; j++) {
                result[j] = opcode[j];
            }

            int[] ary1 = make2Digits(Integer.parseInt(input[1]), 3);
            for (int j = 0; j < 3; j++) {
                result[j + 6] = ary1[j];
            }
            int[] ary2 = make2Digits(Integer.parseInt(input[2]), 3);
            for (int j = 0; j < 3; j++) {
                result[j + 9] = ary2[j];
            }

            if (result[4] == 1) {
                // #C
                int[] ary3 = make2Digits(Integer.parseInt(input[3]), 4);
                for (int j = 0; j < 4; j++) {
                    result[j + 12] = ary3[j];
                }
            } else {
                // rB
                int[] ary3 = make2Digits(Integer.parseInt(input[3]), 3);
                for (int j = 0; j < 3; j++) {
                    result[j + 12] = ary3[j];
                }
            }


//            System.out.println(Arrays.toString(opcode));
//            System.out.println("0@@");
//            System.out.println(Arrays.toString(ary1));
//            System.out.println(Arrays.toString(ary2));
//            System.out.println(Arrays.toString(ary3));
//            System.out.println("===");
//            System.out.println(Arrays.toString(result));
            for (int each : result) {
                sb.append(each);
            }
            if (i != N - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    // original을 나눈다
    static int[] make2Digits(int original, int size) {
        int[] result = new int[size];
        int idx = size - 1;
        while (original > 0) {
            result[idx--] = original % 2;
            original /= 2;
        }
        return result;
    }

    // String을 다섯자리 숫자로 매핑하기
    static int[] validateOpcode(String code) {
        return codes.get(code);
    }
}
