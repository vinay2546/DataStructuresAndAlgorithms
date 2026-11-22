package Array.A_Fundamentals.AP_MedianOfTwoSortedArray_4;

import java.util.Arrays;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = nums1.length - 1;
        int j = nums2.length - 1;
        int k = nums1.length + nums2.length - 1;

        nums1 = Arrays.copyOf(nums1, nums1.length + nums2.length);

        while (j >= 0) {
            if(i >= 0 && nums1[i] > nums2[j]){
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        int count = nums1.length;

        return count % 2 !=0 ? nums1[count/2] : (double)(nums1[(count/2) - 1] +nums1[(count/2)])/2;
    }
}
