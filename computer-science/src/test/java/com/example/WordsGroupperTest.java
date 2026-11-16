package com.example;

import junit.framework.TestCase;
import static org.junit.Assert.*;

public class WordsGroupperTest extends TestCase {

  public void testGroupWords() {
    String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
    String[][] output = {{"ate", "eat", "tea"}, {"nat", "tan"}, {"bat"}};
    System.out.println(WordsGroupper.groupWords(input));
  }
}