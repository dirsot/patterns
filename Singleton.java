public class Singleton {
	public static void main(String[] s) {
		Sin a = Sin.getSin();
		Sin b = Sin.getSin();

		a.dodaj();
		a.wypisz();
		b.dodaj();
		b.wypisz();
	}
}

class Sin {
	private static volatile Sin main;
	public int z = 0;

	private Sin() {
	}

	public void dodaj() {
		z++;
	}
	public void wypisz(){
		System.out.println(z);
	}
	public static synchronized Sin getSin() {
		if (main == null) {
			main = new Sin();
		}
		return main;
	}
}
