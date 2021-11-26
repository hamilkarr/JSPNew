package chapter13;

public class Outer {
  public int outerNumA = 10;
  public static int outerNumB = 20;

  class Inner {
    public int numA = 10;
    public static int numB = 20; // 15 버전 이하는 사용 불가

    public void method() {
      System.out.println(outerNumA);
      System.out.println(outerNumB);
    }

    public static void staticMethod() {
      System.out.println("static method");
    }
  }
}
