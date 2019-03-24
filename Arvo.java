package JokeriPokeri;

public enum Arvo {
	ÄSSÄ(1), KAKSI(2), KOLME(3), NELJÄ(4), VIISI(5), KUUSI(6), SEITSEMÄN(7), KAHDEKSAN(8), YHDKEKSÄN(9), KYMMENEN(10), JÄTKÄ(11), KUNINGATAR(12), KUNINGAS(13), JOKERI(14);
	
	private int arvo;
	
	private Arvo(int arvo) {
		this.arvo = arvo;
	}
	
	public int annaArvo( ) {
		return arvo;
	}
	
}
