public class Decorator {
	public static void main(String[] args) {
		Cola cola = new Cola();

		Beverage napoj = new Beverage();
		napoj = new ice(cola);
		napoj = new straw(napoj);

		System.out.println(napoj.dest());

	}

}

class Beverage {
	public String dest() {
		return "Nic";
	}
}

class Cola extends Beverage {
	public String dest() {
		return "cola";
	}
}

class extra extends Beverage {

}

class ice extends extra {

	Beverage b;

	public ice(Beverage be) {
		b = be;
	}

	public String dest() {
		return "lód " + b.dest();
	}
}

class straw extends extra {

	Beverage b;

	public straw(Beverage be) {
		b = be;
	}

	public String dest() {
		return "słomka " + b.dest();
	}
}