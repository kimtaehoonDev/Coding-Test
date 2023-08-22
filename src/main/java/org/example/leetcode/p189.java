package org.example.leetcode;

// 2번 실패 후 성공/15분

// 첫 번째 실패는 예외처리를 깜빡했다.
// 문제는 수도코드에 적어두고 깜빡했다는 점이다. 이런 실수는 정말 하지 말자.

// 두 번째 실패는 % 연산을 한다는게 / 연산을 해버렸다.
public class p189 {
    public void rotate(int[] nums, int k) {
        if (nums.length < k) {
            k %= nums.length;
        }

        // 정상 케이스
        int[] stack = new int[k];
        for(int i=0; i<k; i++) {
            stack[k - 1 - i] = nums[nums.length - 1- i];
        }

        for(int i=nums.length - k - 1; i>=0; i-- ) {
            nums[i+k] = nums[i];
        }


        for(int i=0; i<stack.length;i++) {
            nums[i] = stack[i];
        }
    }
}
