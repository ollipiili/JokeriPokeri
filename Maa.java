package JokeriPokeri;

public enum Maa{
	HERTTA(1), RUUTU(2), RISTI(3), PATA(4), JOKERI(5);
	private int maa;
	
	private Maa(int maa) {
		this.maa = maa;
	}
	
	public int annaMaa() {
		return maa;
	}

	}

