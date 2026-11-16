package com.example;

import java.util.Arrays;

public class Solution {
  public int[] sortedSquares1(int[] nums) {
    //[-4,-1,0,3,10]
    int i = 0;
    int j= nums.length - 1;
    System.out.println(Arrays.toString(nums));
    while (i <= j) {
      int a = nums[i] * nums[i];
      int b = nums[j] * nums[j];
      int c;
      if (a > b) {
        c = nums[i];
        nums[i] = nums[j];
        nums[j] = c;
      }
        j--;

      System.out.println(Arrays.toString(nums));

    }
    return nums;
  }

  public int[] sortedSquares2(int[] nums) {
    int[] result = new int[nums.length];
    int i = 0;
    int j= nums.length - 1, k = nums.length - 1;
    while (i <= j) {
      int a = nums[i] * nums[i];
      int b = nums[j] * nums[j];
      if (a > b) {
        result[k] = a;
        i++;
      }
      else {
        result[k] = b;
        j--;
      }
      k--;
    }
    return result;
  }
}
