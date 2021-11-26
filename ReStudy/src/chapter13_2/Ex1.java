package chapter13_2;

public class Ex1 {
  public static void main(String[] args) {
    Calc calc = new Calc() {
      public int sum(int num1, int num2) {
        return num1 + num2;
      }
    };

    int result = calc.sum(20, 30);
    System.out.println(result);

    Calc calc2 =(x,y) -> x + y;
    System.out.println(calc2.sum(50, 60));
  }
}
