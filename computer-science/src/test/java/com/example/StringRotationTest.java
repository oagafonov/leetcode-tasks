package com.example;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringRotationTest {

  @Test
  public void test1(){
    StringRotator rotator = new StringRotator("agafonov");
    var rotatedString = rotator.rotate();
    assertEquals("vonofaga",rotatedString);
  }

  @Test
  public void test2(){
    byte[] s = "agafonov1".getBytes();
    StringRotator.rotate(s);
//    assertArrayEquals("agafonov".getBytes(),s);
    assertArrayEquals("1vonofaga".getBytes(),s);
    assertEquals("1vonofaga", new String(s));
  }
}
