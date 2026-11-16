package com.example;

public class StringRotator {

  private String value;
  StringRotator(String s){
    this.value = s;
  }

  String rotate(){
    StringBuilder output = new StringBuilder(value.length());
    for (int i = value.length() - 1 ; i >= 0; i--) {
      output.append(value.charAt(i));
    }
//    output.reverse()
    return output.toString();
  }

  static void rotate(byte[] value) {
    // разделяем массив посередине
    // движемся слева направо до середины
    // обмениваем элементы слева и справа
    int middle =  value.length >> 1;
    for (int left = 0; left < middle; left++) {
      int right = value.length - left - 1;
      byte temp = value[right];
      value[right] = value[left];
      value[left] = temp;
    }
  }
}
