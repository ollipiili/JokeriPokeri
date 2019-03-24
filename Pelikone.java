package JokeriPokeri;

import java.util.*;
import java.util.stream.*;

public class Pelikone {
	private double peliVarat;
	private double panos;
	private final double maxPanos = 1.0;
	private final double minPanos = 0.2;
	private ArrayList<Pelikortti> käsi;
	private Korttipakka pelikortit;
	
	public Pelikone(double peliVarat, double panos) {
		this.peliVarat = peliVarat;
		this.panos = panos;
		pelikortit = new Korttipakka();
		käsi = new ArrayList<Pelikortti>();
	}
	
	public String toString(ArrayList<Pelikortti> käsi) {
		String temp = "";
		for(Pelikortti kortti : käsi) {
			temp = " " + kortti.toString();
		}
		return temp;
	}
	
	
	public ArrayList<Pelikortti> annaKäsi() {
		return käsi;
	}
	
	
	
	public double annaPanos() {
		return panos;
	}
	
	
	public double annaPeliVarat() {
		return peliVarat;
	}
	
	//* Nostaa kortin korttipakasta Käteen
	public void nostaKortti() {
		käsi.add(pelikortit.annaKortti());
	}
	
	
	//* Antaa kortin Kädestä takaisin korttipakkaan */
	public void vaihdaKortti(int kortti) {
		Pelikortti apuKortti = käsi.get(kortti);
		käsi.remove(kortti);
		pelikortit.otaKortti(apuKortti);
	}
	
	//* Nostaa panosta 0.2 yksikköä, jos maxPanos ylittyy niin palauttaa minimipanokseen */
	public void nostaPanosta() {
		if (panos < maxPanos) {
			panos = panos + 0.2;
		}else {
			panos = minPanos;
		}
	}
	
	//* Tarkastaa onko Käsi voittava, alkaen suurimmasta voitosta */
	public void tarkastaVoitto(ArrayList<Pelikortti> käsi) {
		if(onkoVärisuora(käsi)) {
			System.out.println("Värisuora!");
		}
		else if (onkoViitoset(käsi)) {
			System.out.println("Viitoset!");
		}
		else if (onkoNeloset(käsi)) {
			System.out.println("Neloset!");
		}
		else if (onkoTäyskäsi(käsi)) {
			System.out.println("Täyskäsi!");
		}
		else if (onkoVäri(käsi)) {
			System.out.println("Väri!");
		}
		else if(onkoSuora(käsi)) {
			System.out.println("Suora!");
		}
		else if(onkoKolmoset(käsi)) {
			System.out.println("Kolmoset!");
		}
		else if(onkoKaksiParia(käsi)) {
			System.out.println("KaksiParia!");
		}
		else {
			System.out.println("Ei voittoa");
		}
		}
	
	
	/* ------------------------------------------------------------
	   --------------------TARKISTUS METODIT-----------------------
	   -----------------------------------------------------------*/
	
	
	//* Tarkastaa onko Käsi VÄRISUORA */
	public boolean onkoVärisuora(ArrayList<Pelikortti> käsi) {
		return (onkoVäri(käsi) && onkoSuora(käsi));
		
	}
	
	
	//* Tarkastaa onko Käsi VIITOSET */
	public boolean onkoViitoset(ArrayList<Pelikortti> käsi) {
		int[] arvot = käsi.stream().map( Pelikortti -> Pelikortti.annaArvo().annaArvo() ).mapToInt(i->i).toArray();
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
	

	//* Tarkastaa onko Käsi NELOSET */
	public boolean onkoNeloset(ArrayList<Pelikortti> käsi) {
		int[] arvot = käsi.stream().map( Pelikortti -> Pelikortti.annaArvo().annaArvo() ).mapToInt(i->i).toArray();
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
	
	
	//* Tarkastaa onko Käsi TÄYSKÄSI */
	public boolean onkoTäyskäsi(ArrayList<Pelikortti> käsi) {
		int[] arvot = käsi.stream().map( Pelikortti -> Pelikortti.annaArvo().annaArvo() ).mapToInt(i->i).toArray();
		Arrays.sort(arvot);
		
		//* Onko y y y z z */
		boolean onkoTäyskäsi1 = arvot[0] == arvot[1] && arvot[1] == arvot[2] && arvot[3] == arvot[4];
		
		//* Onko y y z z z */
		boolean onkoTäyskäsi2 = arvot[0] == arvot[1] && arvot[2] == arvot[3] && arvot[3] == arvot[4];
		
		return (onkoTäyskäsi1 || onkoTäyskäsi2);
	}
	
	
	//* Tarkastaa onko Käsi VÄRI */
	public boolean onkoVäri(ArrayList<Pelikortti> käsi) {
		int[] maat = käsi.stream().map( Pelikortti -> Pelikortti.annaMaa().annaMaa() ).mapToInt(i->i).toArray();
		Arrays.sort(maat);
		
		//* Tarkastaa onko x x x x x */
		boolean väri1 = maat[0] == maat[4];
		
		//* Tarkastaa onko x x x x j */
		boolean väri2 = maat[0] == maat[3] && maat[4] == 5;
		
		//* Tarkastaa onko x x x j j */
		boolean väri3 = maat[0] == maat[2] && maat[3] == 5 && maat[4] == 5;
		
		return (väri1 || väri2 || väri3);
	}
	
	//* Tarkastaa onko Käsi SUORA */
	public boolean onkoSuora(ArrayList<Pelikortti> käsi) {
		int[] arvot = käsi.stream().map( Pelikortti -> Pelikortti.annaArvo().annaArvo() ).mapToInt(i->i).toArray();
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
	
	
	//* Tarkastaa onko Käsi KOLMOSET */
	public boolean onkoKolmoset(ArrayList<Pelikortti> käsi) {
		int[] arvot = käsi.stream().map( Pelikortti -> Pelikortti.annaArvo().annaArvo() ).mapToInt(i->i).toArray();
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
		
		//* Ilman Jokeria, etsii kolmea peräkkäistä samaa *//
		for(int i = 0; i < 5; i++) {
			if(arvot[i] == arvot[i+1] && arvot[i] == arvot[i+2]) {
				return true;
			}
		}
		return false;
	}
	
	//* Tarkastaa onko Käsi KAKSIPARIA */
	public boolean onkoKaksiParia(ArrayList<Pelikortti> käsi) {
		int[] arvot = käsi.stream().map( Pelikortti -> Pelikortti.annaArvo().annaArvo() ).mapToInt(i->i).toArray();
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

