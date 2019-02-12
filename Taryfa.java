package parking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeMap;

public class Taryfa {

	private int godzinaDzien = 10;
	private int godzinaNoc = 5;
	private LocalTime poczatekNocnej = LocalTime.of(20, 00);
	private LocalTime poczatekDziennej = LocalTime.of(6, 00);
	private TreeMap<Integer, Integer> stawkaGodzina = new TreeMap();
	private int stawkaCalyDzien = godzinaDzien * 14 + godzinaNoc * 10;

	public Taryfa() {
		wpiszStawki();
	}

	private void wpiszStawki() {
		for (int i = 1; i < 25; i++) {
			if (i > 6 && i <= 20) {
				stawkaGodzina.put(i, godzinaDzien);
			} else {
				stawkaGodzina.put(i, godzinaNoc);
			}
		}
	}

	public int obliczWartosc(LocalDate dataWjazdu, LocalTime czasWjazdu, LocalDate dataWyjazdu, LocalTime czasWyjazdu) {
		int doZaplaty;
		int godzinaWjazdu = czasWjazdu.getHour();
		int godzinaWyjazdu = czasWyjazdu.getHour();

		if (dataWjazdu == dataWyjazdu)
			doZaplaty = tenSamDzien(godzinaWjazdu, godzinaWyjazdu);
		if ((dataWyjazdu.getDayOfYear() - dataWjazdu.getDayOfYear()) == 1)
			doZaplaty = postoj2dni(godzinaWjazdu, godzinaWyjazdu);
		else
			doZaplaty = ponad2Dni((dataWyjazdu.getDayOfYear() - dataWjazdu.getDayOfYear()) - 1, godzinaWjazdu,
					godzinaWyjazdu);

		return doZaplaty;
	}

	private int tenSamDzien(int godzinaWjazdu, int godzinaWyjazdu) {
		int doZaplaty = 0;
		if (godzinaWyjazdu == 0) {
			godzinaWyjazdu = 24;
		}
		for (int i = 1; i <= godzinaWyjazdu - godzinaWjazdu; i++) {
			doZaplaty = doZaplaty + stawkaGodzina.get(godzinaWjazdu + i);
		}
		return doZaplaty;
	}

	private int postoj2dni(int godzinaWjazdu, int godzinaWyjazdu) {
		int doZaplaty = 0;
		if (godzinaWyjazdu == 0) {
			godzinaWyjazdu = 24;
		}
		doZaplaty = tenSamDzien(godzinaWjazdu, 24);
		doZaplaty = doZaplaty + tenSamDzien(0, godzinaWyjazdu);

		return doZaplaty;
	}

	private int ponad2Dni(int iloscDni, int godzinaWjazdu, int godzinaWyjazdu) {
		int doZaplaty = 0;
		if (godzinaWyjazdu == 0) {
			godzinaWyjazdu = 24;
		}
		doZaplaty = postoj2dni(godzinaWjazdu, godzinaWyjazdu);
		doZaplaty = doZaplaty + iloscDni * stawkaCalyDzien;
		return doZaplaty;
	}

	public TreeMap<Integer, Integer> getStawkaGodzina() {
		return stawkaGodzina;
	}

}
