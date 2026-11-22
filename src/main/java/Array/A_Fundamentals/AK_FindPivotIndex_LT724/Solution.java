package Array.A_Fundamentals.AK_FindPivotIndex_LT724;

class Solution {
    public int pivotIndex(int[] nums) {
        int total = 0;
        for (int x: nums) total += x;

        int leftsum = 0;
        int rightsum = 0;

        for(int i=0; i<nums.length; i++) {
            int n = nums[i];

            rightsum = total - leftsum;

            leftsum += n;

            if(leftsum == rightsum){
                return i;
            }
        }
        return -1;
    }
}
