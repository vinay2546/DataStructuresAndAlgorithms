package Array.A_Fundamentals.AG_MajorityElement_169;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int majorityElement(int[] nums) {
        int majority = nums.length/2;
        Map<Integer,Integer> frequency = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            frequency.put(nums[i], frequency.getOrDefault(nums[i], 0) + 1);
        }

        for( int x : frequency.keySet()){
            if (frequency.get(x) > majority) {
                return x;
            }
        }

        return -1;
    }
}
