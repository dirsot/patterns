public class Command {
	public static void main(String[] a) {
		Light lig = new Light();
		LightOn on = new LightOn(lig);
		LightOff off = new LightOff(lig);
		Remote rem = new Remote(on, off);

		System.out.println(rem.toString());
		rem.offPushed();
		rem.onPushed();

	}
}

class Remote {
	private Commands onCommand;
	private Commands offCommand;

	public Remote(Commands com, Commands com2) {
		onCommand = com;
		offCommand = com2;
	}

	public void onPushed() {
		onCommand.execute();
	}

	public void offPushed() {
		offCommand.execute();
	}

	public String toString() {
		return "---Pilot---\n" + onCommand.getClass().getName() + "  "
				+ offCommand.getClass().getName();
	}
}

class Light {

	public void on() {
		System.out.println("Żarówka on");
	}

	public void off() {
		System.out.println("Żarówka off");
	}
}

interface Commands {
	public void execute();
}

class LightOn implements Commands {
	Light light;

	public LightOn(Light lig) {
		light = lig;
	}

	public void execute() {
		light.on();
	}
}

class LightOff implements Commands {
	Light light;

	public LightOff(Light lig) {
		light = lig;
	}

	public void execute() {
		light.off();
	}
}
