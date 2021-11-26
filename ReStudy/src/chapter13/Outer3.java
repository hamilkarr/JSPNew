package chapter13;

public class Outer3 {
  public InterA method(int num3) { // 컴파일할때 public final
    return new InterA() {
      public void print() {
        System.out.println("print!");
      }

      @Override
      public int sum(int num1, int num2) {
        
        return num1 + num2 + num3;
      }
    };
    
  }
}
