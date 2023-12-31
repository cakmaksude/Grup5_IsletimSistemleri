package pkt;

public class Frame {
	private int offset;  //asıl bellekteki yeri
	private int size; //mb cinsinden çerçevenin boyutu
	private int allocated;  //sayfanın ayrılmış olup olmama bilgisi
	
	public Frame(int off) {
		this.offset = off;
		this.size = 4;
		this.allocated = 0;  //sayfa ayırılmamış
	}
	
	public void reserveFrame() {
		this.allocated = 1;   //sayfa ayırıldı
	}
	
	public boolean kontrolCerceve() {  //sayfa boşta mı?
		return (this.allocated == 0);
	}
	
	public void cerceveIade() {   //sayfayı boşalt
		this.allocated = 0;
	}
	
	public int getOffset() {   //prosess offset numarasına ulaşmak isterse diye
		return offset;
	}

}
