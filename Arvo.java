package JokeriPokeri;

public enum Arvo {
	�SS�(1), KAKSI(2), KOLME(3), NELJ�(4), VIISI(5), KUUSI(6), SEITSEM�N(7), KAHDEKSAN(8), YHDKEKS�N(9), KYMMENEN(10), J�TK�(11), KUNINGATAR(12), KUNINGAS(13), JOKERI(14);
	
	private int arvo;
	
	private Arvo(int arvo) {
		this.arvo = arvo;
	}
	
	public int annaArvo( ) {
		return arvo;
	}
	
}
