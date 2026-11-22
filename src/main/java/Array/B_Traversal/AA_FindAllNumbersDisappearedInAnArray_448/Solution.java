package Array.B_Traversal.AA_FindAllNumbersDisappearedInAnArray_448;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] frequency = new int[nums.length + 1];
        for (int x: nums){
            frequency[x] = 1;
        }
        List<Integer> list = new ArrayList<>();
        for(int i =1; i < frequency.length; i++){
            if(frequency[i] == 0) list.add(i);
        }
        return list;
    }
}