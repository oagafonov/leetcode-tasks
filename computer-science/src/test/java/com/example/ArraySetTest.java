package com.example;

import static org.junit.Assert.assertArrayEquals;

import junit.framework.TestCase;
import org.junit.Test;

public class ArraySetTest extends TestCase {

  @Test
  public void testIntersect(){
    var intersect = ArraySet.intersect(new int[]{1, 2, 3, 2, 0, 2, 3},  new int[]{5, 1, 2, 7, 3, 2});
    assertArrayEquals(intersect, new int[]{1, 2, 3, 2});
  }

}