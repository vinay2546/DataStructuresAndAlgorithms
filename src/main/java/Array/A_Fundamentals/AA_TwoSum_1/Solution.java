package Array.A_Fundamentals.AA_TwoSum_1;

import java.util.HashMap;

class Solution {
    HashMap<Integer,Integer> seen = new HashMap<>();
    public int[] twoSum(int[] nums, int target) {
        for( int i =0; i < nums.length; i++){
            int tar = target - nums[i];
            if (seen.containsKey(tar)){
                return new int[]{seen.get(tar), i};
            } else {
                seen.put(nums[i], i);
            }
        }
        return new int[]{};
    }
}
