
public class Main {
	public static void main(String[] args){
		WetDuck duck = new WetDuck();
		duck.showFly();
		duck.setFly(new CantFly());
		duck.showFly();
	}
}
