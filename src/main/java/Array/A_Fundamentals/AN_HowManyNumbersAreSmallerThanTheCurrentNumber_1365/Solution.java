package Array.A_Fundamentals.AN_HowManyNumbersAreSmallerThanTheCurrentNumber_1365;

class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        for(int i =0; i < nums.length; i++) {
            int valids = 0;
            for(int y: nums) {
                if(y < nums[i]) valids +=1;
            }
            result[i] = valids;
        }
        return result;
    }
}
