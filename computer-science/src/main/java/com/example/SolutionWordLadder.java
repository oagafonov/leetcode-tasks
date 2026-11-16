package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolutionWordLadder {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    // храним граф в виде вектора смежности: слов - вектор смежных слов
    HashMap<String, List<String>> matrix = new HashMap<>(wordList.size());
    // формируем граф из словаря
    for (String word: wordList) {
      matrix.put(word, findAdjacentWords(word, wordList));
    }
    // добавляем в граф начальное и конечное слова
    matrix.put(beginWord, findAdjacentWords(beginWord, wordList));
    matrix.put(endWord, findAdjacentWords(endWord, wordList));


    Set<String> path = new HashSet<>();
    path.add(beginWord);
    findPath(path, beginWord, endWord, matrix);

    System.out.printf("%s\n", path);

    printMatrix(matrix);

    return 0;
  }
  /*
  позволяет найти в
   */
  private Set<String> findPath(Set<String> path, String beginWord, String endWord, HashMap<String, List<String>> matrix) {
    List<String> adjacentWords = matrix.get(beginWord);
    if (adjacentWords.contains(endWord)) {
      return new HashSet<>(Set.of(endWord));
    }

    Set<String> maxPath = null;

    for (String word: adjacentWords) {
      if (path.contains(word)) {
        continue;
      }

      path.add(word);

      var p = findPath(path, word, endWord, matrix);
      if (maxPath == null || maxPath.size() > p.size()) {
        maxPath = p;
      }

      path.remove(word);
    }

    return maxPath;
  }

  private void printMatrix(HashMap<String, List<String>> matrix) {
    matrix.forEach((k, v)->{
      System.out.printf("%s: %s\n", k, Arrays.toString(v.toArray()));
    });
  }

  private List<String> findAdjacentWords(String word, List<String> words) {
    return words.stream().filter(w->{
      if (w.equals(word)){
        return false;
      }
      int count = 0;
      for (int i=0; i < word.length(); i++) {
        var c = word.charAt(i);
        if (w.indexOf(c) == -1){
          count++;
        }
        if (count > 1) {
          return false;
        }
      }
      return true;
    }).toList();
  }
}
