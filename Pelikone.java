package JokeriPokeri;

import java.util.*;
import java.util.stream.*;

public class Pelikone {
	private double peliVarat;
	private double panos;
	private final double maxPanos = 1.0;
	private final double minPanos = 0.2;
	private ArrayList<Pelikortti> k�si;
	private Korttipakka pelikortit;
	
	public Pelikone(double peliVarat, double panos) {
		this.peliVarat = peliVarat;
		this.panos = panos;
		pelikortit = new Korttipakka();
		k�si = new ArrayList<Pelikortti>();
	}
	
	public String toString(ArrayList<Pelikortti> k�si) {
		String temp = "";
		for(Pelikortti kortti : k�si) {
			temp = " " + kortti.toString();
		}
		return temp;
	}
	
	
	public ArrayList<Pelikortti> annaK�si() {
		return k�si;
	}
	
	
	
	public double annaPanos() {
		return panos;
	}
	
	
	public double annaPeliVarat() {
		return peliVarat;
	}
	
	//* Nostaa kortin korttipakasta K�teen
	public void nostaKortti() {
		k�si.add(pelikortit.annaKortti());
	}
	
	
	//* Antaa kortin K�dest� takaisin korttipakkaan */
	public void vaihdaKortti(int kortti) {
		Pelikortti apuKortti = k�si.get(kortti);
		k�si.remove(kortti);
		pelikortit.otaKortti(apuKortti);
	}
	
	//* Nostaa panosta 0.2 yksikk��, jos maxPanos ylittyy niin palauttaa minimipanokseen */
	public void nostaPanosta() {
		if (panos < maxPanos) {
			panos = panos + 0.2;
		}else {
			panos = minPanos;
		}
	}
	
	//* Tarkastaa onko K�si voittava, alkaen suurimmasta voitosta */
	public void tarkastaVoitto(ArrayList<Pelikortti> k�si) {
		if(onkoV�risuora(k�si)) {
			System.out.println("V�risuora!");
		}
		else if (onkoViitoset(k�si)) {
			System.out.println("Viitoset!");
		}
		else if (onkoNeloset(k�si)) {
			System.out.println("Neloset!");
		}
		else if (onkoT�ysk�si(k�si)) {
			System.out.println("T�ysk�si!");
		}
		else if (onkoV�ri(k�si)) {
			System.out.println("V�ri!");
		}
		else if(onkoSuora(k�si)) {
			System.out.println("Suora!");
		}
		else if(onkoKolmoset(k�si)) {
			System.out.println("Kolmoset!");
		}
		else if(onkoKaksiParia(k�si)) {
			System.out.println("KaksiParia!");
		}
		else {
			System.out.println("Ei voittoa");
		}
		}
	
	
	/* ------------------------------------------------------------
	   --------------------TARKISTUS METODIT-----------------------
	   -----------------------------------------------------------*/
	
	
	//* Tarkastaa onko K�si V�RISUORA */
	public boolean onkoV�risuora(ArrayList<Pelikortti> k�si) {
		return (onkoV�ri(k�si) && onkoSuora(k�si));
		
	}
	
	
	//* Tarkastaa onko K�si VIITOSET */
	public boolean onkoViitoset(ArrayList<Pelikortti> k�si) {
		int[] arvot = k�si.stream().map( Pelikortti -> Pelikortti.annaArvo().annaArvo() ).mapToInt(i->i).toArray();
		Arrays.sort(arvot);
		boolean onkoViitoset = false;
		int kuinkaMontaSamaa = 0;
		
		for (int arvo : arvot) {
			for (int arvo2 : arvot) {
				if (arvo == arvo2 || arvo2 == 14) {
					kuinkaMontaSamaa++;
				}
			}
		}
		if (kuinkaMontaSamaa == 5) {
			onkoViitoset = true;
		}
		return onkoViitoset;
	}
	

	//* Tarkastaa onko K�si NELOSET */
	public boolean onkoNeloset(ArrayList<Pelikortti> k�si) {
		int[] arvot = k�si.stream().map( Pelikortti -> Pelikortti.annaArvo().annaArvo() ).mapToInt(i->i).toArray();
		Arrays.sort(arvot);
		boolean onkoNeloset = false;
		int kuinkaMontaSamaa = 0;
		
		for (int arvo : arvot) {
			for (int arvo2 : arvot) {
				if (arvo == arvo2 || arvo2 == 14) {
					kuinkaMontaSamaa++;
				}
			}
		
		}
		if (kuinkaMontaSamaa == 4) {
			onkoNeloset = true;
		}
		return onkoNeloset;
	}
	
	
	//* Tarkastaa onko K�si T�YSK�SI */
	public boolean onkoT�ysk�si(ArrayList<Pelikortti> k�si) {
		int[] arvot = k�si.stream().map( Pelikortti -> Pelikortti.annaArvo().annaArvo() ).mapToInt(i->i).toArray();
		Arrays.sort(arvot);
		
		//* Onko y y y z z */
		boolean onkoT�ysk�si1 = arvot[0] == arvot[1] && arvot[1] == arvot[2] && arvot[3] == arvot[4];
		
		//* Onko y y z z z */
		boolean onkoT�ysk�si2 = arvot[0] == arvot[1] && arvot[2] == arvot[3] && arvot[3] == arvot[4];
		
		return (onkoT�ysk�si1 || onkoT�ysk�si2);
	}
	
	
	//* Tarkastaa onko K�si V�RI */
	public boolean onkoV�ri(ArrayList<Pelikortti> k�si) {
		int[] maat = k�si.stream().map( Pelikortti -> Pelikortti.annaMaa().annaMaa() ).mapToInt(i->i).toArray();
		Arrays.sort(maat);
		
		//* Tarkastaa onko x x x x x */
		boolean v�ri1 = maat[0] == maat[4];
		
		//* Tarkastaa onko x x x x j */
		boolean v�ri2 = maat[0] == maat[3] && maat[4] == 5;
		
		//* Tarkastaa onko x x x j j */
		boolean v�ri3 = maat[0] == maat[2] && maat[3] == 5 && maat[4] == 5;
		
		return (v�ri1 || v�ri2 || v�ri3);
	}
	
	//* Tarkastaa onko K�si SUORA */
	public boolean onkoSuora(ArrayList<Pelikortti> k�si) {
		int[] arvot = k�si.stream().map( Pelikortti -> Pelikortti.annaArvo().annaArvo() ).mapToInt(i->i).toArray();
		Arrays.sort(arvot);
		
		//* Jokerilla */
		if (arvot[4] == 14) {
			if (arvot[0] == arvot[3] - 4 || arvot[0] == arvot[3] - 3 || arvot[0] == 1 && arvot[1] >= 10) {
				for(int i = 0; i < 5; i++) {
					if (arvot[i] == arvot[i+1]) {
						return false;
					}
				}
				return true;
			}else {
				return false;
			}
		} 
		
		//* Ilman Jokeria */
		if (arvot[0] == arvot[4] - 4 || arvot[0] == 1 && arvot[2] == 10) {
			for (int i = 0; i < 5; i++) {
				if (arvot[i] == arvot[i+1]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	
	//* Tarkastaa onko K�si KOLMOSET */
	public boolean onkoKolmoset(ArrayList<Pelikortti> k�si) {
		int[] arvot = k�si.stream().map( Pelikortti -> Pelikortti.annaArvo().annaArvo() ).mapToInt(i->i).toArray();
		Arrays.sort(arvot);
		
		//* Jokerilla, etsii paria */
		if (arvot[4] == 14) {
			for(int i = 0; i < 5; i++) {
				if (arvot[i] == arvot[i+1]) {
					return true;
				}
			}
		}else {
			return false;
		}
		
		//* Ilman Jokeria, etsii kolmea per�kk�ist� samaa *//
		for(int i = 0; i < 5; i++) {
			if(arvot[i] == arvot[i+1] && arvot[i] == arvot[i+2]) {
				return true;
			}
		}
		return false;
	}
	
	//* Tarkastaa onko K�si KAKSIPARIA */
	public boolean onkoKaksiParia(ArrayList<Pelikortti> k�si) {
		int[] arvot = k�si.stream().map( Pelikortti -> Pelikortti.annaArvo().annaArvo() ).mapToInt(i->i).toArray();
		Arrays.sort(arvot);
		
		if (arvot[0] == arvot[1]) {
			if (arvot[2] == arvot[3] || arvot[3] == arvot[4]) {
				return true;
			}
		}
		
		if (arvot[1] == arvot[2] && arvot[3] == arvot[4]) {
			return true;
		}
		return false;
	}
	
}

