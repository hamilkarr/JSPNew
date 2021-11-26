package chapter12_3;

import java.util.HashMap;
import java.util.Map;

public class BookHashMap {
  HashMap<Integer, Book> list =  new HashMap<>();
  
  public void add(Book book){ 
    list.put(book.getBookNo(), book);
  }

  public void print() {
      for (Map.Entry<Integer, Book> map : list.entrySet()) {
        System.out.printf("Key = %d, book = %s", map.getKey(),map.getValue());
      }
    }
  }

