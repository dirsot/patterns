
import java.util.ArrayList;


interface Observable {
	public void addObserver(Observer1 o);

	public void notifyObservers();
}

interface Observer1 {
	public void update(int a);
}

public class Obs1 implements Observable {
	public int jeden = 3;
	ArrayList lista = new ArrayList();

	public static void main(String[] args) {
		Obs1 paper = new Obs1();

		window1 okno = new window1();
		paper.addObserver((Observer1) okno);
		paper.zmiana();
		paper.jeden=11;
		paper.zmiana();

	}

	public void zmiana() {
		this.notifyObservers();
	}

	@Override
	public void addObserver(Observer1 o) {
		lista.add(o);
	}

	@Override
	public void notifyObservers() {
		for(int i=0;i<lista.size();i++){
			Observer1 ob = (Observer1) lista.get(i);
			ob.update(this.jeden);
		}

	}

}

class window1 implements Observer1 {
	private int jeden;

	@Override
	public void update(int a) {
		jeden = a;
		this.display();

	}

	public void display() {
		System.out.println(jeden);
	}

}
