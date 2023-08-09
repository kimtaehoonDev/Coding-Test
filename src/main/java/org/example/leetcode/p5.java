package org.example.leetcode;

class p5 {
    public String longestPalindrome(String s) {
        char[] strs = s.toCharArray();
        int max = 0;
        int maxStart = 0;
        int maxEnd = 1;
        Distance distance;

        for(int i=0; i<s.length(); i++) {
            distance = calculateOddAnswer(strs, i);
            if (distance.end - distance.start > max) {
                maxEnd = distance.end;
                maxStart = distance.start;
                max = maxEnd - maxStart;
            }
            if (i+1 < strs.length && strs[i] == strs[i+1]) {
                distance = calculateEvenAnswer(strs, i);
                if (distance.end - distance.start > max) {
                    maxEnd = distance.end;
                    maxStart = distance.start;
                    max = maxEnd - maxStart;
                }
            }
        }
        return s.substring(maxStart, maxEnd);
    }

    public Distance calculateOddAnswer(char[] strs, int idx) {
        int dist = 1;
        int start = idx;
        int end = idx + 1;

        while(true) {
            if (idx - dist < 0 || strs.length <= idx + dist) {
                return new Distance(start, end);
            }
            if (strs[idx - dist] == strs[idx+dist]) {
                dist++;
                start--;
                end++;
            } else {
                return new Distance(start, end);
            }
        }
    }

    public Distance calculateEvenAnswer(char[] strs, int idx) {
        int dist = 1;
        int start = idx;
        int end = idx + 2;

        while(true) {
            if (idx - dist < 0 || strs.length <= idx + dist + 1) {
                return new Distance(start, end);
            }
            if (strs[idx - dist] == strs[idx+dist+1]) {
                dist++;
                start--;
                end++;
            } else {
                return new Distance(start, end);
            }
        }
    }

    static class Distance {
        int start;
        int end;

        public Distance(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}