class Fruit {
	public void squeeze() {
		System.out.println("Nothing happens");
	}
}

class Lemon extends Fruit {
	public void squeeze() {
		System.out.println("Here comes lemon juice!");
	}
}

class Banana extends Fruit {
	public void squeeze() {
		System.out.println("Squoosh!");
	}
}

class Apple extends Fruit {
}

public class Fruity {

	public static void main(String args[]) {

		Fruit[] fruit = new Fruit[3];
		fruit[0] = new Lemon();
		fruit[1] = new Banana();
		fruit[2] = new Apple();
		for (int i = 0; i < fruit.length; i++) {
			fruit[i].squeeze();
		}
	}
}
