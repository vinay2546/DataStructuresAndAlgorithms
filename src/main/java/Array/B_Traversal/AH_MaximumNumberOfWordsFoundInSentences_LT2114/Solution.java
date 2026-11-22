package Array.B_Traversal.AH_MaximumNumberOfWordsFoundInSentences_LT2114;

class Solution {
    public int mostWordsFound(String[] sentences) {
        int max = Integer.MIN_VALUE;
        for (int i =0; i<sentences.length; i++) {
            int words = 1;
            for (int j = 0; j < sentences[i].length(); j++) {
                if (sentences[i].charAt(j) == ' ') {
                    words += 1;
                }
            }

            if (words > max) max = words;
        }

        return max;
    }
}
