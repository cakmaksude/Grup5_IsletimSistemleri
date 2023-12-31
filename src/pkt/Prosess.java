package pkt;

public class Prosess {
	public int varisZamani;
	public int oncelik;
	public int patlamaZamani;
	public int bellekGereksinimi;
	public int yazici;
	public int tarayici;
	public int modem;
	public int cdSurucu;
	public int[] bellekIndis;
	//public int offset;   //temisili
	public int cerceveSayisi;
	
	Prosess(int vz, int on, int pz, int bg, int y, int t, int m, int cd){
		this.varisZamani = vz;
		this.oncelik = on;
		this.patlamaZamani = pz;
		this.bellekGereksinimi = bg;
		this.yazici = y;
		this.tarayici = t;
		this.modem = m;
		this.cdSurucu = cd;
		int cerceve = bg / 4;   //her çerçeve 4 mb ; bellek gereksinimini 4mb'ye bölünce kullanılacak cerceve sayısını buluyoruz
		this.cerceveSayisi = cerceve;
		this.bellekIndis = new int[cerceve];  //bellekte ayırdığımız çerçevelerin indislerini tutmak için
	}

}
