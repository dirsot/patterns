public class Factory {
	public static void main(String[] args) {
		Store dane = new WAStore(1);
		System.out.println(dane.make());
		dane = new WAStore(2);
		System.out.println(dane.make());
		dane = new NYStore(1);
		System.out.println(dane.make());
		dane = new NYStore(2);
		System.out.println(dane.make());

	}
}

interface Store {
	public String make();
}

class WAStore implements Store {

	int a;

	public WAStore(int tmp) {
		a = tmp;
	}

	public String make() {
		switch (a) {
		case 1:
			return "JEDEN";
		default:
			return "NON";
		}
	}
}

class NYStore implements Store {

	int a;

	public NYStore(int tmp) {
		a = tmp;
	}

	public String make() {
		switch (a) {
		case 1: {
			return "jeden";
		}
		default:
			return "non";
		}
	}
}