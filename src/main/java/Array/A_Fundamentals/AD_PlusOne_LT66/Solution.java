package Array.A_Fundamentals.AD_PlusOne_LT66;

import java.util.Arrays;

class Solution {
    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;

        int[] result = new int[i + 2];
        int k = result.length - 1;

        int carry = 1;

        while (i >= 0 || carry > 0) {
            int sum = carry;

            if (i >= 0) {
                sum += digits[i--];
            }

            result[k--] = sum % 10; // Place current digit from right to left
            carry = sum / 10;       // Update carry for next position
        }

        // If index 0 wasn't used (no extra leading carry), trim the leading 0
        if (k == 0) {
            return Arrays.copyOfRange(result, 1, result.length);
        }
        return result;

    }
}