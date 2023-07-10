package org.example.baekjoon.p2751;

import java.util.Scanner;

// merge, 01234오름차순
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 입력을 받아 배열을 만든다
        int[] ary = new int[N];
        for (int i = 0; i < N; i++) {
            ary[i] = sc.nextInt();
        }

        mergeSort(ary, 0, N - 1);
        StringBuilder sb = new StringBuilder();
        for (int i : ary) {
            sb.append(i + "\n");
        }
        System.out.println(sb);
    }

    // 배열과 시작 인덱스, 끝 인덱스를 넣는다
    // ary를 정렬해준다
    public static void mergeSort(int[] ary, int start, int end) {
        int[] newAry = mergeSortHelper(ary, start, end);
        for (int i = 0; i < newAry.length; i++) {
            ary[i] = newAry[i];
        }
    }

    // ary를 정렬한 ary를 반환한다
    private static int[] mergeSortHelper(int[] ary, int start, int end) {
        int size = end - start + 1;
        if (start > end) {
            throw new RuntimeException("불가능");
        }
        if (start == end) {
            int[] newAry = new int[size];
            newAry[0] = Math.min(ary[start], ary[end]);
            return newAry;
        }
        if (start + 1 == end) {
            int[] newAry = new int[size];
            newAry[0] = Math.min(ary[start], ary[end]);
            newAry[1] = Math.max(ary[start], ary[end]);
            return newAry;
        }

        int middle = (start + end) / 2;
        int[] first = mergeSortHelper(ary, start, middle);
        int[] second = mergeSortHelper(ary, middle + 1, end);

        // 새로운 배열을 만들어낸다(투포인터 방식)
        int[] newAry = new int[size];
        int firstIdx = 0;
        int secondIdx = 0;
        int newAryIdx = 0;

        // newAry를 만든다
        while (newAryIdx != size) {
            // secode 배열 원소가 남아있음. 그러나 first 배열 원소를 전부 다 사용
            if (firstIdx == first.length) {
                newAry[newAryIdx] = second[secondIdx];
                newAryIdx++;
                secondIdx++;
                continue;
            }
            if (secondIdx == second.length) {
                newAry[newAryIdx] = first[firstIdx];
                newAryIdx++;
                firstIdx++;
                continue;
            }

            // first 배열의 남아있는 가장 작은 원소 vs second 배열의 남아있는 가장 작은 원소
            if (first[firstIdx] < second[secondIdx]) {
                newAry[newAryIdx] = first[firstIdx];
                firstIdx++;
            } else {
                newAry[newAryIdx] = second[secondIdx];
                secondIdx++;
            }
            newAryIdx++;
        }

        for (int i = 0; i < size; i++) {
            ary[i] = newAry[i];
        }
        return newAry;
    }
}
