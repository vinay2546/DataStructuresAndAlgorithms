package Array.A_Fundamentals.AL_RichestCustomerWealth_1672;

class Solution {
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for(int[] nums: accounts) {
            int sum = 0;
            for(int bal: nums) {
                sum += bal;
            }
            if(sum > max) max = sum;
        }

        return max;
    }
}
