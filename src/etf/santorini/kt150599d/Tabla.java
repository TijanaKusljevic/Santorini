package etf.santorini.kt150599d;

import java.awt.*;
import java.awt.event.*;

public class Tabla {
	private Panel ploca;
	private Dugme[] dugmici = new Dugme[25];
	private int pritisnuto;

	private static class Dugme extends Button {
		public int indeks;

		public Dugme(int ind) {
			super(" ");
			indeks = ind;
		}
	}

	public Tabla(Panel pl) {
		ploca = pl;
		ploca.setLayout(new GridLayout(5, 5));
		ploca.setEnabled(false);

		DugmeAkcija osl = new DugmeAkcija();
		for (int iter = 0; iter < 25; iter++) {
			ploca.add(dugmici[iter] = new Dugme(iter));
			dugmici[iter].addActionListener(osl);
		}
	}

	public synchronized int koJePritisnut() throws InterruptedException {
		ploca.setEnabled(true);
		wait();
		ploca.setEnabled(false);
		return pritisnuto;
	}

	private class DugmeAkcija implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			pritisnuto = ((Dugme) arg0.getSource()).indeks;
			dalje();
		}

	}

	private synchronized void dalje() {
		notify();
	}

	public void postaviOznaku(int ind, String oznaka) {
		// if(!dugmici[ind].getLabel().equals(" "))
		dugmici[ind].setLabel(oznaka);

	}
	
	public void prazni() {
		for(int i=0; i<25; i++) {
			dugmici[i].setLabel(" ");
			dugmici[i].setEnabled(true);
			dugmici[i].setBackground(Color.lightGray);
		}
	}

	public void oboji(int ind, int broj) {
		if (broj == 1) {
			dugmici[ind].setBackground(new Color(102, 255, 102));
		}
		if(broj==2) {
			dugmici[ind].setBackground(new Color(0, 204, 0));
		}
		if(broj==3) {
			dugmici[ind].setBackground(new Color(0, 102, 0));
		}
		if(broj==4) {
			dugmici[ind].setBackground(new Color(0, 0, 153));
		}

	}

}
