package pkt;

public class Kaynaklar {
	private int yazici;
	private int CDsurucu;
	private int modem;
	private int tarayici;
	
	public Kaynaklar(int y, int c, int m, int t){
		this.yazici = y;     //sistemdeki toplam yazıcı miktarı
		this.CDsurucu = c;    //sistemdeki toplam CD sürücüsü miktarı
		this.modem = m;			//sistemdeki toplam modem miktarı
		this.tarayici = t;		//sistemdeki toplam tarayıcı miktarı
	}
	
	public boolean kaynakKontrol(Prosess islem) {   //gelen prosese ayrılabilecek yeterli kaynak var mı diye kontrol eder
		
		return (this.yazici > islem.yazici && this.CDsurucu > islem.cdSurucu && this.modem > islem.modem && this.tarayici > islem.tarayici);
	}
	
	public void kaynakTahsisi(Prosess islem) {   //sistemde yeterli kaynak varsa kaynakları prosese tahsis eder
		if(kaynakKontrol(islem)) {      //sistemdeki kaynakları kontrol et
			this.yazici -= islem.yazici;
			this.CDsurucu -= islem.cdSurucu;
			this.modem -= islem.modem;
			this.tarayici -= islem.tarayici;
		}
		else this.hataMesajı();  //yeterli kaynak yok
	}
	 public String hataMesajı() {
		 return "Yeterli kaynak yok";
	 }
	public void kaynakIadesi(Prosess islem) {   //proses işlemini bitirdikten sonra kaynakları iade eder
		this.yazici += islem.yazici;
		this.CDsurucu += islem.cdSurucu;
		this.modem += islem.modem;
		this.tarayici += islem.tarayici;
	}
	
	
}
