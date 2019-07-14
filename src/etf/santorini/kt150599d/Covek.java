package etf.santorini.kt150599d;

public class Covek extends Igrac{

	public Covek(String oznaka, Tabla tabla) {
		super(oznaka, tabla);
	}

	@Override
	public int birajPolje() throws InterruptedException {
		
		return tabla.koJePritisnut();
	}

}
