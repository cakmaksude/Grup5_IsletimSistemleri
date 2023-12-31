package pkt;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BellekTahsisi bellek = new BellekTahsisi(2, 2, 1, 1);
		//prosesleri alıp proses sınıfına yerleştir
		//çizelgeleyici algoritmalarında prosesleri çalıştırırken bellek tahsisi yap
		//örn:
		// Prosess p1 = new Prosess(12, 0, 1, 64, 0, 0, 0, 0 );
		//bellek.gercekZamanli(p1);   //gerçek zamanlı proses olduğu için
		//Prosess p2 = new Prosess(12, 1, 2, 128, 1, 0, 0, 1 );
		//bellek.kullaniciZamanli(p2);    //kullanıcı prosesi olduğu için
		
		//çizelgeleyicinin tasarımına göre BellekTahsisi bellek = new BellekTahsisi(2,2,1,1) kodu çizelgeleyicinin sınıfının içinde de oluşturulabilir<s
	}

}
