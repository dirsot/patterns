public class Duck {
	public static void main(String[] args){
		WetDuck duck = new WetDuck();
		duck.showFly();
		duck.setFly(new CantFly());
		duck.showFly();
	}
	
	FlyBehavior ability;

	public void display() {
		System.out.println("Duck");
	}
}

class WetDuck extends Duck {

	WetDuck() {
		ability = new CanFly();
	}

	public void showFly() {
		ability.fly();
	}

	public void setFly(FlyBehavior type) {
		ability = type;
	}

	@Override
	public void display() {
		System.out.println("WetDuck");
	}
}

interface FlyBehavior {
	public void fly();
}

class CanFly implements FlyBehavior {
	public void fly() {
		System.out.println("I can fly");
	}
}

class CantFly implements FlyBehavior {
	public void fly() {
		System.out.println("I cant fly");
	}
}