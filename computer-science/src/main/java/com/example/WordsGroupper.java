package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsGroupper {

  public static List<List<String>> groupWords(String[] words) {
    Map<String, List<String>> map = new HashMap<>();
    for (String word: words) {
      char[] wordA = word.toCharArray();
      Arrays.sort(wordA);
      String key = String.valueOf(wordA);
      map.computeIfAbsent(key, (k)-> new ArrayList<>()).add(word);
    }

    return new ArrayList<>(map.values());
  }
}
