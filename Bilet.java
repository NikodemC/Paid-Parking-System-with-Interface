package parking;

import java.time.LocalDate;
import java.time.LocalTime;

public class Bilet {

	private LocalDate dataWjazdu;
	private LocalTime czasWjazdu;
	private LocalDate dataWyjazdu;
	private LocalTime czasWyjazdu;
	private int wartosc;

	public Bilet(LocalDate dataWjazdu, LocalTime czasWjazdu) {
		this.dataWjazdu = dataWjazdu;
		this.czasWjazdu = czasWjazdu;
	}

	public void oplacBilet(LocalDate dataWyjazdu, LocalTime czasWyjazdu) {
		this.dataWyjazdu = dataWyjazdu;
		this.czasWyjazdu = czasWyjazdu;
		this.wartosc = new Taryfa().obliczWartosc(this.dataWjazdu, this.czasWjazdu, this.dataWyjazdu,
				this.czasWyjazdu);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((czasWjazdu == null) ? 0 : czasWjazdu.hashCode());
		result = prime * result + ((czasWyjazdu == null) ? 0 : czasWyjazdu.hashCode());
		result = prime * result + ((dataWjazdu == null) ? 0 : dataWjazdu.hashCode());
		result = prime * result + ((dataWyjazdu == null) ? 0 : dataWyjazdu.hashCode());
		result = prime * result + wartosc;
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
		Bilet other = (Bilet) obj;
		if (czasWjazdu == null) {
			if (other.czasWjazdu != null)
				return false;
		} else if (!czasWjazdu.equals(other.czasWjazdu))
			return false;
		if (czasWyjazdu == null) {
			if (other.czasWyjazdu != null)
				return false;
		} else if (!czasWyjazdu.equals(other.czasWyjazdu))
			return false;
		if (dataWjazdu == null) {
			if (other.dataWjazdu != null)
				return false;
		} else if (!dataWjazdu.equals(other.dataWjazdu))
			return false;
		if (dataWyjazdu == null) {
			if (other.dataWyjazdu != null)
				return false;
		} else if (!dataWyjazdu.equals(other.dataWyjazdu))
			return false;
		if (wartosc != other.wartosc)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bilet [data: " + dataWyjazdu + ", wartoœæ: " + wartosc + "]";
	}

	public LocalDate getDataWjazdu() {
		return dataWjazdu;
	}

	public LocalTime getCzasWjazdu() {
		return czasWjazdu;
	}

	public LocalDate getDataWyjazdu() {
		return dataWyjazdu;
	}

	public LocalTime getCzasWyjazdu() {
		return czasWyjazdu;
	}

	public int getWartosc() {
		return wartosc;
	}

}
