package parking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;


public interface ParkingSystem {

	void stworz(int liczbaMiejsc);
	void zajmij(Integer numerMiejsca, LocalDate dataWjazd, LocalTime godzinaWjazd);
	void zwolnij(Integer numerMiejsca, LocalDate dataWyjazd, LocalTime godzinaWyjazd);
	
	List wolneMiejsca();
	int zarobioneWTerminie(LocalDate dataOd, LocalDate dataDo);
	int zarobionePrzezMiejsceWTerminie(int numerMiejsca, LocalDate dataOd, LocalDate dataDo);
	List statysykaMiejsc();
	
	
	
	
}

