package chapter13;

public class Ex1Test {
  public static void main(String[] args) {
    Outer outer = new Outer();
    Outer.Inner inner = outer.new Inner();
    inner.method();
    Outer.Inner.staticMethod();
  }
}
