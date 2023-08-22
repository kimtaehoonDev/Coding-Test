package org.example.leetcode;

// 성공/18분
public class p122 {
    public int maxProfit(int[] prices) {
        int tomorrow = prices[prices.length - 1];
        int total = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            // 내일보다 오늘이 더 싸면 판매
            if (tomorrow > prices[i]) {
                total += (tomorrow - prices[i]);
            }
            // 날짜 갱신
            tomorrow = prices[i];
        }
        return total;
    }

}
