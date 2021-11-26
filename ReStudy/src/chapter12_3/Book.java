package chapter12_3;

public class Book implements Comparable<Book> {
	private static int serialNo;
	private int bookNo;
	private String title;
	private String author;
	private int price;
	
	public Book(String title, String author, int price) {
		bookNo = ++serialNo;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Book) {
			Book book = (Book)obj;
			if (title.equals(book.title) && author.equals(book.author)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return new String(title + author).hashCode();
	}
	
	
	
	
	public String toString() {
		return "bookNo : " + bookNo + ", title : " + title + ", author : " + author + ", price : " + price;
	}

	@Override
	public int compareTo(Book o) {
    return Integer.valueOf(bookNo).compareTo(o.bookNo) * -1;
		// return title.compareTo(o.title) * -1;
	}


  public int getBookNo() {
    return bookNo;
  }
}