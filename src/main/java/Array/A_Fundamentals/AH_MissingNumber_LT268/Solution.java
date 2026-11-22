package Array.A_Fundamentals.AH_MissingNumber_268;

class Solution {
    public int missingNumber(int[] nums) {

        int n = nums.length;

        int rangeTotal = n*(n+1)/2;

        for(int x: nums) {
            rangeTotal -= x;
        }

        return rangeTotal;
    }
}
