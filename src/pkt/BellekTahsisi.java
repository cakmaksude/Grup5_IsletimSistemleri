package pkt;

public class BellekTahsisi {
	// Toplam bellek miktarı
//    private final int TOPLAM_BELLEK = 1024; // MB cinsinden  //bu kodda kullanılmıyor, ihtiyaca göre eklenebilir
	
	// Gerçek Zamanlı prosesler için ayrılan bellek miktarı
    //private final int GZ_BELLEK = 64; // MB cinsinden  //bu kodda kullanılmıyor ihtiyaca göre eklenebilir

    // Etkin kullanıcı işleri için ayrılan bellek miktarı
  //  private final int ETKIN_KULLANICI_BELLEK = 960; // MB cinsinden   **bu kodda kullanılmıyor ihtiyaca göre eklenebilir
 // Toplam kullanılabilir bellek
   // private int kullanilabilirBellek = TOPLAM_BELLEK;   //bu kodda kullanılmıyor ihtiyaca göre eklenebilir
    
    private int gercekZamanliBosCerceve = 16;
    private int kullaniciBosCerceve = 240;
    
    private Frame[] gercekZamanliCerceveler;
    private Frame[] kullaniciCerceve;
    private Kaynaklar kaynak;
    
    
    public BellekTahsisi(int y, int c, int m, int t) {
    	this.kaynak = new Kaynaklar(y,c,m,t);  //girilen sistem kaynak bilgileriyle kaynak sınıfından bir nesne oluştur
    	this.gercekZamanliCerceveler = new Frame[16];   //4 mb'lik 16 çerçeve oluştur
    	int bellek = 0;  //varsayılan olarak fiziksel bellekteki yeri
    	for(int i = 0; i<16; i++) {
    		gercekZamanliCerceveler[i] = new Frame(bellek);   //offset numaralarını ayarlamak için
    		bellek += 4;  
    	}
    	this.kullaniciCerceve = new Frame[240];    //4 mb'lık 250 çerçeve oluştur
    	for(int i=0; i<240; i++) {
    		kullaniciCerceve[i] = new Frame(bellek);
    		bellek +=4;
    	}
    }
    
    private boolean bellekKontrol(Prosess p , int bilgi) { //bilgi gelen prosesin gerçek zamanlı mı olduğunu anlamak için
    	if(bilgi == 0) {  //gerçek zamanlı ise
    		return (p.cerceveSayisi < this.gercekZamanliBosCerceve);
    	}
    	else // kullanıcı prosesi ise
    		return (p.cerceveSayisi < this.kullaniciBosCerceve);
    }
    private void bellekReserve(Prosess p, int bilgi) {  //bellekten yer ayırtmak için
    	//private fonksiyon olduğu için bellekte yer olup olmadığı kontrolü yapılmıyor çünkü çağıran fonksiyonda yapıldı
    	if(bilgi == 0) { //gerçek zamanlı
    		int indis = 0;
    		for(int i=0; i<16; i++) {
    			if(this.gercekZamanliCerceveler[i].kontrolCerceve()) {  //boş çerçeve bulduysan
    				gercekZamanliCerceveler[i].reserveFrame();   //boş çerçeveyi ayır
    				p.bellekIndis[indis] = i;  //çevçevenin indisini kaydet
    				indis ++;
    				if(indis == p.cerceveSayisi) break;   //prosesin talep ettiği çerçeve sayısı bittiyse döngüden çık
    			}
    		}
    		this.gercekZamanliBosCerceve -= p.cerceveSayisi;  //boş çerçeve sayısını azalt
    		
    	}
    	else {   //yukarıdaki işlemlerin aynılarını kullanıcı zamanlı prosesler için yap
    		int indis = 0;
    		for(int i=0; i<240; i++) {
    			if(this.kullaniciCerceve[i].kontrolCerceve()) {
    				kullaniciCerceve[i].reserveFrame();
    				p.bellekIndis[indis] = i;
    				indis++;
    				if(indis == p.cerceveSayisi) break;
    			}
    		}
    		this.kullaniciBosCerceve -= p.cerceveSayisi;
    	}
    }
    public void bellekIade(Prosess p, int bilgi) {   //prosesin işi bittiğinde belleği iade edecek
    	if(bilgi == 0) { //gerçek zamanlı prosesse
    		int indis = 0;
    		for(; indis < p.cerceveSayisi; indis++) {
    			this.gercekZamanliCerceveler[p.bellekIndis[indis]].cerceveIade();  //kaydetiğimiz çerçeve bilgisinden çerçeveyi iade et
    		}
    		this.gercekZamanliBosCerceve += p.cerceveSayisi;  //boş çerçeve sayısını arttır
    		kaynak.kaynakIadesi(p);  //kullandığın kaynakları iade et
    	}
    	else {  //etkin kullanıcı prosesi için aynı işlemler
    		int indis = 0;
    		for(; indis < p.cerceveSayisi; indis++) {
    			this.kullaniciCerceve[p.bellekIndis[indis]].cerceveIade();
    		}
    		this.kullaniciBosCerceve += p.cerceveSayisi;
    		kaynak.kaynakIadesi(p);
    	}
    }
    public String hataMesajı() {   //Boş olandan fazla çerçeve talep edildiyse
    	return "Bellekte yeterli alan yok";
    }
    public void gercekZamanli(Prosess p) {   //gerçek zamanlı proses için bellek tahsisi
    	if(kaynak.kaynakKontrol(p)) {  //yeterli kaynak var mı kontrol et
    		if(bellekKontrol(p,0)) {  //bellekte yeterli yer var mı?
    			kaynak.kaynakTahsisi(p);  //kaynakları tahsis et
    			this.bellekReserve(p, 0);  //belleği tahsis et
    		}
    		else {
    			this.hataMesajı();  //bellekte yeterli yer yok
    		}
    	}
    	else kaynak.hataMesajı();   //yeterli kaynak yok
    }
    public void kullaniciZamanli(Prosess p) {   //kullanıcı zamanlı prosess için bellek tahsisi
    	if(kaynak.kaynakKontrol(p)) {  //yeterli kaynak var mı?
    		if(bellekKontrol(p,1)) {  //yeterli bellek var mı?
    			kaynak.kaynakTahsisi(p);  //kaynağı ayır
    			this.bellekReserve(p, 1);  //belleği ayır
    		}
    		else {
    			this.hataMesajı();   //bellekte yeterli yer yok
    		}
    	}
    	else kaynak.hataMesajı();   //yeterli kaynak yok
    }
    

}
