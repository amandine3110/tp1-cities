package monprojet.dao;

import java.util.List;

import monprojet.dto.CountryPopulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query(value = "SELECT SUM(POPULATION)"
            + "FROM CITY"
            + "WHERE COUNTRY_ID = :country_id"
            + "GROUP BY POPULATION",
            nativeQuery = true)
    public Integer countryPopulation(int COUNTRY_ID);

    @Query(value = "SELECT COUNTRY.NOM,SUM(POPULATION)"
            + "FROM CITY INNER JOIN COUNTRY ON COUNTRY_ID=CITY.COUNTRY_ID"
            + "GROUP BY CITY.COUNTRY_ID",
            nativeQuery = true)
    public List<CountryPopulation> getListCountryPopulation();


}
