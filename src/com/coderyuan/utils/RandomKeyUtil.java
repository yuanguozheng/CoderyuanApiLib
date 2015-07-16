package com.coderyuan.utils;

import java.util.Random;

public class RandomKeyUtil {

    public static String generateNumber(int len) {
        String no = "";
        int[] defaultNums = new int[10];
        for (int i = 0; i < defaultNums.length; i++) {
            defaultNums[i] = i;
        }
        Random random = new Random();
        int[] nums = new int[len];
        int canBeUsed = 10;
        for (int i = 0; i < nums.length; i++) {
            int index = random.nextInt(canBeUsed);
            nums[i] = defaultNums[index];
            swap(index, canBeUsed - 1, defaultNums);
            canBeUsed--;
        }
        if (nums.length > 0) {
            for (int i = 0; i < nums.length; i++) {
                no += nums[i];
            }
        }
        return no;
    }

    private static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
