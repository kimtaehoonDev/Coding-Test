package org.example.baekjoon.p14658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 실패 후 성공 (로직은 맞음. 문법을 몰랐음..!)
// List를 복사하는 subList는 얕은 복사였다는 것... 틀려서 다행이다
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken()); // 장판 크기
        int K = Integer.parseInt(st.nextToken()); // 별똥별 개수
        List<int[]> stars = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st1.nextToken());
            int y = Integer.parseInt(st1.nextToken());
            stars.add(new int[]{x, y});
        }

        int max = 0;
        // 1. 별똥별 x기준 오름차순 정렬
        stars.sort((s1, s2) -> s1[0] - s2[0]);
//        System.out.println(String.format("정렬결과 "));
//        for (int[] star : stars) {
//            System.out.println(Arrays.toString(star));
//        }
//        System.out.println("\n--");

        for (int i = 0; i < stars.size(); i++) { // i는 시작 별똥별
            int[] firstStar = stars.get(i);
            int lastX = stars.size();
            for (int j = i + 1; j < stars.size(); j++) { // j는 끝 별똥별이 어딘지 찾는거
                int[] lastStar = stars.get(j);
                if (lastStar[0] - firstStar[0] > L) {
                    lastX = j;
                    break;
                }
            }
            // i <= < lastX 사이 값을 찾아라
            List<int[]> tempStars = new ArrayList<>(stars.subList(i,lastX));
            tempStars.sort((s1, s2) -> s1[1] - s2[1]); // y 기준 오름차순 정리
            for (int firstY = 0; firstY < tempStars.size(); firstY++) {
                int[] firstYStar = tempStars.get(firstY);
                int lastY = tempStars.size();
                for (int j = firstY + 1; j < tempStars.size(); j++) {
                    int[] lastYStar = tempStars.get(j);
                    if (lastYStar[1] - firstYStar[1] > L) {
                        lastY = j;
                        break;
                    }
                }
//                (i, firstY)를 시작점으로 L*L 장막을 펼치면 x개의 별똥별을 튕겨낼 수 있다
//                System.out.println(String.format("(%d, %d)시작점 별똥별 포함된거 ", firstStar[0], firstYStar[1]));
//                for(int zz = firstY; zz < lastY; zz++) {
//                    System.out.print(Arrays.toString(tempStars.get(zz)) + ", ");
//                }
//                System.out.println("\n--");

                if (lastY - firstY > max) {
                    max = lastY - firstY;
                }
            }
        }
        System.out.println(K - max);
    }

}
