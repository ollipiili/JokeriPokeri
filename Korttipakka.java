package JokeriPokeri;
import java.util.*;


public class Korttipakka {
	private ArrayList<Pelikortti> korttipakka;
	private Maa[] maat;
	private Arvo[] arvot;
	
	//* Luo korttipakan kahdella jokerilla */
	public Korttipakka() {
		maat = Maa.values();
		arvot = Arvo.values();
		korttipakka = new ArrayList<Pelikortti>();
		
		for (int i = 0; i < maat.length -1; i++) {
			for (int j = 1; j < arvot.length -1; j++) {
				korttipakka.add(new Pelikortti(maat[i], arvot[j]));
			}
		}
		korttipakka.add(new Pelikortti(maat[maat.length -1], arvot[arvot.length -1]));					
	}
	
	public void sekoitaKortit() {
		Collections.shuffle(korttipakka);
	}
	
	public Pelikortti annaKortti() {
		int apu = new Random().nextInt(korttipakka.size());
		Pelikortti apuKortti = korttipakka.get(apu);
		korttipakka.remove(apu);
		return apuKortti;
	}
	
	public void otaKortti(Pelikortti kortti) {
		korttipakka.add(kortti);
	}
	
}
