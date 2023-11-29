package org.example.programmers;

import java.util.*;

// TODO 대소문자통일 외우기
// TODO 우선순위 큐에서 입력 순서 처리하는 방법?
// TODO 생성자 정렬해도 되는지

class 파일명_정렬 {
    public String[] solution(String[] files) {
        Queue<FileName> heap = new PriorityQueue<>(new Comparator<>() {
            // head 사전순 정리 -> 숫자 작은 순서 -> 입력 순서
            public int compare(FileName f1, FileName f2) {
                int headResult = f1.head.compareTo(f2.head);
                if (headResult != 0) {
                    return headResult;
                }
                if (f1.number != f2.number) {
                    return f1.number - f2.number;
                }

                return f1.inputOrder - f2.inputOrder;
            }
        });

        int idx = 0;
        for(String input: files) {
            FileName fileName = new FileName(input, idx++);
            heap.offer(fileName);
            // System.out.println("헤드 " + fileName.head);
            // System.out.println("넘버 " + fileName.number);
            // System.out.println("---");
        }

        String[] answer = new String[heap.size()];
        idx = 0;
        while(!heap.isEmpty()) {
            answer[idx++] = heap.poll().original;
        }
        return answer;
    }

    static class FileName {
        String original;
        String head;
        int number;
        int inputOrder;
        // String tail;

        public FileName(String original, int inputOrder) {
            this.original = original;
            this.inputOrder = inputOrder;

            char[] chars = original.toCharArray();
            int idx = -1;
            int start = 0;
            boolean isHead = true;
            boolean isNumber = false;
            int numberLength = 0;
            for(char each : chars) {
                idx++;
                if (isHead) {
                    if ('0' <= each && each <= '9') {
                        // start에서 < idx 까지 head로 만들기
                        // 전부 대문자로 만들기
                        StringBuilder sb = new StringBuilder();
                        for(int i = start; i<idx; i++) {
                            sb.append(Character.toUpperCase(chars[i]));
                        }
                        isHead = false;
                        isNumber = true;
                        numberLength = 1;
                        this.head = sb.toString();
                        start = idx;
                    }
                    continue;
                }

                if (isNumber) {
                    if ('0' > each || each > '9' || numberLength++ >= 5) { // out of range
                        // start부터 Idx까지 숫자로 변환하기
                        char[] temp = Arrays.copyOfRange(chars, start, idx);
                        String numStr = new String(temp);
                        this.number = Integer.parseInt(numStr);
                        isNumber = false;
                        start = idx;
                    }
                    continue;
                }
                // start부터 끝까지 Tail인데, 저장 안해도 될듯
            }

            if (isNumber) {
                char[] temp = Arrays.copyOfRange(chars, start, idx + 1);
                String numStr = new String(temp);
                this.number = Integer.parseInt(numStr);
                isNumber = false;
                start = idx;
            }

        }
    }
}