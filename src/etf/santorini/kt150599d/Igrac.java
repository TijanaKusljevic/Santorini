package etf.santorini.kt150599d;

import java.awt.Label;

//import etf.santorini.kt150599d.Igra.Polje;

public abstract class Igrac {
	private String oznaka;
	protected Tabla tabla;
	protected int mojID = 0;
	protected int dubina;
	protected int takmicar=0;
	

	public int getTakmicar() {
		return takmicar;
	}

	public void setTakmicar(int takmicar) {
		this.takmicar = takmicar;
	}

	protected Label labela;

	public Label getLabela() {
		return labela;
	}

	public void setLabela(Label labela) {
		this.labela = labela;
	}
	
	protected int nacin=0;
	
	public int getNacin() {
		return nacin;
	}

	public void setNacin(int nacin) {
		this.nacin = nacin;
	}

	public int getDubina() {
		return dubina;
	}

	public void setDubina(int dubina) {
		this.dubina = dubina;
	}

	public int getMojID() {
		return mojID;
	}

	public void setMojID(int mojID) {
		this.mojID = mojID;
	}

	Polje[][] matrica;

	public Polje[][] getMatrica() {
		return matrica;
	}

	public void setMatrica(Polje[][] matrica) {
		this.matrica = matrica;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public Igrac(String oznaka, Tabla tabla) {
		super();
		this.oznaka = oznaka;
		this.tabla = tabla;
	}

	public abstract int birajPolje() throws InterruptedException;

	public int odigraj() throws InterruptedException {
		int koje = birajPolje();
		return koje;
	}

}
