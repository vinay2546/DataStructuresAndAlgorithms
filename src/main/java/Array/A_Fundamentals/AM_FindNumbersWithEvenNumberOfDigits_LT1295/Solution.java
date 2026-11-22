package Array.A_Fundamentals.AM_FindNumbersWithEvenNumberOfDigits_LT1295;

class Solution {
    public int findNumbers(int[] nums) {
        int total = 0;
        for (int x: nums){
            int digits = 0;
            int divider = 1;
            while(x >= divider){
                divider = divider * 10;
                digits += 1;
            }
            if(digits % 2 == 0) total += 1;
        }
        return total;
    }
}
