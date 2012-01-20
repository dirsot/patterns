import java.util.Observable;
import java.util.Observer;

public class Obs2 extends Observable {
	public int jeden = 3;

	public static void main(String[] args) {
		Obs2 paper = new Obs2();

		window okno = new window();
		okno.update(null, null);
		paper.addObserver(okno);
		paper.zmiana();

	}

	public void zmiana() {
		this.setChanged();
		this.notifyObservers();
	}

}

class window implements Observer {
	private int jeden;

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Observable) {
			Obs2 ob = (Obs2) o;
			jeden = ob.jeden;
		}
		this.display();

	}

	public void display() {
		System.out.println(jeden);
	}

}
