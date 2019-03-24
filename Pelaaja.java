package JokeriPokeri;
import java.util.*;

public class Pelaaja {
	private String nimi;
	private double peliVarat;
	private double panos;
	private ArrayList<Pelikortti> k‰si;
	
	public Pelaaja(String nimi, double peliVarat, double panos) {
		this.nimi = nimi;
		this.peliVarat = peliVarat;
		this.panos = panos;
	}
	
	
	public ArrayList<Pelikortti> annaK‰si() {
		return k‰si;
	}
	
	
	public String annaNimi() {
		return nimi;
	}
	
	
	public double annaPanos() {
		return panos;
	}
	
	
	public double annaPeliVarat() {
		return peliVarat;
	}
	
	
	public void lis‰‰Kortti(Pelikortti kortti) {
		k‰si.add(kortti);
	}
	
	
	public Kortti annaKortti(int kortti) {
		Kortti apuKortti = k‰si.get(kortti);
		k‰si.remove(kortti);
		return apuKortti;
	
}
}
