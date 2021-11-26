package chapter12_3;

public class TreeMapEx {
  public static void main(String[] args) {
    BookTreeMap books = new BookTreeMap();
    books.add(new Book("제목3","저자3",30000));
    books.add(new Book("제목2","저자2",20000));
    books.add(new Book("제목1","저자1",10000));
    books.add(new Book("제목4","저자4",40000));
  }
}
