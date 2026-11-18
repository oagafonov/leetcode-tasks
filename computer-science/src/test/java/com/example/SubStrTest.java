package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SubStrTest {

  /*
Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.



Example 1:

Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
Example 2:

Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.


Constraints:

1 <= haystack.length, needle.length <= 104
haystack and needle consist of only lowercase English characters.
   */

  @ParameterizedTest
  @CsvSource({
      "sadbutsad, sad, 0",
      "leetcode, leeto, -1",
      "aaa, aaaa, -1",
      "mississippi, issip, 4",
      "mississippi, issipi, -1",
      "hello, ll, 2",
      "a, a, 0"
  })
  public void subStrTest(String haystack, String needle, int result) {
    assertEquals(result, subStr(haystack, needle));
  }

  //0 s s -> 1
  //1 a a -> 2
  //2 d d -> 3

  public int subStr(String haystack, String needle) {
    if (haystack.length() < needle.length()) {
      return -1;
    }
    for (int i = 0; i < haystack.length() && ((haystack.length() - i) >= needle.length()); i++) {
      boolean found = true;
      for (int j = 0; j < needle.length(); j++) {
        if (haystack.charAt(i + j) != needle.charAt(j)) {
          found = false;
          break;
        }
      }
      if (found) {
        return i;
      }
    }
    return -1;
  }
}
