package etf.santorini.kt150599d;

import java.awt.*;
import java.awt.event.*;

public class Prikaz extends Frame {

	private Panel glavna = new Panel();
	private Tabla tabla = new Tabla(glavna);
	private Igra igra;
	// private Igrac igrac;

	private int dub[] = { 4, 4 };
	private int brojac = 0;
	private int brojevi[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	private int nacin = 0;
	private int racunari = 0;
	private int pisi = 0;
	private int datoteka = 0;

	private class Izbor extends Choice {
		public Izbor(int brojac) {
			for (int i = 2; i < 10; i++) {
				add(i + "");
			}
			select(2);
			addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					dub[brojac] = brojevi[getSelectedIndex()];

				}
			});
		}
	}

	private class Sporo extends Choice {
		public Sporo(String s1, String s2, int br) {
			add(s1);
			add(s2);

			select(0);
			addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if(br==1)
						nacin = getSelectedIndex();
					else
						{datoteka= getSelectedIndex(); }
					
				}
			});
		}

	}

	private Label labela = new Label("Izaberite igrace", Label.CENTER);
	private Label laba = new Label("Procena", Label.CENTER);

	private class Dodatno extends Panel {
		private Sporo sporo = new Sporo("Normalno", "Usporeno",1);

		public Dodatno() {
			setLayout(new GridLayout(1, 3));
			add(sporo);
			add(laba);
			add(labela);
		}

	}

	private class Biranje extends Panel {
		private CheckboxGroup grupa = new CheckboxGroup();
		private Checkbox prvi = new Checkbox("Covek", true, grupa);
		private Checkbox drugi = new Checkbox("Racunar", false, grupa);
		private Checkbox treci = new Checkbox("Takmicar", false, grupa);
		private String oznaka;
		private Igrac igrac;
		private Izbor izbor = new Izbor(brojac++);

		Biranje(String ozn) {
			oznaka = ozn;
			Label ntp = new Label(ozn, Label.CENTER);

			ItemListener osl = new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if ((Checkbox) arg0.getSource() == treci) {
						igrac = new Racunar(oznaka, tabla);
						igrac.setTakmicar(1);
						racunari++;
						if (oznaka.equals("O")) {
							igrac.setMojID(1);
							igrac.setDubina(dub[1]);
							igrac.setLabela(laba);
							igrac.setNacin(nacin);
						} else {
							igrac.setMojID(0);
							igrac.setDubina(dub[0]);
							igrac.setLabela(laba);
							igrac.setNacin(nacin);
						}
						return;
						
					} 
					if ((Checkbox) arg0.getSource() == prvi) {
						igrac = new Covek(oznaka, tabla);
					} else {
						igrac = new Racunar(oznaka, tabla);
						racunari++;
						if (oznaka.equals("O")) {
							igrac.setMojID(1);
							igrac.setDubina(dub[1]);
							igrac.setLabela(laba);
							igrac.setNacin(nacin);
						} else {
							igrac.setMojID(0);
							igrac.setDubina(dub[0]);
							igrac.setLabela(laba);
							igrac.setNacin(nacin);
						}

					}

				}

			};
			prvi.addItemListener(osl);
			drugi.addItemListener(osl);
			treci.addItemListener(osl);

			setLayout(new GridLayout(5, 1));
			add(ntp);
			add(prvi);
			add(drugi);
			add(treci);
			add(izbor);
			igrac = new Covek(oznaka, tabla);
		}
	}

	private class Donje extends Panel {
		Sporo sporo = new Sporo("Igraj od pocetka", "Citaj iz datoteke",2);

		Button dd = new Button("Nova igra");

		public Donje() {
			dd.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (igra != null)
						igra.prekini();
					tabla.prazni();
					igra = new Igra(p.igrac, d.igrac, tabla, labela);
					igra.setCitaj(datoteka);
					if (racunari == 2) {
						pisi = 1;
						igra.setPisi(pisi);
					}
					p.igrac.setMatrica(igra.getMatrica());
					d.igrac.setMatrica(igra.getMatrica());
				}
			});
		}
		
		public void popuni() {
			setLayout(new GridLayout(2, 1));
			add(sporo);
			add(dd);
		}
	}

	private Biranje p = new Biranje("X");
	private Biranje d = new Biranje("O");
	private Dodatno dodatno = new Dodatno();
	private Donje donje=null;

	private void popuni() {
		add(glavna, "Center");
		// add(labela, "North");
		add(dodatno, "North");
		add(p, "West");
		add(d, "East");

		/*
		 * Button dd=new Button("Nova igra"); dd.addActionListener( new ActionListener()
		 * {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) { if(igra != null)
		 * igra.prekini(); igra=new Igra(p.igrac, d.igrac, tabla, labela);
		 * if(racunari==2) { pisi=1; igra.setPisi(pisi); }
		 * p.igrac.setMatrica(igra.getMatrica()); d.igrac.setMatrica(igra.getMatrica());
		 * } });
		 */
		
		donje=new Donje();
		donje.popuni();
		add(donje, "South");
	}

	public Prikaz() {
		super("IGRA SANTORINI");
		setBounds(100, 100, 400, 300);
		popuni();
		setVisible(true);
		

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg) {
				if(igra!=null)
					igra.prekini();
				dispose();
			}
		});
	}

	public static void main(String[] shdg) {
		new Prikaz();
	}
}
