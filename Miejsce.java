package parking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class Miejsce {

	private int nrMiejsca;
	private Bilet bilet;
	private Status status;
	private TreeMap<LocalDate, ArrayList<Bilet>> bazaMiejsca;
	private int zarobioneWSumie = 0;

	public Miejsce(int nrMiejsca) {
		this.nrMiejsca = nrMiejsca;
		this.bilet = null;
		this.status = Status.Wolne;
		this.bazaMiejsca = new TreeMap<LocalDate, ArrayList<Bilet>>();
		this.zarobioneWSumie = 0;
	}

	public void zajmij(LocalDate dataWjazdu, LocalTime czasWjazdu) {
		this.bilet = new Bilet(dataWjazdu, czasWjazdu);
		this.status = Status.Zajęte;
	}

	public void zwolnij(LocalDate dataWyjazdu, LocalTime czasWyjazdu) {

		this.bilet.oplacBilet(dataWyjazdu, czasWyjazdu);
		zarobioneWSumie = zarobioneWSumie + this.bilet.getWartosc();
		if (!this.bazaMiejsca.containsKey(dataWyjazdu)) {
			ArrayList<Bilet> lista = new ArrayList<>();
			lista.add(this.bilet);
			this.bazaMiejsca.put(dataWyjazdu, lista);
		} else {
			ArrayList<Bilet> tmpLista = this.bazaMiejsca.get(dataWyjazdu);
			tmpLista.add(this.bilet);
			this.bazaMiejsca.put(dataWyjazdu, tmpLista);
		}
		this.bilet = null;
		this.status = Status.Wolne;
	}

	public int getNrMiejsca() {
		return nrMiejsca;
	}

	public Bilet getBilet() {
		return bilet;
	}

	public Status getStatus() {
		return status;
	}

	public TreeMap<LocalDate, ArrayList<Bilet>> getBazaMiejsca() {
		return bazaMiejsca;
	}

	public int getZarobioneWSumie() {
		return zarobioneWSumie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bazaMiejsca == null) ? 0 : bazaMiejsca.hashCode());
		result = prime * result + ((bilet == null) ? 0 : bilet.hashCode());
		result = prime * result + nrMiejsca;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Miejsce other = (Miejsce) obj;
		if (bazaMiejsca == null) {
			if (other.bazaMiejsca != null)
				return false;
		} else if (!bazaMiejsca.equals(other.bazaMiejsca))
			return false;
		if (bilet == null) {
			if (other.bilet != null)
				return false;
		} else if (!bilet.equals(other.bilet))
			return false;
		if (nrMiejsca != other.nrMiejsca)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Miejsce " + nrMiejsca;
	}

}
