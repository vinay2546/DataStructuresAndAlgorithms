package Array.B_Traversal.AI_CountItemsMatchingARule_LT1773;

import java.util.List;

class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int index = "type".equals(ruleKey) ? 0 : "color".equals(ruleKey) ? 1 : 2;

        int count = 0;
        for (List<String> item : items) {
            if (item.get(index).equals(ruleValue)) {
                count++;
            }
        }

        return count;
    }
}
