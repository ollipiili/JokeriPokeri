package JokeriPokeri;

public class Pelikortti extends Kortti {
	Maa maa;
	Arvo arvo;
	
	public Pelikortti(Maa maa, Arvo arvo) {
		this.maa = maa;
		this.arvo = arvo;
	}
	
	public Maa annaMaa() {
		return maa;
	}
	
	public Arvo annaArvo() {
		return arvo;
	}
	
	public String toString() {
		return maa + " " + arvo;
	}
}
