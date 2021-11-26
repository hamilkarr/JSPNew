package chapter12_3;

import java.util.*;

public class Ex3 {
	public static void main(String[] args) {
		HashMap<Integer, String> list = new HashMap<>();
		list.put(1000, "이름1");
		list.put(1001, "이름2");
		list.put(1002, "이름3");
		list.put(1003, "이름4");

    // Iterator<Integer> ir = list.keySet().iterator();
    // while (ir.hasNext()) {
    //   int key = ir.next();
    //   String value = list.get(key);
    //   System.out.printf("key = %d, value = %s%n", key, value);
    // }
    for (Map.Entry<Integer, String> map : list.entrySet()) {
      int key = map.getKey();
      String value = map.getValue();
      System.out.printf("key = %d, value = %s%n", key, value);
    }
	}
}

