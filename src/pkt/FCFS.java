package pkt;

import java.util.Queue;

class FCFS {

	//Bütün proseslerin bekleme zamanlarını bulan fonksiyon
	static void beklemeZamaniBul(Queue<Process> processQueue, int n,
			int bt[], int wt[]) {
		//bekleme zamanı ilk proses için 0
		wt[0] = 0;

		//bekleme zamanı hesaplanıyor her biri için 
		for (int i = 1; i < n; i++) {
			wt[i] = bt[i - 1] + wt[i - 1];
		}
	}

	// İş bitirme zamanlarını hesaplayan fonksiyon
	static void isBitirmeBul(Queue<Process> processQueue, int n,
			int bt[], int wt[], int is_bitirme[]) {
		// İş bitirme zamanlarını bekleme ve patlama sürelerini toplayarak buluyoruz 
 
		for (int i = 0; i < n; i++) {
			is_bitirme[i] = bt[i] + wt[i];
		}
	}

	//Ortlama cevap süresini bulan fonksiyon
	static void ortCevapSuresi(Queue<Process> processQueue, int n, int bt[]) {
		int wt[] = new int[n], is_bitirme[] = new int[n];
		int total_wt = 0, total_is_bitirme = 0;

		//Proseslerin bekleme sürelerini bulan fonksiyon
		beklemeZamaniBul(processQueue, n, bt, wt);

		//Proseslerin iş bitirme sürelerini bulan fonksiyon
		isBitirmeBul(processQueue, n, bt, wt, is_bitirme);

		//Proseslerle ilgili detayları ekrana yazdırma 
		System.out.printf("Processes Burst time Waiting time Turn around time\n");

		// toplam bekleme süresi ve iş bitirme sürelerini hesaplıyouz
		int i = 0;
		for (Process process : processQueue) {
			total_wt = total_wt + wt[i];
			total_is_bitirme = total_is_bitirme + is_bitirme[i];
			System.out.printf(" %d ", (i + 1));
			System.out.printf("	 %d ", bt[i]);
			System.out.printf("	 %d", wt[i]);
			System.out.printf("	 %d\n", is_bitirme[i]);
		}
		float s = (float)total_wt /(float) n;
		int t = total_is_bitirme / n;
		System.out.printf("Average waiting time = %f", s);
		System.out.printf("\n");
		System.out.printf("Average turn around time = %d ", t);
	}

	
}