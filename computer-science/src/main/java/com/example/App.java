package com.example;

import static sun.misc.Unsafe.getUnsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args)  {
//    var solution = new Solution();
//    int[] numbs = new int[]{-12, -9, -7,-3,2,3,10,11, 13};
//    System.out.println(Arrays.toString(solution.sortedSquares1(numbs)));
//    System.out.println(Arrays.toString(solution.sortedSquares2(numbs)));
    var s = new SolutionWordLadder();
    s.ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog"));
  }
}
