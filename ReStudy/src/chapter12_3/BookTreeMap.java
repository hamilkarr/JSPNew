package chapter12_3;

import java.util.*;


// public class IntegerDesc implements Comparator<Integer>  {
//   @Override
//   public int compare(Integer o1, Integer o2) {
//     return o1.compareTo(o2) * -1;
//   }
  
// }

public class BookTreeMap {
  TreeMap<Integer, Book> list =  new TreeMap<>();
  
  public void add(Book book){ 
    list.put(book.getBookNo(), book);
  }

  public void print() {
      for (Map.Entry<Integer, Book> map : list.entrySet()) {
        System.out.printf("Key = %d, book = %s", map.getKey(),map.getValue());
      }
    }
}
