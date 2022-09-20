package Subject;

import LetCodeTool.LetCode;

public class Solution implements LetCode {
    //letcode 263
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        boolean result = true;
        int[] array = new int[]{2, 3, 5};
        int i = 0;
        do {
            for (; i < array.length; i++) {
                if (n % array[i] == 0) {
                    n /= array[i];
                    break;
                }
            }
            if (i == 3 && n != 1) {
                result = false;
                break;
            }
            i = 0;
        }
        while (n != 1);
        return result;
    }

    @Override
    public void test() {
        System.out.println(isUgly(1));
    }

    @Override
    public boolean needTest() {
        return true;
    }
}