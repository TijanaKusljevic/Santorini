package etf.santorini.kt150599d;

import java.awt.Label;

//import etf.santorini.kt150599d.Igra.Polje;

import java.util.concurrent.*;

public class Racunar extends Igrac {

	//int mojID = 0;
	int nmp=0;

	Polje[][] matrica;
	
	public Polje[][] getMatrica() {
		return matrica;
	}

	public void setMatrica(Polje[][] matrica) {
		this.matrica = matrica;
	}
	


	public Racunar(String oznaka, Tabla tabla, Polje[][] matrica) {
		super(oznaka, tabla);
		this.matrica = matrica;
	}

	public Racunar(String oznaka, Tabla tabla) {
		super(oznaka, tabla);
		nmp=mojID;
	}
	
	
	
	
	
	public int nadjiMetrinku1(Polje[][] matrica, int minilimax, int gde, int gradnja) {
		int prosli = 0;
		int trenutni = 0;
		int njegov=0;
		int f = 0;
		int m = 0;
		int l = 0;
		int vm1 = 0, km1 = 0, vm2 = 0, km2 = 0, vn1 = 0, vn2 = 0, kn1 = 0, kn2 = 0;
		int ko=0;
		int kg=gde%5;
		int vg=gde/5;
		
		if(minilimax==0) ko=nmp;
		else ko=1-nmp;
		
		

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matrica[i][j].ko == ko) {
					vm1 = i;
					km1 = j;
					trenutni = i * 5 + j;
					prosli = trenutni;
				}
			}
		}
		
		if(matrica[vm1][km1].visina==3)  {if(minilimax==0) { return 500; }else return -500;}
		
		//if(imalikuda(matrica, ko)==0) {if(minilimax==0) { return -500; }else return 500;}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matrica[i][j].ko == ko && i * 5 + j != prosli) {
					trenutni = i * 5 + j;
					vm2 = i;
					km2 = j;
				}
			}
		}
		
		if(matrica[vm2][km2].visina==3) { if(minilimax==0) {return 500; } else return -500;}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matrica[i][j].ko == 1 - ko) {
					vn1 = i;
					kn1 = j;
					njegov = i * 5 + j;
				}
			}
		}
		

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matrica[i][j].ko == 1 - ko && i * 5 + j != njegov) {
					vn2 = i;
					kn2 = j;
				}
			}
		}
		
		
		
		

		if (prosli == gde) {
			m = matrica[prosli / 5][prosli % 5].visina;
		} else {
			m = matrica[trenutni / 5][trenutni % 5].visina;
		}
		
		//if(m==0) {m=-20;}

		int gv = gradnja / 5;
		int gk = gradnja % 5;
		
		if(matrica[gv][gk].visina==4 && Math.abs(gv-vn1)>1 && Math.abs(gv-vn2)>1 && Math.abs(gk-kn1)>1 && Math.abs(gk-kn2)>1) {
			
			if(minilimax==0)
				return -400;
			else
				return 400;
		}
		
		if((Math.abs(kn1-km1)>2 && Math.abs(kn1-km2)>2) || ( Math.abs(vn1-vm1)>2 && Math.abs(vn1-vm2)>2)) {
		
			if(minilimax==0)
				return -300;
			else
				return 300;
		}
		
		if((Math.abs(kn2-km1)>2 && Math.abs(kn2-km2)>2) || ( Math.abs(vn2-vm1)>2 && Math.abs(vn2-vm2)>2)) {
		
			if(minilimax==0)
				return -300;
			else
				return 300;
		}
		
		

		l = Math.abs(gv - vm1) + Math.abs(gv - vm2) + Math.abs(gk - km1) + Math.abs(gk - km2);
		
		l = Math.abs(gv - vn1) - Math.abs(gv - vn2) - Math.abs(gk - kn1) - Math.abs(gk - kn2)-l;

		l = l * (matrica[gv][gk].visina);
		
		//int r=matrica[vm1][km1].visina+matrica[vm2][km2].visina-matrica[vn1][kn1].visina-matrica[vn2][kn2].visina;

		//f = l + m+r;
		
		f=l+m;
		
		if(minilimax==1) {return f*(-1);}
		
		return f;

	}

	
	
	
	



	public int nadjiMetrinku(Polje[][] matrica, int minilimax, int gde, int gradnja) {
		int prosli = 0;
		int trenutni = 0;
		int njegov=0;
		int f = 0;
		int m = 0;
		int l = 0;
		int vm1 = 0, km1 = 0, vm2 = 0, km2 = 0, vn1 = 0, vn2 = 0, kn1 = 0, kn2 = 0;
		int ko=0;
		
		if(minilimax==0) ko=nmp;
		else ko=1-nmp;
		
		

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matrica[i][j].ko == ko) {
					vm1 = i;
					km1 = j;
					trenutni = i * 5 + j;
					prosli = trenutni;
				}
			}
		}
		
		//if(matrica[vm1][km1].visina==3)  {System.out.println("dfdg");if(minilimax==0) {System.out.println("kiaijs"); return 500; }else return -500;}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matrica[i][j].ko == ko && i * 5 + j != prosli) {
					trenutni = i * 5 + j;
					vm2 = i;
					km2 = j;
				}
			}
		}
		
		//if(matrica[vm2][km2].visina==3) { System.out.println("dfdg");if(minilimax==0) {System.out.println("kiaijs"); return 500; } else return -500;}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matrica[i][j].ko == 1 - ko) {
					vn1 = i;
					kn1 = j;
					njegov = i * 5 + j;
				}
			}
		}
		

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matrica[i][j].ko == 1 - ko && i * 5 + j != njegov) {
					vn2 = i;
					kn2 = j;
				}
			}
		}
		
		

		if (prosli == gde) {
			m = matrica[prosli / 5][prosli % 5].visina;
		} else {
			m = matrica[trenutni / 5][trenutni % 5].visina;
		}
		

		int gv = gradnja / 5;
		int gk = gradnja % 5;
		
		

		l = Math.abs(gv - vm1) + Math.abs(gv - vm2) + Math.abs(gk - km1) + Math.abs(gk - km2);
		//l = Math.abs(l - Math.abs(gv - vn1) - Math.abs(gv - vn2) - Math.abs(gk - kn1) - Math.abs(gk - kn2));
		l =  Math.abs(gv - vn1) - Math.abs(gv - vn2) - Math.abs(gk - kn1) - Math.abs(gk - kn2)-l;

		l = l * (matrica[gv][gk].visina);
		
		
		
		f=l+m;
		
		if(minilimax==1) {return f*(-1);}
		
		return f;

	}

	int stari=0;
	int gradnja=0;
	int gde=0;
	
	int stari1=0;
	int gradnja1=0;
	int gde1=0;
	
	private int zaprikaz=0;
	
	
	public int imalikuda(Polje[][] matrica1, int red) {
		boolean prvi=true;
		int trazen = 0;
		int prosli = 0;
		int k = 0;
		int j = 0;
		for (k = 0; k < 5; k++) {
			for (j = 0; j < 5; j++) {
				if (matrica1[k][j].ko == red) {
					trazen = k * 5 + j;
					prosli = trazen;

				}
			}
		}

		int[] kand = Igra.komsije(prosli);
		int i = 0;
		for (i = 0; i < kand.length; i++) {
			if (Math.abs(matrica1[prosli / 5][prosli % 5].visina - matrica1[kand[i] / 5][kand[i] % 5].visina) < 2) {
				if (!matrica1[kand[i] / 5][kand[i] % 5].zauzeto) {
					return 1;
				}
			}
		}

		for (k = 0; k < 5; k++) {
			for (j = 0; j < 5; j++) {
				if (k * 5 + j != prosli && matrica1[k][j].ko == red) {
					trazen = k * 5 + j;

				}
			}
		}

		int[] kand1 = Igra.komsije(trazen);
		int i1 = 0;
		for (i1 = 0; i1 < kand1.length; i1++) {
			if (Math.abs(matrica1[trazen / 5][trazen % 5].visina - matrica1[kand1[i1] / 5][kand1[i1] % 5].visina) < 2) {
				if (!matrica1[kand1[i1] / 5][kand1[i1] % 5].zauzeto) {
					//if(!prvi)
						return 1;
				}
			}
		}

		return 0;
	}
	
	
	public int minimax(Polje[][] matrica, int maxDubina, int trenutnaDubina, int minilimax, int alfa, int beta) {
		int dole=alfa;
		int gore=beta;
		
		int kraj=imalikuda(matrica, 0);
		if(trenutnaDubina==maxDubina || kraj==0) { 
			if(takmicar==0)
				return nadjiMetrinku(matrica,1- minilimax, gde, gradnja); 
			else 
				return nadjiMetrinku1(matrica,1- minilimax, gde, gradnja); 
		}
		
		kraj=imalikuda(matrica, 1);
		if(kraj==0) {
			if(takmicar==0)
				return nadjiMetrinku(matrica,1- minilimax, gde, gradnja); 
			else
				return nadjiMetrinku1(matrica, 1-minilimax, gde, gradnja);
		}
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if (matrica[i][j].zauzeto && matrica[i][j].visina==3) {
					if(takmicar==0)
						return nadjiMetrinku(matrica, 1-minilimax, gde, gradnja);
					else 
						return nadjiMetrinku1(matrica, 1-minilimax, gde, gradnja);
				}
			}
		}
		 
			
		int najboljaVrednost = 0;
		int trenutnaVrednost = 0;
		int s=0;
		int g=0;
		int gd=0;
		
		
		if (minilimax == 0) {
			najboljaVrednost = -1000000;
		}
		if (minilimax == 1) {
			najboljaVrednost = 1000000;
		}

		int prosli = 0;
		int trenutni = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matrica[i][j].ko == mojID) {
					trenutni = i * 5 + j;
					prosli = trenutni;
				}
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matrica[i][j].ko == mojID && i * 5 + j != prosli) {
					trenutni = i * 5 + j;
				}
			}
		}

		for (int iter = 0; iter < 2; iter++) {
			int[] kand = Igra.komsije(prosli);
			if (iter == 1) {
				kand = Igra.komsije(trenutni);
				prosli=trenutni; //*****************************************
			}

			for (int i = 0; i < kand.length; i++) {
				
				if (!matrica[kand[i] / 5][kand[i] % 5].zauzeto && Math
						.abs(matrica[prosli / 5][prosli % 5].visina - matrica[kand[i] / 5][kand[i] % 5].visina) < 2) {
					int[] niz = Igra.komsije(kand[i]);
					for (int j = 0; j < niz.length; j++) {
						if ((!matrica[niz[j] / 5][niz[j] % 5].zauzeto && matrica[niz[j] / 5][niz[j] % 5].visina < 4) || niz[j]==prosli) {
							Polje[][] nova = new Polje[5][5];
							for (int m = 0; m < 5; m++) {
								for (int n = 0; n < 5; n++) {
									nova[m][n] = new Polje(0, 0, m*5+n);
									nova[m][n].zauzeto=matrica[m][n].zauzeto;
									nova[m][n].ko=matrica[m][n].ko;
									nova[m][n].visina=matrica[m][n].visina;
									//matrica[m][n];
								}
							}
							
								nova[prosli / 5][prosli % 5].ko = 2;
								nova[prosli / 5][prosli % 5].zauzeto = false;
							
							
							stari=prosli;
							
							gde=kand[i];

							nova[kand[i] / 5][kand[i] % 5].ko = mojID;
							nova[kand[i] / 5][kand[i] % 5].zauzeto = true;
							
							gradnja=niz[j];
							
							s=stari;
							gd=gde;
							g=gradnja;
							
							nova[niz[j]/5][niz[j]%5].visina++;
							int zap=mojID;
							mojID=1-mojID;
							trenutnaVrednost = minimax(nova, maxDubina, trenutnaDubina + 1, 1 - minilimax, dole, gore);
							mojID=zap;

							if (minilimax == 0 && trenutnaVrednost > najboljaVrednost) {
								najboljaVrednost = trenutnaVrednost;
								if(najboljaVrednost>=gore)
									return najboljaVrednost;
								dole=Math.max(dole, najboljaVrednost);
								if(trenutnaDubina==1) {
									stari1=s; gradnja1=g; gde1=gd;
								}
								
							}
							if (minilimax == 1 && trenutnaVrednost < najboljaVrednost) {
								najboljaVrednost = trenutnaVrednost;
								if(najboljaVrednost<=dole)
									return najboljaVrednost;
								gore=Math.min(gore, najboljaVrednost);
							}
						}
					}
				} else {
					continue;
				}
				
			}
		}
		zaprikaz=najboljaVrednost;
		return najboljaVrednost;
	}
	
	int num=0;
	int broj=0;

	@Override
	public int birajPolje() throws InterruptedException {
		//mojID=1;
		//nmp=mojID;
		if(nacin==1) {
			TimeUnit.SECONDS.sleep(1);
		}
		nmp=mojID;
		
		if(broj==0) {
			broj++; 
			int random=0;
			while(true) {
				random = (int)(Math.random() * 24 + 1); 
				if(!matrica[random/5][random%5].zauzeto)
					break;
			}
			return random;
			}
		if(broj==1) {
			broj++; 
			int random=0;
			while(true) {
				random = (int)(Math.random() * 24 + 1); 
				if(!matrica[random/5][random%5].zauzeto)
					break;
			}
			return random;
			}
		if(num==0) {
			
			minimax( matrica, dubina, 1, 0, -1000000, 1000000);
			

			labela.setText(""+zaprikaz);
			num++;
			return stari1;
		}
		if(num==1) {
			num++;
			return gde1;
		}
		
		if(num==2) {
			num=0;
			return gradnja1;
		}

		
		return 0;
	}

}
