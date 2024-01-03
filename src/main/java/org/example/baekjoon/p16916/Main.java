package org.example.baekjoon.p16916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 1회 실패 후 성공
// 실패 후에 로직을 살펴보니 문자열이 1개만 입력되었을 때 이상이 생길 거 같아서 확인해봄 -> 해당 부분이 오류가 맞았음
// 난이도 브론즈 2 되어있는데 굉장히 어려웠음. 체감 골드 상위권
// 아이디어도 힘들었고 구현의 난이도도 꽤 있었다. 다른 아이디어가 있는건가?
public class Main {

    public static void main(String[] args) throws IOException {
        List<List<Integer>> alphas = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            alphas.add(new ArrayList<>());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Info> compressedS = compress(br.readLine());
        List<Info> compressedP = compress(br.readLine());

        int idx = 0;
        for (Info each : compressedS) {
            alphas.get(each.val - 'a').add(idx++);
        }
        // 검증
//        System.out.println("알파벳들");
//        for (List<Integer> alpha : alphas) {
//            System.out.println(alpha);
//        }
//        System.out.println("압축결과");
//        for (Info compressed : compressedS) {
//            System.out.println(compressed.val + ", " + compressed.cnt+ "| idx is " + compressed.idx);
//        }

        // P는 S의 부분 문자열인지 검증한다
        Info firstAboutP = compressedP.get(0);
        List<Integer> locatedList = alphas.get(firstAboutP.val - 'a'); // S의 first지점 후보군
        boolean answer = false;
        for (Integer located : locatedList) {
            Info firstAboutS = compressedS.get(located);

            if (firstAboutP.cnt > firstAboutS.cnt) {
                // P의 시작값은 S 시작값의 크기보다 작거나 같아야 한다
                continue;
            }
            if (compressedP.size() > compressedS.size() - firstAboutS.idx) {
                // 정답이 될 수 없음. P는 2개 봐야 하는데, 남아있는 S가 1개일경우
                break;
            }
            if (compressedP.size() == 1) {
                answer = true;
                break;
            }

            //다음 경우의 수들이 전부 일치하는지 확인한다
            int pointerP = firstAboutP.idx;
            int pointerS = firstAboutS.idx;
            boolean isAnswer = true;
            while(true) {
                pointerP++;
                pointerS++;
                if (pointerP >= compressedP.size() - 1) {
                    break;
                }
                Info pInfo = compressedP.get(pointerP);
                Info sInfo = compressedS.get(pointerS);

                if (pInfo.val != sInfo.val || pInfo.cnt != sInfo.cnt) {
                    // 정답이 될 수 없음
                    isAnswer = false;
                    break;
                }
            }
            if (!isAnswer) {
                continue;
            }
            // 마지막 인덱스 확인
            Info pInfo = compressedP.get(pointerP);
            Info sInfo = compressedS.get(pointerS);
            if (pInfo.val != sInfo.val || pInfo.cnt > sInfo.cnt) {
                // 마지막 원소 역시 동일한 문자여야 한다
                // P의 끝값은 S 시작값의 크기보다 작거나 같아야 한다
                continue;
            }

            answer = true;
            break;
        }
        if (answer) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    public static List<Info> compress(String str) {
        List<Info> result = new ArrayList<>();
        char prev = 'A'; // 나올 수 없는 문자
        int cnt = 0;
        int idx = 0;
        char[] charArray = str.toCharArray();
        for (char eachChar : charArray) {
            if (prev == 'A') { // 첫 문자
                prev = eachChar;
                cnt = 1;
                continue;
            }

            if (prev == eachChar) {
                cnt++;
                continue;
            }
            result.add(new Info(prev, cnt, idx++));
            prev = eachChar;
            cnt = 1;
        }
        result.add(new Info(prev, cnt, idx));
        return result;
    }

    // val이 몇 회 나왔는지 정보를 담고 있음
    static class Info {
        char val;
        int cnt;
        int idx;

        public Info(char val, int cnt, int idx) {
            this.val = val;
            this.cnt = cnt;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Info{" +
                "val=" + val +
                ", cnt=" + cnt +
                ", idx=" + idx +
                '}';
        }
    }
}
