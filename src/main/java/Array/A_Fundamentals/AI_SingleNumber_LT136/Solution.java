package Array.A_Fundamentals.AI_SingleNumber_136;

class Solution {
    public int singleNumber(int[] nums) {
        int val = nums[0];
        for(int i = 1; i < nums.length; i++){
            val ^= nums[i];
        }

        return val;
    }
}
