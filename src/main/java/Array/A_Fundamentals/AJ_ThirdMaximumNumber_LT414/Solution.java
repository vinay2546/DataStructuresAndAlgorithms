package Array.A_Fundamentals.AJ_ThirdMaximumNumber_LT414;

class Solution {
    public int thirdMax(int[] nums) {

        long h = Long.MIN_VALUE;
        long sh = Long.MIN_VALUE;
        long th = Long.MIN_VALUE;
        for (int x : nums) {

            if (x == h || x == sh || x == th)
                continue;

            if (x > h) {
                th = sh;
                sh = h;
                h = x;
            } else if (x > sh) {
                th = sh;
                sh = x;
            } else if (x > th) {
                th = x;
            }
        }

        if(th == Long.MIN_VALUE) {
            return (int)h;
        } else {
            return (int)th;
        }
    }
}
