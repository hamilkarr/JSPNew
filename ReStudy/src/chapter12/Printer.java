package chapter12;

public class Printer<T extends Material> {
	private T material;

	public void setMaterial(T material) {
		this.material = material;
	}

	public T getMaterial() {
		return this.material;
	}

	public void run() {
		this.material.run();
	}
}
