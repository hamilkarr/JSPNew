package chapter12;

public class Ex1 {
	public static void main(String[] args) {
		Printer<Plastic> plasticPrinter = new Printer<Plastic>();
		plasticPrinter.setMaterial(new Plastic());
		
		Plastic plastic = plasticPrinter.getMaterial();
		System.out.println(plastic);
		plasticPrinter.run();
		
		System.out.println("===================================");
		
		Printer<Powder> powderPrinter = new Printer<>();
		powderPrinter.setMaterial(new Powder());
		Powder powder = powderPrinter.getMaterial();
		System.out.println(powder);
		powderPrinter.run();
		
	}
}
