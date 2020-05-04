public class Main {
	public static void main(String[] args) {

		for (int i=0; i<3; i++) {
			(new Dog()).bark();
		}

		Dog moti = new Dog();

		moti.bark();
		moti.bark();
		moti.bark();
	}
}
