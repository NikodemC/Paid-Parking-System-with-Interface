package parking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Parking implements ParkingSystem {

	private ArrayList<Miejsce> listaMiejsc;

	public Parking(int liczbaMiejsc) {
		this.listaMiejsc = new ArrayList<>();
		stworz(liczbaMiejsc);
	}

	@Override
	public void stworz(int liczbaMiejsc) {
		for (int i = 0; i < liczbaMiejsc; i++) {
			this.listaMiejsc.add(new Miejsce(i + 1));
		}
	}

	@Override
	public void zajmij(Integer numerMiejsca, LocalDate dataWjazdu, LocalTime godzinaWjazdu) {
		int nrMiejsca = numerMiejsca - 1;
		if (listaMiejsc.get(nrMiejsca).getStatus().equals(Status.Zajête)) {
			System.out.println("Miejsce aktualnie zajête, przepraszamy.");
		} else {
			listaMiejsc.get(nrMiejsca).zajmij(dataWjazdu, godzinaWjazdu);
		}
	}

	@Override
	public void zwolnij(Integer numerMiejsca, LocalDate dataWyjazdu, LocalTime godzinaWyjazdu) {
		listaMiejsc.get(numerMiejsca - 1).zwolnij(dataWyjazdu, godzinaWyjazdu);
	}

	@Override
	public List wolneMiejsca() {
		List lista = new ArrayList<Miejsce>();
		for (int i = 0; i < listaMiejsc.size(); i++) {
			if (listaMiejsc.get(i).getStatus().equals(Status.Wolne)) {
				lista.add(listaMiejsc.get(i));
			}
		}
		return lista;
	}

	@Override
	public int zarobioneWTerminie(LocalDate dataOd, LocalDate dataDo) {
		int zarobione = 0;
		for (int i = 0; i < listaMiejsc.size(); i++) {
			Miejsce miejsce = listaMiejsc.get(i);
			for (LocalDate data : miejsce.getBazaMiejsca().keySet()) {
				if ((data.isAfter(dataOd) || data.isEqual(dataOd)) && (data.isBefore(dataDo) || data.isEqual(dataDo))) {
					for (Bilet bilet : miejsce.getBazaMiejsca().get(data)) {
						zarobione = zarobione + bilet.getWartosc();
					}
				}
			}
		}
		return zarobione;
	}

	@Override
	public int zarobionePrzezMiejsceWTerminie(int numerMiejsca, LocalDate dataOd, LocalDate dataDo) {
		int zarobione = 0;
		for (int i = 0; i < listaMiejsc.size(); i++) {
			if (listaMiejsc.get(i).getNrMiejsca() == numerMiejsca) {
				Miejsce miejsce = listaMiejsc.get(i);
				for (LocalDate data : miejsce.getBazaMiejsca().keySet()) {
					if ((data.isAfter(dataOd) || data.isEqual(dataOd))
							&& (data.isBefore(dataDo) || data.isEqual(dataDo))) {
						for (Bilet bilet : miejsce.getBazaMiejsca().get(data)) {
							zarobione = zarobione + bilet.getWartosc();
						}
					}
				}
			}
		}
		return zarobione;
	}

	@Override
	public List statysykaMiejsc() {
		List lista = new ArrayList<String>();
		for (int i = 0; i < listaMiejsc.size(); i++) {
			String string = listaMiejsc.get(i) + " zarobi³o: " + listaMiejsc.get(i).getZarobioneWSumie() + " z³";
			lista.add(string);
		}
		return lista;
	}

	public ArrayList<Miejsce> getListaMiejsc() {
		return listaMiejsc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listaMiejsc == null) ? 0 : listaMiejsc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parking other = (Parking) obj;
		if (listaMiejsc == null) {
			if (other.listaMiejsc != null)
				return false;
		} else if (!listaMiejsc.equals(other.listaMiejsc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parking [Miejsca: " + listaMiejsc + "]";
	}

}
