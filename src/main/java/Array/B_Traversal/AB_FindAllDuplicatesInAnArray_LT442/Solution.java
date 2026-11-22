package Array.B_Traversal.AB_FindAllDuplicatesInAnArray_442;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            int index=Math.abs(nums[i])-1;
            if(nums[Math.abs(nums[i])-1]<0) list.add(Math.abs(nums[i]));
            else {
                nums[Math.abs(nums[i])-1]=-nums[Math.abs(nums[i])-1];
            }
        }
        return list;

    }
}
