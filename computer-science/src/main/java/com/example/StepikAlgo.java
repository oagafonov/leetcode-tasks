package com.example;

import java.util.Scanner;

public class StepikAlgo {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    var temperature = scanner.nextInt();
    if (temperature < -25) {
      System.out.println("жутко холодно");
    } else if (temperature > -26 && temperature < 0) {
      System.out.println("холодно");
    } else if (temperature > -1 && temperature < 10) {
      System.out.println("прохладно");
    } else if (temperature > 9 && temperature < 25) {
      System.out.println("тепло");
    } else if (temperature > 24) {
      System.out.println("жара");
    }
  }
}

