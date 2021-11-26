package chapter13_2;

import java.util.*;

interface PreInter <Integer> {
  boolean test (E el, int index);
}

public class Ex2 {
  public static void main(String[] args) {
    int[] list = {0,1,2,3,4,5,6,7,8,9,10};
    
    filter(list, (el,i)-> {
      if (el %2 == 0)
        return true;
    });
  }

  public static <E> ArrayList<E> filter(int[] list, PreInter<E> inter) {
    ArrayList<E> data = new ArrayList<>();
    for (int i = 0; i < list.length; i++) {
      if (inter.test(list[i],i)) {
        data.add(list[i]);
      }
    }
    return data;
  }
}
