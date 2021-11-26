package chapter13;

public class Ex3 {
  public static InterA interAA = new InterA() {
    public void print() {

    }
    public int sum(int num1, int num2) {
      return num1 + num2;
    }
  };
  public static void main(String[] args) {
    Outer3 outer = new Outer3();
    InterA interA = outer.method(30);
    interA.print();
    int result = interA.sum(10, 20);
    System.out.println(result);

    int result2 = interAA.sum(10, 20);
    System.out.println(result2);
  }
}
