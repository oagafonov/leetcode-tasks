package com.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class ArraySet {

  static int[] intersect(int[] a, int[] b){
    Map<Integer, Integer> counterB = new HashMap<>();
    for (int i: b) {
      counterB.put(i, counterB.getOrDefault(i, 0) + 1);
    }

    List<Integer> intersect = new LinkedList<>();
    for (int i: a) {
      int c = counterB.getOrDefault(i, 0);
      if (c > 0) {
        intersect.add(i);
        counterB.put(i, --c);
      }
    }

    return intersect.stream().mapToInt(Integer::intValue).toArray();
  }
}
