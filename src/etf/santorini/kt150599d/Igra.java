package etf.santorini.kt150599d;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Igra extends Thread {
	private int potez1 = 0;
	private int potez2 = 0;
	private Tabla tabla;
	private Label labela;
	private boolean gotovo = false;
	private int pisi=0;
	
	private int citaj=0;
	
	public int getCitaj() {
		return citaj;
	}

	public void setCitaj(int citaj) {
		this.citaj = citaj;
	}

	public int getPisi() {
		return pisi;
	}

	public void setPisi(int pisi) {
		this.pisi = pisi;
	}

	private int red = 0;
	private int faza = 0;
	private int prosli = 0;

	private Igrac igraci[] = new Igrac[2];


	public Tabla getTabla() {
		return tabla;
	}

	public void setTabla(Tabla tabla) {
		this.tabla = tabla;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public Polje[][] getMatrica() {
		return matrica;
	}

	public void setMatrica(Polje[][] matrica) {
		this.matrica = matrica;
	}

	private  Polje matrica[][] = new Polje[5][5];

	public Igra(Igrac prvi, Igrac drugi, Tabla t, Label l) {
		labela = l;
		igraci[0] = prvi;
		igraci[1] = drugi;
		tabla = t;
		int num = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Polje p = new Polje(i, j, num);
				matrica[i][j] = p;
			}
		}
		start();
	}

	public static int[] komsije(int ko) {
		int niz1[] = { 0, 0, 0, 0, 0, 0, 0, 0 };

		if (ko == 0) {
			int niz[] = { 0, 0, 0 };
			niz[0] = 1;
			niz[1] = 5;
			niz[2] = 6;
			return niz;
		}
		if (ko == 4) {
			int niz[] = { 0, 0, 0 };
			niz[0] = 3;
			niz[1] = 8;
			niz[2] = 9;
			return niz;
		}
		if (ko == 20) {
			int niz[] = { 0, 0, 0 };
			niz[0] = 15;
			niz[1] = 16;
			niz[2] = 21;
			return niz;
		}
		if (ko == 24) {
			int niz[] = { 0, 0, 0 };
			niz[0] = 18;
			niz[1] = 19;
			niz[2] = 23;
			return niz;
		}
		if (ko / 5 == 0) {
			int niz[] = { 0, 0, 0, 0, 0 };
			niz[0] = ko - 1;
			niz[1] = ko + 1;
			niz[2] = ko + 4;
			niz[3] = ko + 5;
			niz[4] = ko + 6;
			return niz;
		}
		if (ko / 5 == 4) {
			int niz[] = { 0, 0, 0, 0, 0 };
			niz[0] = ko - 6;
			niz[1] = ko - 5;
			niz[2] = ko - 4;
			niz[3] = ko - 1;
			niz[4] = ko + 1;
			return niz;
		}
		if (ko % 5 == 0) {
			int niz[] = { 0, 0, 0, 0, 0 };
			niz[0] = ko - 5;
			niz[1] = ko - 4;
			niz[2] = ko + 1;
			niz[3] = ko + 5;
			niz[4] = ko + 6;
			return niz;
		}
		if (ko % 5 == 4) {
			int niz[] = { 0, 0, 0, 0, 0 };
			niz[0] = ko - 6;
			niz[1] = ko - 5;
			niz[2] = ko - 1;
			niz[3] = ko + 4;
			niz[4] = ko + 5;
			return niz;
		}
		niz1[0] = ko - 6;
		niz1[1] = ko - 5;
		niz1[2] = ko - 4;
		niz1[3] = ko - 1;
		niz1[4] = ko + 1;
		niz1[5] = ko + 4;
		niz1[6] = ko + 5;
		niz1[7] = ko + 6;
		return niz1;
	}

	public int imalikuda(int red) {
		boolean prvi=true;
		int trazen = 0;
		int prosli = 0;
		int k = 0;
		int j = 0;
		for (k = 0; k < 5; k++) {
			for (j = 0; j < 5; j++) {
				if (matrica[k][j].ko == red) {
					trazen = k * 5 + j;
					prosli = trazen;

				}
			}
		}

		int[] kand = komsije(prosli);
		int i = 0;
		for (i = 0; i < kand.length; i++) {
			if (Math.abs(matrica[prosli / 5][prosli % 5].visina - matrica[kand[i] / 5][kand[i] % 5].visina) < 2) {
				if (!matrica[kand[i] / 5][kand[i] % 5].zauzeto) {
					return 1;
				}
			}
		}

		for (k = 0; k < 5; k++) {
			for (j = 0; j < 5; j++) {
				if (k * 5 + j != prosli && matrica[k][j].ko == red) {
					trazen = k * 5 + j;

				}
			}
		}

		int[] kand1 = komsije(trazen);
		int i1 = 0;
		for (i1 = 0; i1 < kand1.length; i1++) {
			if (Math.abs(matrica[trazen / 5][trazen % 5].visina - matrica[kand1[i1] / 5][kand1[i1] % 5].visina) < 2) {
				if (!matrica[kand1[i1] / 5][kand1[i1] % 5].zauzeto) {
					//if(!prvi)
						return 1;
				}
			}
		}

		return 0;
	}
	
	
	public boolean mozeli(int indeks) {
		
		int[] kand = komsije(indeks);
		int i = 0;
		for (i = 0; i < kand.length; i++) {
			if (Math.abs(matrica[indeks / 5][indeks % 5].visina - matrica[kand[i] / 5][kand[i] % 5].visina) < 2) {
				if (!matrica[kand[i] / 5][kand[i] % 5].zauzeto) {
					return true;
				}
			}
		}
		
		return false;
		
		
	}
	
	public String pretvori(int br) {
		if(br==0) {return "A";}
		if(br==1) {return "B";}
		if(br==2) {return "C";}
		if(br==3) {return "D";}
		return "E";
	}
	
	public int obrni(char s) {
		if(s=='A') {return 0;}
		if(s=='B') {return 1;}
		if(s=='C') {return 2;}
		if(s=='D') {return 3;}
		if(s=='E') {return 4;}
		return 7;
	}

	public void run() {
		int brojac=0;
		PrintWriter writer = null;
		Scanner skn=null;
		if(citaj==1) {
			try {
				skn=new Scanner(new FileInputStream("ulaz.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		if(pisi==1) {
			try {
				writer = new PrintWriter("izlaz.txt", "UTF-8");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		while (!interrupted() && !gotovo) {
			try {
				if (red == 0) {
					labela.setText("Na potezu X");
				}
				if (red == 1) {
					labela.setText("Na potezu O");
				}
				
				int kraj = (imalikuda(red));
				if (kraj == 0 && potez1>1 && potez2>1 && !(faza==1)) {
					if (red == 1) {
						labela.setText("Pobednik X");
					}
					if (red == 0) {
						labela.setText("Pobednik O");
					}
					gotovo = true;
					break;
				}
				kraj = imalikuda(1-red);
				if (kraj == 0 && potez1>1 && potez2>1) {
					if (red == 0) {
						labela.setText("Pobednik X");
					}
					if (red == 1) {
						labela.setText("Pobednik O");
					}
					gotovo = true;
					break;
				}
				int koje=0;
				
				if(citaj==1) {
					if(skn.hasNext()) {
						String pom=skn.next();
						char c=pom.charAt(0);
						int p=obrni(c);
						c=pom.charAt(1);
						int d=c-'1';
						koje=p*5+d;
					} else {
						citaj=0;
						koje = igraci[red].odigraj();
					}
				} else {
					koje = igraci[red].odigraj();
				}
				
				brojac++;
				if(pisi==1) {
					String str=pretvori(koje/5);
					writer.print(str+(koje%5+1)+" ");
					if(brojac==2 || brojac==4) {
						writer.println();
					} else {
						if(brojac%3==1 && brojac!=1) {writer.println();}
					}
					;
				}
				/*brojac++;
				if(brojac<1000)
				System.out.println(koje+"\n");*/
				if ((potez1 == 0 || potez1 == 1) && !matrica[koje / 5][koje % 5].zauzeto) {
					potez1++;
					matrica[koje / 5][koje % 5].ko = 0;
					matrica[koje / 5][koje % 5].zauzeto = true;
					tabla.postaviOznaku(koje, igraci[red].getOznaka());
					red = 1;
					if (potez1 == 1) {
						red = 0;
					}
				} else if (!(potez1 == 0 || potez1 == 1)) {
					{
						if ((potez2 == 0 || potez2 == 1) && !matrica[koje / 5][koje % 5].zauzeto) {
							potez2++;
							
							matrica[koje / 5][koje % 5].ko = 1;
							matrica[koje / 5][koje % 5].zauzeto = true;
							tabla.postaviOznaku(koje, igraci[red].getOznaka());
							red = 0;
							if (potez2 == 1) {
								red = 1;
							}
						} else {
							
							if (faza == 0 && matrica[koje / 5][koje % 5].zauzeto
									&& matrica[koje / 5][koje % 5].ko == red && mozeli(koje)) {
								prosli = koje;
								matrica[koje / 5][koje % 5].zauzeto = false;
								matrica[koje / 5][koje % 5].ko = 2;
								faza++;
								tabla.postaviOznaku(koje, " ");
							}
							int aa = matrica[koje / 5][koje % 5].visina;
							int bb = matrica[prosli / 5][prosli % 5].visina;
							int r = aa - bb;

							if (faza == 1 && !matrica[koje / 5][koje % 5].zauzeto && (Math.abs(r) < 2)) {
								aa = 0;
								bb = 0;

								int[] kand = komsije(prosli);
								int i = 0;
								for (i = 0; i < kand.length; i++) {
									if (kand[i] == koje)
										break;
								}
								if (i != kand.length) {
									matrica[koje / 5][koje % 5].zauzeto = true;
									matrica[koje / 5][koje % 5].ko = red;
									faza++;
									prosli = koje;
									tabla.postaviOznaku(koje, igraci[red].getOznaka());

								}
								if (matrica[koje / 5][koje % 5].visina == 3) {
									gotovo = true;
									if (red == 0)
										labela.setText("Pobednik je X");
									else
										labela.setText("Pobednik je O");
									break;

								}
							}
							if (faza == 2 && !matrica[koje / 5][koje % 5].zauzeto
									&& matrica[koje / 5][koje % 5].visina < 4) {
								int[] kand = komsije(prosli);
								int i = 0;
								for (i = 0; i < kand.length; i++) {
									if (kand[i] == koje)
										break;
								}
								if (i != kand.length) {
									matrica[koje / 5][koje % 5].visina++;
									faza = 0;
									tabla.oboji(koje, matrica[koje / 5][koje % 5].visina);
									red = 1 - red;
								} 
							}

						}

					}
				}
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}

		}
		if(pisi==1) writer.close();
	}

	void prekini() {
		interrupt();
	}

}
