package Array.A_Fundamentals.AO_ShuffleTheArray_1470;

class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[n*2];
        int k = 0;
        for(int i =0; i < n ;i++){
            result[k++] = nums[i];
            result[k++] = nums[n+i];
        }
        return result;
    }
}
